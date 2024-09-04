package com.uwdp.workflow.controller;

import com.alibaba.cola.dto.Response;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.core.call.dto.TaskCall;
import com.uwdp.module.api.service.IQiYeWorkService;
import com.uwdp.module.biz.infrastructure.entity.PromptDo;
import com.uwdp.module.biz.infrastructure.repository.PromptRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hpcc-customer-manage/v1_0/workflow/send_notification")
public class DoTaskSendNotificationController {

    private final IQiYeWorkService iQiYeWorkService;

    @PostMapping("/submitNotification")
    @ApiOperation(value = "发送通知", notes = "submitNotification")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody TaskCall taskCall) {
        log.info("======================taskCall的数据为："+taskCall);
        try {
            if (taskCall.getEventName().equals("copy")) {
                Set<String> processedUserNumbers = new HashSet<>();
                taskCall.getCandidateUsers().forEach(user -> {
                    String userNo = user.getUserNo();

                    // 检查用户编号是否已经处理过
                    if (!processedUserNumbers.contains(userNo)) {
                        iQiYeWorkService.sendTextcard(taskCall.getProcessInstanceId(),taskCall.getTaskId(),"addClient",userNo,user.getUserFullName());
                        // 将用户编号标记为已处理
                        processedUserNumbers.add(userNo);
                    }
                });
            }

            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
