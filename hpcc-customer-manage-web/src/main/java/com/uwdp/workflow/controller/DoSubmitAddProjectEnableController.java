package com.uwdp.workflow.controller;

import com.alibaba.cola.dto.Response;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.core.call.dto.TaskCall;
import com.uwdp.module.api.vo.cmd.ProjectEnableAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectEnableUpdateCmd;
import com.uwdp.module.api.vo.enums.ScoreTableFields;
import com.uwdp.workflow.service.SubmitAddProjectEnableServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hpcc-customer-manage/v1_0/workflow/project-Enable")
public class DoSubmitAddProjectEnableController {
    private final SubmitAddProjectEnableServiceImpl submitAddProjectEnableServiceImpl;

    private final com.uwdp.module.api.service.IProjectEnableService projectEnableService;

    @PostMapping("/submit")
    @ApiOperation(value = "项目赋能发起流程接口", notes = "submit")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated ProjectEnableAddCmd addCmd) {
        try {
            return submitAddProjectEnableServiceImpl.add(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/reStartProcess")
    @ApiOperation(value = "流程驳回后重新推送流程接口", notes = "reStartProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response reStartProcess(@RequestBody @Validated ProjectEnableAddCmd addCmd) {
        try {
            return submitAddProjectEnableServiceImpl.reStartProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/draftProcess")
    @ApiOperation(value = "草稿状态推送流程接口", notes = "draftProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response draftProcess(@RequestBody @Validated ProjectEnableAddCmd addCmd) {
        try {
            return submitAddProjectEnableServiceImpl.draftProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


    @PostMapping("/updateScoreList/taskCall")
    @ApiOperation(value = "任务回调修改ScoreList字段", notes="ScoreList")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response updateScoreList(@RequestBody TaskCall taskCall) {
        try {
            log.info(">>>>>>>>>>任务回调修改ScoreList字段接收到回调参数TaskCall:{}",taskCall);
            // 仅在同意时执行
            if (null != taskCall && null != taskCall.getAgree() && taskCall.getAgree()) {
                projectEnableService.updateScoreList(taskCall);
            }
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

}
