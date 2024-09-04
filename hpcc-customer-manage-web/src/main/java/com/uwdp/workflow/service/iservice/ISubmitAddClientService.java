package com.uwdp.workflow.service.iservice;

import com.alibaba.cola.dto.Response;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;

/**
 * 客户新增发起流程
 */
public interface ISubmitAddClientService extends IBizProcessHandler  {

    /**
     * 新增
     */
    Response add(ClientInfoAddCmd addCmd);

    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response reStartProcess(ClientInfoAddCmd addCmd);

    /**
     * 草稿状态提交流程
     * @param addCmd
     */
    public Response draftProcess(ClientInfoAddCmd addCmd);

    /**
     * 审批状态提交流程
     * @param addCmd
     */
    public Response approveProcess(ClientInfoAddCmd addCmd);
}
