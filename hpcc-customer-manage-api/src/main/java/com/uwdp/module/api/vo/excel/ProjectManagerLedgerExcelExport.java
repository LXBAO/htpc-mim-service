package com.uwdp.module.api.vo.excel;

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

import java.time.LocalDateTime;

/**
 * <p>
 * 项目经理台账
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectManagerLedgerExcelExport对象", description = "项目经理台账", discriminator = "projectManagerLedger")
@SearchBean(tables = "T_PROJECTMANAGERLEDGER")
@EqualsAndHashCode(callSuper = false)
public class ProjectManagerLedgerExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @DbField("FDID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"ID"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
    private String fdId;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 1)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 2)
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新者"}, index = 3)
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 4)
    private String updatedTime;

    @ApiModelProperty(value = "工号")
    @DbField("FDJOBNUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"工号"}, index = 5)
    // @Length(max = 36, message = "fdJobNumber长度不在有效范围内")
    private String fdJobNumber;

    @ApiModelProperty(value = "姓名")
    @DbField("FDPMNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"姓名"}, index = 6)
    // @Length(max = 200, message = "fdPMName长度不在有效范围内")
    private String fdPMName;

    @ApiModelProperty(value = "身份证号码")
    @DbField("FDIDCARDNO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"身份证号码"}, index = 7)
    // @Length(max = 200, message = "fdIDCardNo长度不在有效范围内")
    private String fdIDCardNo;

    @ApiModelProperty(value = "手机号码")
    @DbField("FDPHONENUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"手机号码"}, index = 8)
    // @Length(max = 200, message = "fdPhoneNumber长度不在有效范围内")
    private String fdPhoneNumber;

    @ApiModelProperty(value = "单位")
    @DbField("FDUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"单位"}, index = 9)
    // @Length(max = 200, message = "fdUnit长度不在有效范围内")
    private String fdUnit;

    @ApiModelProperty(value = "部门")
    @DbField("FDDEPARTMENT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门"}, index = 10)
    // @Length(max = 200, message = "fdDepartment长度不在有效范围内")
    private String fdDepartment;

    @ApiModelProperty(value = "岗位")
    @DbField("FDPOSITION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"岗位"}, index = 11)
    // @Length(max = 200, message = "fdPosition长度不在有效范围内")
    private String fdPosition;

    @ApiModelProperty(value = "岗位性质")
    @DbField("FDNATUREOFPOST")
    @ColumnWidth(16)
    @ExcelProperty(value = {"岗位性质"}, index = 12)
    // @Length(max = 200, message = "fdNatureOfPost长度不在有效范围内")
    private String fdNatureOfPost;

    @ApiModelProperty(value = "岗位层级")
    @DbField("FDJOBLEVEL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"岗位层级"}, index = 13)
    // @Length(max = 200, message = "fdJobLevel长度不在有效范围内")
    private String fdJobLevel;

    @ApiModelProperty(value = "职业资格")
    @DbField("FDNVQ")
    @ColumnWidth(16)
    @ExcelProperty(value = {"职业资格"}, index = 14)
    // @Length(max = 500, message = "fdNVQ长度不在有效范围内")
    private String fdNVQ;

    @ApiModelProperty(value = "安全证书")
    @DbField("FDSAFETYCERTIFICATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"安全证书"}, index = 15)
    // @Length(max = 500, message = "fdSafetyCertificate长度不在有效范围内")
    private String fdSafetyCertificate;

    @ApiModelProperty(value = "证书编号")
    @DbField("FDCERTIFICATENO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"证书编号"}, index = 16)
    // @Length(max = 500, message = "fdCertificateNo长度不在有效范围内")
    private String fdCertificateNo;

    @ApiModelProperty(value = "授予单位")
    @DbField("FDGRANTINGUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"授予单位"}, index = 17)
    // @Length(max = 200, message = "fdGrantingUnit长度不在有效范围内")
    private String fdGrantingUnit;

    @ApiModelProperty(value = "授予时间")
    @DbField("FDGRANTTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"授予时间"}, index = 18)
    private String fdGrantTime;

    @ApiModelProperty(value = "获得单位")
    @DbField("FDGAINUNITS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"获得单位"}, index = 19)
    // @Length(max = 200, message = "fdGainUnits长度不在有效范围内")
    private String fdGainUnits;

    @ApiModelProperty(value = "最新注册时间")
    @DbField("FDNEWREGISTERTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"最新注册时间"}, index = 20)
    private String fdNewRegisterTime;

    @ApiModelProperty(value = "到期时间")
    @DbField("FDEXPIREDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"到期时间"}, index = 21)
    private String fdExpireDate;

    @ApiModelProperty(value = "备注")
    @DbField("FDMEMO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"备注"}, index = 22)
    // @Length(max = 2000, message = "fdMemo长度不在有效范围内")
    private String fdMemo;

    @ApiModelProperty(value = "证件名称")
    @DbField("FDCERTIFICATENAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"证件名称"}, index = 23)
    // @Length(max = 200, message = "fdCertificateName长度不在有效范围内")
    private String fdCertificateName;

    @ApiModelProperty(value = "证件编号")
    @DbField("FDCERTIFICATENUM")
    @ColumnWidth(16)
    @ExcelProperty(value = {"证件编号"}, index = 23)
    // @Length(max = 200, message = "fdCertificateNum长度不在有效范围内")
    private String fdCertificateNum;

    @ApiModelProperty(value = "证件内容")
    @DbField("FDCERTIFICATECONTENT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"证件内容"}, index = 24)
    // @Length(max = 200, message = "fdCertificateContent长度不在有效范围内")
    private String fdCertificateContent;

    @ApiModelProperty(value = "证件类别")
    @DbField("FDCERTIFICATECATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"证件类别"}, index = 25)
    // @Length(max = 200, message = "fdCertificateCategory长度不在有效范围内")
    private String fdCertificateCategory;

    @ApiModelProperty(value = "发证日期")
    @DbField("FDLICENCEISSUED")
    @ColumnWidth(16)
    @ExcelProperty(value = {"发证日期"}, index = 26)
    private LocalDateTime fdLicenceIssued;

    @ApiModelProperty(value = "有效期开始日期")
    @DbField("FDEXPIRATIONDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"有效期开始日期"}, index = 27)
    private LocalDateTime fdExpirationDate;

    @ApiModelProperty(value = "有效期截止日期")
    @DbField("FDEXPIRYDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"有效期截止日期"}, index = 28)
    private LocalDateTime fdExpiryDate;

    @ApiModelProperty(value = "人员类型")
    @DbField("FDPERSONNELTYPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"人员类型"}, index = 29)
    // @Length(max = 200, message = "fdPersonnelType长度不在有效范围内")
    private String fdPersonnelType;

    @ApiModelProperty(value = "项目投入状态")
    @DbField("FDINPUTTYPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目投入状态"}, index = 30)
    // @Length(max = 200, message = "fdInputType长度不在有效范围内")
    private String fdInputType;
}
