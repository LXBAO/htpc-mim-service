package com.uwdp.workflow.service.iservice;

import com.alibaba.cola.dto.Response;
import com.uwdp.module.api.vo.cmd.WinTheBidAddCmd;

public interface IWinTheBidService extends IBizProcessHandler{
    Response add(WinTheBidAddCmd addCmd);
    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response reStartProcess(WinTheBidAddCmd addCmd);

    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response draftProcess(WinTheBidAddCmd addCmd);
}
