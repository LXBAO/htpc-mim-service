package com.uwdp.module.biz.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.gientech.lcds.workflow.sdk.core.runtime.*;
import com.gientech.lcds.workflow.sdk.formdata.service.FormDataService;
import com.gientech.lcds.workflow.sdk.runtime.procinst.service.RuntimeProcInstService;
import com.gientech.lcds.workflow.sdk.runtime.task.service.RuntimeTaskService;
import com.uwdp.module.api.service.*;
import com.uwdp.module.biz.utils.BizCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.shadow.org.terracotta.offheapstore.disk.paging.MappedPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {


    @Autowired
    private RuntimeTaskService runtimeTaskService;

    @Autowired
    private RuntimeProcInstService runtimeProcInstService;

    @Autowired
    private IClientInfoService clientInfoService;

    @Autowired
    private IVisitPlanService visitPlanService;

    @Autowired
    private IPublicRelationsService publicRelationsService;

    @Autowired
    private IProjectRecordsService projectRecordsService;

    @Autowired
    private IProjectBiddingService projectBiddingService;

    @Autowired
    private IProjectTrackingService projectTrackingService;

    @Autowired
    private IProjectSigningService projectSigningService;

    @Autowired
    private IWinTheBidService winTheBidService;

    @Autowired
    private IProjectImplementService projectImplementService;

    @Autowired
    private IMarketDmpTagService marketDmpTagService;

    @Autowired
    private IProjectSuspensionService projectSuspensionService;

    @Autowired
    private IProjectEnableService projectEnableService;

    @Autowired
    private FormDataService formDataService;

    @Value("${hpcc-mim-server.appId}")
    public String appId;

    @Value("${hpcc-mim-server.tenantId}")
    public String tenantId;

    @Override
    public Object queryTodoTask(String userNo, String userFullName, Integer pageSize, Integer pageIndex) {
        TaskTodoRequestDto taskTodoRequestDto = new TaskTodoRequestDto();
//        taskTodoRequestDto.setAppId(appId);
        taskTodoRequestDto.setUserNo(userNo);
        taskTodoRequestDto.setUserFullName(userFullName);
        taskTodoRequestDto.setTenantId(tenantId);
        taskTodoRequestDto.setPageIndex(pageIndex);
        taskTodoRequestDto.setPageSize(pageSize);
        Response response = runtimeTaskService.todoTask(taskTodoRequestDto);
        log.info("【移动端获取用户待办任务】请求参数：{},响应：{}", taskTodoRequestDto, response);
        return response;
    }

    @Override
    public Object queryMyProcess(String userNo, String userFullName, Integer pageSize, Integer pageIndex) {
        TaskTodoRequestDto taskTodoRequestDto = new TaskTodoRequestDto();
//        taskTodoRequestDto.setAppId(appId);
        taskTodoRequestDto.setUserNo(userNo);
        taskTodoRequestDto.setUserFullName(userFullName);
        taskTodoRequestDto.setTenantId(tenantId);
        taskTodoRequestDto.setPageIndex(pageIndex);
        taskTodoRequestDto.setPageSize(pageSize);
        Response response = runtimeProcInstService.myProcess(taskTodoRequestDto);
        log.info("【移动端获取用户我的申请任务】请求参数：{},响应：{}", taskTodoRequestDto, response);
        return response;
    }

    @Override
    public Object queryDoneTask(String userNo, String userFullName, Integer pageSize, Integer pageIndex) {
        TaskDoneRequestDto taskDoneRequestDto = new TaskDoneRequestDto();
//        taskDoneRequestDto.setAppId(appId);
        taskDoneRequestDto.setUserNo(userNo);
        taskDoneRequestDto.setUserFullName(userFullName);
        taskDoneRequestDto.setTenantId(tenantId);
        taskDoneRequestDto.setPageIndex(pageIndex);
        taskDoneRequestDto.setPageSize(pageSize);
        Response response = runtimeTaskService.doneTask(taskDoneRequestDto);
        log.info("【移动端获取用户已办任务】请求参数：{},响应：{}", taskDoneRequestDto, response);
        return response;
    }

    @Override
    public Object queryTaskLog(String userNo, String userFullName, String taskId) {
        TaskDoneRequestDto taskDoneRequestDto = new TaskDoneRequestDto();
//        taskDoneRequestDto.setAppId(appId);
        taskDoneRequestDto.setUserNo(userNo);
        taskDoneRequestDto.setUserFullName(userFullName);
        taskDoneRequestDto.setTenantId(tenantId);
        Response response = runtimeProcInstService.processLog(taskId, taskDoneRequestDto);
        log.info("【移动端根据taskId获取流程审批记录】请求参数：{},响应：{}", taskDoneRequestDto, response);
        return response;
    }

    @Override
    public Object queryByType(String processDefinitionKey, Long businessKey) {
        Object obj;
        if (BizCodeUtil.ADD_CLIENT.getValue().equals(processDefinitionKey)) {
            obj = clientInfoService.get(businessKey);
        } else if (BizCodeUtil.ADD_FEEDBACK.getValue().equals(processDefinitionKey)) {
            obj =  publicRelationsService.get(businessKey);
        } else if (BizCodeUtil.ADD_VISIT_PLAN.getValue().equals(processDefinitionKey)) {
            obj =  visitPlanService.get(businessKey);
        } else if (BizCodeUtil.ADD_PROJECT_RECORDS.getValue().equals(processDefinitionKey)) {
            obj =  projectRecordsService.get(businessKey);
        } else if (BizCodeUtil.ADD_PROJECT_BIDDING.getValue().equals(processDefinitionKey)) {
            obj =  projectBiddingService.get(businessKey);
        } else if (BizCodeUtil.ADD_PROJECT_TRACKING.getValue().equals(processDefinitionKey)) {
            obj =  projectTrackingService.get(businessKey);
        } else if (BizCodeUtil.ADD_PROJECT_SIGNING.getValue().equals(processDefinitionKey)) {
            obj =  projectSigningService.get(businessKey);
        } else if (BizCodeUtil.ADD_WIN_THE_BID.getValue().equals(processDefinitionKey)) {
            obj =  winTheBidService.get(businessKey);
        } else if (BizCodeUtil.ADD_PROJECT_IMPLEMENT.getValue().equals(processDefinitionKey)) {
            obj =  projectImplementService.get(businessKey);
        } else if (BizCodeUtil.ADD_MARKET_DMP_TAG.getValue().equals(processDefinitionKey)) {
            obj =  marketDmpTagService.get(businessKey);
        } else if (BizCodeUtil.ADD_PROJECT_SUSPENSION.getValue().equals(processDefinitionKey)) {
            obj =  projectSuspensionService.get(businessKey);
        } else if (BizCodeUtil.ADD_PROJECT_ENABLE.getValue().equals(processDefinitionKey)) {
            obj =  projectEnableService.get(businessKey);
        } else {
            return PageResponse.buildFailure("500", "processDefinitionKey异常");
        }
        if(obj != null)
            return obj;
        else
            return PageResponse.buildFailure("500", "businessKey异常");
    }

    @Override
    public Object nextTaskCandidate(String userNo, String userFullName, String taskId) {
        BaseDto baseDto = new BaseDto();
        baseDto.setUserFullName(userFullName);
        baseDto.setUserNo(userNo);
        baseDto.setTenantId(tenantId);
//        baseDto.setAppId(appId);
        Response response = runtimeTaskService.nextTaskCandidate(taskId, baseDto);
        log.info("【移动端获取下一个节点的候选人】请求参数：{},TaskId:{},响应：{}", baseDto, taskId, response);
        return response;
    }

    @Override
    public Object complete(String userNo, String userFullName, String taskId, String comment, String nextCandidateUser, String nextTaskId) {
        TaskCompleteRequestDto taskCompleteRequestDto = new TaskCompleteRequestDto();
//        taskCompleteRequestDto.setAppId(appId);
        taskCompleteRequestDto.setTenantId("1");
        taskCompleteRequestDto.setUserNo(userNo);
        taskCompleteRequestDto.setUserFullName(userFullName);
        taskCompleteRequestDto.setTaskId(taskId);
        taskCompleteRequestDto.setComment(comment);
        taskCompleteRequestDto.setNextCandidateUser(nextCandidateUser);
        taskCompleteRequestDto.setNextTaskId(nextTaskId);
        Response response = runtimeTaskService.complete(taskCompleteRequestDto);
        log.info("【流程审批通过】请求参数：{},响应：{}", taskCompleteRequestDto, response);
        return response;
    }

    @Override
    public Object back(String userNo, String userFullName, String currentTaskId, String processInstanceId, String rejectComment) {
        TaskBackRequestDto taskBackRequestDto = new TaskBackRequestDto();
//        taskBackRequestDto.setAppId(appId);
        taskBackRequestDto.setTenantId(tenantId);
        taskBackRequestDto.setUserNo(userNo);
        taskBackRequestDto.setUserFullName(userFullName);
        taskBackRequestDto.setCurrentTaskId(currentTaskId);
        taskBackRequestDto.setProcessInstanceId(processInstanceId);
        taskBackRequestDto.setRejectComment(rejectComment);
        Response response = runtimeTaskService.back(taskBackRequestDto);
        log.info("【流程审批通过】请求参数：{},响应：{}", taskBackRequestDto, response);
        return response;
    }

    @Override
    public Object assign(String taskId,
                         String userNo,
                         String userFullName,
                         String candidateUserName,
                         String candidateUserNo) {
        TaskAssignRequestDto taskAssignRequestDto = new TaskAssignRequestDto();
//        taskAssignRequestDto.setAppId(appId);
        taskAssignRequestDto.setTaskId(taskId);
        taskAssignRequestDto.setTenantId(tenantId);
        taskAssignRequestDto.setUserNo(userNo);
        taskAssignRequestDto.setUserFullName(userFullName);
        Map<String, String> map = new HashMap<>();
        map.put(candidateUserNo, candidateUserName);
        //taskAssignRequestDto.setUsers(map);
        log.info("【流程转办】请求参数：{}", taskAssignRequestDto);
        Response response = runtimeTaskService.assign(taskAssignRequestDto);
        log.info("【流程转办】请求参数：{},响应：{}", taskAssignRequestDto, response);
        return response;
    }

    @Override
    public Object getFormDate(String proInstId, String taskId) {
        return formDataService.getFormData(Long.parseLong(appId), proInstId, taskId);
    }
}
