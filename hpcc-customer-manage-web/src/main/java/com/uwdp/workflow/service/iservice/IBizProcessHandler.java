package com.uwdp.workflow.service.iservice;

import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.gientech.lcds.workflow.sdk.core.call.event.ProcessCallEvent;
import com.uwdp.module.api.vo.cmd.VisitPlanAddCmd;
import lombok.extern.slf4j.Slf4j;

public interface IBizProcessHandler {

    /**
     * 处理流程回调-发起流程
     * @param processCall
     */
    public void handleStart(ProcessCall processCall);
    /**
     * 处理流程回调-审核通过
     * @param processCall
     */
    public void handlePass(ProcessCall processCall);

    /**
     * 处理流程回调-流程驳回
     * @param processCall
     */
    public void handleBack(ProcessCall processCall);

    /**
     * 处理流程回调-流程撤回
     * @param processCall
     */
    public void handleRevoke(ProcessCall processCall);
}
