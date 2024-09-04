package com.uwdp.workflow.controller;

import com.alibaba.cola.dto.Response;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.core.call.dto.CallBackTime;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagUpdateCmd;
import com.uwdp.workflow.enums.BizCode;
import com.uwdp.workflow.service.SubmitAddClientServiceImpl;
import com.uwdp.workflow.service.SubmitAddMarketDmpTagServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hpcc-customer-manage/v1_0/workflow/market")
public class DoSubmitAddMarketDmpTagController {

    private final SubmitAddMarketDmpTagServiceImpl submitAddMarketDmpTagService;

    @PostMapping("/submit")
    @ApiOperation(value = "新增客户发起流程接口", notes = "submit")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated MarketDmpTagAddCmd addCmd) {
        try {
            return submitAddMarketDmpTagService.add(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
    @PostMapping("/reStartProcess")
    @ApiOperation(value = "新增客户流程驳回后重新推送流程接口", notes = "reStartProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response reStartProcess(@RequestBody @Validated MarketDmpTagUpdateCmd addCmd) {
        try {
            return submitAddMarketDmpTagService.reStartProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/draftProcess")
    @ApiOperation(value = "草稿状态推送流程接口", notes = "draftProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response draftProcess(@RequestBody @Validated MarketDmpTagUpdateCmd addCmd) {
        try {
            return submitAddMarketDmpTagService.draftProcess(addCmd);
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
                    submitAddMarketDmpTagService.handlePass(processCall);
                }else if(CallBackTime.PROCESS_BACK.equals(callBackTime)) {
                    submitAddMarketDmpTagService.handleBack(processCall);
                }
            }
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


}
