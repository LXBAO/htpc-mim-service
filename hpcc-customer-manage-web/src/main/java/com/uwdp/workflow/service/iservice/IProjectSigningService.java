package com.uwdp.workflow.service.iservice;

import com.alibaba.cola.dto.Response;
import com.uwdp.module.api.vo.cmd.ProjectSigningAddCmd;

public interface IProjectSigningService extends IBizProcessHandler{
    Response add(ProjectSigningAddCmd addCmd);
    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response reStartProcess(ProjectSigningAddCmd addCmd);

    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response draftProcess(ProjectSigningAddCmd addCmd);
}
