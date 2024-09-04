package com.uwdp.module.api.vo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
@ApiModel(value = "CALedgerExcelExport对象", description = "CA台账", discriminator = "CALedger")
@SearchBean(tables = "T_CALEDGER")
@EqualsAndHashCode(callSuper = false)
public class CALedgerExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "ID")
//    @DbField("FDID")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"ID"}, index = 0)
//    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
//    private String fdId;
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
//    private String createdTime;
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
//    private String updatedTime;

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
//    @ExcelProperty(value = {"网站图标"}, index = 7)
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
    @ExcelProperty(value = {"平台有效期(例:2023-08-02)"}, index = 6)
    private String fdPlatformValidity;

    @ApiModelProperty(value = "是否有证书")
    @DbField("FDISCERTIFICATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否有证书(是为1,否为0)"}, index = 7)
    // @Length(max = 36, message = "fdIsCertificate长度不在有效范围内")
    private String fdIsCertificate;

    @ApiModelProperty(value = "是否实行电子招投标")
    @DbField("FDISELECTRONICBIDDING")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否实行电子招投标(是为1,否为0)"}, index = 8)
    // @Length(max = 36, message = "fdIsElectronicBidding长度不在有效范围内")
    private String fdIsElectronicBidding;

    @ApiModelProperty(value = "证书有效期")
    @DbField("FDVALIDITYPERIOD")
    @ColumnWidth(16)
    @ExcelProperty(value = {"证书有效期(例:2023-08-02)"}, index = 9)
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
    @ColumnWidth(40)
    @ExcelProperty(value = {"所属人员(集团工号)"}, index = 13)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    @ColumnWidth(36)
    @ExcelProperty(value = {"更新者(集团工号)"}, index = 14)
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人名称"}, index = 15)
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

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
}
