package com.uwdp.module.api.service;

/**
 * 企业微信服务类
 */
public interface IQiYeWorkService {

    Object getToken();

    /**
     * 获取用户ID
     * @return
     */
    String getUserId(String phone);

    /**
     * 向用户发送文本消息
     * @param phones
     * @return
     */
    Object sendTextMsg(String phones);

    /**
     * 向用户发送图文消息
     * @param proInstId
     * @param taskId
     * @param userNo
     * @param userFullName
     * @return
     */
    Object sendTextcard(String proInstId, String taskId, String type, String userNo, String userFullName);

    /**
     * 向用户发送小程序应用消息
     * @return
     */
    Object sendMiniprogramNoticeMsg(String phones);
}
