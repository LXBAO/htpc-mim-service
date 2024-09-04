package com.uwdp.workflow.service.iservice;

import com.alibaba.cola.dto.Response;
import com.uwdp.module.api.vo.cmd.ProjectBiddingAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectImplementAddCmd;

public interface IProjectImplementService extends IBizProcessHandler{
    Response add(ProjectImplementAddCmd addCmd);
    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response reStartProcess(ProjectImplementAddCmd addCmd);

    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response draftProcess(ProjectImplementAddCmd addCmd);
}
