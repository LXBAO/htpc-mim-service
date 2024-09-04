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
 * 更新查看详情
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_UPDATEVIEWDETAIL")
@ApiModel(value = "UpdateViewDetailDo entity对象", description = "更新查看详情")
public class UpdateViewDetailDo implements Serializable {

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

    @ApiModelProperty("公司名称")
    @TableField("COMPANYNAME")
    private String companyName;

    @ApiModelProperty("力争金额")
    @TableField("TOAMT")
    private String toAmt;

    @ApiModelProperty("确保金额")
    @TableField("FORMAMT")
    private String formAmt;

    @ApiModelProperty("更新表详情id")
    @TableField("MDTDETAILID")
    private String mdtDetailId;

    @ApiModelProperty("更新人")
    @TableField("UPDATEPERSON")
    private String updatePerson;

    @ApiModelProperty("更新时间")
    @TableField("UPDATEDATE")
    private String updateDate;

    @ApiModelProperty("更新表id")
    @TableField("mdtId")
    private String mdtId;
}
