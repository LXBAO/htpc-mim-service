package com.uwdp.timed.projectmanagerledger.controller;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.module.api.service.ICALedgerService;
import com.uwdp.module.api.service.IPlatformLedgerService;
import com.uwdp.module.api.service.IPromptService;
import com.uwdp.module.api.vo.cmd.PromptAddCmd;
import com.uwdp.module.api.vo.cmd.PromptUpdateCmd;
import com.uwdp.module.api.vo.dto.PlatformLedgerDto;
import com.uwdp.module.api.vo.dto.PromptDto;
import com.uwdp.module.api.vo.dto.searcher.CALedgerCerInfoDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hpcc-customer-manage/timed/pml3/sync")
@Api(tags = "定时任务")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "定时任务")
@Validated
@Slf4j
public class PlatformLedgerTaskController {
//    private boolean isTaskRunning = false;
//
//    private Long timing = 30L;
//    private ScheduledExecutorService executorService;
//
//    private final IPromptService promptService;
//
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
//        executorService.scheduleAtFixedRate(this::executeTask, 0, timing, TimeUnit.MINUTES);
//    }
//
//    @GetMapping("/stopTask")
//    public void stopTask() {
//        if (isTaskRunning) {
//            executorService.shutdown();
//            isTaskRunning = false;
//        }
//        log.info("{}-招标平台证书到期提示停止！！！", new Date());
//    }
//
//    private final IPlatformLedgerService platformLedgerService;
//
//    public void executeTask() {
//        log.info("{}-查询招标平台证书到期时间,定时任务执行中...", new Date());
//
//        LocalDate currentDate = LocalDate.now();
//        LocalDate remindDate = currentDate.plusDays(5);
//
//        try {
//            Map<String, Object> map = new HashMap<>();
//            List<PlatformLedgerDto> search = platformLedgerService.searchAll(map);
//
//            for (PlatformLedgerDto platformLedgerDto : search) {
//                String fdPlatformValidity = platformLedgerDto.getFdPlatformValidity();
//                LocalDate expireDate;
//
//                if (fdPlatformValidity != null) {
//                    expireDate = LocalDate.parse(fdPlatformValidity.substring(0, 10));
//                } else {
//                    continue;
//                }
//
//                PromptDto promptDto = promptService.getByQid(platformLedgerDto.getFdId());
//
//                if (null != promptDto && "1".equals(promptDto.getDeleteStatus())) {
//                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
//                    if (currentDate.isBefore(expireDate) && remindDate.isAfter(expireDate)) {
//                        log.info("进来了update1：" + expireDate);
//
//                        if (promptDto.getPromptStatus().equals("")) {
//                            promptUpdateCmd.setPromptDetails(String.format("您的招标平台证书即将于 %s 到期。", expireDate));
//                            promptUpdateCmd.setPromptStatus("warning");
//                            promptUpdateCmd.setExpireDate(expireDate);
//                        }
//                        promptUpdateCmd.setDeleteStatus("0");
//                        promptUpdateCmd.setId(promptDto.getId());
//                    } else if (expireDate.isBefore(currentDate)) {
//                        log.info("进来了update2：" + expireDate);
//
//                        if (promptDto.getPromptStatus().equals("warning")) {
//                            promptUpdateCmd.setPromptDetails("您的招标平台证书已经到期。");
//                            promptUpdateCmd.setPromptStatus("");
//                        }
//                        promptUpdateCmd.setDeleteStatus("0");
//                        promptUpdateCmd.setId(promptDto.getId());
//                    }
//                    promptService.update(promptUpdateCmd);
//                }
//
//                if (promptDto == null && expireDate.isBefore(remindDate)) {
//                    log.info("进来了add");
//                    String message = "";
//                    String status = "";
//                    if (expireDate.isBefore(currentDate)) {
//                        log.info("证书已过期！");
//                        message = "您的招标平台证书已经到期。";
//                        status = "";
//                    } else {
//                        message = String.format("您的招标平台证书即将于 %s 到期。", expireDate);
//                        status = "warning";
//                    }
//
//                    PromptAddCmd addCmd = new PromptAddCmd();
//                    addCmd.setPromptTitle("招标平台证书到期提醒");
//                    addCmd.setPromptDetails(message);
//                    addCmd.setExpireDate(expireDate);
//                    addCmd.setCreatedBy(platformLedgerDto.getCreatedBy());
//                    addCmd.setPromptId(platformLedgerDto.getCreatedBy());
//                    addCmd.setCreatedName(platformLedgerDto.getFdRegistrant());
//                    addCmd.setQid(platformLedgerDto.getFdId());
//                    addCmd.setPromptStatus(status);  // warning 快到期  error 已经到期
//                    addCmd.setDeleteStatus("0");  // 0未删  1已删
//                    addCmd.setPromptPath("platformLedger");
//
//                    promptService.add(addCmd);
//
//                    promptService.toBeDoneToOa(platformLedgerDto.getFdId(), "8", platformLedgerDto.getCreatedBy(), 1, 0);
//                }
//            }
//
//            log.info("{}-查询招标平台证书到期时间,定时任务执行中...", new Date());
//        }catch (Exception e){
//            log.error("查询招标平台证书到期时间,定时任务执行报错！{}", e);
//            e.fillInStackTrace();
//        }
//
//
//    }



}
