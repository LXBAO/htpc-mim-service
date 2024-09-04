package com.uwdp.workflow.controller;

import com.alibaba.cola.dto.Response;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.call.event.handler.process.impl.ComposedProcessCallEventHandler;
import com.gientech.lcds.workflow.sdk.core.call.dto.CallBackTime;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.gientech.lcds.workflow.sdk.core.call.event.ProcessCallEvent;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;
import com.uwdp.module.biz.infrastructure.repository.LmcWorkflowRepository;
import com.uwdp.workflow.service.AddClientProcessCallService;
import com.uwdp.workflow.service.iservice.ISubmitAddClientService;
import com.uwdp.workflow.service.iservice.ISubmitAddPublicRelationsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
//@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hpcc-customer-manage/v1_0/workflow/relations")
public class DoSubmitAddPublicRelationsController {

    @Autowired
    private ComposedProcessCallEventHandler processCallEventHandler;

    @Autowired
    private ISubmitAddPublicRelationsService submitAddPublicRelationsService;

    @Autowired
    private AddClientProcessCallService acp;

    @PostMapping("/submit")
    @ApiOperation(value = "新增公关反馈发起流程接口", notes="submit")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated PublicRelationsAddCmd addCmd) {
        try {
            return submitAddPublicRelationsService.add(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
    @PutMapping("/reStartProcess")
    @ApiOperation(value = "新增公关反馈驳回后重新推送流程接口", notes="reStartProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response reStartProcess(@RequestBody @Validated PublicRelationsAddCmd addCmd) {
        try {
            return submitAddPublicRelationsService.reStartProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/draftProcess")
    @ApiOperation(value = "草稿状态推送流程接口", notes="draftProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response draftProcess(@RequestBody @Validated PublicRelationsAddCmd addCmd) {
        try {
            return submitAddPublicRelationsService.draftProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


}
