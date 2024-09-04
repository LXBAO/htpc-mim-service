package com.uwdp.workflow.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.fastjson2.JSONObject;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.call.event.handler.process.impl.ComposedProcessCallEventHandler;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.gientech.lcds.workflow.sdk.core.call.dto.TaskCall;
import com.uwdp.module.api.service.IVisitPlanService;
import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanAddCmd;
import com.uwdp.workflow.service.AddClientProcessCallService;
import com.uwdp.workflow.service.iservice.ISubmitAddPublicRelationsService;
import com.uwdp.workflow.service.iservice.ISubmitAddVisitPlanService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hpcc-customer-manage/v1_0/workflow/visitplan")
public class DoSubmitAddVisitPlanController {

    @Autowired
    private ComposedProcessCallEventHandler processCallEventHandler;

    @Autowired
    private ISubmitAddVisitPlanService submitAddVisitPlanService;

    @Autowired
    private AddClientProcessCallService acp;

    @Autowired
    private IVisitPlanService iVisitPlanService;

    @PostMapping("/submit")
    @ApiOperation(value = "新增接口", notes="relations")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated VisitPlanAddCmd addCmd) {
        try {
            return submitAddVisitPlanService.add(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
    @PutMapping("/reStartProcess")
    @ApiOperation(value = "新增接口", notes="reStartProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response reStartProcess(@RequestBody @Validated VisitPlanAddCmd addCmd) {
        try {
            return submitAddVisitPlanService.reStartProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/draftProcess")
    @ApiOperation(value = "草稿状态推送流程接口", notes="draftProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response draftProcess(@RequestBody @Validated VisitPlanAddCmd addCmd) {
        try {
            return submitAddVisitPlanService.draftProcess(addCmd);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/updateIfConference/taskCall")
    @ApiOperation(value = "任务回调修改ifConference字段", notes="updateIfConference")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response updateIfConference(@RequestBody TaskCall taskCall) {
        try {
            log.info(">>>>>>>>>>任务回调修改ifConference字段接收到回调参数TaskCall:{}",taskCall);
            if (null != taskCall && null != taskCall.getAgree() && taskCall.getAgree()) {
                iVisitPlanService.updateConferenceLeadersInWorkflow(taskCall);
            }
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/updateIfConference")
    @ApiOperation(value = "任务回调修改ifConference字段", notes="updateIfConference")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response updateIfConference(@RequestBody JSONObject taskCall) {
        try {
            log.info(">>>>>>>>>>任务回调修改ifConference字段接收到回调参数JSONObject:{}",taskCall);
//            TaskCall taskCall1 = taskCall.to(TaskCall.class);

            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

}
