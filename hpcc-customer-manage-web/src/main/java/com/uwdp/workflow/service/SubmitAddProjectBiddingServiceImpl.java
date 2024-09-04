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
import com.uwdp.module.api.service.ILmcWorkflowService;
import com.uwdp.module.api.service.ILmcWorkflowTimelineService;
import com.uwdp.module.api.service.IProjectRecordsService;
import com.uwdp.module.api.vo.cmd.*;
import com.uwdp.module.api.vo.enums.WorkflowStatusEnums;
import com.uwdp.module.biz.infrastructure.assembler.ProjectBiddingAssembler;
import com.uwdp.module.biz.infrastructure.entity.*;
import com.uwdp.module.biz.infrastructure.repository.*;
import com.uwdp.utils.MdmPersonUtils;

import com.uwdp.utils.StringUtils;
import com.uwdp.workflow.enums.BizCode;

import com.uwdp.workflow.service.iservice.IProjectBiddingService;
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
public class SubmitAddProjectBiddingServiceImpl implements IProjectBiddingService {


    @Value("${hpcc-mim-server.app-id}")
    public String appId; /*应用ID*/

    private static final String flowCode = BizCode.ADD_PROJECT_BIDDING.getValue(); /*流程编码*/

    private final com.uwdp.module.api.service.IProjectBiddingService projectBiddingService;

    private final IProjectRecordsService projectRecordsService;

    private final ProjectBiddingRepository projectBiddingRepository;

    private final ProjectBiddingAssembler projectBiddingAssembler;

    private final ILmcWorkflowService lmcWorkflowService;

    private final LmcWorkflowRepository lmcWorkflowRepository;

    private final ILmcWorkflowTimelineService lmcWorkflowTimelineService;

    private final RuntimeProcInstServiceImpl rpi;

    private final MdmPersonRepository mdmPersonRepository;

    private final MdmPersonUtils mdmPersonUtils;

    private final DatabaseReaderUtil readerUtil;

    private final StringUtils stringUtils;

    private final RequestLogRepository requestLogRepository;

    @Override
    public Response add(ProjectBiddingAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUser(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }

        ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new  ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(addCmd.getProjectNumber()));
        projectRecordsUpdateCmd.setHideState("2");
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

        Long id = projectBiddingService.addThenReturnId(addCmd);

        /**
         * 根据id查询关联的所有项目经理并改变其项目投入状态
         * start
         */
        String findSql="select * from T_LINKMAN where biddingId='"+addCmd.getProjectNumber()+"'";
        List<Map> mapList=readerUtil.findData("local",findSql);
        String strId="";
        if(mapList!=null&&!mapList.isEmpty()){
            for (int i = 0; i < mapList.size(); i++) {
                Map map=mapList.get(i);
                String nameId=stringUtils.getStr(map.get("nameid"));
                if(i==0){
                    strId="'"+nameId+"'";
                }else{
                    strId=strId+",'"+nameId+"'";
                }
            }
        }
        log.info("所有人员工号：{}",strId);
        String updateSql="update T_PROJECTMANAGERLEDGER set fdInputType='投标状态' where" +
                " fdJobNumber in("+strId+")";
        int updateSize=readerUtil.operationData("local",updateSql);
        log.info("更新项目经理表{}条数据！！！",updateSize);
        /**
         * 根据id查询关联的所有项目经理并改变其项目投入状态
         * stop
         */

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】【"+addCmd.getProjectName()+"】项目投标审批单");
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
        log.info("==项目登记发起流程 响应:{}",response);
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
        lmcWorkflowDo.setTitle("项目投标新增-"+addCmd.getProjectName());
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
    public Response reStartProcess(ProjectBiddingAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUser(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }

        LmcWorkflowDo workflowDo = Optional.ofNullable(
                lmcWorkflowRepository.getOne(new LambdaQueryWrapper<LmcWorkflowDo>()
                        .eq(LmcWorkflowDo::getBizId, addCmd.getId()))
        ).orElseThrow(
                () -> new BizRuntimeException(
                        "根据新增项目投标的Id:{" + addCmd.getId() + "}找不到对应流程实例",new Exception())
        );
        ProjectBiddingDo projectBiddingDo = projectBiddingAssembler.toDO(addCmd);
        projectBiddingDo.setUpdatedTime(LocalDateTime.now());
        boolean bool = projectBiddingRepository.updateById(projectBiddingDo);
        if (!bool)log.error("重新发起流程:更新新增项目投标时失败!项目投标:{}",projectBiddingDo);

        /**
         * 根据id查询关联的所有项目经理并改变其项目投入状态
         * start
         */
        String findSql="select * from T_LINKMAN where biddingId='"+addCmd.getProjectNumber()+"'";
        List<Map> mapList=readerUtil.findData("local",findSql);
        String strId="";
        if(mapList!=null&&!mapList.isEmpty()){
            for (int i = 0; i < mapList.size(); i++) {
                Map map=mapList.get(i);
                String nameId=stringUtils.getStr(map.get("nameid"));
                if(i==0){
                    strId="'"+nameId+"'";
                }else{
                    strId=strId+",'"+nameId+"'";
                }
            }
        }
        log.info("所有人员工号：{}",strId);
        String updateSql="update T_PROJECTMANAGERLEDGER set fdInputType='投标状态' where" +
                " fdJobNumber in("+strId+")";
        int updateSize=readerUtil.operationData("local",updateSql);
        log.info("更新项目经理表{}条数据！！！",updateSize);
        /**
         * 根据id查询关联的所有项目经理并改变其项目投入状态
         * stop
         */


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
        log.info("==项目投标新增流程驳回后重新发起 响应:{}",response);

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
    public Response draftProcess(ProjectBiddingAddCmd addCmd) {
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

        ProjectBiddingDo projectBiddingDo = projectBiddingAssembler.toDO(addCmd);
        projectBiddingDo.setUpdatedTime(LocalDateTime.now());
        boolean bool = projectBiddingRepository.updateById(projectBiddingDo);
        if (!bool)log.error("发起流程:更新新增项目投标时失败!项目投标:{}",projectBiddingDo);
        Long fdId=projectBiddingDo.getId();

        /**
         * 根据id查询关联的所有项目经理并改变其项目投入状态
         * start
         */
        String findSql="select * from T_LINKMAN where biddingId='"+addCmd.getProjectNumber()+"'";
        List<Map> mapList=readerUtil.findData("local",findSql);
        String strId="";
        if(mapList!=null&&!mapList.isEmpty()){
            for (int i = 0; i < mapList.size(); i++) {
                Map map=mapList.get(i);
                String nameId=stringUtils.getStr(map.get("nameid"));
                if(i==0){
                    strId="'"+nameId+"'";
                }else{
                    strId=strId+",'"+nameId+"'";
                }
            }
        }
        log.info("所有人员工号：{}",strId);
        String updateSql="update T_PROJECTMANAGERLEDGER set fdInputType='投标状态' where" +
                " fdJobNumber in("+strId+")";
        int updateSize=readerUtil.operationData("local",updateSql);
        log.info("更新项目经理表{}条数据！！！",updateSize);
        /**
         * 根据id查询关联的所有项目经理并改变其项目投入状态
         * stop
         */

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】【"+addCmd.getProjectName()+"】项目投标审批单");
//        dto.setAppId(appId);    // 应用id
        dto.setCode(flowCode);  // 流程编码
        dto.setTenantId("1");   // 租户id
        dto.setUserNo(addCmd.getCreatedBy());   // 用户编码
        dto.setUserFullName(personDo.getPersonName());
        dto.setBusinessKey(fdId.toString());    //
        dto.setVariables(variables);
        dto.setApproveUser(addCmd.getApproveUser());

//        log.info("==指定的审批人:{}",dto.getApproveUser());
        log.info("==流程发起信息:{}",dto);
        SingleResponse<String>
                response = rpi.startProcessByCode(dto);
        log.info("==项目投标发起流程 响应:{}",response);
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
        lmcWorkflowDo.setTitle("项目投标新增-"+addCmd.getProjectName());
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

    @Override
    public void handlePass(ProcessCall processCall) {
        ProjectRecordsUpdateCmd projectRecordsQuery = new  ProjectRecordsUpdateCmd();
        projectRecordsQuery.setId(Long.valueOf((String) processCall.getFormData().get("projectNumber")));
        log.info("项目投标 项目id为：{}",processCall.getFormData().get("projectNumber"));
        projectRecordsQuery.setProjectPhase("3");
        projectRecordsService.update(projectRecordsQuery);

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

    @Override
    public void handleBack(ProcessCall processCall) {
        Map<String, Object> mapData= processCall.getFormData();
        String projectNumber=mapData.get("projectNumber")+"";
        /**
         * 根据id查询关联的所有项目经理并改变其项目投入状态
         * start
         */
        String findSql="select * from T_LINKMAN where biddingId='"+projectNumber+"'";
        List<Map> mapList=readerUtil.findData("local",findSql);
        String strId="";
        if(mapList!=null&&!mapList.isEmpty()){
            for (int i = 0; i < mapList.size(); i++) {
                Map map=mapList.get(i);
                String nameId=stringUtils.getStr(map.get("nameid"));
                if(i==0){
                    strId="'"+nameId+"'";
                }else{
                    strId=strId+",'"+nameId+"'";
                }
            }
        }
        log.info("所有人员工号：{}",strId);
        String updateSql="update T_PROJECTMANAGERLEDGER set fdInputType='未使用' where" +
                " fdJobNumber in("+strId+")";
        int updateSize=readerUtil.operationData("local",updateSql);
        log.info("更新项目经理表{}条数据！！！",updateSize);
        /**
         * 根据id查询关联的所有项目经理并改变其项目投入状态
         * stop
         */

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
        Map<String, Object> mapData= processCall.getFormData();
        String projectNumber=mapData.get("projectNumber")+"";
        /**
         * 根据id查询关联的所有项目经理并改变其项目投入状态
         * start
         */
        String findSql="select * from T_LINKMAN where biddingId='"+projectNumber+"'";
        List<Map> mapList=readerUtil.findData("local",findSql);
        String strId="";
        if(mapList!=null&&!mapList.isEmpty()){
            for (int i = 0; i < mapList.size(); i++) {
                Map map=mapList.get(i);
                String nameId=stringUtils.getStr(map.get("nameid"));
                if(i==0){
                    strId="'"+nameId+"'";
                }else{
                    strId=strId+",'"+nameId+"'";
                }
            }
        }
        log.info("所有人员工号：{}",strId);
        String updateSql="update T_PROJECTMANAGERLEDGER set fdInputType='未使用' where" +
                " fdJobNumber in("+strId+")";
        int updateSize=readerUtil.operationData("local",updateSql);
        log.info("更新项目经理表{}条数据！！！",updateSize);
        /**
         * 根据id查询关联的所有项目经理并改变其项目投入状态
         * stop
         */

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
}
