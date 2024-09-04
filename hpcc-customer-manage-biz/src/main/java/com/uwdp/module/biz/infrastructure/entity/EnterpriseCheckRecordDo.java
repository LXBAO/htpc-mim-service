package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 企查查记录
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_ENTERPRISECHECKRECORD")
@ApiModel(value = "EnterpriseCheckRecordDo entity对象", description = "企查查记录")
public class EnterpriseCheckRecordDo implements Serializable {

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

    @ApiModelProperty("主键")
    @TableField("KEYNO")
    private String KeyNo;

    @ApiModelProperty("企业名称")
    @TableField("`NAME`")
    private String Name;

    @ApiModelProperty("统一社会信用代码")
    @TableField("CREDITCODE")
    private String CreditCode;

    @ApiModelProperty("成立日期")
    @TableField("STARTDATE")
    private String StartDate;

    @ApiModelProperty("法定代表人姓名")
    @TableField("OPERNAME")
    private String OperName;

    @ApiModelProperty("状态")
    @TableField("`STATUS`")
    private String Status;

    @ApiModelProperty("注册号")
    @TableField("`NO`")
    private String No;

    @ApiModelProperty("注册地址")
    @TableField("ADDRESS")
    private String Address;

}
