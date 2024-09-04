package com.uwdp.module.api.vo.dto;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 平台台账
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "PlatformLedgerDTO对象", description = "平台台账", discriminator = "platformLedger")
@SearchBean(tables = "T_PLATFORMLEDGER")
public class PlatformLedgerDto implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "ID")
//    @DbField("FDID")
//    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
//    private Long fdId;
//
//    @ApiModelProperty(value = "创建者")
//    @DbField("CREATED_BY")
//    // @Length(max = 64, message = "createdBy长度不在有效范围内")
//    private String createdBy;
//
//    @ApiModelProperty(value = "创建时间")
//    @DbField("CREATED_TIME")
//    private LocalDateTime createdTime;
//
//    @ApiModelProperty(value = "更新者")
//    @DbField("UPDATED_BY")
//    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
//    private String updatedBy;
//
//    @ApiModelProperty(value = "更新时间")
//    @DbField("UPDATED_TIME")
//    private LocalDateTime updatedTime;
//
//    @ApiModelProperty(value = "投标网站名称")
//    @DbField("FDWEBSITENAME")
//    // @Length(max = 500, message = "fdWebsiteName长度不在有效范围内")
//    private String fdWebsiteName;
//
//    @ApiModelProperty(value = "CA证书数量")
//    @DbField("FDCALCENUM")
//    // @Length(max = 200, message = "fdCALCENum长度不在有效范围内")
//    private String fdCALCENum;
//
//    @ApiModelProperty(value = "维护部门")
//    @DbField("FDPROTECTIONDEPT")
//    // @Length(max = 200, message = "fdProtectionDept长度不在有效范围内")
//    private String fdProtectionDept;
//
//    @ApiModelProperty(value = "维护人员")
//    @DbField("FDPROTECTIONPERSON")
//    // @Length(max = 200, message = "fdProtectionPerson长度不在有效范围内")
//    private String fdProtectionPerson;
//
//    @ApiModelProperty(value = "说明")
//    @DbField("FDEXPLAIN")
//    // @Length(max = 2000, message = "fdExplain长度不在有效范围内")
//    private String fdExplain;
//
//    @ApiModelProperty(value = "备注")
//    @DbField("FDMEMO")
//    // @Length(max = 2000, message = "fdMemo长度不在有效范围内")
//    private String fdMemo;

    @ApiModelProperty(value = "ID")
    @DbField("FDID")
    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
    private Long fdId;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEDNAME")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "网站名称")
    @DbField("FDWEBSITENAME")
    // @Length(max = 200, message = "fdwebsitename长度不在有效范围内")
    private String fdwebsitename;

    @ApiModelProperty(value = "网站地址")
    @DbField("FDWEBSITEADDRESS")
    // @Length(max = 500, message = "fdWebsiteAddress长度不在有效范围内")
    private String fdWebsiteAddress;

    @ApiModelProperty(value = "网站图标")
    @DbField("FDWEBSITEICON")
    // @Length(max = 500, message = "fdWebsiteIcon长度不在有效范围内")
    private String fdWebsiteIcon;

    @ApiModelProperty(value = "省份")
    @DbField("FDPROVINCE")
    // @Length(max = 200, message = "fdProvince长度不在有效范围内")
    private String fdProvince;

    @ApiModelProperty(value = "适用范围")
    @DbField("FDSCOPE")
    // @Length(max = 200, message = "fdScope长度不在有效范围内")
    private String fdScope;

    @ApiModelProperty(value = "用户名（账号）")
    @DbField("FDUSERNAME")
    // @Length(max = 100, message = "fdUserName长度不在有效范围内")
    private String fdUserName;

    @ApiModelProperty(value = "密码")
    @DbField("FDPASSWORD")
    // @Length(max = 200, message = "fdPassword长度不在有效范围内")
    private String fdPassword;

    @ApiModelProperty(value = "平台有效期")
    @DbField("FDPLATFORMVALIDITY")
    private String fdPlatformValidity;

    @ApiModelProperty(value = "是否有证书")
    @DbField("FDISCERTIFICATE")
    // @Length(max = 36, message = "fdIsCertificate长度不在有效范围内")
    private String fdIsCertificate;

    @ApiModelProperty(value = "是否实行电子招投标")
    @DbField("FDISELECTRONICBIDDING")
    // @Length(max = 36, message = "fdIsElectronicBidding长度不在有效范围内")
    private String fdIsElectronicBidding;

    @ApiModelProperty(value = "证书有效期")
    @DbField("FDVALIDITYPERIOD")
    private String fdValidityPeriod;

    @ApiModelProperty(value = "证书有效期类型")
    @DbField("FDVALIDITYPERIODTYPE")
    private String fdValidityPeriodType;

    @ApiModelProperty(value = "会员费")
    @DbField("FDMEMBERSHIPFEES")
    // @Range(max = Long.MAX_VALUE, message = "fdMembershipFees长度不在有效范围内")
    private BigDecimal fdMembershipFees;

    @ApiModelProperty(value = "注册人")
    @DbField("FDREGISTRANT")
    // @Length(max = 200, message = "fdRegistrant长度不在有效范围内")
    private String fdRegistrant;

    @ApiModelProperty(value = "备注")
    @DbField("FDMEMO")
    // @Length(max = 2000, message = "fdMemo长度不在有效范围内")
    private String fdMemo;

    @ApiModelProperty(value = "权限id")
    @DbField("GROUPFULLCODE")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    //附件表
    @DbIgnore
    List<AttachmentDto> attachmentDtos;
}
