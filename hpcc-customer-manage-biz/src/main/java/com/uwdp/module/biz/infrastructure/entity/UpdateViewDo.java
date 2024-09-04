package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ejlchina.searcher.bean.DbField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 更新查看
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_UPDATEVIEW")
@ApiModel(value = "UpdateViewDo entity对象", description = "更新查看")
public class UpdateViewDo implements Serializable {

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

    @ApiModelProperty("更新人")
    @TableField("UPDATEPERSON")
    private String updatePerson;

    @ApiModelProperty("更新时间")
    @TableField("UPDATEDATE")
    private String updateDate;

    @ApiModelProperty("行信息id")
    @TableField("ROWID")
    private String rowId;

    @ApiModelProperty(value = "公司名称")
    @TableField("companyName")
    private String companyName;

    @ApiModelProperty(value = "力争金额")
    @TableField("toAmt")
    private Double toAmt;

    @ApiModelProperty(value = "确保金额")
    @TableField("formAmt")
    private Double formAmt;

    @ApiModelProperty(value = "更新表详情id")
    @TableField("mdtDetailId")
    private Double mdtDetailId;
}
