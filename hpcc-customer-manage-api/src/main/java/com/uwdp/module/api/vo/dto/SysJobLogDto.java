package com.uwdp.module.api.vo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ejlchina.searcher.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 定时任务调度日志表 sys_job_log
 *
 * @author ruoyi
 */
@Data
@SearchBean(tables = "sys_job_log")
public class SysJobLogDto{
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "日志序号")
    private Long jobLogId;

    /**
     * 任务名称
     */
    @ExcelProperty(value = "任务名称")
    private String jobName;

    /**
     * 任务组名
     */
    @ExcelProperty(value = "任务组名")
    private String jobGroup;

    /**
     * 调用目标字符串
     */
    @ExcelProperty(value = "调用目标字符串")
    private String invokeTarget;

    /**
     * 日志信息
     */
    @ExcelProperty(value = "日志信息")
    private String jobMessage;

    /**
     * 执行状态（0正常 1失败）
     */
    @ExcelProperty(value = "执行状态")
    private String status;

    /**
     * 异常信息
     */
    @ExcelProperty(value = "异常信息")
    private String exceptionInfo;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
