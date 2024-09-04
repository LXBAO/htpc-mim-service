package com.uwdp.workflow.service.iservice;

import com.alibaba.cola.dto.Response;
import com.uwdp.module.api.vo.cmd.ProjectSuspensionAddCmd;

public interface IProjectSuspensionService extends IBizProcessHandler{
    Response add(ProjectSuspensionAddCmd addCmd);
    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response reStartProcess(ProjectSuspensionAddCmd addCmd);

    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response draftProcess(ProjectSuspensionAddCmd addCmd);
}
