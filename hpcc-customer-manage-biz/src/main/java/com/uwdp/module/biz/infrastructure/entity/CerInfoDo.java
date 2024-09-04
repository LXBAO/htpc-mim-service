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
 * 证书信息
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_CERINFO")
@ApiModel(value = "CerInfoDo entity对象", description = "证书信息")
public class CerInfoDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
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

    @ApiModelProperty("关联认证平台id")
    @TableField("FDCAID")
    private String fdCaId;

    @ApiModelProperty("证书名称")
    @TableField("FDCERTIFICATENAME")
    private String fdCertificateName;

    @ApiModelProperty("证书编号")
    @TableField("FDCERTIFICATENO")
    private String fdCertificateNo;

    @ApiModelProperty("附件")
    @TableField("`FILE`")
    private String file;

    @ApiModelProperty("认证时间")
    @TableField("CERTIFICATIONDATE")
    private String certificationDate;

    @ApiModelProperty("到期时间")
    @TableField("EXPIREDATE")
    private String expireDate;

    @ApiModelProperty("关联认证平台名称")
    @TableField("FDCANAME")
    private String fdCaName;

}
