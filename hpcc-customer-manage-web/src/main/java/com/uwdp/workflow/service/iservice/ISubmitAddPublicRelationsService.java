package com.uwdp.workflow.service.iservice;

import com.alibaba.cola.dto.Response;
import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;

/**
 * 公关反馈流程发起
 */
public interface ISubmitAddPublicRelationsService extends IBizProcessHandler {
    /**
     * 新增
     */
    Response add(PublicRelationsAddCmd addCmd);

    /**
     * 驳回后重启流程
     * @param addCmd
     */
    public Response reStartProcess(PublicRelationsAddCmd addCmd);

    /**
     * 草稿状态推送流程
     * @param addCmd
     */
    public Response draftProcess(PublicRelationsAddCmd addCmd);
}
