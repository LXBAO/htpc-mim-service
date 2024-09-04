package com.uwdp.workflow.service;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.gientech.lcds.workflow.sdk.core.runtime.ReStartProcessRequestDto;
import com.gientech.lcds.workflow.sdk.core.runtime.StartProcessByCodeRequestDto;
import com.gientech.lcds.workflow.sdk.runtime.procinst.service.impl.RuntimeProcInstServiceImpl;
import com.uwdp.module.api.service.*;
import com.uwdp.module.api.vo.cmd.*;
import com.uwdp.module.api.vo.dto.VisitPlanDto;
import com.uwdp.module.api.vo.enums.AttachmentOrderType;
import com.uwdp.module.api.vo.enums.WorkflowStatusEnums;
import com.uwdp.module.biz.infrastructure.assembler.PublicRelationsAssembler;
import com.uwdp.module.biz.infrastructure.entity.*;
import com.uwdp.module.biz.infrastructure.repository.LmcWorkflowRepository;
import com.uwdp.module.biz.infrastructure.repository.MdmPersonRepository;
import com.uwdp.module.biz.infrastructure.repository.PublicRelationsRepository;
import com.uwdp.module.biz.infrastructure.repository.RequestLogRepository;
import com.uwdp.utils.MdmPersonUtils;
import com.uwdp.utils.WorkflowUtils;
import com.uwdp.workflow.enums.BizCode;

import com.uwdp.workflow.service.iservice.ISubmitAddPublicRelationsService;
import com.uwdp.workflow.util.FunctionalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

/**
 * 公关反馈流程实现类
 */
@Service
@Slf4j
public class SubmitAddPublicRelationsServiceImpl implements ISubmitAddPublicRelationsService {

    @Value("${hpcc-mim-server.app-id}")
    public String appId; /*应用ID*/

    private static final String flowCode = "feedback"; /*流程编码*/

    @Autowired
    private IVisitPlanService visitPlanService;

    @Autowired
    private IPublicRelationsService publicRelationsService;

    @Autowired
    private PublicRelationsRepository publicRelationsRepository;

    @Autowired
    private PublicRelationsAssembler publicRelationsAssembler;

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
    private IAttachmentService attachmentService;

    @Autowired
    private RequestLogRepository requestLogRepository;

    @Override
    public Response add(PublicRelationsAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUser(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }

        Long visitPlanId = addCmd.getVisitPlanId();
        VisitPlanDto visitPlanDto = visitPlanService.get(visitPlanId);

        MdmPersonDo personDo = mdmPersonUtils.checkIfPersonCodeExist(addCmd.getCreatedBy());
        // 数据权限 添加创建人字段(保存创建人personCode)
        String groupFullCode = mdmPersonUtils.checkIfOrganizationExist(
                personDo.getGroupBelongDepartmentCode()
        ).getGroupFullCode();

        // 数据权限 添加部门全路径字段(保存创建人所属部门全路径)
        addCmd.setGroupFullCode(groupFullCode);

        // 根据保存的人员ids:{} 获取工号 加入表单数据传递至工作流 , 以指定审批人
        String userCodes = mdmPersonUtils.getUserCodes(addCmd.getMainPersonIds());
        log.info("根据保存的人员ids:{} 获取工号:{}",addCmd.getMainPersonIds(),userCodes);

        //获取id
        Long fdId = publicRelationsService.addThenReturnId(addCmd);
        attachmentService.saveBatch(addCmd.getAddCmdList(),fdId.toString(), AttachmentOrderType.PUBLIC_RELATION);
        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        log.info(">>>>>>>>公关反馈:bean转化后的map: ",variables);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        variables.put("date",df.format(addCmd.getDate()));

        variables.put("title",visitPlanDto.getTitle());

        if (!StringUtils.isEmpty(userCodes))variables.put("mdmUserCodes",userCodes);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");
        variables.remove("addCmdList");
        variables.remove("mainPersonIds");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】客户拜访反馈审批单");
//        dto.setAppId(appId);
        dto.setCode(flowCode);
        dto.setTenantId("1");
        dto.setUserNo(addCmd.getCreatedBy());
        dto.setUserFullName(personDo.getPersonName());
        dto.setBusinessKey(fdId.toString());
        dto.setVariables(variables);

        dto.setApproveUser(addCmd.getApproveUser());

        SingleResponse<String>
                response = rpi.startProcessByCode(dto);
        log.info("==公关反馈发起流程 响应=="+response);

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
        lmcWorkflowDo.setBizCode(BizCode.ADD_FEEDBACK.getValue()); /*业务类型*/
        lmcWorkflowDo.setBizId(fdId.toString());
        lmcWorkflowDo.setProcessCode("feedback");
        lmcWorkflowDo.setProcessInstanceId(response.getData());
        lmcWorkflowDo.setTitle("新增公关反馈-");
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
        timelineAddCmd.setBizCode(BizCode.ADD_FEEDBACK.getValue());
        timelineAddCmd.setBizId(String.valueOf(fdId));
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
    public Response reStartProcess(PublicRelationsAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUser(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }

        LmcWorkflowDo workflowDo = Optional.ofNullable(
                lmcWorkflowRepository.getOne(new LambdaQueryWrapper<LmcWorkflowDo>()
                        .eq(LmcWorkflowDo::getBizId, addCmd.getId()))
        ).orElseThrow(
                () -> new BizRuntimeException(
                        "根据新增客户反馈的Id:{" + addCmd.getId() + "}找不到对应流程实例",new Exception())
        );
        PublicRelationsDo publicRelationsDo = publicRelationsAssembler.toDO(addCmd);
        boolean bool = publicRelationsRepository.updateById(publicRelationsDo);
        if (!bool)log.error("重新发起流程:更新新增客户反馈时失败!客户反馈:{}",publicRelationsDo);

        // 根据保存的人员ids:{} 获取工号 加入表单数据传递至工作流 , 以指定审批人
        String userCodes = mdmPersonUtils.getUserCodes(addCmd.getMainPersonIds());
        log.info("根据保存的人员ids:{} 获取工号:{}",addCmd.getMainPersonIds(),userCodes);

        Long visitPlanId = addCmd.getVisitPlanId();
        VisitPlanDto visitPlanDto = visitPlanService.get(visitPlanId);

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.put("title",visitPlanDto.getTitle());

        if (!StringUtils.isEmpty(userCodes))variables.put("mdmUserCodes",userCodes);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");
        variables.remove("addCmdList");

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
        log.info("==客户反馈新增流程驳回后重新发起 响应:{}",response);

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
    public Response draftProcess(PublicRelationsAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUser(addCmd.getApproveUser());
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
        PublicRelationsDo publicRelationsDo = publicRelationsAssembler.toDO(addCmd);
        boolean bool = publicRelationsRepository.updateById(publicRelationsDo);
        if (!bool)log.error("发起流程:更新新增客户反馈时失败!客户反馈:{}",publicRelationsDo);

        Long fdId=publicRelationsDo.getId();

//        attachmentService.saveBatch(addCmd.getAddCmdList(),fdId.toString(), AttachmentOrderType.PUBLIC_RELATION);

        Long visitPlanId = addCmd.getVisitPlanId();
        VisitPlanDto visitPlanDto = visitPlanService.get(visitPlanId);

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.put("title",visitPlanDto.getTitle());

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】客户计划实施反馈审批单");
//        dto.setAppId(appId);
        dto.setCode(flowCode);
        dto.setTenantId("1");
        dto.setUserNo(addCmd.getCreatedBy());
        dto.setUserFullName(personDo.getPersonName());
        dto.setBusinessKey(fdId.toString());
        dto.setVariables(variables);

        dto.setApproveUser(addCmd.getApproveUser());

        SingleResponse<String>
                response = rpi.startProcessByCode(dto);
        log.info("==公关反馈发起流程 响应=="+response);

        LmcWorkflowDo lmcWorkflowDo = new LmcWorkflowDo();
//            lmcWorkflowDo.setId();
        lmcWorkflowDo.setCreatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setCreatedTime(LocalDateTime.now());
        lmcWorkflowDo.setUpdatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setUpdatedTime(LocalDateTime.now());
        lmcWorkflowDo.setBizCode(BizCode.ADD_FEEDBACK.getValue()); /*业务类型*/
        lmcWorkflowDo.setBizId(fdId.toString());
        lmcWorkflowDo.setProcessCode("feedback");
        lmcWorkflowDo.setProcessInstanceId(response.getData());
        lmcWorkflowDo.setTitle("新增公关反馈-");
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
        timelineAddCmd.setBizCode(BizCode.ADD_FEEDBACK.getValue());
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
        System.out.println("ISubmitAddPublicRelationsService.java");
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
        );

        LmcWorkflowTimelineAddCmd timelineAddCmd = this.addTimeLine(processCall);
        timelineAddCmd.setReason("流程驳回");
        lmcWorkflowTimelineService.add(timelineAddCmd);
    }

    @Override
    public void handleRevoke(ProcessCall processCall) {

        // 更新业务关联的工作流状态
        lmcWorkflowRepository.update(new LambdaUpdateWrapper<LmcWorkflowDo>()
                .eq(LmcWorkflowDo::getProcessInstanceId, processCall.getProcessInstanceId())
                .eq(LmcWorkflowDo::getBizId, processCall.getBusinessKey())
                .set(LmcWorkflowDo::getWorkflowStatus, WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_REVOKE.getValue())
                .set(LmcWorkflowDo::getUpdatedTime, LocalDateTime.now())
        );

        LmcWorkflowTimelineAddCmd timelineAddCmd = this.addTimeLine(processCall);
        timelineAddCmd.setReason("流程撤回");
        lmcWorkflowTimelineService.add(timelineAddCmd);
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
        timelineAddCmd.setBizCode(BizCode.ADD_FEEDBACK.getValue());
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
}
