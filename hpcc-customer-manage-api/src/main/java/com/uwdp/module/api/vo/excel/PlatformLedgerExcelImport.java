package com.uwdp.module.api.vo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@ApiModel(value = "PlatformLedgerExcelImport对象", description = "平台台账", discriminator = "platformLedger")
@SearchBean(tables = "T_PLATFORMLEDGER")
@EqualsAndHashCode(callSuper = false)
public class PlatformLedgerExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "ID")
//    @DbField("FDID")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"ID"}, index = 0)
//    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
//    private Long fdId;
//
//    @ApiModelProperty(value = "创建者")
//    @DbField("CREATED_BY")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"创建者"}, index = 1)
//    // @Length(max = 64, message = "createdBy长度不在有效范围内")
//    private String createdBy;
//
//    @ApiModelProperty(value = "创建时间")
//    @DbField("CREATED_TIME")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"创建时间"}, index = 2)
//    private LocalDateTime createdTime;
//
//    @ApiModelProperty(value = "更新者")
//    @DbField("UPDATED_BY")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新者"}, index = 3)
//    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
//    private String updatedBy;
//
//    @ApiModelProperty(value = "更新时间")
//    @DbField("UPDATED_TIME")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新时间"}, index = 4)
//    private LocalDateTime updatedTime;
//
//    @ApiModelProperty(value = "投标网站名称")
//    @DbField("FDWEBSITENAME")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"投标网站名称"}, index = 5)
//    // @Length(max = 500, message = "fdWebsiteName长度不在有效范围内")
//    private String fdWebsiteName;
//
//    @ApiModelProperty(value = "CA证书数量")
//    @DbField("FDCALCENUM")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"CA证书数量"}, index = 6)
//    // @Length(max = 200, message = "fdCALCENum长度不在有效范围内")
//    private String fdCALCENum;
//
//    @ApiModelProperty(value = "维护部门")
//    @DbField("FDPROTECTIONDEPT")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"维护部门"}, index = 7)
//    // @Length(max = 200, message = "fdProtectionDept长度不在有效范围内")
//    private String fdProtectionDept;
//
//    @ApiModelProperty(value = "维护人员")
//    @DbField("FDPROTECTIONPERSON")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"维护人员"}, index = 8)
//    // @Length(max = 200, message = "fdProtectionPerson长度不在有效范围内")
//    private String fdProtectionPerson;
//
//    @ApiModelProperty(value = "说明")
//    @DbField("FDEXPLAIN")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"说明"}, index = 9)
//    // @Length(max = 2000, message = "fdExplain长度不在有效范围内")
//    private String fdExplain;
//
//    @ApiModelProperty(value = "备注")
//    @DbField("FDMEMO")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"备注"}, index = 10)
//    // @Length(max = 2000, message = "fdMemo长度不在有效范围内")
//    private String fdMemo;
//
//    @ExcelIgnore
//    @DbIgnore
//    @JsonIgnore
//    private String rowIndex;

//    @ApiModelProperty(value = "ID")
//    @DbField("FDID")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"ID"}, index = 0)
//    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
//    private Long fdId;
//
//    @ApiModelProperty(value = "创建者")
//    @DbField("CREATED_BY")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"创建者"}, index = 1)
//    // @Length(max = 64, message = "createdBy长度不在有效范围内")
//    private String createdBy;
//
//    @ApiModelProperty(value = "创建时间")
//    @DbField("CREATED_TIME")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"创建时间"}, index = 2)
//    private LocalDateTime createdTime;
//
//    @ApiModelProperty(value = "更新者")
//    @DbField("UPDATED_BY")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新者"}, index = 3)
//    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
//    private String updatedBy;
//
//    @ApiModelProperty(value = "更新时间")
//    @DbField("UPDATED_TIME")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新时间"}, index = 4)
//    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "网站名称")
    @DbField("FDWEBSITENAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"网站名称"}, index = 0)
    // @Length(max = 200, message = "fdwebsitename长度不在有效范围内")
    private String fdwebsitename;

    @ApiModelProperty(value = "网站地址")
    @DbField("FDWEBSITEADDRESS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"网站地址"}, index = 1)
    // @Length(max = 500, message = "fdWebsiteAddress长度不在有效范围内")
    private String fdWebsiteAddress;

//    @ApiModelProperty(value = "网站图标")
//    @DbField("FDWEBSITEICON")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"网站图标"}, index = 2)
//    // @Length(max = 500, message = "fdWebsiteIcon长度不在有效范围内")
//    private String fdWebsiteIcon;

    @ApiModelProperty(value = "省份")
    @DbField("FDPROVINCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"省份"}, index = 2)
    // @Length(max = 200, message = "fdProvince长度不在有效范围内")
    private String fdProvince;

    @ApiModelProperty(value = "适用范围")
    @DbField("FDSCOPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"适用范围"}, index = 3)
    // @Length(max = 200, message = "fdScope长度不在有效范围内")
    private String fdScope;

    @ApiModelProperty(value = "用户名（账号）")
    @DbField("FDUSERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"用户名（账号）"}, index = 4)
    // @Length(max = 100, message = "fdUserName长度不在有效范围内")
    private String fdUserName;

    @ApiModelProperty(value = "密码")
    @DbField("FDPASSWORD")
    @ColumnWidth(16)
    @ExcelProperty(value = {"密码"}, index = 5)
    // @Length(max = 200, message = "fdPassword长度不在有效范围内")
    private String fdPassword;

    @ApiModelProperty(value = "平台有效期")
    @DbField("FDPLATFORMVALIDITY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"平台有效期"}, index = 6)
    private String fdPlatformValidity;

    @ApiModelProperty(value = "是否有证书(0:否;1:是)")
    @DbField("FDISCERTIFICATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否有证书(0:否;1:是)"}, index = 7)
    // @Length(max = 36, message = "fdIsCertificate长度不在有效范围内")
    private String fdIsCertificate;

    @ApiModelProperty(value = "是否实行电子招投标(0:否;1:是)")
    @DbField("FDISELECTRONICBIDDING")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否实行电子招投标(0:否;1:是)"}, index = 8)
    // @Length(max = 36, message = "fdIsElectronicBidding长度不在有效范围内")
    private String fdIsElectronicBidding;

    @ApiModelProperty(value = "证书有效期")
    @DbField("FDVALIDITYPERIOD")
    @ColumnWidth(16)
    @ExcelProperty(value = {"证书有效期"}, index = 9)
    private String fdValidityPeriod;

    @ApiModelProperty(value = "会员费")
    @DbField("FDMEMBERSHIPFEES")
    @ColumnWidth(16)
    @ExcelProperty(value = {"会员费"}, index = 10)
    // @Range(max = Long.MAX_VALUE, message = "fdMembershipFees长度不在有效范围内")
    private String fdMembershipFees;

    @ApiModelProperty(value = "注册人")
    @DbField("FDREGISTRANT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"注册人"}, index = 11)
    // @Length(max = 200, message = "fdRegistrant长度不在有效范围内")
    private String fdRegistrant;

    @ApiModelProperty(value = "备注")
    @DbField("FDMEMO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"备注"}, index = 12)
    // @Length(max = 2000, message = "fdMemo长度不在有效范围内")
    private String fdMemo;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属人员(集团工号)"}, index = 13)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人名称"}, index = 14)
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "更新者(集团工号)")
    @DbField("UPDATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新者(集团工号)"}, index = 15)
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ExcelIgnore
    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    @ColumnWidth(16)
    private String createdTime;



    @ExcelIgnore
    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    @ColumnWidth(16)
    private String updatedTime;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
