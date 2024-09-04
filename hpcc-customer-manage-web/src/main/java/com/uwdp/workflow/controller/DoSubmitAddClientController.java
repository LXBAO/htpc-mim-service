package com.uwdp.workflow.controller;

import com.gientech.lcds.workflow.sdk.core.call.dto.CallBackTime;

import com.alibaba.cola.dto.Response;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.call.event.handler.process.impl.ComposedProcessCallEventHandler;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.gientech.lcds.workflow.sdk.core.call.event.ProcessCallEvent;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.biz.infrastructure.repository.LmcWorkflowRepository;
import com.uwdp.workflow.enums.BizCode;
import com.uwdp.workflow.handler.WorkflowprocessCallHandler;
import com.uwdp.workflow.service.AddClientProcessCallService;
import com.uwdp.workflow.service.iservice.ISubmitAddClientService;
import com.uwdp.workflow.service.SubmitAddClientServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hpcc-customer-manage/v1_0/workflow/client")
public class DoSubmitAddClientController {

    private final SubmitAddClientServiceImpl submitAddClientService;

    @PostMapping("/submit")
    @ApiOperation(value = "新增客户发起流程接口", notes = "submit")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated ClientInfoAddCmd addCmd) {
        try {
            return submitAddClientService.add(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
    @PutMapping("/reStartProcess")
    @ApiOperation(value = "新增客户流程驳回后重新推送流程接口", notes = "reStartProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response reStartProcess(@RequestBody @Validated ClientInfoAddCmd addCmd) {
        try {
            return submitAddClientService.reStartProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/draftProcess")
    @ApiOperation(value = "草稿状态推送流程接口", notes = "draftProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response draftProcess(@RequestBody @Validated ClientInfoAddCmd addCmd) {
        try {
            return submitAddClientService.draftProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/approveProcess")
    @ApiOperation(value = "审批通过重新发起推送流程接口", notes = "approveProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response approveProcess(@RequestBody @Validated ClientInfoAddCmd addCmd) {
        try {
            return submitAddClientService.approveProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/addClientCallback")
    @ApiOperation(value = "流程回调", notes = "WORKFLOW")
    @OperationLog(type = OperateTypeEnum.OTHER)
    public Response processCall(@RequestBody ProcessCall processCall) {
        try {
            if (Objects.isNull(processCall)) {
                log.warn("流程[同意|驳回|否决]回调>>>回调数据为空！");
                return Response.buildFailure("501", "回调数据为空！");
            }
            CallBackTime callBackTime = processCall.getCallBackTime();
            String processKey = processCall.getProcessKey();
            if (BizCode.ADD_CLIENT.getValue().equals(processKey)){
                    if(CallBackTime.PROCESS_PASS.equals(callBackTime)) {
                        submitAddClientService.handlePass(processCall);
                    }else if(CallBackTime.PROCESS_BACK.equals(callBackTime)) {
                        submitAddClientService.handleBack(processCall);
                    }
            }
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


}
