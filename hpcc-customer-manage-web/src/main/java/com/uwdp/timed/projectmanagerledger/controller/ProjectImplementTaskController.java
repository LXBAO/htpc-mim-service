package com.uwdp.timed.projectmanagerledger.controller;// 导入其他需要的类

import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.module.api.service.IProjectImplementService;
import com.uwdp.module.api.service.IProjectSigningService;
import com.uwdp.module.api.service.IPromptService;
import com.uwdp.module.api.service.IWinTheBidService;
import com.uwdp.module.api.vo.cmd.PromptAddCmd;
import com.uwdp.module.api.vo.cmd.PromptUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectImplementDto;
import com.uwdp.module.api.vo.dto.ProjectSigningDto;
import com.uwdp.module.api.vo.dto.PromptDto;
import com.uwdp.module.api.vo.dto.WinTheBidDto;
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
@RequestMapping("/hpcc-customer-manage/timed/project-implement/sync")
@Api(tags = "项目实施定时任务")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "定时任务")
@Validated
@Slf4j
public class ProjectImplementTaskController {
    private boolean isTaskRunning = false;

    private Long timing = 30L; // 定时任务执行周期，单位为分钟
    private ScheduledExecutorService executorService;

    private final IProjectImplementService projectImplementService;
    private final IPromptService promptService; // 添加了 PromptService
    private final IProjectSigningService projectSigningService;

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
        log.info("项目实施定时任务停止！！！");
    }

    @Transactional
    public void executeTask() {
        log.info("项目实施定时任务执行中...");
        try {
            // 查询所有项目信息
            List<ProjectSigningDto> projectSigningDtos = projectSigningService.searchByParam(new HashMap<>());

            LocalDate currentDate = LocalDate.now();

            for (ProjectSigningDto projectSigningDto : projectSigningDtos) {
                // 获取项目登记通过日期
                String commencementOfContract = projectSigningDto.getCommencementOfContract();

                if (commencementOfContract == null) {
                    continue;
                }

                LocalDate commencementOfContractDate = LocalDate.parse(commencementOfContract);

                //根据登记id查出提示信息
                PromptDto promptDto = promptService.getByQid(Long.valueOf(projectSigningDto.getProjectNumber()),"ProjectImplement");
                //查询项目实施有没有项目信息
                ProjectImplementDto projectImplementDto = projectImplementService.getProjectNumber(projectSigningDto.getProjectNumber());
                //判断提示信息不为空且删除状态未已删除、投标信息为空
                if (promptDto != null && projectImplementDto == null && promptDto.getDeleteStatus().equals("1")) {
                    log.info("进来了update：" + projectSigningDto);
                    //已经删除的提醒，如果未签约继续提醒
                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
                    promptUpdateCmd.setDeleteStatus("0"); // 0未删  1已删
                    promptUpdateCmd.setExpireDate(commencementOfContractDate);
                    promptUpdateCmd.setId(promptDto.getId());
                    promptService.update(promptUpdateCmd);
                } else if (currentDate.isAfter(commencementOfContractDate) || currentDate.isEqual(commencementOfContractDate)) {
                    if (projectImplementDto == null && promptDto == null){
                        log.info("进来了add：" + projectSigningDto);
                        // 需要提醒
                        // 查询提示信息
                        String promptMessage = "请尽快对"+projectSigningDto.getProjectName()+"项目进行实施！";

                        // 构建 PromptAddCmd
                        PromptAddCmd addCmd = new PromptAddCmd();
                        addCmd.setPromptTitle("项目实施提醒");
                        addCmd.setPromptId(projectSigningDto.getCreatedBy());
                        addCmd.setPromptDetails(promptMessage);
                        addCmd.setExpireDate(LocalDate.parse(projectSigningDto.getCommencementOfContract()));
                        addCmd.setCreatedBy(projectSigningDto.getCreatedBy());
                        addCmd.setQid(Long.valueOf(projectSigningDto.getProjectNumber()));
                        addCmd.setCreatedName(projectSigningDto.getCreatedName());
                        addCmd.setPromptStatus("warning");
                        addCmd.setDeleteStatus("0"); // 0未删  1已删
                        addCmd.setPromptPath("ProjectImplement");

                        // 添加提示信息
                        promptService.add(addCmd);
                    }
                }
            }

            log.info("项目实施定时任务执行完成");
        } catch (Exception e) {
            log.error("项目实施定时任务执行报错！{}", e);
            e.printStackTrace();
        }
    }
}
