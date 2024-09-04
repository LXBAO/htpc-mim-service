package com.uwdp.workflow.handler;

import com.alibaba.cola.dto.Response;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.core.call.dto.CallBackTime;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.uwdp.jdbcutils.DatabaseReaderUtil;
import com.uwdp.workflow.enums.BizCode;
import com.uwdp.workflow.service.*;
import com.uwdp.workflow.service.iservice.ISubmitAddClientService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@Slf4j
@Configuration
@RequiredArgsConstructor
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/workflow/")
public class WorkflowprocessCallHandler {

    private final SubmitAddPublicRelationsServiceImpl publicRelationsService;
    private final ISubmitAddClientService addClientService;
    private final SubmitAddVisitPlanServiceImpl addVisitPlanService;
    private final SubmitAddProjectRecordsServiceImpl submitAddProjectRecordsService;
    private final SubmitAddProjectBiddingServiceImpl submitAddProjectBiddingService;
    private final SubmitAddProjectTrackingServiceImpl submitAddProjectTrackingService;
    private final SubmitAddWinTheBidServiceImpl submitAddWinTheBidService;
    private final SubmitAddProjectSigningServiceImpl submitAddProjectSigningService;
    private final SubmitAddProjectImplementServiceImpl submitAddProjectImplementService;
    private final SubmitAddMarketDmpTagServiceImpl submitAddMarketDmpTagService;
    private final SubmitAddProjectSuspensionServiceImpl suspensionService;
    private final SubmitAddProjectEnableServiceImpl projectEnableService;

    private final DatabaseReaderUtil readerUtil;

    @PostMapping("/handleProcessCall")
    @ApiOperation(value = "流程回调", notes = "WORKFLOW")
    @OperationLog(type = OperateTypeEnum.OTHER)
    public Response processCall(@RequestBody ProcessCall processCall) {
        try {
            if (Objects.isNull(processCall)) {
                log.warn("流程[同意|驳回|否决]回调>>>回调数据为空！");
                return Response.buildFailure("501", "回调数据为空！");
            }
            this.handleProcessCall(processCall);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    public void handleProcessCall(ProcessCall processCall) {
        log.info(processCall.getProcessKey() + "****流程回调: {}", processCall);
        if (Objects.isNull(processCall)) {
            log.warn("流程[同意|驳回|否决]回调>>>回调数据为空！");
            return;
        }
        CallBackTime callBackTime = processCall.getCallBackTime();
        String processKey = processCall.getProcessKey();
        if (CallBackTime.PROCESS_PASS.equals(callBackTime)) {
            log.info(processKey + "流程[同意]回调: {}", processCall);
            if (BizCode.ADD_CLIENT.getValue().equals(processKey)) {
                addClientService.handlePass(processCall);
            } else if (BizCode.ADD_FEEDBACK.getValue().equals(processKey)) {
                publicRelationsService.handlePass(processCall);
            } else if (BizCode.ADD_VISIT_PLAN.getValue().equals(processKey)) {
                addVisitPlanService.handlePass(processCall);
            } else if (BizCode.ADD_PROJECT_RECORDS.getValue().equals(processKey)) {
                submitAddProjectRecordsService.handlePass(processCall);
            } else if (BizCode.ADD_PROJECT_BIDDING.getValue().equals(processKey)) {
                submitAddProjectBiddingService.handlePass(processCall);
            } else if (BizCode.ADD_PROJECT_TRACKING.getValue().equals(processKey)) {
                submitAddProjectTrackingService.handlePass(processCall);
            }else if (BizCode.ADD_PROJECT_SIGNING.getValue().equals(processKey)) {
                submitAddProjectSigningService.handlePass(processCall);
            } else if (BizCode.ADD_WIN_THE_BID.getValue().equals(processKey)) {
                submitAddWinTheBidService.handlePass(processCall);
            } else if (BizCode.ADD_PROJECT_IMPLEMENT.getValue().equals(processKey)) {
                submitAddProjectImplementService.handlePass(processCall);
            }else if(BizCode.ADD_MARKET_DMP_TAG.getValue().equals(processKey)){
                submitAddMarketDmpTagService.handlePass(processCall);//指标
            }else if(BizCode.ADD_PROJECT_SUSPENSION.getValue().equals(processKey)){
                suspensionService.handlePass(processCall);//项目中止
            }else if(BizCode.ADD_PROJECT_ENABLE.getValue().equals(processKey)){
                projectEnableService.handlePass(processCall);//项目赋能
            }
        } else if (CallBackTime.PROCESS_BACK.equals(callBackTime)) {
            if (BizCode.ADD_CLIENT.getValue().equals(processKey)) {
                addClientService.handleBack(processCall);
            } else if (BizCode.ADD_FEEDBACK.getValue().equals(processKey)) {
                publicRelationsService.handleBack(processCall);
            } else if (BizCode.ADD_VISIT_PLAN.getValue().equals(processKey)) {
                addVisitPlanService.handleBack(processCall);
            } else if (BizCode.ADD_PROJECT_RECORDS.getValue().equals(processKey)) {
                submitAddProjectRecordsService.handleBack(processCall);
            } else if (BizCode.ADD_PROJECT_BIDDING.getValue().equals(processKey)) {
                submitAddProjectBiddingService.handleBack(processCall);
            } else if (BizCode.ADD_PROJECT_TRACKING.getValue().equals(processKey)) {
                submitAddProjectTrackingService.handleBack(processCall);
            } else if (BizCode.ADD_PROJECT_SIGNING.getValue().equals(processKey)) {
                submitAddProjectSigningService.handleBack(processCall);
            } else if (BizCode.ADD_WIN_THE_BID.getValue().equals(processKey)) {
                submitAddWinTheBidService.handleBack(processCall);
            } else if (BizCode.ADD_PROJECT_IMPLEMENT.getValue().equals(processKey)) {
                submitAddProjectImplementService.handleBack(processCall);
            }else if(BizCode.ADD_MARKET_DMP_TAG.getValue().equals(processKey)){
                submitAddMarketDmpTagService.handleBack(processCall);//指标
            }else if(BizCode.ADD_PROJECT_SUSPENSION.getValue().equals(processKey)){
                suspensionService.handleBack(processCall);//项目中止
            }else if(BizCode.ADD_PROJECT_ENABLE.getValue().equals(processKey)){
                projectEnableService.handleBack(processCall);//项目赋能
            }

        } else if (CallBackTime.PROCESS_REVOKE.equals(callBackTime)) {
            //流程撤回回调
            log.info(processKey + "流程{}回调: {}",CallBackTime.PROCESS_REVOKE, processCall);
            if (BizCode.ADD_CLIENT.getValue().equals(processKey)) {
                addClientService.handleRevoke(processCall);
            } else if (BizCode.ADD_FEEDBACK.getValue().equals(processKey)) {
                publicRelationsService.handleRevoke(processCall);
            } else if (BizCode.ADD_VISIT_PLAN.getValue().equals(processKey)) {
                addVisitPlanService.handleRevoke(processCall);
            } else if (BizCode.ADD_PROJECT_RECORDS.getValue().equals(processKey)) {
                submitAddProjectRecordsService.handleRevoke(processCall);
            } else if (BizCode.ADD_PROJECT_BIDDING.getValue().equals(processKey)) {
                submitAddProjectBiddingService.handleRevoke(processCall);
            } else if (BizCode.ADD_PROJECT_TRACKING.getValue().equals(processKey)) {
                submitAddProjectTrackingService.handleRevoke(processCall);
            } else if (BizCode.ADD_PROJECT_SIGNING.getValue().equals(processKey)) {
                submitAddProjectSigningService.handleRevoke(processCall);
            } else if (BizCode.ADD_WIN_THE_BID.getValue().equals(processKey)) {
                submitAddWinTheBidService.handleRevoke(processCall);
            } else if (BizCode.ADD_PROJECT_IMPLEMENT.getValue().equals(processKey)) {
                submitAddProjectImplementService.handleRevoke(processCall);
            }else if(BizCode.ADD_MARKET_DMP_TAG.getValue().equals(processKey)){
                submitAddMarketDmpTagService.handleRevoke(processCall);//指标
            }else if(BizCode.ADD_PROJECT_SUSPENSION.getValue().equals(processKey)){
                suspensionService.handleRevoke(processCall);//项目中止
            }else if(BizCode.ADD_PROJECT_ENABLE.getValue().equals(processKey)){
                projectEnableService.handleRevoke(processCall);//项目赋能
            }

        }
//        if (CallBackTime.PROCESS_START.equals(callBackTime)){
//            log.info(processKey + "流程[同意]回调: {}", processCall);
//            if (BizCode.ADD_CLIENT.getValue().equals(processKey)){
//                addClientService.handleStart(processCall);
//            }
//            if (BizCode.ADD_FEEDBACK.getValue().equals(processKey)){
//                publicRelationsService.handleStart(processCall);
//            }
//            if (BizCode.ADD_GGJHAP.getValue().equals(processKey)){
//                addVisitPlanService.handleStart(processCall);
//            }
//        }
    }

}
