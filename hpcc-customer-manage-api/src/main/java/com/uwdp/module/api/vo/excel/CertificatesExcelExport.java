package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
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
@ToString
@ApiModel(value = "CertificatesExcelExport对象", description = "荣誉证书", discriminator = "certificates")
@SearchBean(tables = "T_CERTIFICATES")
@EqualsAndHashCode(callSuper = false)
public class CertificatesExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "主键")
//    @DbField("ID")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"主键"}, index = 0)
//    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
//    private String id;

    @ApiModelProperty(value = "获奖单位或项目名称")
    @DbField("PROJECTNAME")
    @ColumnWidth(36)
    @ExcelProperty(value = {"获奖单位或项目名称"}, index = 0)
    // @Length(max = 100, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "荣誉称号或奖项名称")
    @DbField("HONORARYTITLE")
    @ColumnWidth(36)
    @ExcelProperty(value = {"荣誉称号或奖项名称"}, index = 1)
    // @Length(max = 200, message = "honoraryTitle长度不在有效范围内")
    private String honoraryTitle;

    @ApiModelProperty(value = "授奖单位")
    @DbField("AWARDINGUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"授奖单位"}, index = 2)
    // @Length(max = 200, message = "awardingUnit长度不在有效范围内")
    private String awardingUnit;

    @ApiModelProperty(value = "授奖时间")
    @DbField("AWARDTIME")
    @ColumnWidth(32)
    @ExcelProperty(value = {"授奖时间(例:2023-08-02)"}, index = 3)
    private String awardTime;

//    @ApiModelProperty(value = "附件")
//    @DbField("FILE")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"附件"}, index = 9)
//    // @Length(max = 200, message = "file长度不在有效范围内")
//    private String file;

    @ApiModelProperty(value = "文号")
    @DbField("REFERENCENUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"文号"}, index = 4)
    // @Length(max = 100, message = "referenceNumber长度不在有效范围内")
    private String referenceNumber;

    @ApiModelProperty(value = "电子文档序号")
    @DbField("DOCUMENTNUMBER")
    @ColumnWidth(24)
    @ExcelProperty(value = {"电子文档序号"}, index = 5)
    // @Length(max = 100, message = "documentNumber长度不在有效范围内")
    private String documentNumber;

    @ApiModelProperty(value = "是否报党建部")
    @DbField("TAKEBE")
    @ColumnWidth(50)
    @ExcelProperty(value = {"是否报党建部(是为1,否为0)"}, index = 6)
    // @Length(max = 200, message = "takebe长度不在有效范围内")
    private String takebe;

    @ApiModelProperty(value = "备注")
    @DbField("REMARK")
    @ColumnWidth(16)
    @ExcelProperty(value = {"备注"}, index = 7)
    // @Length(max = 200, message = "remark长度不在有效范围内")
    private String remark;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    @ColumnWidth(40)
    @ExcelProperty(value = {"所属人员(集团工号)"}, index = 8)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人名称"}, index = 9)
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    @ColumnWidth(36)
    @ExcelProperty(value = {"更新者(集团工号)"}, index = 10)
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

//
//    @ApiModelProperty(value = "权限id")
//    @DbField("GROUPFULLCODE")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"权限id"}, index = 14)
//    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
//    private String groupFullCode;
}
