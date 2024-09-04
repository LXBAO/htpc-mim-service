package com.uwdp.workflow.controller;

import com.alibaba.cola.dto.Response;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.vo.cmd.ProjectSigningAddCmd;
import com.uwdp.workflow.service.SubmitAddProjectSigningServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hpcc-customer-manage/v1_0/workflow/project-signing")
public class DoSubmitAddProjectSigningController {
    private final SubmitAddProjectSigningServiceImpl submitAddProjectSigningService;


    @PostMapping("/submit")
    @ApiOperation(value = "项目登记发起流程接口", notes = "submit")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated ProjectSigningAddCmd addCmd) {
        try {
            return submitAddProjectSigningService.add(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/reStartProcess")
    @ApiOperation(value = "流程驳回后重新推送流程接口", notes = "reStartProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response reStartProcess(@RequestBody @Validated ProjectSigningAddCmd addCmd) {
        try {
            return submitAddProjectSigningService.reStartProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/draftProcess")
    @ApiOperation(value = "草稿状态推送流程接口", notes = "draftProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response draftProcess(@RequestBody @Validated ProjectSigningAddCmd addCmd) {
        try {
            return submitAddProjectSigningService.draftProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
