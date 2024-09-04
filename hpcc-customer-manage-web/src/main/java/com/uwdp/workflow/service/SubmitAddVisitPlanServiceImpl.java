package com.uwdp.workflow.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.gientech.lcds.workflow.sdk.core.call.dto.TaskCall;
import com.gientech.lcds.workflow.sdk.core.runtime.ReStartProcessRequestDto;
import com.gientech.lcds.workflow.sdk.core.runtime.StartProcessByCodeRequestDto;
import com.gientech.lcds.workflow.sdk.runtime.procinst.service.impl.RuntimeProcInstServiceImpl;
import com.uwdp.module.api.service.*;
import com.uwdp.module.api.vo.cmd.LmcWorkflowTimelineAddCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanAddCmd;
import com.uwdp.module.api.vo.enums.WorkflowStatusEnums;
import com.uwdp.module.biz.infrastructure.assembler.VisitPlanAssembler;
import com.uwdp.module.biz.infrastructure.entity.*;
import com.uwdp.module.biz.infrastructure.repository.*;
import com.uwdp.utils.MdmPersonUtils;
import com.uwdp.utils.WorkflowUtils;
import com.uwdp.workflow.enums.BizCode;

import com.uwdp.workflow.service.iservice.ISubmitAddVisitPlanService;
import com.uwdp.workflow.util.FunctionalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * 公关计划流程实现类
 */
@Service
@Slf4j
public class SubmitAddVisitPlanServiceImpl implements ISubmitAddVisitPlanService {

    @Value("${hpcc-mim-server.app-id}")
    public String appId; /*应用ID*/

    private static final String flowCode = "ggjhap"; /*流程编码*/

    @Autowired
    private IVisitPlanService visitPlanService;

    @Autowired
    private VisitPlanRepository visitPlanRepository;

    @Autowired
    private VisitPlanAssembler visitPlanAssembler;

    @Autowired
    private ClientInfoRepository clientInfoRepository;

    @Autowired
    private ILmcWorkflowService lmcWorkflowService;

    @Autowired
    private LmcWorkflowRepository lmcWorkflowRepository;

    @Autowired
    private ILmcWorkflowTimelineService lmcWorkflowTimelineService;

    @Autowired(required = false)
    private RuntimeProcInstServiceImpl rpi;

    @Autowired
    private MdmPersonRepository mdmPersonRepository;

    @Autowired
    private MdmPersonUtils mdmPersonUtils;

    @Autowired
    private RequestLogRepository requestLogRepository;

    @Override
    public Response add(VisitPlanAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUserTwo(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }
        // 数据权限 添加创建人字段(保存创建人personCode)
        MdmPersonDo personDo = mdmPersonUtils
                .checkIfPersonCodeExist(addCmd.getCreatedBy());
        // 数据权限 添加创建人字段(保存创建人personCode)
        String groupFullCode = mdmPersonUtils.checkIfOrganizationExist(
                personDo.getGroupBelongDepartmentCode()
        ).getGroupFullCode();

        // 数据权限 添加部门全路径字段(保存创建人所属部门全路径)
        addCmd.setGroupFullCode(groupFullCode);
        //获取id
        Long id = visitPlanService.addThenReturnId(addCmd);
        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        // 根据保存的人员ids:{} 获取工号 加入表单数据传递至工作流 , 以指定审批人
        String userCodes = mdmPersonUtils.getUserCodes(addCmd.getTravelLeader());
        log.info("根据保存的人员ids:{} 获取工号:{}",addCmd.getTravelLeader(),userCodes);

        // 添加计划关联客户名称
        String fdName = clientInfoRepository.getById(addCmd.getClientInfoId()).getFdName();
        variables.put("clientInfo",fdName);
        variables.put("title",addCmd.getTitle());
        variables.put("id",id);
        if (!StringUtils.isEmpty(userCodes))variables.put("mdmUserCodes",userCodes);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】【"+addCmd.getTitle()+"】客户拜访计划审批单");
//        dto.setAppId(appId);
        dto.setCode(flowCode);
        dto.setTenantId("1");
        dto.setUserNo(addCmd.getCreatedBy());
        dto.setUserFullName(personDo.getPersonName());
        dto.setBusinessKey(id.toString());
        dto.setVariables(variables);
        dto.setApproveUser(addCmd.getApproveUser());

        SingleResponse<String>
                response = rpi.startProcessByCode(dto);
        log.info("==公关计划发起流程 响应=="+response);

        //给流程日志添加信息
        RequestLogDo requestLogDo = new RequestLogDo();
        requestLogDo.setRequestUrl("url");
        requestLogDo.setCtime(LocalDateTime.now());
        requestLogDo.setRequestParam(dto.toString());
        requestLogDo.setResponseParam(response.getData());

        requestLogRepository.save(requestLogDo);

        LmcWorkflowDo lmcWorkflowDo = new LmcWorkflowDo();
//            lmcWorkflowDo.setId();
        lmcWorkflowDo.setCreatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setCreatedTime(LocalDateTime.now());
        lmcWorkflowDo.setUpdatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setUpdatedTime(LocalDateTime.now());
        lmcWorkflowDo.setBizCode(BizCode.ADD_VISIT_PLAN.getValue()); /*业务类型*/
        lmcWorkflowDo.setBizId(id.toString());
        lmcWorkflowDo.setProcessCode("ggjhap");
        lmcWorkflowDo.setProcessInstanceId(response.getData());
        lmcWorkflowDo.setTitle("新增公关计划-");
        lmcWorkflowDo.setAppId(appId);
        lmcWorkflowDo.setSubmitterCode(addCmd.getCreatedBy());
        lmcWorkflowDo.setSubmitterName(personDo.getPersonName());
        lmcWorkflowDo.setSubmitTime(LocalDateTime.now());
//            lmcWorkflowDo.setEndTime();
        lmcWorkflowDo.setWorkflowStatus(WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_ACTIVE.getValue());
//            lmcWorkflowDo.setReason();
//            lmcWorkflowDo.setSortNumber();
//            lmcWorkflowDo.setVersion();
        lmcWorkflowDo.setIsDeleted(0);
        lmcWorkflowDo.setCreateUserCode(addCmd.getCreatedBy());
        lmcWorkflowDo.setCreateUserName(personDo.getPersonName());
        lmcWorkflowDo.setCreateTime(LocalDateTime.now());
        lmcWorkflowDo.setUpdateUserCode(addCmd.getCreatedBy());
        lmcWorkflowDo.setUpdateUserName(personDo.getPersonName());
        lmcWorkflowDo.setUpdateTime(LocalDateTime.now());

        lmcWorkflowRepository.save(lmcWorkflowDo);
        log.info("====workflow添加成功:{}",lmcWorkflowDo.getId());

        if (null == lmcWorkflowDo.getId()) {
            return Response.buildFailure("506","找不到id！");
        }

        LmcWorkflowTimelineAddCmd timelineAddCmd = new LmcWorkflowTimelineAddCmd();
//        timelineAddCmd.setId(0L);
        timelineAddCmd.setCreatedBy(addCmd.getCreatedBy());
        timelineAddCmd.setCreatedTime(LocalDateTime.now());
        timelineAddCmd.setUpdatedBy(addCmd.getCreatedBy());
        timelineAddCmd.setUpdatedTime(LocalDateTime.now());
        if (null != lmcWorkflowDo.getId()) {
            timelineAddCmd.setWorkflowId(lmcWorkflowDo.getId().toString());
        }
        timelineAddCmd.setWorkflowStatus(WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_ACTIVE.getValue());
        timelineAddCmd.setTriggerTime(LocalDateTime.now());
        timelineAddCmd.setProcessCode(null);
        timelineAddCmd.setProcessName(null);
        timelineAddCmd.setProcessInstanceId(response.getData());
        timelineAddCmd.setProcessCallEventId(null);
        timelineAddCmd.setAppId(appId);
        timelineAddCmd.setOperatorCode(addCmd.getCreatedBy());
        timelineAddCmd.setOperatorName(personDo.getPersonName());
        timelineAddCmd.setCurrentTaskId(null);
        timelineAddCmd.setCurrentTaskKey(null);
        timelineAddCmd.setCurrentTaskName(null);
        timelineAddCmd.setBizCode(BizCode.ADD_VISIT_PLAN.getValue());
        timelineAddCmd.setBizId(String.valueOf(id));
        timelineAddCmd.setReason("自动提交流程");
        timelineAddCmd.setSucceed(response.isSuccess()?1:0);
        timelineAddCmd.setErrorMsg(response.getErrMessage());
        timelineAddCmd.setErrorText(null);
        timelineAddCmd.setVersion(null);
        timelineAddCmd.setDeleted(null);
        timelineAddCmd.setCreateUserCode(addCmd.getCreatedBy());
        timelineAddCmd.setCreateUserName(personDo.getPersonName());
        timelineAddCmd.setCreateTime(LocalDateTime.now());
        timelineAddCmd.setUpdateUserCode(addCmd.getCreatedBy());
        timelineAddCmd.setUpdateUserName(personDo.getPersonName());
        timelineAddCmd.setUpdateTime(LocalDateTime.now());

        lmcWorkflowTimelineService.add(timelineAddCmd);
        log.info("====timeline添加成功");
        return Response.buildSuccess();
    }


    @Override
    public Response reStartProcess(VisitPlanAddCmd addCmd){
        Boolean b = FunctionalUtil.verifyAndParseApproveUserTwo(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }
        LmcWorkflowDo workflowDo = Optional.ofNullable(
                lmcWorkflowRepository.getOne(new LambdaQueryWrapper<LmcWorkflowDo>()
                        .eq(LmcWorkflowDo::getBizId, addCmd.getId()))
        ).orElseThrow(
                () -> new BizRuntimeException(
                        "根据新增公关计划的Id:{" + addCmd.getId() + "}找不到对应流程实例",new Exception())
        );
        VisitPlanDo visitPlanDo = visitPlanAssembler.toDO(addCmd);
        // 重新发起流程时 将 "确认参会的出行领导姓名" 置空
        visitPlanDo.setConferenceLeaderName("");
        // 标识本数据是撤回或驳回的
        visitPlanDo.setIfConference("3");
        boolean bool = visitPlanRepository.updateById(visitPlanDo);
        if (!bool)log.error("重新发起流程:更新新增公关计划时失败!公关计划:{}",visitPlanDo);

        // 根据保存的人员ids:{} 获取工号 加入表单数据传递至工作流 , 以指定审批人
        String userCodes = mdmPersonUtils.getUserCodes(addCmd.getTravelLeader());
        log.info("根据保存的人员ids:{} 获取工号:{}",addCmd.getTravelLeader(),userCodes);

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);


        // 添加计划关联客户名称
        String fdName = clientInfoRepository.getById(addCmd.getClientInfoId()).getFdName();
        variables.put("clientInfo",fdName);

        if (!StringUtils.isEmpty(userCodes))variables.put("mdmUserCodes",userCodes);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");
        variables.remove("ifConference");

        // 数据权限 添加创建人字段(保存创建人personCode)
        MdmPersonDo personDo = mdmPersonUtils
                .checkIfPersonCodeExist(addCmd.getCreatedBy());
        ReStartProcessRequestDto dto = new ReStartProcessRequestDto();
        dto.setVariables(variables);
//        dto.setFileIds();     // 附件ID集合
        dto.setProcessInstanceId(workflowDo.getProcessInstanceId());     // 流程实例id
//        dto.setReason();      // 原因/评论
//        dto.setAppId(appId);
        dto.setTenantId("1");
        dto.setUserNo(addCmd.getCreatedBy());
        dto.setUserFullName(personDo.getPersonName());
//        dto.setRoleNos();       // 角色编号
//        dto.setOrgNos();        // 部门编号
//        dto.setStandardPositions();     // 标准岗
//        dto.setBusinessPositions();     // 业务岗

        log.info("==已驳回流程重新发起信息:{}",dto);
        SingleResponse<String> response = rpi.reStartProcess(dto);
        log.info("==公关计划新增流程驳回后重新发起 响应:{}",response);

        if (response.isSuccess()){
            // 重新设置流程状态为审批中
            workflowDo.setWorkflowStatus(WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_ACTIVE.getValue());
            workflowDo.setVersion(workflowDo.getVersion()+1);
            workflowDo.setUpdatedTime(LocalDateTime.now());
            lmcWorkflowRepository.updateById(workflowDo);
        }

        LmcWorkflowTimelineAddCmd timelineAddCmd = new LmcWorkflowTimelineAddCmd();
//        timelineAddCmd.setId(0L);
        timelineAddCmd.setCreatedBy(addCmd.getCreatedBy());
        timelineAddCmd.setCreatedTime(LocalDateTime.now());
        timelineAddCmd.setUpdatedBy(addCmd.getCreatedBy());
        timelineAddCmd.setUpdatedTime(LocalDateTime.now());
        if (null != workflowDo.getId()) {
            timelineAddCmd.setWorkflowId(workflowDo.getId().toString());
        }
        timelineAddCmd.setWorkflowStatus(WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_ACTIVE.getValue());
        timelineAddCmd.setTriggerTime(LocalDateTime.now());
        timelineAddCmd.setProcessCode(null);
        timelineAddCmd.setProcessName(null);
        timelineAddCmd.setProcessInstanceId(response.getData());
        timelineAddCmd.setProcessCallEventId(null);
        timelineAddCmd.setAppId(appId);
        timelineAddCmd.setOperatorCode(addCmd.getCreatedBy());
        timelineAddCmd.setOperatorName(personDo.getPersonName());
        timelineAddCmd.setCurrentTaskId(null);
        timelineAddCmd.setCurrentTaskKey(null);
        timelineAddCmd.setCurrentTaskName(null);
        timelineAddCmd.setBizCode(flowCode);
        timelineAddCmd.setBizId(String.valueOf(addCmd.getId()));
        timelineAddCmd.setReason("驳回后重新发起");
        timelineAddCmd.setSucceed(response.isSuccess()?1:0);
        timelineAddCmd.setErrorMsg(response.getErrMessage());
        timelineAddCmd.setVersion(0L);
        timelineAddCmd.setDeleted(null);
        timelineAddCmd.setCreateUserCode(addCmd.getCreatedBy());
        timelineAddCmd.setCreateUserName(personDo.getPersonName());
        timelineAddCmd.setCreateTime(LocalDateTime.now());
        timelineAddCmd.setUpdateUserCode(addCmd.getCreatedBy());
        timelineAddCmd.setUpdateUserName(personDo.getPersonName());
        timelineAddCmd.setUpdateTime(LocalDateTime.now());

        lmcWorkflowTimelineService.add(timelineAddCmd);
        log.info("====timeline添加成功");
        return Response.buildSuccess();
    }


    @Override
    public Response draftProcess(VisitPlanAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUserTwo(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }
        MdmPersonDo personDo = mdmPersonUtils.checkIfPersonCodeExist(addCmd.getCreatedBy());
        // 数据权限 添加创建人字段(保存创建人personCode)
        String groupFullCode = mdmPersonUtils.checkIfOrganizationExist(
                personDo.getGroupBelongDepartmentCode()
        ).getGroupFullCode();

        // 数据权限 添加部门全路径字段(保存创建人所属部门全路径)
        addCmd.setGroupFullCode(groupFullCode);
        //获取id
//        Long fdId = publicRelationsService.addThenReturnId(addCmd);
        VisitPlanDo visitPlanDo = visitPlanAssembler.toDO(addCmd);
        boolean bool = visitPlanRepository.updateById(visitPlanDo);
        if (!bool)log.error("发起流程:更新新增公关计划时失败!公关计划:{}",visitPlanDo);

        Long fdId = visitPlanDo.getId();

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        // 根据保存的人员ids:{} 获取工号 加入表单数据传递至工作流 , 以指定审批人
        String userCodes = mdmPersonUtils.getUserCodes(addCmd.getTravelLeader());
        log.info("根据保存的人员ids:{} 获取工号:{}",addCmd.getTravelLeader(),userCodes);

        // 添加计划关联客户名称
        String fdName = clientInfoRepository.getById(addCmd.getClientInfoId()).getFdName();
        variables.put("clientInfo",fdName);
        variables.put("title",visitPlanDo.getTitle());
        variables.put("id",fdId);
        if (!StringUtils.isEmpty(userCodes))variables.put("mdmUserCodes",userCodes);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】【"+addCmd.getTitle()+"】客户拜访计划审批单");
//        dto.setAppId(appId);
        dto.setCode(flowCode);
        dto.setTenantId("1");
        dto.setUserNo(addCmd.getCreatedBy());
        dto.setUserFullName(personDo.getPersonName());
        dto.setBusinessKey(fdId.toString());
        dto.setVariables(variables);
        dto.setApproveUser(
                addCmd.getApproveUser());

        SingleResponse<String>
                response = rpi.startProcessByCode(dto);
        log.info("==公关计划发起流程 响应=="+response);

        LmcWorkflowDo lmcWorkflowDo = new LmcWorkflowDo();
//            lmcWorkflowDo.setId();
        lmcWorkflowDo.setCreatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setCreatedTime(LocalDateTime.now());
        lmcWorkflowDo.setUpdatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setUpdatedTime(LocalDateTime.now());
        lmcWorkflowDo.setBizCode(BizCode.ADD_VISIT_PLAN.getValue()); /*业务类型*/
        lmcWorkflowDo.setBizId(fdId.toString());
        lmcWorkflowDo.setProcessCode(BizCode.ADD_VISIT_PLAN.getValue());
        lmcWorkflowDo.setProcessInstanceId(response.getData());
        lmcWorkflowDo.setTitle("新增公关计划-");
        lmcWorkflowDo.setAppId(appId);
        lmcWorkflowDo.setSubmitterCode(addCmd.getCreatedBy());
        lmcWorkflowDo.setSubmitterName(personDo.getPersonName());
        lmcWorkflowDo.setSubmitTime(LocalDateTime.now());
//            lmcWorkflowDo.setEndTime();
        lmcWorkflowDo.setWorkflowStatus(WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_ACTIVE.getValue());
//            lmcWorkflowDo.setReason();
//            lmcWorkflowDo.setSortNumber();
//            lmcWorkflowDo.setVersion();
        lmcWorkflowDo.setIsDeleted(0);
        lmcWorkflowDo.setCreateUserCode(addCmd.getCreatedBy());
        lmcWorkflowDo.setCreateUserName(personDo.getPersonName());
        lmcWorkflowDo.setCreateTime(LocalDateTime.now());
        lmcWorkflowDo.setUpdateUserCode(addCmd.getCreatedBy());
        lmcWorkflowDo.setUpdateUserName(personDo.getPersonName());
        lmcWorkflowDo.setUpdateTime(LocalDateTime.now());

        lmcWorkflowRepository.save(lmcWorkflowDo);
        log.info("====workflow添加成功:{}",lmcWorkflowDo.getId());

        if (null == lmcWorkflowDo.getId()) {
            return Response.buildFailure("506","找不到id！");
        }

        LmcWorkflowTimelineAddCmd timelineAddCmd = new LmcWorkflowTimelineAddCmd();
//        timelineAddCmd.setId(0L);
        timelineAddCmd.setCreatedBy(addCmd.getCreatedBy());
        timelineAddCmd.setCreatedTime(LocalDateTime.now());
        timelineAddCmd.setUpdatedBy(addCmd.getCreatedBy());
        timelineAddCmd.setUpdatedTime(LocalDateTime.now());
        if (null != lmcWorkflowDo.getId()) {
            timelineAddCmd.setWorkflowId(lmcWorkflowDo.getId().toString());
        }
        timelineAddCmd.setWorkflowStatus(WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_ACTIVE.getValue());
        timelineAddCmd.setTriggerTime(LocalDateTime.now());
        timelineAddCmd.setProcessCode(null);
        timelineAddCmd.setProcessName(null);
        timelineAddCmd.setProcessInstanceId(response.getData());
        timelineAddCmd.setProcessCallEventId(null);
        timelineAddCmd.setAppId(appId);
        timelineAddCmd.setOperatorCode(addCmd.getCreatedBy());
        timelineAddCmd.setOperatorName(personDo.getPersonName());
        timelineAddCmd.setCurrentTaskId(null);
        timelineAddCmd.setCurrentTaskKey(null);
        timelineAddCmd.setCurrentTaskName(null);
        timelineAddCmd.setBizCode(BizCode.ADD_VISIT_PLAN.getValue());
        timelineAddCmd.setBizId(String.valueOf(fdId));
        timelineAddCmd.setReason("自动提交流程");
        timelineAddCmd.setSucceed(1);
        timelineAddCmd.setErrorMsg(null);
        timelineAddCmd.setErrorText(null);
        timelineAddCmd.setVersion(null);
        timelineAddCmd.setDeleted(null);
        timelineAddCmd.setCreateUserCode(addCmd.getCreatedBy());
        timelineAddCmd.setCreateUserName(personDo.getPersonName());
        timelineAddCmd.setCreateTime(LocalDateTime.now());
        timelineAddCmd.setUpdateUserCode(addCmd.getCreatedBy());
        timelineAddCmd.setUpdateUserName(personDo.getPersonName());
        timelineAddCmd.setUpdateTime(LocalDateTime.now());

        lmcWorkflowTimelineService.add(timelineAddCmd);
        log.info("====timeline添加成功");
        return Response.buildSuccess();
    }


    @Override
    public void handleStart(ProcessCall processCall) {

    }

    @Override
    public void handlePass(ProcessCall processCall) {

        // 更新业务关联的工作流状态
        lmcWorkflowRepository.update(new LambdaUpdateWrapper<LmcWorkflowDo>()
                .eq(LmcWorkflowDo::getProcessInstanceId, processCall.getProcessInstanceId())
                .eq(LmcWorkflowDo::getBizId, processCall.getBusinessKey())
                .set(LmcWorkflowDo::getEndTime, processCall.getDate())
                .set(LmcWorkflowDo::getWorkflowStatus, WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_COMPLETED.getValue())
                .set(LmcWorkflowDo::getUpdatedTime, LocalDateTime.now())
        );

        LmcWorkflowTimelineAddCmd timelineAddCmd = this.addTimeLine(processCall);
        timelineAddCmd.setReason("流程审核通过-流程结束");
        lmcWorkflowTimelineService.add(timelineAddCmd);

    }

    @Override
    public void handleBack(ProcessCall processCall) {

        // 更新业务关联的工作流状态
        lmcWorkflowRepository.update(new LambdaUpdateWrapper<LmcWorkflowDo>()
                .eq(LmcWorkflowDo::getProcessInstanceId, processCall.getProcessInstanceId())
                .eq(LmcWorkflowDo::getBizId, processCall.getBusinessKey())
                .set(LmcWorkflowDo::getWorkflowStatus, WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_BACK.getValue())
                .set(LmcWorkflowDo::getUpdatedTime, LocalDateTime.now())
//                .set(LmcWorkflowDo::getVersion,)
        );

        LmcWorkflowTimelineAddCmd timelineAddCmd = this.addTimeLine(processCall);
        timelineAddCmd.setReason("流程驳回");
        lmcWorkflowTimelineService.add(timelineAddCmd);

        VisitPlanDo visitPlanDo = Optional.ofNullable(
                visitPlanRepository.getOne(new LambdaQueryWrapper<VisitPlanDo>()
                        .eq(VisitPlanDo::getId, processCall.getBusinessKey()))
        ).orElseThrow(
                () -> new BizRuntimeException(
                        "根据流程驳回回调的BusinessKey:{" + processCall.getBusinessKey() + "}找不到对应公关计划", new Exception())
        );
        // 重新发起流程时 将 "确认参会的出行领导姓名" 置空
        visitPlanDo.setConferenceLeaderName("");
        // 标识本数据是撤回或驳回的
        visitPlanDo.setIfConference("3");
        boolean bool = visitPlanRepository.updateById(visitPlanDo);
        if (!bool)log.error("驳回流程:更新公关计划 确认参会领导 字段时失败!公关计划:{}",visitPlanDo);
    }

    @Override
    public void handleRevoke(ProcessCall processCall) {

        // 更新业务关联的工作流状态
        lmcWorkflowRepository.update(new LambdaUpdateWrapper<LmcWorkflowDo>()
                .eq(LmcWorkflowDo::getProcessInstanceId, processCall.getProcessInstanceId())
                .eq(LmcWorkflowDo::getBizId, processCall.getBusinessKey())
                .set(LmcWorkflowDo::getWorkflowStatus, WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_REVOKE.getValue())
                .set(LmcWorkflowDo::getUpdatedTime, LocalDateTime.now())
//                .set(LmcWorkflowDo::getVersion,)
        );

        LmcWorkflowTimelineAddCmd timelineAddCmd = this.addTimeLine(processCall);
        timelineAddCmd.setReason("流程撤回");
        lmcWorkflowTimelineService.add(timelineAddCmd);

        VisitPlanDo visitPlanDo = Optional.ofNullable(
                visitPlanRepository.getOne(new LambdaQueryWrapper<VisitPlanDo>()
                        .eq(VisitPlanDo::getId, processCall.getBusinessKey()))
        ).orElseThrow(
                () -> new BizRuntimeException(
                        "根据流程撤回回调的BusinessKey:{" + processCall.getBusinessKey() + "}找不到对应公关计划", new Exception())
        );
        // 重新发起流程时 将 "确认参会的出行领导姓名" 置空
        visitPlanDo.setConferenceLeaderName("");
        // 标识本数据是撤回或驳回的
        visitPlanDo.setIfConference("3");
        boolean bool = visitPlanRepository.updateById(visitPlanDo);
        if (!bool)log.error("撤回流程:更新公关计划 确认参会领导 字段时失败!公关计划:{}",visitPlanDo);
    }

    public LmcWorkflowTimelineAddCmd addTimeLine(ProcessCall processCall) {


        LmcWorkflowTimelineAddCmd timelineAddCmd = new LmcWorkflowTimelineAddCmd();
//        timelineAddCmd.setId(0L);
        timelineAddCmd.setCreatedBy(processCall.getProcInstStarterNo());
        timelineAddCmd.setCreatedTime(LocalDateTime.now());
        timelineAddCmd.setUpdatedBy(processCall.getProcInstStarterNo());
        timelineAddCmd.setUpdatedTime(LocalDateTime.now());
//        timelineAddCmd.setWorkflowId(workflowAddCmd.getId().toString());
        timelineAddCmd.setWorkflowStatus(WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_ACTIVE.getValue());
        timelineAddCmd.setTriggerTime(LocalDateTime.now());
        timelineAddCmd.setProcessCode(null);
        timelineAddCmd.setProcessName(null);
        timelineAddCmd.setProcessInstanceId(processCall.getProcessInstanceId());
        timelineAddCmd.setProcessCallEventId(null);
//        timelineAddCmd.setAppId();
//        timelineAddCmd.setOperatorCode("weixiangxian");
//        timelineAddCmd.setOperatorName("魏向贤");
        timelineAddCmd.setCurrentTaskId(null);
        timelineAddCmd.setCurrentTaskKey(null);
        timelineAddCmd.setCurrentTaskName(null);
        timelineAddCmd.setBizCode(BizCode.ADD_VISIT_PLAN.getValue());
        timelineAddCmd.setBizId(processCall.getBusinessKey());
        timelineAddCmd.setReason("审批通过回调");
        timelineAddCmd.setSucceed(1);
        timelineAddCmd.setErrorMsg(null);
        timelineAddCmd.setErrorText(null);
        timelineAddCmd.setVersion(null);
        timelineAddCmd.setDeleted(null);
        timelineAddCmd.setCreateUserCode(processCall.getProcInstStarterNo());
        timelineAddCmd.setCreateUserName(processCall.getProcInstStarterFullName());
        timelineAddCmd.setCreateTime(LocalDateTime.now());
//        timelineAddCmd.setUpdateUserCode(processCall);
//        timelineAddCmd.setUpdateUserName("魏向贤");
        timelineAddCmd.setUpdateTime(LocalDateTime.now());
        return timelineAddCmd;
    }

    // 根据流程回调的是否参会状态更新业务表单的是否参会状态
    public void updateIfConference(TaskCall taskCall) {
        try {
            log.info("在saveIfConference()方法触发了任务回调:",taskCall);
            if (null == taskCall){
                log.info("在saveIfConference()方法触发了任务回调,taskCall为空");
                return;
            }else if(!taskCall.getAgree()){
                log.info("在saveIfConference()方法触发了错误的任务回调:",taskCall);
                return;
            }
            Map<String, Object> formData = taskCall.getFormData();
            if (!formData.isEmpty()){
                String businessKey = (String) formData.get("id");
                if (StrUtil.isEmptyIfStr(businessKey)){
                    log.info("在saveIfConference()方法触发了任务回调:id为空 ",taskCall);
                    return;
                }
            }
            String businessKey = (String) formData.get("id");
            VisitPlanDo visitPlanDo = Optional.ofNullable(
                    visitPlanRepository.getOne(new LambdaQueryWrapper<VisitPlanDo>()
                            .eq(VisitPlanDo::getId, Long.valueOf(businessKey)))
            ).orElseThrow(
                    () -> new BizRuntimeException(
                            "根据流程回调的Id:{" + businessKey + "}找不到对应公关计划", new Exception())
            );
            visitPlanDo.setIfConference((String)(formData.get("fConference")));
            boolean b = visitPlanRepository.updateById(visitPlanDo);
            if (b) log.info("根据流程回调的是否参会状态更新业务表单的是否参会状态,更新值:{}",formData.get("fConference"));
        }catch (Exception e){
            throw new BizRuntimeException("根据流程回调的是否参会状态更新业务表单的是否参会状态 发生错误!",e);
        }
    }

}
