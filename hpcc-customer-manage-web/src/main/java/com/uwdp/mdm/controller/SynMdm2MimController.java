package com.uwdp.mdm.controller;

import cn.net.ceec.hpcc.mdm.sdk.enums.MdmDataTypeEnum;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.mdm.service.SynMdm2MimService;
import com.uwdp.module.api.service.IPromptService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hpcc-customer-manage/timed/mdm/sync")
@Api(tags = "定时任务")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "定时任务")
@Validated
@Slf4j
public class SynMdm2MimController{
//    private boolean isTaskRunning = false;
//
//    private Long timing = 30L;
//    private ScheduledExecutorService executorService;
//
//    private final IPromptService promptService;
//
//    @PostConstruct
//    public void initExecutorService() {
//        // 初始化定时任务执行器
//        executorService = Executors.newSingleThreadScheduledExecutor();
//        // 开始执行定时任务
//        if (!executorService.isShutdown()) {
//            startTaskWhenNext24clock();
//        }
//    }
//
//    public void startTaskWhenNext24clock() {
//        if (executorService == null || executorService.isShutdown()) {
//            executorService = Executors.newSingleThreadScheduledExecutor();
//        }
//        if (!isTaskRunning) {
//            isTaskRunning = true;
//        }
//
//        LocalTime now = LocalTime.now();
//        LocalTime midnight = LocalTime.of(23, 0);
//        long minutes = ChronoUnit.MINUTES.between(now, midnight);
//        log.info(">>>>>>>同步mdm定时任务将在{}分钟后执行",minutes);
//        executorService.scheduleAtFixedRate(this::executeTask, minutes, 24*60, TimeUnit.MINUTES);
//    }
//    //----------------------------------------------------------------
//    @GetMapping("/startTask/{timing}")
//    public void startTask(@PathVariable Long timing) {
//        if (executorService == null || executorService.isShutdown()) {
//            executorService = Executors.newSingleThreadScheduledExecutor();
//        }
//        if (!isTaskRunning) {
//            isTaskRunning = true;
//        }
//        if (timing == null) {
//            timing = this.timing;
//        }
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(executorService);
//        executorService.scheduleAtFixedRate(this::executeTask, 0, timing, TimeUnit.MINUTES);
//    }
//
//    @GetMapping("/stopTask")
//    public void stopTask() {
//        if (isTaskRunning) {
//            executorService.shutdown();
//            isTaskRunning = false;
//        }
//        log.info("{}-mdmPerson停止！！！", new Date());
//    }
//
//    private final SynMdm2MimService synMdm2MimService;
//
//    public void executeTask() {
//        synMdm2MimService.updateMdmStats2Mim(MdmDataTypeEnum.MDM_PERSON);
//        synMdm2MimService.updateMdmStats2Mim(MdmDataTypeEnum.MDM_ORGANIZATION);
//    }


}

