package com.uwdp.module.api.vo.dto.searcher;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.uwdp.module.api.vo.dto.AttachmentDto;
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
 * CA台账
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "CALedgerDTO对象", description = "CA台账", discriminator = "CALedger")
@SearchBean(tables = "T_CALEDGER cl left join T_CERINFO c on cl.uuid =  c.fdCaId")
public class CALedgerCerInfoDto implements Serializable {

    @ApiModelProperty(value = "ID")
    @DbField("cl.FDID")
    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
    private Long fdId;

    @ApiModelProperty(value = "创建者")
    @DbField("cl.CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("cl.CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("cl.UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("cl.UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "网站名称")
    @DbField("cl.FDWEBSITENAME")
    // @Length(max = 200, message = "fdwebsitename长度不在有效范围内")
    private String fdwebsitename;

    @ApiModelProperty(value = "网站地址")
    @DbField("cl.FDWEBSITEADDRESS")
    // @Length(max = 500, message = "fdWebsiteAddress长度不在有效范围内")
    private String fdWebsiteAddress;

    @ApiModelProperty(value = "网站图标")
    @DbField("cl.FDWEBSITEICON")
    // @Length(max = 500, message = "fdWebsiteIcon长度不在有效范围内")
    private String fdWebsiteIcon;

    @ApiModelProperty(value = "省份")
    @DbField("cl.FDPROVINCE")
    // @Length(max = 200, message = "fdProvince长度不在有效范围内")
    private String fdProvince;

    @ApiModelProperty(value = "适用范围")
    @DbField("cl.FDSCOPE")
    // @Length(max = 200, message = "fdScope长度不在有效范围内")
    private String fdScope;

    @ApiModelProperty(value = "用户名（账号）")
    @DbField("cl.FDUSERNAME")
    // @Length(max = 100, message = "fdUserName长度不在有效范围内")
    private String fdUserName;

    @ApiModelProperty(value = "密码")
    @DbField("cl.FDPASSWORD")
    // @Length(max = 200, message = "fdPassword长度不在有效范围内")
    private String fdPassword;

    @ApiModelProperty(value = "平台有效期")
    @DbField("cl.FDPLATFORMVALIDITY")
    private String fdPlatformValidity;

    @ApiModelProperty(value = "是否有证书")
    @DbField("cl.FDISCERTIFICATE")
    // @Length(max = 36, message = "fdIsCertificate长度不在有效范围内")
    private String fdIsCertificate;

    @ApiModelProperty(value = "是否实行电子招投标")
    @DbField("cl.FDISELECTRONICBIDDING")
    // @Length(max = 36, message = "fdIsElectronicBidding长度不在有效范围内")
    private String fdIsElectronicBidding;

    @ApiModelProperty(value = "证书有效期")
    @DbField("cl.FDVALIDITYPERIOD")
    private LocalDateTime fdValidityPeriod;

    @ApiModelProperty(value = "证书有效期类型")
    @DbField("cl.FDVALIDITYPERIODTYPE")
    private String fdValidityPeriodType;

    @ApiModelProperty(value = "会员费")
    @DbField("cl.FDMEMBERSHIPFEES")
    // @Range(max = Long.MAX_VALUE, message = "fdMembershipFees长度不在有效范围内")
    private BigDecimal fdMembershipFees;

    @ApiModelProperty(value = "注册人")
    @DbField("cl.FDREGISTRANT")
    // @Length(max = 200, message = "fdRegistrant长度不在有效范围内")
    private String fdRegistrant;

    @ApiModelProperty(value = "备注")
    @DbField("cl.FDMEMO")
    // @Length(max = 2000, message = "fdMemo长度不在有效范围内")
    private String fdMemo;

    @ApiModelProperty(value = "uuid")
    @DbField("cl.UUID")
    // @Length(max = 100, message = "uuid长度不在有效范围内")
    private String uuid;

    @ApiModelProperty(value = "权限id")
    @DbField("cl.GROUPFULLCODE")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "关联认证平台id")
    @DbField("c.FDCAID")
    // @Length(max = 200, message = "fdCaId长度不在有效范围内")
    private String fdCaId;

    @ApiModelProperty(value = "证书编号")
    @DbField("c.FDCERTIFICATENO")
    // @Length(max = 255, message = "fdCertificateNo长度不在有效范围内")
    private String fdCertificateNo;

    @ApiModelProperty(value = "证书名称")
    @DbField("c.FDCERTIFICATENAME")
    // @Length(max = 255, message = "fdCertificateName长度不在有效范围内")
    private String fdCertificateName;

    @ApiModelProperty(value = "认证时间")
    @DbField("c.CERTIFICATIONDATE")
    private LocalDateTime certificationDate;

    @ApiModelProperty(value = "到期时间")
    @DbField("c.EXPIREDATE")
    private LocalDateTime expireDate;

    @ApiModelProperty(value = "关联认证平台名称")
    @DbField("c.FDCANAME")
    // @Length(max = 255, message = "fdCaName长度不在有效范围内")
    private String fdCaName;


}
