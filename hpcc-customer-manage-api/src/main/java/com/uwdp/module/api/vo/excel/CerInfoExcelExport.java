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
 * 证书信息
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "CerInfoExcelExport对象", description = "证书信息", discriminator = "cerInfo")
@SearchBean(tables = "T_CERINFO")
@EqualsAndHashCode(callSuper = false)
public class CerInfoExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "ID")
//    @DbField("ID")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"ID"}, index = 0)
//    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
//    private String id;
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



    @ApiModelProperty(value = "证书名称")
    @DbField("FDCERTIFICATENAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"证书名称"}, index = 0)
    // @Length(max = 255, message = "fdCertificateName长度不在有效范围内")
    private String fdCertificateName;

    @ApiModelProperty(value = "证书编号")
    @DbField("FDCERTIFICATENO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"证书编号"}, index = 1)
    // @Length(max = 255, message = "fdCertificateNo长度不在有效范围内")
    private String fdCertificateNo;

//    @ApiModelProperty(value = "附件")
//    @DbField("FILE")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"附件"}, index = 8)
//    // @Length(max = 500, message = "file长度不在有效范围内")
//    private String file;

    @ApiModelProperty(value = "认证时间")
    @DbField("CERTIFICATIONDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"认证时间(例:2023-08-02)"}, index = 2)
    private String certificationDate;

    @ApiModelProperty(value = "到期时间")
    @DbField("EXPIREDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"到期时间(例:2023-08-02)"}, index = 3)
    private String expireDate;

    @ExcelIgnore
    @DbField("FDCANAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联认证平台名称"}, index = 4)
    // @Length(max = 255, message = "fdCaName长度不在有效范围内")
    private String fdCaName;

    @ExcelIgnore
    @DbField("FDCAID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联认证平台id"}, index = 5)
    // @Length(max = 200, message = "fdCaId长度不在有效范围内")
    private String fdCaId;
}
