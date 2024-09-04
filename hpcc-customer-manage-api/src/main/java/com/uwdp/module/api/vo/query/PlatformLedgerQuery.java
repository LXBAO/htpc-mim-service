package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 平台台账
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PlatformLedgerDo Query对象", description = "平台台账", discriminator = "platformLedger")
public class PlatformLedgerQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "ID")
//    private Long fdId;
//
//    @ApiModelProperty(value = "创建者")
//    private String createdBy;
//
//    @ApiModelProperty(value = "创建时间")
//    private LocalDateTime createdTime;
//
//    @ApiModelProperty(value = "更新者")
//    private String updatedBy;
//
//    @ApiModelProperty(value = "更新时间")
//    private LocalDateTime updatedTime;
//
//    @ApiModelProperty(value = "投标网站名称")
//    private String fdWebsiteName;
//
//    @ApiModelProperty(value = "CA证书数量")
//    private String fdCALCENum;
//
//    @ApiModelProperty(value = "维护部门")
//    private String fdProtectionDept;
//
//    @ApiModelProperty(value = "维护人员")
//    private String fdProtectionPerson;
//
//    @ApiModelProperty(value = "说明")
//    private String fdExplain;
//
//    @ApiModelProperty(value = "备注")
//    private String fdMemo;

    @ApiModelProperty(value = "ID")
    private Long fdId;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "网站名称")
    private String fdwebsitename;

    @ApiModelProperty(value = "网站地址")
    private String fdWebsiteAddress;

    @ApiModelProperty(value = "网站图标")
    private String fdWebsiteIcon;

    @ApiModelProperty(value = "省份")
    private String fdProvince;

    @ApiModelProperty(value = "适用范围")
    private String fdScope;

    @ApiModelProperty(value = "用户名（账号）")
    private String fdUserName;

    @ApiModelProperty(value = "密码")
    private String fdPassword;

    @ApiModelProperty(value = "平台有效期")
    private String fdPlatformValidity;

    @ApiModelProperty(value = "是否有证书")
    private String fdIsCertificate;

    @ApiModelProperty(value = "是否实行电子招投标")
    private String fdIsElectronicBidding;

    @ApiModelProperty(value = "证书有效期")
    private String fdValidityPeriod;

    @ApiModelProperty(value = "证书有效期")
    private String fdValidityPeriodType;

    @ApiModelProperty(value = "会员费")
    private BigDecimal fdMembershipFees;

    @ApiModelProperty(value = "注册人")
    private String fdRegistrant;

    @ApiModelProperty(value = "备注")
    private String fdMemo;

    @ApiModelProperty(value = "权限id")
    private String groupFullCode;

    @ApiModelProperty(value = "创建人名称")
    private String createdName;
}
