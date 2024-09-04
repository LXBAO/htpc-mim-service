package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_WEEKLY_REPORT_DETAIL")
@ApiModel(value = "WeeklyReportDetailDo entity对象", description = "周报明细表")
public class WeeklyReportDetailDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("日期")
    @TableField("DATESTR")
    private String dateStr;

    @ApiModelProperty("省")
    @TableField("PROVINCE")
    private String province;

    @ApiModelProperty("工作计划")
    @TableField("WORKPLAN")
    private String workPlan;

    @ApiModelProperty("项目名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty("周报主表id")
    @TableField("PARENTID")
    private Long parentId;

    @ApiModelProperty("状态1，0")
    @TableField("`STATUS`")
    private Integer status;

}
