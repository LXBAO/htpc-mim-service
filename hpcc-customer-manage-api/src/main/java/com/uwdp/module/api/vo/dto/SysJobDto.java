package com.uwdp.module.api.vo.dto;


import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ejlchina.searcher.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务调度表 sys_job
 *
 * @author ruoyi
 */
@Data
@SearchBean(tables = "sys_job")
public class SysJobDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @ExcelProperty(value = "任务序号")
    private Long jobId;

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
     * cron执行表达式
     */
    @ExcelProperty(value = "执行表达式 ")
    private String cronExpression;

    /**
     * cron计划策略
     */
    @ExcelProperty(value = "计划策略 ")
    private String misfirePolicy;

    /**
     * 是否并发执行（0允许 1禁止）
     */
    @ExcelProperty(value = "并发执行")
    private String concurrent;

    /**
     * 任务状态（0正常 1暂停）
     */
    @ExcelProperty(value = "任务状态")
    private String status;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;
}