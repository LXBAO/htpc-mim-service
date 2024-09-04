package com.uwdp.timed.projectmanagerledger.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.module.api.service.IProjectManagerLedgerService;
import com.uwdp.module.api.service.IPromptService;
import com.uwdp.module.api.vo.cmd.PromptAddCmd;
import com.uwdp.module.api.vo.cmd.PromptUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectManagerLedgerDto;
import com.uwdp.module.api.vo.dto.PromptDto;
import com.uwdp.module.biz.infrastructure.entity.ProjectManagerLedgerDo;
import com.uwdp.module.biz.infrastructure.repository.ProjectManagerLedgerRepository;
import com.zengtengpeng.operation.RedissonCollection;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hpcc-customer-manage/timed/pml/sync")
@Api(tags = "定时任务")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "定时任务")
@Validated
@Slf4j
public class projectMangerLedgerTaskController {
//    private boolean isTaskRunning = false;
//
//    private Long timing = 30L;
//    private ScheduledExecutorService executorService;
//
//    private final IPromptService promptService;
//
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
//        executorService.scheduleAtFixedRate(this::executeTask, 1, timing, TimeUnit.MINUTES);
//    }
//
//    @GetMapping("/stopTask")
//    public void stopTask() {
//        if (isTaskRunning) {
//            executorService.shutdown();
//            isTaskRunning = false;
//        }
//        log.info("{}-资质信息证书到期提示停止！！！", new Date());
//    }
//
//    private final IProjectManagerLedgerService projectManagerLedgerService;
//
//    private final ProjectManagerLedgerRepository projectManagerLedgerRepository;
//
//    public void executeTask() {
//        log.info("{}-查询资质信息证书到期时间,定时任务执行中...", new Date());
//
//        LocalDate currentDate = LocalDate.now();
//        LocalDate remindDate = currentDate.plusDays(5);
//
//        try {
//            QueryWrapper<ProjectManagerLedgerDo> queryWrapper = new QueryWrapper<>();
//            queryWrapper.between("fdExpireDate", LocalDate.from(currentDate), remindDate)
//                    .or()
//                    .between("fdExpiryDate", LocalDate.from(currentDate), remindDate);
//
//            List<ProjectManagerLedgerDo> list = projectManagerLedgerRepository.list(queryWrapper);
//
//            queryWrapper = new QueryWrapper<>();
//            queryWrapper.le("fdExpireDate", LocalDate.from(currentDate))
//                    .or()
//                    .le("fdExpiryDate", LocalDate.from(currentDate));
//
//            List<ProjectManagerLedgerDo> list1 = projectManagerLedgerRepository.list(queryWrapper);
//            list.addAll(list1);
//
//            for (ProjectManagerLedgerDo projectManagerLedgerDo : list) {
//                LocalDateTime fdExpireDate = projectManagerLedgerDo.getFdExpireDate();
//                LocalDateTime fdExpiryDate = projectManagerLedgerDo.getFdExpiryDate();
//                LocalDate expireDate = null;
//                LocalDate expiryDate = null;
//
//                if (null != fdExpireDate) {
//                    expireDate = LocalDate.from(fdExpireDate);
//                } else if (null != fdExpiryDate) {
//                    expiryDate = LocalDate.from(fdExpiryDate);
//                }
//
//                PromptDto promptDto = promptService.getByQid(projectManagerLedgerDo.getFdId());
//
//                if (null != promptDto && "1".equals(promptDto.getDeleteStatus())) {
//                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
//                    if ((expireDate != null && currentDate.isBefore(expireDate) && remindDate.isAfter(expireDate)) ||
//                            (expiryDate != null && currentDate.isBefore(expiryDate) && remindDate.isAfter(expiryDate))) {
//
//                        log.info("进来了update1：" + expireDate + " " + expiryDate);
//                        if (promptDto.getPromptStatus().equals("")) {
//                            if (expireDate != null) {
//                                promptUpdateCmd.setPromptDetails(String.format("您的项目经理证书即将于 %s 到期。", expireDate));
//                            } else {
//                                promptUpdateCmd.setPromptDetails(String.format("您的九大员证件即将于 %s 到期。", expiryDate));
//                            }
//                            promptUpdateCmd.setPromptStatus("warning");
//                            promptUpdateCmd.setExpireDate(expireDate != null ? expireDate : expiryDate);
//                        }
//                        promptUpdateCmd.setDeleteStatus("0");
//                        promptUpdateCmd.setId(promptDto.getId());
//
//
//                    } else if ((expireDate != null && expireDate.isBefore(currentDate)) ||
//                            (expiryDate != null && expiryDate.isBefore(currentDate))) {
//
//                        log.info("进来了update2：" + expireDate + " " + expiryDate);
//
//                        if (promptDto.getPromptStatus().equals("warning")) {
//                            promptUpdateCmd.setPromptDetails("您的资质信息证书(件)已经到期。");
//                            promptUpdateCmd.setPromptStatus("");
//                        }
//                        promptUpdateCmd.setDeleteStatus("0");
//                        promptUpdateCmd.setId(promptDto.getId());
//
//
//                    }
//                    promptService.update(promptUpdateCmd);
//                }
//                if (promptDto == null) {
//                    String message = "";
//                    String status = "";
//                    if ((expireDate != null && expireDate.isBefore(currentDate)) ||
//                            (expiryDate != null && expiryDate.isBefore(currentDate))) {
//                        log.info("证书已过期！");
//                        message = "您的资质信息证书(件)已经到期。";
//                        status = "";
//                    } else if (expireDate != null && expireDate.isBefore(remindDate)) {
//                        message = String.format("您的项目经理证书即将于 %s 到期。", expireDate);
//                        status = "warning";
//                    } else if (expiryDate != null && expiryDate.isBefore(remindDate)) {
//                        message = String.format("您的九大员证件即将于 %s 到期。", expiryDate);
//                        status = "warning";
//                    }
//
//                    if (!message.isEmpty()) {
//                        PromptAddCmd addCmd = new PromptAddCmd();
//                        log.info("进来了add{}", addCmd);
//                        addCmd.setPromptTitle("资质信息证书到期提醒");
//                        addCmd.setPromptDetails(message);
//                        addCmd.setExpireDate(expireDate != null ? expireDate : expiryDate);
//                        addCmd.setCreatedBy(projectManagerLedgerDo.getFdJobNumber());
//                        addCmd.setPromptId(projectManagerLedgerDo.getFdJobNumber());
//                        addCmd.setCreatedName(projectManagerLedgerDo.getFdPMName());
//                        addCmd.setQid(projectManagerLedgerDo.getFdId());
//                        addCmd.setPromptStatus(status);  // 0未读  1已读
//                        addCmd.setDeleteStatus("0");  // 0未删  1已删
//                        addCmd.setPromptPath("projectManagerLedger");
//                        promptService.add(addCmd);
//
//                        promptService.toBeDoneToOa(projectManagerLedgerDo.getFdId(), "8", projectManagerLedgerDo.getCreatedBy(), 2, 0);
//                    }
//                }
//            }
//
//            log.info("{}-查询资质信息证书到期时间,定时任务执行中...", new Date());
//        } catch (Exception e) {
//            log.error("查询资质信息证书到期时间,定时任务执行报错！{}", e);
//            e.fillInStackTrace();
//        }
//    }


}
