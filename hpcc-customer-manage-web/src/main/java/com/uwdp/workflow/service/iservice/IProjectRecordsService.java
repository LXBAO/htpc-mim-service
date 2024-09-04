package com.uwdp.workflow.service.iservice;

import com.alibaba.cola.dto.Response;
import com.uwdp.module.api.vo.cmd.ProjectRecordsAddCmd;

import java.util.Map;

public interface IProjectRecordsService extends IBizProcessHandler{
    Response add(ProjectRecordsAddCmd addCmd);
    /**
     * 驳回后重启流程
     * @param addCmd
     */
    Response reStartProcess(ProjectRecordsAddCmd addCmd);

    /**
     * 驳回后重启流程
     * @param addCmd
     */
    Response draftProcess(ProjectRecordsAddCmd addCmd);
}
