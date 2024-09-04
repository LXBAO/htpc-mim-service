package com.uwdp.module.web.controller;

import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.common.annotation.Log;
import com.uwdp.common.core.controller.BaseController;
import com.uwdp.common.core.domain.AjaxResult;
import com.uwdp.common.core.page.TableDataInfo;
import com.uwdp.common.enums.BusinessType;
import com.uwdp.common.utils.StringUtils;
import com.uwdp.module.api.service.ISysJobLogService;
import com.uwdp.module.api.service.ISysJobService;
import com.uwdp.module.api.vo.dto.SysJobDto;
import com.uwdp.module.api.vo.dto.SysJobLogDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 调度日志操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/joblog")
@Api(tags = "定时任务调度日志")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "定时任务调度日志")
@Validated
public class SysJobLogController extends BaseController
{

    @Autowired
    private ISysJobService jobService;

    @Autowired
    private ISysJobLogService jobLogService;

    @GetMapping()
    public SysJobDto jobLog(@RequestParam(value = "jobId", required = false) Long jobId, ModelMap mmap)
    {
        return jobService.selectJobById(jobId);
    }

    @GetMapping("/list")
    @ResponseBody
    public SearchResult<SysJobLogDto> list(SysJobLogDto jobLog, HttpServletRequest request)
    {
        Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        return jobLogService.selectJobLogList(map);
    }

    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysJobLogDto jobLog)
    {
        return null;
    }

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jobLogService.deleteJobLogByIds(ids));
    }

    @GetMapping("/detail/{jobLogId}")
    public SysJobLogDto detail(@PathVariable("jobLogId") Long jobLogId, ModelMap mmap)
    {
        return jobLogService.selectJobLogById(jobLogId);
    }

    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        jobLogService.cleanJobLog();
        return success();
    }
}
