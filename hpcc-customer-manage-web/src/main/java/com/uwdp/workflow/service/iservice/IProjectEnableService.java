package com.uwdp.workflow.service.iservice;

import com.alibaba.cola.dto.Response;
import com.uwdp.module.api.vo.cmd.ProjectEnableAddCmd;

public interface IProjectEnableService extends IBizProcessHandler{
    Response add(ProjectEnableAddCmd addCmd);
    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response reStartProcess(ProjectEnableAddCmd addCmd);

    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response draftProcess(ProjectEnableAddCmd addCmd);
}
