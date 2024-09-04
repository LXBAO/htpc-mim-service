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
import java.util.List;

/**
 * <p>
 * 周报主表信息
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "WeeklyReportAddCmd对象", description = "周报主表信息", discriminator = "weekly_Report")
public class WeeklyReportAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
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

    @ApiModelProperty(value = "用户名称")
    // @Length(max = 30, message = "userName长度不在有效范围内")
    private String userName;

    @ApiModelProperty(value = "行政区域", required = true)
    // @Length(max = 20, message = "administrAreaId长度不在有效范围内")
    private String administrAreaId;

    @ApiModelProperty(value = "标题")
    // @Length(max = 60, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "部门名称")
    // @Length(max = 100, message = "dmptName长度不在有效范围内")
    private String dmptName;

    @ApiModelProperty(value = "市场分部名称")
    // @Length(max = 40, message = "administrAreaName长度不在有效范围内")
    private String administrAreaName;

    @ApiModelProperty(value = "分部负责人")
    // @Length(max = 30, message = "branchHead长度不在有效范围内")
    private String branchHead;

    @ApiModelProperty(value = "分管负责人")
    // @Length(max = 30, message = "manageHead长度不在有效范围内")
    private String manageHead;

    @ApiModelProperty(value = "周报状态", required = true)
    // @Length(max = 20, message = "weeklyStatus长度不在有效范围内")
    private String weeklyStatus;

    @ApiModelProperty(value = "权限id")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "周报明细")
    private List<WeeklyReportDetailAddCmd> weeklyReportDetailAddCmdList;


}
