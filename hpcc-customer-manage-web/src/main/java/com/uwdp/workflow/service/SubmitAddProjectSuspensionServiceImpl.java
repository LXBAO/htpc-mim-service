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
import com.uwdp.jdbcutils.DatabaseReaderUtil;
import com.uwdp.module.api.service.*;
import com.uwdp.module.api.vo.cmd.LmcWorkflowTimelineAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectSuspensionAddCmd;
import com.uwdp.module.api.vo.enums.WorkflowStatusEnums;
import com.uwdp.module.biz.infrastructure.assembler.ProjectSuspensionAssembler;
import com.uwdp.module.biz.infrastructure.entity.LmcWorkflowDo;
import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;
import com.uwdp.module.biz.infrastructure.entity.ProjectSuspensionDo;
import com.uwdp.module.biz.infrastructure.entity.RequestLogDo;
import com.uwdp.module.biz.infrastructure.repository.LmcWorkflowRepository;
import com.uwdp.module.biz.infrastructure.repository.MdmPersonRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectSuspensionRepository;
import com.uwdp.module.biz.infrastructure.repository.RequestLogRepository;
import com.uwdp.utils.MdmPersonUtils;
import com.uwdp.utils.StringUtils;
import com.uwdp.workflow.enums.BizCode;
import com.uwdp.workflow.util.FunctionalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubmitAddProjectSuspensionServiceImpl implements com.uwdp.workflow.service.iservice.IProjectSuspensionService {

    @Value("${hpcc-mim-server.app-id}")
    public String appId; /*应用ID*/

    private static final String flowCode = BizCode.ADD_PROJECT_SUSPENSION.getValue(); /*流程编码*/

    private final IProjectSuspensionService projectSuspensionService;

    private final ProjectSuspensionRepository projectSuspensionRepository;

    private final ProjectSuspensionAssembler projectSuspensionAssembler;

    private final ILmcWorkflowService lmcWorkflowService;

    private final LmcWorkflowRepository lmcWorkflowRepository;

    private final ILmcWorkflowTimelineService lmcWorkflowTimelineService;

    private final RuntimeProcInstServiceImpl rpi;

    private final MdmPersonRepository mdmPersonRepository;

    private final MdmPersonUtils mdmPersonUtils;

    private final RequestLogRepository requestLogRepository;

    @Override
    public Response add(ProjectSuspensionAddCmd addCmd) {
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

        Long id =projectSuspensionService.addThenReturnId(addCmd);

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】【"+addCmd.getProjectName()+"】项目中止审批单");
//        dto.setAppId(appId);    // 应用id
        dto.setCode(flowCode);  // 流程编码
        dto.setTenantId("1");   // 租户id
        dto.setUserNo(addCmd.getCreatedBy());   // 用户编码
        dto.setUserFullName(personDo.getPersonName());
        dto.setBusinessKey(String.valueOf(id));    //
        dto.setVariables(variables);
        dto.setApproveUser(addCmd.getApproveUser());

//        log.info("==指定的审批人:{}",dto.getApproveUser());
        log.info("==流程发起信息:{}",dto);
        SingleResponse<String>
                response = rpi.startProcessByCode(dto);
        log.info("==项目中止发起流程 响应:{}",response);
        if (!response.isSuccess()){
            throw new RuntimeException("流程发起报错"+response);
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
        lmcWorkflowDo.setTitle("项目中止新增-"+addCmd.getProjectName()+addCmd.getProjectNo());
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
        log.info("====workflow添加成功:{}",lmcWorkflowDo);
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
        log.info("====timeline添加成功,业务id:{}",id);
        return Response.buildSuccess();
    }

    @Override
    public Response reStartProcess(ProjectSuspensionAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUser(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }
        LmcWorkflowDo workflowDo = Optional.ofNullable(
                lmcWorkflowRepository.getOne(new LambdaQueryWrapper<LmcWorkflowDo>()
                        .eq(LmcWorkflowDo::getBizId, addCmd.getId()))
        ).orElseThrow(
                () -> new BizRuntimeException(
                        "根据新增项目中止的Id:{" + addCmd.getId() + "}找不到对应流程实例",new Exception())
        );
        ProjectSuspensionDo projectSuspensionDo = projectSuspensionAssembler.toDO(addCmd);
        projectSuspensionDo.setUpdatedTime(LocalDateTime.now());
        boolean bool = projectSuspensionRepository.updateById(projectSuspensionDo);
        if (!bool)log.error("重新发起流程:更新新增项目中止时失败!项目中止:{}",projectSuspensionDo);
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

        log.info("==已驳回流程重新发起信息:{}",dto);
        SingleResponse<String> response = rpi.reStartProcess(dto);
        log.info("==项目中止新增流程驳回后重新发起 响应:{}",response);

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
    public Response draftProcess(ProjectSuspensionAddCmd addCmd) {
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

//        String fdId = projectSigningService.addThenReturnId(addCmd);

        ProjectSuspensionDo projectSuspensionDo = projectSuspensionAssembler.toDO(addCmd);
        projectSuspensionDo.setUpdatedTime(LocalDateTime.now());
        boolean bool = projectSuspensionRepository.updateById(projectSuspensionDo);
        if (!bool)log.error("发起流程:更新新增项目中止时失败!项目中止:{}",projectSuspensionDo);
        Long fdId=projectSuspensionDo.getId();

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】【"+addCmd.getProjectName()+"】项目中止审批单");
//        dto.setAppId(appId);    // 应用id
        dto.setCode(flowCode);  // 流程编码
        dto.setTenantId("1");   // 租户id
        dto.setUserNo(addCmd.getCreatedBy());   // 用户编码
        dto.setUserFullName(personDo.getPersonName());
        dto.setBusinessKey(fdId.toString());    //
        dto.setVariables(variables);
//        dto.setApproveUser(addCmd.getApproveUser());

//        log.info("==指定的审批人:{}",dto.getApproveUser());
        log.info("==流程发起信息:{}",dto);
        SingleResponse<String>
                response = rpi.startProcessByCode(dto);
        log.info("==项目中止发起流程 响应:{}",response);
        if (!response.isSuccess()){
            throw new RuntimeException("流程发起报错"+response);
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
        lmcWorkflowDo.setTitle("项目中止新增-"+addCmd.getProjectName()+addCmd.getProjectNo());
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
        log.info("====workflow添加成功:{}",lmcWorkflowDo);
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
        log.info("====timeline添加成功,业务id:{}",fdId);
        return Response.buildSuccess();
    }

    @Override
    public void handleStart(ProcessCall processCall) {

    }

    private final IProjectRecordsService projectRecordsService;

    @Override
    public void handlePass(ProcessCall processCall) {
        String projectId=stringUtils.getStr(processCall.getFormData().get("projectId"));
        log.info("进入handlePass(流程结束)方法--项目id：{}",projectId);
        //先查投标关联明细
        String sql1="select * from T_LINKMAN where biddingId='"+projectId+"'";
        List<Map> list1=readerUtil.findData("local",sql1);
        if(list1!=null&&!list1.isEmpty()){
            for (int i = 0; i < list1.size(); i++) {
                Map map1=list1.get(i);
                //项目经理id
                String nameId=stringUtils.getStr(map1.get("nameid"));
                //再查项目经理或九大员关联的项目
                String sql2="select * from T_LINKMAN where nameId='"+nameId+"'";
                List<Map> list2=readerUtil.findData("local",sql2);
                boolean isChange=true;
                if (list2!=null&&!list2.isEmpty()){
                    for (int j = 0; j < list2.size(); j++) {
                        Map map2=list2.get(j);
                        //项目id
                        String biddingId=stringUtils.getStr(map2.get("biddingid"));
                        //如果有其他项目，不作更改
                        if(!projectId.equals(biddingId)){
                            isChange=false;
                        }
                    }
                    if(isChange){
                        String sql4="select * from T_PROJECTMANAGERLEDGER where fdJobNuber='"+nameId+"'";
                        List<Map> list4=readerUtil.findData("local",sql4);
                        Map map4=list4.get(0);
                        //项目经理使用状态
                        String fdInputType=stringUtils.getStr(map4.get("fdinputtype"));
                        if("3".equals(fdInputType)){
                            String sql3="update T_PROJECTMANAGERLEDGER set fdInputType='未使用'" +
                                    " where fdJobNumber='"+nameId+"'";
                            int size=readerUtil.operationData("local",sql3);
                            log.info("项目中止审批通过更改项目经理状态:{}条",size);
                        }
                    }
                }
            }

        }


        // 更新业务关联的工作流状态
        boolean update = lmcWorkflowRepository.update(new LambdaUpdateWrapper<LmcWorkflowDo>()
                .eq(LmcWorkflowDo::getProcessInstanceId, processCall.getProcessInstanceId())
                .eq(LmcWorkflowDo::getBizId, processCall.getBusinessKey())
                .set(LmcWorkflowDo::getEndTime, processCall.getDate())
                .set(LmcWorkflowDo::getWorkflowStatus, WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_COMPLETED.getValue())
                .set(LmcWorkflowDo::getUpdatedTime, LocalDateTime.now())
        );
        if(!update)log.info("流程回调更新关联业务状态失败:{}",processCall);

        LmcWorkflowTimelineAddCmd timelineAddCmd = this.addTimeLine(processCall);
        timelineAddCmd.setReason("流程审核通过-流程结束");
        lmcWorkflowTimelineService.add(timelineAddCmd);


    }
    private final DatabaseReaderUtil readerUtil;
    private final StringUtils stringUtils;
    @Override
    public void handleBack(ProcessCall processCall) {
        String projectId=stringUtils.getStr(processCall.getFormData().get("projectId"));
        log.info("流程驳回项目id：{}",projectId);
        //查询原项目阶段
        String findSql="select projectPhaseOld from t_projectrecords where id='"+projectId+"'";
        List<Map> list=readerUtil.findData("local",findSql);
        String projectPhase=stringUtils.getStr(list.get(0).get("projectphaseold"));
        String updateSql="update t_projectrecords set projectPhase='"+projectPhase+"' where id='"+projectId+"'";
        readerUtil.operationData("local",updateSql);
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
        String projectId=stringUtils.getStr(processCall.getFormData().get("projectId"));
        log.info("流程撤回项目id：{}",projectId);
        //查询原项目阶段
        String findSql="select projectPhaseOld from t_projectrecords where id='"+projectId+"'";
        List<Map> list=readerUtil.findData("local",findSql);
        String projectPhase=stringUtils.getStr(list.get(0).get("projectphaseold"));
        String updateSql="update t_projectrecords set projectPhase='"+projectPhase+"' where id='"+projectId+"'";
        readerUtil.operationData("local",updateSql);
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
}
