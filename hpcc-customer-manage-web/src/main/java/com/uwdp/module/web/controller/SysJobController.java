package com.uwdp.module.web.controller;

import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.common.constant.Constants;
import com.uwdp.common.core.controller.BaseController;
import com.uwdp.common.core.domain.AjaxResult;
import com.uwdp.common.exception.job.TaskException;
import com.uwdp.common.utils.StringUtils;
import com.uwdp.module.api.service.ISysJobService;
import com.uwdp.module.api.vo.dto.SysJobDto;
import com.uwdp.module.biz.utils.CronUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ejlchina.searcher.SearchResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 调度任务信息操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/job")
@Api(tags = "定时任务")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "定时任务")
@Validated
public class SysJobController extends BaseController {
    @Autowired
    private ISysJobService jobService;

    @GetMapping("/list")
    public SearchResult<SysJobDto> list(SysJobDto job, HttpServletRequest request) {
        Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        return jobService.selectJobList(map);
    }

    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysJobDto job) {
        return null;
    }

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws SchedulerException {
        jobService.deleteJobByIds(ids);
        return success();
    }

    @GetMapping("/detail/{jobId}")
    public SysJobDto detail(@PathVariable("jobId") Long jobId, ModelMap mmap) {
        mmap.put("name", "job");
        mmap.put("job", jobService.selectJobById(jobId));
        return jobService.selectJobById(jobId);
    }

    /**
     * 任务调度状态修改
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysJobDto job) throws SchedulerException {
        SysJobDto newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }

    /**
     * 任务调度立即执行一次
     */
    @PostMapping("/run")
    @ResponseBody
    public AjaxResult run(SysJobDto job) throws SchedulerException {
        boolean result = jobService.run(job);
        return result ? success() : error("任务不存在或已过期！");
    }

    /**
     * 新增保存调度
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysJobDto job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return error("新增任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI)) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.LOOKUP_LDAP, Constants.LOOKUP_LDAPS})) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), Constants.JOB_ERROR_STR)) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串存在违规");
        }
//        else if (!ScheduleUtils.whiteList(job.getInvokeTarget())) {
//            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不在白名单内");
//        }
//        job.setCreateBy(job.getCreateBy());
        return toAjax(jobService.insertJob(job));
    }

    /**
     * 修改保存调度
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysJobDto job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return error("修改任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI)) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.LOOKUP_LDAP, Constants.LOOKUP_LDAPS})) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), Constants.JOB_ERROR_STR)) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串存在违规");
        }
        return toAjax(jobService.updateJob(job));
    }

    /**
     * 校验cron表达式是否有效
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(SysJobDto job) {
        return jobService.checkCronExpressionIsValid(job.getCronExpression());
    }

    /**
     * 查询cron表达式近5次的执行时间
     */
    @GetMapping("/queryCronExpression")
    @ResponseBody
    public AjaxResult queryCronExpression(@RequestParam(value = "cronExpression", required = false) String cronExpression) {
        if (jobService.checkCronExpressionIsValid(cronExpression)) {
            List<String> dateList = CronUtils.getRecentTriggerTime(cronExpression);
            return success(dateList);
        } else {
            return error("表达式无效");
        }
    }
}
