package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 标段
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_SECTION")
@ApiModel(value = "SectionDo entity对象", description = "标段")
public class SectionDo implements Serializable {

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

    @ApiModelProperty("标段名称")
    @TableField("SECTIONNAME")
    private String sectionName;

    @ApiModelProperty("拦标价/预计金额（万元）")
    @TableField("ESTIMATEDAMOUNT")
    private String estimatedAmount;

    @ApiModelProperty("标段内容")
    @TableField("SECTIONCONTENT")
    private String sectionContent;

    @ApiModelProperty("项目id")
    @TableField("PROJECTID")
    private String projectId;

    @ApiModelProperty("预计中标概况")
    @TableField("bidProbability")
    private String bidProbability;
}
