package com.uwdp.module.api.service;

public interface ITaskService {
    /**
     * 移动端获取用户待办任务
     *
     * @param userNo
     * @param userFullName
     * @return
     */
    Object queryTodoTask(String userNo,
                         String userFullName,
                         Integer pageSize,
                         Integer pageIndex);

    /**
     * 移动端获取用户我的申请任务
     *
     * @param userNo
     * @param userFullName
     * @return
     */
    Object queryMyProcess(String userNo,
                          String userFullName,
                          Integer pageSize,
                          Integer pageIndex);


    /**
     * 移动端获取用户已办任务
     *
     * @param userNo
     * @param userFullName
     * @return
     */
    Object queryDoneTask(String userNo,
                         String userFullName,
                         Integer pageSize,
                         Integer pageIndex);

    /**
     * 根据taskId获取流程审批记录
     *
     * @param userNo
     * @param userFullName
     * @param taskId
     * @return
     */
    Object queryTaskLog(String userNo,
                        String userFullName,
                        String taskId);


    /**
     * 根据流程类型返回不同的数据
     *
     * @param processDefinitionKey
     * @param businessKey
     * @return
     */
    Object queryByType(String processDefinitionKey, Long businessKey);

    /**
     * 获取下一个节点的候选人
     *
     * @param userNo
     * @param userFullName
     * @param taskId
     * @return
     */
    Object nextTaskCandidate(String userNo,
                             String userFullName,
                             String taskId);

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
    Object complete(String userNo,
                    String userFullName,
                    String taskId,
                    String comment,
                    String nextCandidateUser,
                    String nextTaskId);

    /**
     * 流程驳回
     * @param currentTaskId 当前任务id
     * @param processInstanceId 实例流程Id
     * @param rejectComment 退回意见
     * @return
     */
    Object back(String userNo,
                String userFullName,
                String currentTaskId,
                String processInstanceId,
                String rejectComment);

    /**
     * 流程转办
     * @param taskId
     * @param candidateUserName
     * @param candidateUserNo
     * @return
     */
    Object assign(String taskId,
                  String userNo,
                  String userFullName,
                  String candidateUserName,
                  String candidateUserNo);

    /**
     * 获取表单数据
     * @param proInstId
     * @param taskId
     * @return
     */
    Object getFormDate(String proInstId, String taskId);
}
