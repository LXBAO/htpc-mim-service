package com.uwdp.timed.projectmanagerledger.controller;// 导入其他需要的类

import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.module.api.service.IProjectBiddingService;
import com.uwdp.module.api.service.IProjectRecordsService;
import com.uwdp.module.api.service.IPromptService;
import com.uwdp.module.api.vo.cmd.PromptAddCmd;
import com.uwdp.module.api.vo.cmd.PromptUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectBiddingDto;
import com.uwdp.module.api.vo.dto.ProjectRecordsDto;
import com.uwdp.module.api.vo.dto.PromptDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hpcc-customer-manage/timed/project-bidding/sync")
@Api(tags = "项目投标定时任务")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "定时任务")
@Validated
@Slf4j
public class ProjectBiddingTaskController {
    private boolean isTaskRunning = false;

    private Long timing = 30L; // 定时任务执行周期，单位为分钟
    private ScheduledExecutorService executorService;

    private final IProjectRecordsService projectRecordsService;
    private final IPromptService promptService; // 添加了 PromptService
    private final IProjectBiddingService projectBiddingService;

    @GetMapping("/startTask/{timing}")
    public void startTask(@PathVariable Long timing) {
        log.info("定时任务开始执行!");
        if (executorService == null || executorService.isShutdown()) {
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
        if (!isTaskRunning) {
            isTaskRunning = true;
        }
        if (timing == null) {
            timing = this.timing;
        }
        executorService.scheduleAtFixedRate(this::executeTask, 0, timing, TimeUnit.MINUTES);
    }

    @GetMapping("/stopTask")
    public void stopTask() {
        if (isTaskRunning) {
            executorService.shutdown();
            isTaskRunning = false;
        }
        log.info("项目投标定时任务停止！！！");
    }

    @Transactional
    public void executeTask() {
        log.info("项目投标定时任务执行中...");
        try {
            // 查询所有项目信息
            List<ProjectRecordsDto> projectRecordsList = projectRecordsService.searchByParam(new HashMap<>());

            LocalDate currentDate = LocalDate.now();

            for (ProjectRecordsDto projectRecord : projectRecordsList) {
                // 获取项目登记通过日期
                LocalDateTime estimatedBiddingTime = projectRecord.getEstimatedBiddingTime();

                if (estimatedBiddingTime == null) {
                    continue;
                }

                LocalDate estimatedBiddingDate = estimatedBiddingTime.toLocalDate();


                //根据登记id查出提示信息
                PromptDto promptDto = promptService.getByQid(projectRecord.getId(),"ProjectBidding");
                //查询项目投标有没有项目信息
                ProjectBiddingDto projectBiddingDto = projectBiddingService.getProjectNumber(String.valueOf(projectRecord.getId()));
                //判断提示信息不为空且删除状态未已删除、投标信息为空
                if (promptDto != null && projectBiddingDto == null && promptDto.getDeleteStatus().equals("1")) {
                    log.info("进来了update：" + projectRecord);
                    //已经删除的提醒，如果未跟踪继续提醒
                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
                    promptUpdateCmd.setDeleteStatus("0"); // 0未删  1已删
                    promptUpdateCmd.setExpireDate(estimatedBiddingDate);
                    promptUpdateCmd.setId(promptDto.getId());
                    promptService.update(promptUpdateCmd);
                } else if (currentDate.isAfter(estimatedBiddingDate) || currentDate.isEqual(estimatedBiddingDate)) {
                    if (projectBiddingDto == null && promptDto == null){
                        log.info("进来了add：" + projectRecord);
                        // 需要提醒
                        // 查询提示信息
                        String promptMessage = "请尽快对"+projectRecord.getProjectName()+"项目进行投标！";

                        // 构建 PromptAddCmd
                        PromptAddCmd addCmd = new PromptAddCmd();
                        addCmd.setPromptTitle("项目投标提醒");
                        addCmd.setPromptId(projectRecord.getCreatedBy());
                        addCmd.setPromptDetails(promptMessage);
                        addCmd.setExpireDate(estimatedBiddingDate);
                        addCmd.setCreatedBy(projectRecord.getCreatedBy());
                        addCmd.setQid(projectRecord.getId());
                        addCmd.setCreatedName(projectRecord.getCreatedName());
                        addCmd.setPromptStatus("warning");
                        addCmd.setDeleteStatus("0"); // 0未删  1已删
                        addCmd.setPromptPath("ProjectBidding");

                        // 添加提示信息
                        promptService.add(addCmd);
                    }
                }
            }

            log.info("项目投标定时任务执行完成");
        } catch (Exception e) {
            log.error("项目投标定时任务执行报错！{}", e);
            e.printStackTrace();
        }
    }
}
