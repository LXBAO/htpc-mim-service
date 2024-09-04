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
 * 市场部分公司年度指标
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_MARKET_DMP_TAG")
@ApiModel(value = "MarketDmpTagDo entity对象", description = "市场部分公司年度指标")
public class MarketDmpTagDo implements Serializable {

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

    @ApiModelProperty("填报人名字")
    @TableField("USER_NAME")
    private String userName;

    @ApiModelProperty("部门id")
    @TableField("DMPT_ID")
    private Long dmptId;

    @ApiModelProperty("部门名称")
    @TableField("DMPT_NAME")
    private String dmptName;

    @ApiModelProperty("标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty("年度")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "力争指标金额总计")
    @TableField("TOAMTTOTAL")
    private Double toAmtTotal;

    @ApiModelProperty(value = "确保指标金额总计")
    @TableField("FORMAMTTOTAL")
    private Double formAmtTotal;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @TableField("GROUPFULLCODE")
    private String GroupFullCode;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEDNAME")
    private String createdName;
}
