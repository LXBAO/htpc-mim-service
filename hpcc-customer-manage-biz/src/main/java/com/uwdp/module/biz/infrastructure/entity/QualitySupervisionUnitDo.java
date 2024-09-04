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
 * 质量监督单位
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_QUALITYSUPERVISIONUNIT")
@ApiModel(value = "QualitySupervisionUnitDo entity对象", description = "质量监督单位")
public class QualitySupervisionUnitDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一标识")
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

    @ApiModelProperty("关联业绩id")
    @TableField("PERFORMANCEID")
    private Long performanceId;

    @ApiModelProperty("名称")
    @TableField("`NAME`")
    private String name;

    @ApiModelProperty("地址")
    @TableField("ADDRESS")
    private String address;

    @ApiModelProperty("邮政编码")
    @TableField("POSTALCODE")
    private String postalCode;

    @ApiModelProperty("联系人")
    @TableField("CONTACTPERSON")
    private String contactPerson;

    @ApiModelProperty("电话")
    @TableField("PHONE")
    private String phone;

}
