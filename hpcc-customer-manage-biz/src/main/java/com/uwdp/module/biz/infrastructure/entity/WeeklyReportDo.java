package com.uwdp.module.biz.infrastructure.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.*;
import com.ejlchina.searcher.bean.DbField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 周报主表信息
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_WEEKLY_REPORT")
@ApiModel(value = "WeeklyReportDo entity对象", description = "周报主表信息")
public class WeeklyReportDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @TableField(value = "max(id)", insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private Long maxId;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("用户名称")
    @TableField("USERNAME")
    private String userName;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEDNAME")
    private String createdName;

    @ApiModelProperty("行政区域")
    @TableField("ADMINISTRAREAID")
    private String administrAreaId;

    @ApiModelProperty("标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty("部门名称")
    @TableField("DMPTNAME")
    private String dmptName;

    @ApiModelProperty("市场分部名称")
    @TableField("ADMINISTRAREANAME")
    private String administrAreaName;

    @ApiModelProperty("分部负责人")
    @TableField("BRANCHHEAD")
    private String branchHead;

    @ApiModelProperty("分管负责人")
    @TableField("MANAGEHEAD")
    private String manageHead;

    @ApiModelProperty(value = "权限id")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty(value = "周报状态", required = true)
    @TableField("WEEKLYSTATUS")
    private String weeklyStatus;

}
