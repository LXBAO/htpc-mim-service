package com.uwdp.workflow.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.gientech.lcds.workflow.sdk.core.runtime.ReStartProcessRequestDto;
import com.gientech.lcds.workflow.sdk.core.runtime.StartProcessByCodeRequestDto;
import com.gientech.lcds.workflow.sdk.runtime.procinst.service.impl.RuntimeProcInstServiceImpl;
import com.uwdp.module.api.service.*;
import com.uwdp.module.api.vo.cmd.*;
import com.uwdp.module.api.vo.enums.WorkflowStatusEnums;
import com.uwdp.module.biz.infrastructure.assembler.*;
import com.uwdp.module.biz.infrastructure.entity.*;
import com.uwdp.module.biz.infrastructure.repository.*;
import com.uwdp.utils.MdmPersonUtils;
import com.uwdp.workflow.enums.BizCode;
import com.uwdp.workflow.service.iservice.IProjectImplementService;
import com.uwdp.workflow.util.FunctionalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubmitAddProjectImplementServiceImpl implements IProjectImplementService {


    @Value("${hpcc-mim-server.app-id}")
    public String appId; /*应用ID*/

    private static final String flowCode = BizCode.ADD_PROJECT_IMPLEMENT.getValue(); /*流程编码*/

    private final com.uwdp.module.api.service.IProjectImplementService projectImplementService;

    private final ProjectImplementRepository projectImplementRepository;

    private final ProjectImplementAssembler projectImplementAssembler;

    private final IProjectRecordsService projectRecordsService;

    private final ILmcWorkflowService lmcWorkflowService;

    private final LmcWorkflowRepository lmcWorkflowRepository;

    private final ILmcWorkflowTimelineService lmcWorkflowTimelineService;

    private final RuntimeProcInstServiceImpl rpi;

    private final MdmPersonRepository mdmPersonRepository;

    private final MdmPersonUtils mdmPersonUtils;

    private final IPerformanceManagementService performanceManagementService;

    private final IProjectSigningService projectSigningService;

    private final IWinTheBidService winTheBidService;

    private final ProjectRecordsRepository projectRecordsRepository;

    private final ProjectRecordsAssembler projectRecordsAssembler;

    private final ProjectSigningRepository projectSigningRepository;

    private final ProjectSigningAssembler projectSigningAssembler;

    private final WinTheBidRepository winTheBidRepository;

    private final WinTheBidAssembler winTheBidAssembler;

    private final RequestLogRepository requestLogRepository;

    @Override
    public Response add(ProjectImplementAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUser(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }
        ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(addCmd.getProjectNumber()));
        projectRecordsUpdateCmd.setHideState("5");
        projectRecordsService.update(projectRecordsUpdateCmd);

        // 数据权限 添加创建人字段(保存创建人personCode)
        MdmPersonDo personDo = mdmPersonUtils
                .checkIfPersonCodeExist(addCmd.getCreatedBy());
        String groupFullCode = mdmPersonUtils.checkIfOrganizationExist(
                personDo.getGroupBelongDepartmentCode()
        ).getGroupFullCode();

        // 数据权限 添加部门全路径字段(保存创建人所属部门全路径)
        addCmd.setGroupFullCode(groupFullCode);
//        addCmd.setPersonName(personDo.getPersonName());

        Long id = projectImplementService.addThenReturnId(addCmd);

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】【"+addCmd.getProjectName()+"】项目实施审批单");
//        dto.setAppId(appId);    // 应用id
        dto.setCode(flowCode);  // 流程编码
        dto.setTenantId("1");   // 租户id
        dto.setUserNo(addCmd.getCreatedBy());   // 用户编码
        dto.setUserFullName(personDo.getPersonName());
        dto.setBusinessKey(String.valueOf(id));    //
        dto.setVariables(variables);
        dto.setApproveUser(addCmd.getApproveUser());

//        log.info("==指定的审批人:{}",dto.getApproveUser());
        log.info("==流程发起信息:{}", dto);
        SingleResponse<String>
                response = rpi.startProcessByCode(dto);
        log.info("==项目实施发起流程 响应:{}", response);
        if (!response.isSuccess()) {
            throw new RuntimeException("流程发起报错" + response);
        }

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
        lmcWorkflowDo.setBizCode(flowCode); /*业务类型*/
        lmcWorkflowDo.setBizId(id.toString());
        lmcWorkflowDo.setProcessCode(flowCode);
        lmcWorkflowDo.setProcessInstanceId(response.getData());
        lmcWorkflowDo.setTitle("项目实施新增-" + addCmd.getProjectName());
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
//        lmcWorkflowService.add(workflowAddCmd);
        log.info("====workflow添加成功:{}", lmcWorkflowDo);
        System.out.println(lmcWorkflowDo.getId());

        if (null == lmcWorkflowDo.getId()) {
            return Response.buildFailure("506","找不到id！");
        }

        // 记录流程审批时间线
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
        timelineAddCmd.setBizCode(flowCode);
        timelineAddCmd.setBizId(String.valueOf(id));
        timelineAddCmd.setReason("自动提交流程");
        timelineAddCmd.setSucceed(response.isSuccess() ? 1 : 0);
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
        log.info("====timeline添加成功,业务id:{}", id);
        return Response.buildSuccess();
    }

    @Override
    public Response reStartProcess(ProjectImplementAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUser(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }
        LmcWorkflowDo workflowDo = Optional.ofNullable(
                lmcWorkflowRepository.getOne(new LambdaQueryWrapper<LmcWorkflowDo>()
                        .eq(LmcWorkflowDo::getBizId, addCmd.getId()))
        ).orElseThrow(
                () -> new BizRuntimeException(
                        "根据新增项目实施的Id:{" + addCmd.getId() + "}找不到对应流程实例", new Exception())
        );
        ProjectImplementDo projectImplementDo = projectImplementAssembler.toDO(addCmd);
        projectImplementDo.setUpdatedTime(LocalDateTime.now());
        boolean bool = projectImplementRepository.updateById(projectImplementDo);
        if (!bool) log.error("重新发起流程:更新新增项目实施时失败!项目实施:{}", projectImplementDo);
        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");

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

        log.info("==已驳回流程重新发起信息:{}", dto);
        SingleResponse<String> response = rpi.reStartProcess(dto);
        log.info("==项目实施新增流程驳回后重新发起 响应:{}", response);

        if (response.isSuccess()) {
            // 重新设置流程状态为审批中
            workflowDo.setWorkflowStatus(WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_ACTIVE.getValue());
            workflowDo.setVersion(workflowDo.getVersion() + 1);
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
        timelineAddCmd.setSucceed(response.isSuccess() ? 1 : 0);
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
    public Response draftProcess(ProjectImplementAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUser(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }
        // 数据权限 添加创建人字段(保存创建人personCode)
        MdmPersonDo personDo = mdmPersonUtils
                .checkIfPersonCodeExist(addCmd.getCreatedBy());
        String groupFullCode = mdmPersonUtils.checkIfOrganizationExist(
                personDo.getGroupBelongDepartmentCode()
        ).getGroupFullCode();

        // 数据权限 添加部门全路径字段(保存创建人所属部门全路径)
        addCmd.setGroupFullCode(groupFullCode);
//        addCmd.setPersonName(personDo.getPersonName());

//        String fdId = projectRecordsService.addThenReturnId(addCmd);

        ProjectImplementDo projectImplementDo = projectImplementAssembler.toDO(addCmd);
        projectImplementDo.setUpdatedTime(LocalDateTime.now());
        boolean bool = projectImplementRepository.updateById(projectImplementDo);
        if (!bool) log.error("发起流程:更新新增项目实施时失败!项目实施:{}", projectImplementDo);
        Long fdId = projectImplementDo.getId();

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】【"+addCmd.getProjectName()+"】项目实施审批单");
//        dto.setAppId(appId);    // 应用id
        dto.setCode(flowCode);  // 流程编码
        dto.setTenantId("1");   // 租户id
        dto.setUserNo(addCmd.getCreatedBy());   // 用户编码
        dto.setUserFullName(personDo.getPersonName());
        dto.setBusinessKey(fdId.toString());    //
        dto.setVariables(variables);
        dto.setApproveUser(addCmd.getApproveUser());

//        log.info("==指定的审批人:{}",dto.getApproveUser());
        log.info("==流程发起信息:{}", dto);
        SingleResponse<String>
                response = rpi.startProcessByCode(dto);
        log.info("==项目实施发起流程 响应:{}", response);
        if (!response.isSuccess()) {
            throw new RuntimeException("流程发起报错" + response);
        }

        LmcWorkflowDo lmcWorkflowDo = new LmcWorkflowDo();
//            lmcWorkflowDo.setId();
        lmcWorkflowDo.setCreatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setCreatedTime(LocalDateTime.now());
        lmcWorkflowDo.setUpdatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setUpdatedTime(LocalDateTime.now());
        lmcWorkflowDo.setBizCode(flowCode); /*业务类型*/
        lmcWorkflowDo.setBizId(fdId.toString());
        lmcWorkflowDo.setProcessCode(flowCode);
        lmcWorkflowDo.setProcessInstanceId(response.getData());
        lmcWorkflowDo.setTitle("项目实施新增-" + addCmd.getProjectName());
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
//        lmcWorkflowService.add(workflowAddCmd);
        log.info("====workflow添加成功:{}", lmcWorkflowDo);
        System.out.println(lmcWorkflowDo.getId());

        if (null == lmcWorkflowDo.getId()) {
            return Response.buildFailure("506","找不到id！");
        }

        // 记录流程审批时间线
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
        timelineAddCmd.setBizCode(flowCode);
        timelineAddCmd.setBizId(fdId.toString());
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
        log.info("====timeline添加成功,业务id:{}", fdId);
        return Response.buildSuccess();
    }

    @Override
    public void handleStart(ProcessCall processCall) {

    }

    @Override
    public void handlePass(ProcessCall processCall) {
        ProjectRecordsUpdateCmd projectRecordsQuery = new ProjectRecordsUpdateCmd();
        projectRecordsQuery.setId(Long.valueOf((String) processCall.getFormData().get("projectNumber")));
        log.info("项目实施 项目id为：{}", processCall.getFormData().get("projectNumber"));
        projectRecordsQuery.setProjectPhase("6");
        projectRecordsService.update(projectRecordsQuery);

        // 项目实施流程结束收集信息沉淀为业绩
        insertOnePerformanceManagement(processCall);

        // 更新业务关联的工作流状态
        boolean update = lmcWorkflowRepository.update(new LambdaUpdateWrapper<LmcWorkflowDo>()
                .eq(LmcWorkflowDo::getProcessInstanceId, processCall.getProcessInstanceId())
                .eq(LmcWorkflowDo::getBizId, processCall.getBusinessKey())
                .set(LmcWorkflowDo::getEndTime, processCall.getDate())
                .set(LmcWorkflowDo::getWorkflowStatus, WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_COMPLETED.getValue())
                .set(LmcWorkflowDo::getUpdatedTime, LocalDateTime.now())
        );
        if (!update) log.info("流程回调更新关联业务状态失败:{}", processCall);

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

    // 项目实施流程结束收集信息沉淀为业绩
    private void insertOnePerformanceManagement(ProcessCall processCall) {
        Map<String, Object> formData = processCall.getFormData();
        String createdBy = String.valueOf(formData.get("createdBy"));
        String projectName = (String) formData.get("projectName");/*项目名称*/
        String projectNameEnglish = (String) formData.get("projectNameEnglish");/*项目名称(外文)*/
        if(projectNameEnglish==null || projectNameEnglish.equals("")){
            projectNameEnglish="暂无";
        }
        String implementingUnit = (String) formData.get("implementingUnit");/*实施单位*/
        if(implementingUnit==null || implementingUnit.equals("")){
            implementingUnit="暂无";
        }
        String useQualification = (String) formData.get("useQualification");/*使用资质*/
        if(useQualification==null || useQualification.equals("")){
            useQualification="暂无";
        }
        String moneyUS = (String) formData.get("moneyUS");/*合同金额(万美元)*/;
        if(moneyUS==null || moneyUS.equals("")){
            moneyUS="暂无";
        }
        String finishedWorkerMoney = (String) formData.get("finishedWorkerMoney");/*竣/完工结算金额(万元)*/
        if(finishedWorkerMoney==null || finishedWorkerMoney.equals("")){
            finishedWorkerMoney="暂无";
        }
        String industryCategory = String.valueOf(formData.get("industryCategory"));/*产业领域类别*/
        Long projectNumber = Long.valueOf((String) formData.get("projectNumber"));
        String workDate = (String) formData.get("workDate");/*实施工期开工时间*/
        String completionOfCompletion = (String) formData.get("completionOfCompletion");
        String qualityAssessment = (String) formData.get("qualityAssessment");
        String winnerlCass = (String) formData.get("winnerlCass");
        String winningProvince = (String) formData.get("winningProvince");
        String winningCity = (String) formData.get("winningCity");
        String literalDescription = (String) formData.get("literalDescription");
        String engineeringCondition = (String) formData.get("engineeringCondition");
        String technicalIndex = (String) formData.get("technicalIndex");
        String postponement = (String) formData.get("postponement");
        String inputInformation = (String) formData.get("inputInformation");
        String saveEntryLocation = (String) formData.get("saveEntryLocation");
        String remark = (String) formData.get("remark");

        //项目签约
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("PROJECTNUMBER", projectNumber);
        ProjectSigningDo projectSigningDo = projectSigningRepository.getOne(queryWrapper);
        String newIndustry = projectSigningDo.getNewIndustry();/*新产业类别*/
        BigDecimal contractAmount = projectSigningDo.getContractAmount();/*合同金额(万元)*/
        if(contractAmount==null){
            contractAmount= new BigDecimal(0);
        }
        String category = projectSigningDo.getCategory();/*产业链类别*/
        if(category==null || category.equals("")){
            category="暂无";
        }

        String physicalQuantityOne = projectSigningDo.getPrincipalPhysicalQuantityOne();/*主要实物量1*/
        String physicalQuantityTwo = projectSigningDo.getPrincipalPhysicalQuantityTwo();/*主要实物量2*/
        String physicalQuantityIndexOne = projectSigningDo.getMainPhysicalQuantityIndexOne();/*主要实物量指标1*/
        String physicalQuantityIndexTwo = projectSigningDo.getMainPhysicalQuantityIndexTwo();/*主要实物量指标2*/
        String timeOfSigning = projectSigningDo.getTimeOfSigning();/*合同签订时间*/
        String projectLevel = projectSigningDo.getProjectLevel();/*项目级别---工程等级*/
        String ofContract = projectSigningDo.getCommencementOfContract();/*合同开始时间--合同工期开始时间*/
        String contractEndTime = projectSigningDo.getContractEndTime();/*合同开始时间--合同工期开始时间*/

        //项目中标
        QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("ITEMNUMBER", projectNumber);
        WinTheBidDo winTheBidDo = winTheBidRepository.getOne(queryWrapper1);
        String businessModel = winTheBidDo.getBusinessModel();/*商业模式*/
        if(businessModel==null || businessModel.equals("")){
            businessModel="暂无";
        }
        //项目登记
        ProjectRecordsDo recordsDo = projectRecordsRepository.getById(projectNumber);
        String location = recordsDo.getLocation(); /*所在地(省市)--项目所在地省(国名)*/
        if(location==null || location.equals("")){
            location="暂无";
        }

        PerformanceManagementAddCmd pmAdd = new PerformanceManagementAddCmd();
        //pmAdd.setId(0L);
        pmAdd.setCreatedBy(createdBy);
        pmAdd.setCreatedTime(LocalDateTime.now());
        pmAdd.setUpdatedBy(createdBy);
        pmAdd.setUpdatedTime(LocalDateTime.now());
        pmAdd.setProjectName(projectName);/*工程项目名*/
        pmAdd.setProjectNameEnglish(projectNameEnglish);
        pmAdd.setImplementingUnit(implementingUnit);
        pmAdd.setUseQualification(useQualification);
        pmAdd.setMoney(String.valueOf(contractAmount));/*合同金额(万元)*/
        pmAdd.setMoneyUS(moneyUS);
        pmAdd.setFinishedWorkerMoney(finishedWorkerMoney);
        pmAdd.setIndustryCategory(industryCategory);/*产业领域类别*/
        pmAdd.setEngineeringGrade(projectLevel);/*项目等级---工程等级*/
        pmAdd.setIndustryChainCategory(category);/*产业链类别*/
        pmAdd.setNewIndustryCategory(newIndustry);/*新产业类别*/
        pmAdd.setBusinessModel(businessModel);/*商业模式*/
        pmAdd.setNationality(location);
        pmAdd.setProjectLandProvince(location);/*所在地(省市)--项目所在地省(国名)*/
        pmAdd.setProjectCity(location);
        pmAdd.setDateOfSigning(timeOfSigning);//*合同签订时间*/
        pmAdd.setStartTime(ofContract);/*合同开始时间--合同工期开始时间*/
        pmAdd.setEndTime(contractEndTime);/*合同结束时间--合同工期结束时间*/
        pmAdd.setPhysicalIndicatorOne(physicalQuantityOne);/*主要实物量1*/
        pmAdd.setMainphysicalIndicatorOne(physicalQuantityIndexOne);/*主要实物量指标1*/
        pmAdd.setStartOfConstructionPeriod(workDate);/*实施工期开工时间*/
        pmAdd.setPhysicalIndicatorTwo(physicalQuantityTwo);/*主要实物量2*/
        pmAdd.setMainphysicalIndicatorTwo(physicalQuantityIndexTwo);/*主要实物量指标2*/
        pmAdd.setCompletionOfProject(LocalDate.now().toString());
        pmAdd.setAcceptanceTime(LocalDate.now().toString());
        pmAdd.setCompletionAcceptanceTime(LocalDate.now().toString());
        pmAdd.setCompletionOfCompletion(completionOfCompletion);
        pmAdd.setQualityAssessment(qualityAssessment);
        pmAdd.setFilingTime(LocalDate.now().toString());
        pmAdd.setWinnerlCass(winnerlCass);
        pmAdd.setWinningProvince(winningProvince);
        pmAdd.setWinningCity(winningCity);
        pmAdd.setLiteralDescription(literalDescription);
        pmAdd.setEngineeringCondition(engineeringCondition);
        pmAdd.setTechnicalIndex(technicalIndex);
        pmAdd.setPostponement(postponement);
        pmAdd.setInputInformation(inputInformation);
        pmAdd.setSaveEntryLocation(saveEntryLocation);
        pmAdd.setRemark(remark);
        pmAdd.setNoticeOfAward("");
        pmAdd.setContractDocument("");
        pmAdd.setCertificate("");
        pmAdd.setSupportingDocument("");
        pmAdd.setAppointmentDocument("");
        pmAdd.setAwardCertificate("");

        //performanceManagementService.add(pmAdd);
    }

    public ProcessCall compo() {
        String a = "{\n" +
                "  \"callId\": \"276 b1b30844d4648a048597470d48546\",\n" +
                "  \"callBackTime\": \"PROCESS_PASS\",\n" +
                "  \"date\": \"2023-08-03T17:54:16\",\n" +
                "  \"processInstanceId\": \"1687039000780861442\",\n" +
                "  \"processInstanceTitle\": \"【市场管理】项目实施审批单\",\n" +
                "  \"processKey\": \"projectImplement\",\n" +
                "  \"processName\": null,\n" +
                "  \"procInstStarterNo\": null,\n" +
                "  \"procInstStartAppId\": \"1658290286455627778\",\n" +
                "  \"procInstStartTenant\": 1,\n" +
                "  \"currentTaskId\": null,\n" +
                "  \"currentTaskKey\": null,\n" +
                "  \"currentTaskName\": null,\n" +
                "  \"procInstStarterFullName\": \"张智峰\",\n" +
                "  \"businessKey\": \"1687039000572674049\",\n" +
                "  \"formData\": {\n" +
                "    \"createdBy\": 5659,\n" +
                "    \"projectName\": \"名称\",\n" +
                "    \"projectState\": 2,\n" +
                "    \"workDate\": \"2023-08-09 00:00:00\",\n" +
                "    \"nonWorkingCause\": \"\",\n" +
                "    \"projectStage\": 6,\n" +
                "    \"projectNumber\": \"1686681054504751106\",\n" +
                "    \"registrationUnit\": \"中国能建湖南火电公司本部\",\n" +
                "    \"industryCategory\": 20201,\n" +
                "    \"registerDate\": \"2023-08-10 00:00:00\",\n" +
                "    \"createdByName\": \"张智峰\",\n" +
                "    \"groupFullCode\": \"012013/01201300/01201300-04/01201300-040001\",\n" +
                "    \"addCmdList\": []\n" +
                "  },\n" +
                "  \"approveInfo\": null\n" +
                "}\n";
        ProcessCall processCall = JSON.parseObject(a, ProcessCall.class);
        return processCall;

    }

}
