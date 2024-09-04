package com.uwdp.workflow.service.iservice;

import com.alibaba.cola.dto.Response;
import com.uwdp.module.api.vo.cmd.MarketDmpTagAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagUpdateCmd;

public interface IMarketDmpTagService extends IBizProcessHandler{
    /**
     * 新增
     */
    Response add(MarketDmpTagAddCmd addCmd);

    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response reStartProcess(MarketDmpTagUpdateCmd addCmd);

    /**
     * 草稿状态提交流程
     * @param addCmd
     */
    public Response draftProcess(MarketDmpTagUpdateCmd addCmd);
}
