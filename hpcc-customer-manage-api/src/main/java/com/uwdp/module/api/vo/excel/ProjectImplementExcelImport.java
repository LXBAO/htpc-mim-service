package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 项目实施
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectImplementExcelImport对象", description = "项目实施", discriminator = "projectImplement")
@SearchBean(tables = "T_PROJECTIMPLEMENT")
@EqualsAndHashCode(callSuper = false)
public class ProjectImplementExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主键"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

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
    private LocalDateTime createdTime;

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
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目名称")
    @DbField("PROJECTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目名称"}, index = 5)
    // @Length(max = 100, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "项目状态")
    @DbField("PROJECTSTATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目状态"}, index = 6)
    // @Length(max = 32, message = "projectState长度不在有效范围内")
    private String projectState;

    @ApiModelProperty(value = "开工时间")
    @DbField("WORKDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"开工时间"}, index = 7)
    private LocalDateTime workDate;

    @ApiModelProperty(value = "投产时间")
    @DbField("COMMISSIONINGDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投产时间"}, index = 8)
    private LocalDateTime commissioningDate;

    @ApiModelProperty(value = "未开工原因")
    @DbField("NONWORKINGCAUSE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"未开工原因"}, index = 9)
    // @Length(max = 200, message = "nonWorkingCause长度不在有效范围内")
    private String nonWorkingCause;

    @ApiModelProperty(value = "项目阶段")
    @DbField("PROJECTSTAGE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目阶段"}, index = 10)
    // @Length(max = 100, message = "projectStage长度不在有效范围内")
    private String projectStage;

    @ApiModelProperty(value = "项目编号")
    @DbField("PROJECTNUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目编号"}, index = 11)
    // @Length(max = 200, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "登记单位")
    @DbField("REGISTRATIONUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"登记单位"}, index = 12)
    // @Length(max = 100, message = "registrationUnit长度不在有效范围内")
    private String registrationUnit;

    @ApiModelProperty(value = "产品领域类别")
    @DbField("INDUSTRYCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"产品领域类别"}, index = 13)
    // @Length(max = 200, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "登记时间")
    @DbField("REGISTERDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"登记时间"}, index = 14)
    private String registerDate;

    @ApiModelProperty(value = "附件")
    @DbField("FILE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"附件"}, index = 15)
    // @Length(max = 200, message = "file长度不在有效范围内")
    private String file;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATEDBYNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 16)
    // @Length(max = 100, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "权限id")
    @DbField("GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"权限id"}, index = 17)
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "入档状态")
    // @Length(max = 200, message = "inGear长度不在有效范围内")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 9)
    @DbField("INGEAR")
    private String inGear;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
