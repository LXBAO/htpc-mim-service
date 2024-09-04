package com.uwdp.workflow.handler;

import com.gientech.lcds.workflow.sdk.call.event.handler.AbstractProcessCallEventHandler;
import com.gientech.lcds.workflow.sdk.call.event.handler.process.impl.ComposedProcessCallEventHandler;
import com.gientech.lcds.workflow.sdk.core.call.dto.CallBackTime;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.gientech.lcds.workflow.sdk.core.call.event.ProcessCallEvent;
import com.google.common.collect.Maps;
import com.uwdp.workflow.service.iservice.IWorkflowService;
import com.uwdp.workflow.util.FunctionalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 流程结束后监听器接收到ProcessCallEvent , 这是总处理器
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class LmcWorkflowProcessCallEventHandler {

    private final LmcProcessCallEventHandler lmcProcessCallEventHandler;

    @Bean
    @Lazy
    public ComposedProcessCallEventHandler processCallEventHandler() {
        ComposedProcessCallEventHandler composedProcessCallEventHandler = new ComposedProcessCallEventHandler();
        composedProcessCallEventHandler.add(lmcProcessCallEventHandler);
        return composedProcessCallEventHandler;
    }

    /**
     * <p>
     * 流程[同意|驳回|否决]回调
     * </p>
     *
     * @author liqingdian
     * @since 2023-05-23
     */
    @Slf4j
    @Component
    @RequiredArgsConstructor
    public static class LmcProcessCallEventHandler extends AbstractProcessCallEventHandler {

        private final IWorkflowService iWorkflowService;

        private Map<CallBackTime, Consumer<ProcessCallEvent>> callBackTimeMap = Maps.newHashMapWithExpectedSize(16);

        @PostConstruct
        public void init() {
            callBackTimeMap.put(CallBackTime.PROCESS_PASS, iWorkflowService::passed);
            callBackTimeMap.put(CallBackTime.PROCESS_BACK, iWorkflowService::backed);
            callBackTimeMap.put(CallBackTime.PROCESS_VETO, iWorkflowService::vetoed);
        }

        @Override
        public void handleEvent(ProcessCallEvent processCallEvent) {
            if (Objects.isNull(processCallEvent)) {
                log.warn("AddClient" + "流程[同意|驳回|否决]回调>>>回调数据为空！");
                return;
            }
            ProcessCall data = processCallEvent.getData();
            if (Objects.isNull(data)) {
                log.warn("AddClient" + "流程[同意|驳回|否决]回调>>>回调数据实体为空: {}", processCallEvent);
                return;
            }
            log.info("AddClient" + "流程[同意|驳回|否决]回调: {}", processCallEvent);
            CallBackTime callBackTime = data.getCallBackTime();
            Consumer<ProcessCallEvent> processCallConsumer = callBackTimeMap.get(callBackTime);
            FunctionalUtil.predicateAndAccept(processCallConsumer, Objects::nonNull, t -> t.accept(processCallEvent));
        }
    }
}