package com.uwdp.workflow.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.gientech.lcds.workflow.sdk.core.runtime.ReStartProcessRequestDto;
import com.gientech.lcds.workflow.sdk.core.runtime.StartProcessByCodeRequestDto;
import com.gientech.lcds.workflow.sdk.runtime.procinst.service.impl.RuntimeProcInstServiceImpl;
import com.uwdp.module.api.service.IClientInfoService;
import com.uwdp.module.api.service.ILmcWorkflowService;
import com.uwdp.module.api.service.ILmcWorkflowTimelineService;
import com.uwdp.module.api.service.ILogSheetService;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowTimelineAddCmd;
import com.uwdp.module.api.vo.dto.ClientInfoDto;
import com.uwdp.module.api.vo.dto.LogSheetDto;
import com.uwdp.module.api.vo.enums.WorkflowStatusEnums;
import com.uwdp.module.biz.infrastructure.assembler.ClientInfoAssembler;
import com.uwdp.module.biz.infrastructure.entity.*;
import com.uwdp.module.biz.infrastructure.repository.*;
import com.uwdp.utils.MdmPersonUtils;
import com.uwdp.utils.WorkflowUtils;
import com.uwdp.workflow.enums.BizCode;

import com.uwdp.workflow.service.iservice.ISubmitAddClientService;
import com.uwdp.workflow.util.FunctionalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubmitAddClientServiceImpl  implements ISubmitAddClientService{

    @Value("${hpcc-mim-server.app-id}")
    public String appId; /*应用ID*/

    private static final String flowCode = "addClient"; /*流程编码*/

    @Autowired
    private IClientInfoService clientInfoService;

    @Autowired
    private ClientInfoRepository clientInfoRepository;

    @Autowired
    private ClientInfoAssembler clientInfoAssembler;

    @Autowired
    private ILmcWorkflowService lmcWorkflowService;

    @Autowired
    private LmcWorkflowRepository lmcWorkflowRepository;

    @Autowired
    private ILmcWorkflowTimelineService lmcWorkflowTimelineService;

    private final LogSheetRepository logSheetRepository;

    @Autowired
    private RequestLogRepository requestLogRepository;


    @Autowired(required = false)
    private RuntimeProcInstServiceImpl rpi;

    @Autowired
    private MdmPersonRepository mdmPersonRepository;

    @Autowired
    private MdmPersonUtils mdmPersonUtils;

    @Override
    public Response add(ClientInfoAddCmd addCmd) {
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
        addCmd.setPersonName(personDo.getPersonName());

        Long fdId = clientInfoService.addThenReturnId(addCmd);

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");
        variables.remove("fdAffiliatedUser");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】【"+addCmd.getFdName()+"】客户信息登记审批单");
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
        log.info("==客户新增发起流程 响应:{}",response);
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
        lmcWorkflowDo.setCreatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setCreatedTime(LocalDateTime.now());
        lmcWorkflowDo.setUpdatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setUpdatedTime(LocalDateTime.now());
        lmcWorkflowDo.setBizCode(BizCode.ADD_CLIENT.getValue()); /*业务类型*/
        lmcWorkflowDo.setBizId(fdId.toString());
        lmcWorkflowDo.setProcessCode("addClient");
        lmcWorkflowDo.setProcessInstanceId(response.getData());
        lmcWorkflowDo.setTitle("新增客户-"+addCmd.getFdName());
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
        log.info("====workflow添加成功:{}",lmcWorkflowDo);

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
        timelineAddCmd.setBizCode(flowCode);
        timelineAddCmd.setBizId(String.valueOf(fdId));
        timelineAddCmd.setReason("自动提交流程");
        timelineAddCmd.setSucceed(response.isSuccess()?1:0);
        timelineAddCmd.setErrorMsg(response.getErrMessage());
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
    public Response reStartProcess(ClientInfoAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUser(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }
        LmcWorkflowDo workflowDo = Optional.ofNullable(
                lmcWorkflowRepository.getOne(new LambdaQueryWrapper<LmcWorkflowDo>()
                        .eq(LmcWorkflowDo::getBizId, addCmd.getFdId()))
        ).orElseThrow(
                () -> new BizRuntimeException(
                        "根据新增客户的fdId:{" + addCmd.getFdId() + "}找不到对应流程实例",new Exception())
        );
        ClientInfoDo clientInfoDo = clientInfoAssembler.toDO(addCmd);
        boolean bool = clientInfoRepository.updateById(clientInfoDo);
        if (!bool)log.error("重新发起流程:更新新增客户信息时失败!客户信息:{}",clientInfoDo);
        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");
        variables.remove("fdAffiliatedUser");



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
        log.info("==客户新增流程驳回后重新发起 响应:{}",response);

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
        timelineAddCmd.setBizId(String.valueOf(addCmd.getFdId()));
        timelineAddCmd.setReason("驳回后重新发起");
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
    public Response draftProcess(ClientInfoAddCmd addCmd) {
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
        addCmd.setPersonName(personDo.getPersonName());

//        Long fdId = clientInfoService.addThenReturnId(addCmd);

        ClientInfoDo clientInfoDo = clientInfoAssembler.toDO(addCmd);
        boolean bool = clientInfoRepository.updateById(clientInfoDo);
        if (!bool)log.error("发起流程:更新新增客户信息时失败!客户信息:{}",clientInfoDo);
        Long fdId=clientInfoDo.getFdId();

        Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

        variables.remove("approveUser");
        variables.remove("GROUPFULLCODE");
        variables.remove("personName");
        variables.remove("fdAffiliatedUser");

        StartProcessByCodeRequestDto
                dto = new StartProcessByCodeRequestDto();
        dto.setTitle("【市场管理】【"+addCmd.getFdName()+"】客户信息登记审批单");
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
        log.info("==客户新增发起流程 响应:{}",response);
        if (!response.isSuccess()){
            throw new RuntimeException("流程发起报错"+response);
        }

        LmcWorkflowDo lmcWorkflowDo = new LmcWorkflowDo();
//            lmcWorkflowDo.setId();
        lmcWorkflowDo.setCreatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setCreatedTime(LocalDateTime.now());
        lmcWorkflowDo.setUpdatedBy(addCmd.getCreatedBy());
        lmcWorkflowDo.setUpdatedTime(LocalDateTime.now());
        lmcWorkflowDo.setBizCode(BizCode.ADD_CLIENT.getValue()); /*业务类型*/
        lmcWorkflowDo.setBizId(fdId.toString());
        lmcWorkflowDo.setProcessCode("addClient");
        lmcWorkflowDo.setProcessInstanceId(response.getData());
        lmcWorkflowDo.setTitle("新增客户-"+addCmd.getFdName());
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

    private final ILogSheetService logSheetService;

    /**
     * 审批状态通过后，重新发起功能方法
     * @param addCmd 客户信息
     * @return 成功信息
     */
    @Override
    public Response approveProcess(ClientInfoAddCmd addCmd) {
        Boolean b = FunctionalUtil.verifyAndParseApproveUser(addCmd.getApproveUser());
        if (!b) {
            return Response.buildFailure("505","请指定审批人！");
        }

        String nextVersion = "";
        //通过map对象来找到关于FdId的所有数据
        Map map = new HashMap();
        map.put("clientId",addCmd.getFdId());
        List<LogSheetDto> searchByParam = logSheetService.searchByParam(map);
        log.info("searchByParam:{}",searchByParam);
        if(searchByParam.isEmpty() || searchByParam.size() ==0){
            log.info("进来了");
            nextVersion = "v1";
        }else {
            log.info("进来了222");
            //从多条数据中找到最新的一条
            LogSheetDto latestLogSheet = findLatestLogSheet(searchByParam);
            //拿到最新的版本号
            String version = latestLogSheet.getVersion();
            nextVersion = getNextVersion(version);
        }

        ClientInfoDto clientInfoDto = clientInfoService.get(addCmd.getFdId());

        LogSheetDo logSheetDo = new LogSheetDo();

        logSheetDo.setClientId(addCmd.getFdId().toString());
        logSheetDo.setUpdatedTime(LocalDateTime.now());
        logSheetDo.setUpdatePerson(addCmd.getCreatedByName());
        logSheetDo.setVersion(nextVersion);

        logSheetDo.setClientInformation(JSONUtil.parse(clientInfoDto).toString());

        boolean save = logSheetRepository.save(logSheetDo);

        // 数据权限 添加创建人字段(保存创建人personCode)
        MdmPersonDo personDo = mdmPersonUtils
                .checkIfPersonCodeExist(addCmd.getCreatedBy());

        if(save){
            lmcWorkflowService.deleteByBizId(addCmd.getFdId().toString());

            ClientInfoDo clientInfoDo = clientInfoAssembler.toDO(addCmd);

            log.info("====客户信息为：",addCmd);
            boolean bool = clientInfoRepository.updateById(clientInfoDo);

            Map<String, Object> variables = BeanUtil.beanToMap(addCmd);

            variables.remove("approveUser");
            variables.remove("GROUPFULLCODE");
            variables.remove("personName");
            variables.remove("fdAffiliatedUser");

            StartProcessByCodeRequestDto
                    dto = new StartProcessByCodeRequestDto();
            dto.setTitle("【市场管理】【"+addCmd.getFdName()+"】客户信息登记审批单");
            dto.setCode(flowCode);  // 流程编码
            dto.setTenantId("1");   // 租户id
            dto.setUserNo(addCmd.getCreatedBy());   // 用户编码
            dto.setUserFullName(personDo.getPersonName());
            dto.setBusinessKey(addCmd.getFdId().toString());    //
            dto.setVariables(variables);

            dto.setApproveUser(addCmd.getApproveUser());

            log.info("==流程发起信息:{}",dto);
            SingleResponse<String>
                    response = rpi.startProcessByCode(dto);
            log.info("==客户新增发起流程 响应:{}",response);
            if (!response.isSuccess()){
                throw new RuntimeException("流程发起报错"+response);
            }

            LmcWorkflowDo lmcWorkflowDo = new LmcWorkflowDo();
            lmcWorkflowDo.setCreatedBy(addCmd.getCreatedBy());
            lmcWorkflowDo.setCreatedTime(LocalDateTime.now());
            lmcWorkflowDo.setUpdatedBy(addCmd.getCreatedBy());
            lmcWorkflowDo.setUpdatedTime(LocalDateTime.now());
            lmcWorkflowDo.setBizCode(BizCode.ADD_CLIENT.getValue()); /*业务类型*/
            lmcWorkflowDo.setBizId(addCmd.getFdId().toString());
            lmcWorkflowDo.setProcessCode("addClient");
            lmcWorkflowDo.setProcessInstanceId(response.getData());
            lmcWorkflowDo.setTitle("新增客户-"+addCmd.getFdName());
            lmcWorkflowDo.setAppId(appId);
            lmcWorkflowDo.setSubmitterCode(addCmd.getCreatedBy());
            lmcWorkflowDo.setSubmitterName(personDo.getPersonName());
            lmcWorkflowDo.setSubmitTime(LocalDateTime.now());
            lmcWorkflowDo.setWorkflowStatus(WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_ACTIVE.getValue());
            lmcWorkflowDo.setIsDeleted(0);
            lmcWorkflowDo.setCreateUserCode(addCmd.getCreatedBy());
            lmcWorkflowDo.setCreateUserName(personDo.getPersonName());
            lmcWorkflowDo.setCreateTime(LocalDateTime.now());
            lmcWorkflowDo.setUpdateUserCode(addCmd.getCreatedBy());
            lmcWorkflowDo.setUpdateUserName(personDo.getPersonName());
            lmcWorkflowDo.setUpdateTime(LocalDateTime.now());

            lmcWorkflowRepository.save(lmcWorkflowDo);
            log.info("====workflow添加成功:{}",lmcWorkflowDo);

            if (null == lmcWorkflowDo.getId()) {
                return Response.buildFailure("506","找不到id！");
            }

            //给流程日志添加信息
            RequestLogDo requestLogDo = new RequestLogDo();
            requestLogDo.setRequestUrl("url");
            requestLogDo.setCtime(LocalDateTime.now());
            requestLogDo.setRequestParam(dto.toString());
            requestLogDo.setResponseParam(response.getData());

            requestLogRepository.save(requestLogDo);
        }
        return Response.buildSuccess();
    }

    /**
     * 获得最新的版本方法
     * @param currentVersion 当前版本
     * @return 最新版本
     */
    private String getNextVersion(String currentVersion) {
        //如果是第一次返回v1
        if (currentVersion == null || currentVersion.isEmpty()) {
            return "v1";
        }

        //截取v后面的数字加一
        int versionNumber = Integer.parseInt(currentVersion.substring(1));
        return "v" + (versionNumber + 1);
    }

    /**
     * 根据多个历史记录数据拿到最新的历史记录数据
     * @param logSheets 多个历史记录数据
     * @return 最新的历史记录数据
     */
    private LogSheetDto findLatestLogSheet(List<LogSheetDto> logSheets) {
        // 如果列表为空，返回 null
        if (logSheets.isEmpty()) {
            return null;
        }

        LogSheetDto latestLogSheet = logSheets.get(0);

        for (LogSheetDto logSheet : logSheets) {
            //每次判断更新时间是否在第一个时间之后
            if (logSheet.getUpdatedTime().isAfter(latestLogSheet.getUpdatedTime())) {
                // 更新最新的对象
                latestLogSheet = logSheet;
            }
        }
        return latestLogSheet;
    }

    @Override
    public void handleStart(ProcessCall processCall) {
        System.out.println("SubmitAddClientServiceImpl");
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

    /**
     * 流程撤回
     */
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


}
