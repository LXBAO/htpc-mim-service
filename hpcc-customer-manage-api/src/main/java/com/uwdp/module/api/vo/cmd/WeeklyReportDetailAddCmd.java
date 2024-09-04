package com.uwdp.module.api.vo.cmd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 周报明细表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "WeeklyReportDetailAddCmd对象", description = "周报明细表", discriminator = "weekly_Report_Detail")
public class WeeklyReportDetailAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "日期")
    // @Length(max = 10, message = "dateStr长度不在有效范围内")
    private String dateStr;

    @ApiModelProperty(value = "省")
    // @Length(max = 100, message = "province长度不在有效范围内")
    private String province;

    @ApiModelProperty(value = "工作计划")
    // @Length(max = 5000, message = "workPlan长度不在有效范围内")
    private String workPlan;

    @ApiModelProperty(value = "项目名称")
    // @Length(max = 200, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "周报主表id")
    // @Range(max = Long.MAX_VALUE, message = "parentId长度不在有效范围内")
    private Long parentId;

    @ApiModelProperty(value = "状态1，0")
    // @Range(max = 1000L, message = "status长度不在有效范围内")
    private Integer status;


}
