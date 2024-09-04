package com.uwdp.module.api.vo.query;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * <p>
 * 周报
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeeklyReportDo Query对象", description = "周报", discriminator = "weeklyReport")
public class WeeklyReportQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "总结类型", required = true)
    private String sumUpType;

    @ApiModelProperty(value = "所属开始时间", required = true)
    private LocalDateTime startDateTime;

    @ApiModelProperty(value = "所属结束时间", required = true)
    private LocalDateTime endDateTime;

    @ApiModelProperty(value = "主题")
    private String topIc;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "部门id")
    private Long departmentId;

    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    @ApiModelProperty(value = "总结内容")
    private String contentStr;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "作者id")
    private Long userId;

    @ApiModelProperty(value = "下周计划")
    private String nextPlan;

    @ApiModelProperty(value = "行政区域", required = true)
    private String administrArea;

    @ApiModelProperty(value = "权限id")
    private String groupFullCode;

    @ApiModelProperty(value = "创建人名称")
    private String createdName;

    @ApiModelProperty(value = "周报状态", required = true)
    private String weeklyStatus;
}
