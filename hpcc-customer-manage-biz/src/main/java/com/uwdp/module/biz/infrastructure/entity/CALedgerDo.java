package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ejlchina.searcher.bean.DbField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * CA台账
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_CALEDGER")
@ApiModel(value = "CALedgerDo entity对象", description = "CA台账")
public class CALedgerDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "FDID", type = IdType.ASSIGN_ID)
    private Long fdId;

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

    @ApiModelProperty("网站名称")
    @TableField("FDWEBSITENAME")
    private String fdwebsitename;

    @ApiModelProperty("网站地址")
    @TableField("FDWEBSITEADDRESS")
    private String fdWebsiteAddress;

    @ApiModelProperty("网站图标")
    @TableField("FDWEBSITEICON")
    private String fdWebsiteIcon;

    @ApiModelProperty("省份")
    @TableField("FDPROVINCE")
    private String fdProvince;

    @ApiModelProperty("适用范围")
    @TableField("FDSCOPE")
    private String fdScope;

    @ApiModelProperty("用户名（账号）")
    @TableField("FDUSERNAME")
    private String fdUserName;

    @ApiModelProperty("密码")
    @TableField("FDPASSWORD")
    private String fdPassword;

    @ApiModelProperty("平台有效期")
    @TableField("FDPLATFORMVALIDITY")
    private String fdPlatformValidity;

    @ApiModelProperty("是否有证书")
    @TableField("FDISCERTIFICATE")
    private String fdIsCertificate;

    @ApiModelProperty("是否实行电子招投标")
    @TableField("FDISELECTRONICBIDDING")
    private String fdIsElectronicBidding;

    @ApiModelProperty("证书有效期")
    @TableField("FDVALIDITYPERIOD")
    private String fdValidityPeriod;

    @ApiModelProperty(value = "证书有效期类型")
    @TableField("FDVALIDITYPERIODTYPE")
    private String fdValidityPeriodType;

    @ApiModelProperty("会员费")
    @TableField("FDMEMBERSHIPFEES")
    private BigDecimal fdMembershipFees;

    @ApiModelProperty("注册人")
    @TableField("FDREGISTRANT")
    private String fdRegistrant;

    @ApiModelProperty("备注")
    @TableField("FDMEMO")
    private String fdMemo;

    @ApiModelProperty("uuid")
    @TableField("UUID")
    private String uuid;

    @ApiModelProperty(value = "权限id")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEDNAME")
    private String createdName;

}
