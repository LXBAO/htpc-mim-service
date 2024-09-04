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
 * 指标明细
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_MARKET_DMP_TAG_DETAIL")
@ApiModel(value = "MarketDmpTagDetailDo entity对象", description = "指标明细")
public class MarketDmpTagDetailDo implements Serializable {

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

    @ApiModelProperty("市场指标主表id")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty("公司id")
    @TableField("COMPANY_ID")
    private String companyId;

    @ApiModelProperty("公司名称")
    @TableField("COMPANY_NAME")
    private String companyName;

    @ApiModelProperty("指标金额")
    @TableField("AMOUNT")
    private Double amount;

    @ApiModelProperty("力争金额")
    @TableField("TO_AMT")
    private Double toAmt;

    @ApiModelProperty("确保金额")
    @TableField("FORM_AMT")
    private Double formAmt;

}
