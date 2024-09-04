package com.uwdp.module.api.vo.cmd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 周报
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "WeeklyReport UpdateCmd对象", description = "周报", discriminator = "weeklyReport")
public class WeeklyReportUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @NotNull(message = "主键不能为空")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "总结类型", required = true)
    // @Length(max = 6, message = "sumUpType长度不在有效范围内")
    private String sumUpType;

    @ApiModelProperty(value = "所属开始时间", required = true)
    private LocalDateTime startDateTime;

    @ApiModelProperty(value = "所属结束时间", required = true)
    private LocalDateTime endDateTime;

    @ApiModelProperty(value = "主题")
    // @Length(max = 100, message = "topIc长度不在有效范围内")
    private String topIc;

    @ApiModelProperty(value = "用户名称")
    // @Length(max = 30, message = "userName长度不在有效范围内")
    private String userName;

    @ApiModelProperty(value = "部门id")
    // @Range(max = Long.MAX_VALUE, message = "departmentId长度不在有效范围内")
    private Long departmentId;

    @ApiModelProperty(value = "部门名称")
    // @Length(max = 30, message = "departmentName长度不在有效范围内")
    private String departmentName;

    @ApiModelProperty(value = "总结内容")
    // @Length(max = 50000, message = "contentStr长度不在有效范围内")
    private String contentStr;

    @ApiModelProperty(value = "状态")
    // @Range(max = 1000L, message = "state长度不在有效范围内")
    private Integer state;

    @ApiModelProperty(value = "作者id")
    // @Range(max = Long.MAX_VALUE, message = "userId长度不在有效范围内")
    private Long userId;

    @ApiModelProperty(value = "下周计划")
    // @Length(max = 50000, message = "nextPlan长度不在有效范围内")
    private String nextPlan;

    @ApiModelProperty(value = "行政区域", required = true)
    // @Length(max = 20, message = "administrArea长度不在有效范围内")
    private String administrArea;

    @ApiModelProperty(value = "周报状态", required = true)
    // @Length(max = 20, message = "weeklyStatus长度不在有效范围内")
    private String weeklyStatus;

    @ApiModelProperty(value = "权限id")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "周报明细")
    private List<WeeklyReportDetailAddCmd> weeklyReportDetailAddCmdList;
}
