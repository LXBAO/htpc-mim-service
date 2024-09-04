package com.uwdp.module.web.controller.v2;

import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.module.api.service.ITaskService;
import com.uwdp.utils.JwtUtil;
import com.uwdp.utils.NoToken;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/task")
@Api(tags = "任务controller")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "任务controller")
@Validated
@Slf4j
@Configuration
public class TaskControllerV2 {

    @Autowired
    private ITaskService taskService;

    /**
     * 移动端获取用户待办任务
     *
     * @param userNo
     * @param userFullName
     * @return
     */
    @GetMapping("/queryTodoTask")
    public Object queryTodoTask(@RequestParam(name = "userNo") String userNo,
                                @RequestParam(name = "userFullName") String userFullName,
                                @RequestParam(name = "pageSize") Integer pageSize,
                                @RequestParam(name = "pageIndex") Integer pageIndex) {
        return taskService.queryTodoTask(userNo, userFullName, pageSize, pageIndex);
    }

    /**
     * 移动端获取用户我的申请任务
     *
     * @param userNo
     * @param userFullName
     * @return
     */
    @GetMapping("/queryMyProcess")
    public Object queryMyProcess(@RequestParam(name = "userNo") String userNo,
                                 @RequestParam(name = "userFullName") String userFullName,
                                 @RequestParam(name = "pageSize") Integer pageSize,
                                 @RequestParam(name = "pageIndex") Integer pageIndex) {
        return taskService.queryMyProcess(userNo, userFullName, pageSize, pageIndex);
    }

    /**
     * 移动端获取用户已办任务
     *
     * @param userNo
     * @param userFullName
     * @return
     */
    @GetMapping("/queryDoneTask")
    public Object queryDoneTask(@RequestParam(name = "userNo") String userNo,
                                @RequestParam(name = "userFullName") String userFullName,
                                @RequestParam(name = "pageSize") Integer pageSize,
                                @RequestParam(name = "pageIndex") Integer pageIndex) {
        return taskService.queryDoneTask(userNo, userFullName, pageSize, pageIndex);
    }

    /**
     * 根据taskId获取流程审批记录
     *
     * @param userNo
     * @param userFullName
     * @param taskId
     * @return
     */
    @NoToken
    @GetMapping("/queryTaskLog")
    public Object queryTaskLog(@RequestParam(name = "userNo") String userNo,
                               @RequestParam(name = "userFullName") String userFullName,
                               @RequestParam(name = "taskId") String taskId) {
        return taskService.queryTaskLog(userNo, userFullName, taskId);
    }

    /**
     * 根据流程类型返回不同的数据
     *
     * @param processDefinitionKey
     * @param businessKey
     * @return
     */
    @NoToken
    @GetMapping("/queryByType")
    public Object queryByType(@RequestParam(name = "processDefinitionKey") String processDefinitionKey, @RequestParam("businessKey") Long businessKey) {
        return taskService.queryByType(processDefinitionKey, businessKey);
    }

    /**
     * 获取表单数据
     *
     * @param proInstId
     * @param taskId
     * @return
     */
    @NoToken
    @GetMapping("/getFormDate")
    public Object getFormDate(@RequestParam(name = "proInstId") String proInstId, @RequestParam("taskId") String taskId) {
        return taskService.getFormDate(proInstId, taskId);
    }


    /**
     * 获取下一个节点的候选人
     *
     * @param userNo
     * @param userFullName
     * @param taskId
     * @return
     */
    @NoToken
    @GetMapping("/nextTaskCandidate")
    public Object nextTaskCandidate(@RequestParam(name = "userNo") String userNo,
                                    @RequestParam(name = "userFullName") String userFullName,
                                    @RequestParam(name = "taskId") String taskId) {
        return taskService.nextTaskCandidate(userNo, userFullName, taskId);
    }

    /**
     * 流程审批通过
     *
     * @param userNo
     * @param userFullName
     * @param taskId
     * @param comment           审批备注
     * @param nextCandidateUser 下一节点候选人
     * @param nextTaskId        下一节点任务ID
     * @return
     */
    @NoToken
    @GetMapping("/complete")
    public Object complete(@RequestParam(name = "userNo") String userNo,
                           @RequestParam(name = "userFullName") String userFullName,
                           @RequestParam(name = "taskId") String taskId,
                           @RequestParam(name = "comment") String comment,
                           @RequestParam(name = "nextCandidateUser") String nextCandidateUser,
                           @RequestParam(name = "nextTaskId") String nextTaskId) {
        return taskService.complete(userNo, userFullName, taskId, comment, nextCandidateUser, nextTaskId);
    }

    /**
     * 流程驳回
     * @param currentTaskId 当前任务id
     * @param processInstanceId 实例流程Id
     * @param rejectComment 退回意见
     * @return
     */
    @NoToken
    @GetMapping("/back")
    public Object back(@RequestParam(name = "userNo")String userNo,
                       @RequestParam(name = "userFullName")String userFullName,
                       @RequestParam(name = "currentTaskId")String currentTaskId,
                       @RequestParam(name = "processInstanceId")String processInstanceId,
                       @RequestParam(name = "rejectComment")String rejectComment){
        return taskService.back(userNo, userFullName, currentTaskId, processInstanceId, rejectComment);
    }


    /**
     * 转办
     * @param taskId
     * @param candidateUserName
     * @param candidateUserNo
     * @return
     */
    @NoToken
    @GetMapping("/assign")
    public Object assign(@RequestParam(name = "taskId")String taskId,
                         @RequestParam(name = "userNo")String userNo,
                         @RequestParam(name = "userFullName")String userFullName,
                         @RequestParam(name = "candidateUserName")String candidateUserName,
                         @RequestParam(name = "candidateUserNo")String candidateUserNo){
        return taskService.assign(taskId, userNo, userFullName, candidateUserName, candidateUserNo);
    }

    /**
     * 获取token
     * @return
     */
    @NoToken
    @GetMapping("/getToken")
    public Object getToken(){
        Map<String, String> userTokenMap = new HashMap<>();
        userTokenMap.put("mobile", "18373019955");
        return JwtUtil.getToken(userTokenMap);
    }

}
