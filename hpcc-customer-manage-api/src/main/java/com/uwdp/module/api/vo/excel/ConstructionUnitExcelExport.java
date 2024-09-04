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
 * 建设单位
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ConstructionUnitExcelExport对象", description = "建设单位", discriminator = "constructionUnit")
@SearchBean(tables = "T_CONSTRUCTIONUNIT")
@EqualsAndHashCode(callSuper = false)
public class ConstructionUnitExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"唯一标识"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private String id;

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

    @ApiModelProperty(value = "关联业绩管理id")
    @DbField("PERFORMANCEID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联业绩管理id"}, index = 5)
    // @Range(max = Long.MAX_VALUE, message = "performanceId长度不在有效范围内")
    private String performanceId;

    @ApiModelProperty(value = "国内名称")
    @DbField("DOMESTICNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"国内名称"}, index = 6)
    // @Length(max = 32, message = "domesticName长度不在有效范围内")
    private String domesticName;

    @ApiModelProperty(value = "国外名称(外文)")
    @DbField("FOREIGNNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"国外名称(外文)"}, index = 7)
    // @Length(max = 32, message = "foreignName长度不在有效范围内")
    private String foreignName;

    @ApiModelProperty(value = "地址")
    @DbField("ADDRESS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"地址"}, index = 8)
    // @Length(max = 32, message = "address长度不在有效范围内")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    @DbField("POSTALCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"邮政编码"}, index = 9)
    // @Length(max = 32, message = "postalCode长度不在有效范围内")
    private String postalCode;

    @ApiModelProperty(value = "联系人")
    @DbField("CONTACTPERSON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"联系人"}, index = 10)
    // @Length(max = 32, message = "contactPerson长度不在有效范围内")
    private String contactPerson;

    @ApiModelProperty(value = "电话")
    @DbField("PHONE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"电话"}, index = 11)
    // @Length(max = 32, message = "phone长度不在有效范围内")
    private String phone;


}
