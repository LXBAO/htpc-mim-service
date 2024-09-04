package com.uwdp.timed.projectmanagerledger.controller;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.module.api.service.ICALedgerService;
import com.uwdp.module.api.service.IPromptService;
import com.uwdp.module.api.vo.cmd.PromptAddCmd;
import com.uwdp.module.api.vo.cmd.PromptUpdateCmd;
import com.uwdp.module.api.vo.dto.PromptDto;
import com.uwdp.module.api.vo.dto.searcher.CALedgerCerInfoDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/hpcc-customer-manage/timed/pml2/sync")
@Api(tags = "定时任务")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "定时任务")
@Validated
@Slf4j
public class CerInfoTaskController {
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
//        log.info("定时任务开始执行!");
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
//        log.info("{}-ca认证平台证书到期提示停止！！！", new Date());
//    }
//
//    private final ICALedgerService cALedgerService;
//
//    @Transactional
//    public void executeTask() {
//        log.info("{}-查询ca认证平台证书到期时间,定时任务执行中...", new Date());
//
//        LocalDate currentDate = LocalDate.now();
//        LocalDate remindDate = currentDate.plusDays(5); // 提醒时间是到期前五天
//
//        try {
//            Map<String, Object> map = new HashMap<>();
//            List<CALedgerCerInfoDto> search = cALedgerService.searchAll(map);
//
//            for (CALedgerCerInfoDto caLedgerCerInfoDto : search) {
//                LocalDateTime expireDateTime = caLedgerCerInfoDto.getExpireDate();
//
//                if (expireDateTime == null) {
//                    continue;
//                }
//
//                LocalDate expireDate = LocalDate.from(expireDateTime);
//                PromptDto promptDto = promptService.getByQid(caLedgerCerInfoDto.getFdId());
//                if (promptDto != null && promptDto.getDeleteStatus().equals("1")) {
//                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
//                    if (currentDate.isBefore(expireDate) && remindDate.isAfter(expireDate)) {
//                        log.info("进来了update1：" + expireDate);
//
//                        if (promptDto.getPromptStatus().equals("")) {
//                            promptUpdateCmd.setPromptDetails(String.format("您的CA认证平台证书即将于 %s 到期。", expireDate));
//                            promptUpdateCmd.setPromptStatus("warning");
//                            promptUpdateCmd.setExpireDate(expireDate);
//                        }
//                        promptUpdateCmd.setDeleteStatus("0");
//                        promptUpdateCmd.setId(promptDto.getId());
//                    } else if (expireDate.isBefore(currentDate)) {
//                        log.info("进来了update2：" + expireDate);
//
//                        if (promptDto.getPromptStatus().equals("warning")) {
//                            promptUpdateCmd.setPromptDetails("您的CA认证平台证书已经到期。");
//                            promptUpdateCmd.setPromptStatus("");
//                        }
//                        promptUpdateCmd.setDeleteStatus("0");
//                        promptUpdateCmd.setId(promptDto.getId());
//                    }
//                    promptService.update(promptUpdateCmd);
//                } else if (promptDto == null && expireDate.isBefore(remindDate)) {
//                    System.out.println("进来了add");
//                    PromptAddCmd addCmd = new PromptAddCmd();
//                    addCmd.setPromptTitle("CA认证平台证书到期提醒");
//
//                    String message;
//                    String status;
//                    if (expireDate.isBefore(currentDate)) {
//                        log.info("证书已过期！");
//                        message = "您的CA认证平台证书已经到期。";
//                        status = "";
//                    } else {
//                        message = String.format("您的CA认证平台证书即将于 %s 到期。", expireDate);
//                        status = "warning";
//                    }
//                    addCmd.setPromptDetails(message);
//                    addCmd.setExpireDate(expireDate);
//                    addCmd.setCreatedBy(caLedgerCerInfoDto.getCreatedBy());
//                    addCmd.setPromptId(caLedgerCerInfoDto.getCreatedBy());
//                    addCmd.setQid(caLedgerCerInfoDto.getFdId());
//                    addCmd.setCreatedName(caLedgerCerInfoDto.getFdRegistrant());
//                    addCmd.setPromptStatus(status);
//                    addCmd.setDeleteStatus("0"); // 0未删  1已删
//                    addCmd.setPromptPath("CALedger");
//                    promptService.add(addCmd);
//
//                    promptService.toBeDoneToOa(caLedgerCerInfoDto.getFdId(), "8", caLedgerCerInfoDto.getCreatedBy(), 0, 0);
//                }
//            }
//            log.info("{}-查询ca认证平台证书到期时间,定时任务执行完成", new Date());
//        }catch (Exception e){
//            log.error("查询ca认证平台证书到期时间,定时任务执行报错！{}", e);
//            e.fillInStackTrace();
//        }
//    }


}
