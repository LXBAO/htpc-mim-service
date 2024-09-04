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
 * 荣誉证书
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_CERTIFICATES")
@ApiModel(value = "CertificatesDo entity对象", description = "荣誉证书")
public class CertificatesDo implements Serializable {

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

    @ApiModelProperty("获奖单位或项目名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty("荣誉称号或奖项名称")
    @TableField("HONORARYTITLE")
    private String honoraryTitle;

    @ApiModelProperty("授奖单位")
    @TableField("AWARDINGUNIT")
    private String awardingUnit;

    @ApiModelProperty("授奖时间")
    @TableField("AWARDTIME")
    private String awardTime;

    @ApiModelProperty("附件")
    @TableField("`FILE`")
    private String file;

    @ApiModelProperty("文号")
    @TableField("REFERENCENUMBER")
    private String referenceNumber;

    @ApiModelProperty("电子文档序号")
    @TableField("DOCUMENTNUMBER")
    private String documentNumber;

    @ApiModelProperty("是否报党建部")
    @TableField("TAKEBE")
    private String takebe;

    @ApiModelProperty("备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "权限id")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEDNAME")
    private String createdName;
}
