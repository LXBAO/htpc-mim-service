package com.uwdp.workflow.service.iservice;

import com.alibaba.cola.dto.Response;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.gientech.lcds.workflow.sdk.core.call.dto.TaskCall;
import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanAddCmd;

/**
 * 公关计划流程发起
 */
public interface ISubmitAddVisitPlanService  extends IBizProcessHandler{
    /**
     * 新增
     */
    Response add(VisitPlanAddCmd addCmd);
    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response reStartProcess(VisitPlanAddCmd addCmd);

    /**
     * 根据流程回调的是否参会状态更新业务表单的是否参会状态
     * @param taskCall
     */
    public void updateIfConference(TaskCall taskCall);

    /**
     * 草稿状态推送流程
     * @param addCmd
     */
    public Response draftProcess(VisitPlanAddCmd addCmd);
}
