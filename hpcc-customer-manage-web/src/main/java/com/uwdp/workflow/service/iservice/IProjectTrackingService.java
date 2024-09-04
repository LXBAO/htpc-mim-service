package com.uwdp.workflow.service.iservice;

import com.alibaba.cola.dto.Response;
import com.uwdp.module.api.vo.cmd.ProjectTrackingAddCmd;

public interface IProjectTrackingService extends IBizProcessHandler{

    Response add(ProjectTrackingAddCmd addCmd);
    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response reStartProcess(ProjectTrackingAddCmd addCmd);

    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response draftProcess(ProjectTrackingAddCmd addCmd);
}
