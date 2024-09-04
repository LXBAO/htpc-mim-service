package com.uwdp.timed.projectmanagerledger.controller;

import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.module.api.service.IPromptService;
import com.uwdp.module.api.service.IPublicRelationsService;
import com.uwdp.module.api.service.IVisitPlanService;
import com.uwdp.module.api.vo.cmd.PromptAddCmd;
import com.uwdp.module.api.vo.cmd.PromptUpdateCmd;
import com.uwdp.module.api.vo.dto.PromptDto;
import com.uwdp.module.api.vo.dto.PublicRelationsDto;
import com.uwdp.module.api.vo.dto.VisitPlanDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hpcc-customer-manage/timed/pml4/sync")
@Api(tags = "定时任务")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "定时任务")
@Validated
@Slf4j
public class visitPlanTaskController {
    private boolean isTaskRunning = false;

    private Long timing = 30L;
    private ScheduledExecutorService executorService;

    private final IPromptService promptService;

    //----------------------------------------------------------------
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
        log.info("{}-查询客户公关反馈时间提示停止！！！", new Date());
    }

    private final IVisitPlanService visitPlanService;
    private final IPublicRelationsService publicRelationsService;

    @Transactional
    public void executeTask() {
        log.info("{}-查询客户公关反馈时间,定时任务执行中...", new Date());
        try {
            Map<String, Object> map = new HashMap<>();
            List<VisitPlanDto> search = visitPlanService.searchAll(map);
            LocalDate currentDate = LocalDate.now();
            for (VisitPlanDto visitPlanDto : search) {
                String dateTimeString = visitPlanDto.getAdviceDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = dateFormat.parse(dateTimeString);
                LocalDateTime expireDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                if (expireDateTime == null) {
                    continue;
                }
                LocalDate expireDate = LocalDate.from(expireDateTime);
                LocalDate remindDate = expireDate.plusDays(7);

                PromptDto promptDto = promptService.getByQid(visitPlanDto.getId());
                PublicRelationsDto publicRelationsDto = publicRelationsService.getByVisitPlanId(visitPlanDto.getId());
                if (promptDto != null && publicRelationsDto == null && promptDto.getDeleteStatus().equals("1")) {
                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
                    if (currentDate.equals(remindDate) || currentDate.isAfter(remindDate)) {
                        log.info("进来了update：" + expireDate);
                        if (promptDto.getPromptStatus().equals("warning")) {
                            promptUpdateCmd.setPromptDetails("请及时对"+visitPlanDto.getTitle()+"计划做出应馈");
                            promptUpdateCmd.setPromptStatus("warning");
                        }
                        promptUpdateCmd.setDeleteStatus("0");
                        promptUpdateCmd.setId(promptDto.getId());
                    }
                    promptService.update(promptUpdateCmd);
                } else if (currentDate.equals(remindDate) || currentDate.isAfter(remindDate)) {
                    if (promptDto == null && publicRelationsDto ==null) {
                        System.out.println("进来了add");
                        PromptAddCmd addCmd = new PromptAddCmd();
                        addCmd.setPromptTitle("客户公关计划反馈");
                        String message = "请及时对"+visitPlanDto.getTitle()+"计划做出应馈";
                        String status = "warning";
                        addCmd.setPromptDetails(message);
                        addCmd.setExpireDate(expireDate);
                        addCmd.setCreatedBy(visitPlanDto.getCreatedBy());
                        addCmd.setPromptId(visitPlanDto.getCreatedBy());
                        addCmd.setQid(visitPlanDto.getId());
                        addCmd.setCreatedName(visitPlanDto.getAdviceDate());
                        addCmd.setPromptStatus(status);
                        addCmd.setDeleteStatus("0"); // 0未删  1已删
                        addCmd.setPromptPath("VisitPlan");
                        promptService.add(addCmd);
                        promptService.toBeDoneToOa(visitPlanDto.getId(), "8", visitPlanDto.getCreatedBy(), 0, 0);
                    }
                }
            }
            log.info("{}-查询客户公关反馈时间,定时任务执行完成", new Date());
        }catch (Exception e){
            log.error("查询客户公关反馈时间,定时任务执行报错！{}", e);
            e.fillInStackTrace();
        }
    }


}
