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
 * 客户相关联系人
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_RELEVANTCONTACT")
@ApiModel(value = "RelevantContactDo entity对象", description = "客户相关联系人")
public class RelevantContactDo implements Serializable {

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

    @ApiModelProperty("关联客户ID")
    @TableField("CLIENTID")
    private Long clientId;

    @ApiModelProperty("联系人姓名")
    @TableField("`NAME`")
    private String name;

    @ApiModelProperty("联系人职位")
    @TableField("POSITION ")
    private String position ;

    @ApiModelProperty("联系方式")
    @TableField("CONTACT")
    private String contact;

    @ApiModelProperty("联系地址")
    @TableField("CONTACTADDRESS")
    private String contactAddress;

}
