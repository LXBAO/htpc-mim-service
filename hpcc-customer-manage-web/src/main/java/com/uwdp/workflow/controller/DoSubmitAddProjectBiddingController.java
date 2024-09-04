package com.uwdp.workflow.controller;

import com.alibaba.cola.dto.Response;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.vo.cmd.ProjectBiddingAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectRecordsAddCmd;
import com.uwdp.workflow.service.SubmitAddProjectBiddingServiceImpl;
import com.uwdp.workflow.service.SubmitAddProjectRecordsServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hpcc-customer-manage/v1_0/workflow/project-bidding")
public class DoSubmitAddProjectBiddingController {
    private final SubmitAddProjectBiddingServiceImpl submitAddProjectBiddingService;


    @PostMapping("/submit")
    @ApiOperation(value = "项目投标发起流程接口", notes = "submit")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated ProjectBiddingAddCmd addCmd) {
        try {
            return submitAddProjectBiddingService.add(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/reStartProcess")
    @ApiOperation(value = "流程驳回后重新推送流程接口", notes = "reStartProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response reStartProcess(@RequestBody @Validated ProjectBiddingAddCmd addCmd) {
        try {
            return submitAddProjectBiddingService.reStartProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/draftProcess")
    @ApiOperation(value = "草稿状态推送流程接口", notes = "draftProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response draftProcess(@RequestBody @Validated ProjectBiddingAddCmd addCmd) {
        try {
            return submitAddProjectBiddingService.draftProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
