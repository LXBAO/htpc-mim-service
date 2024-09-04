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
 * 监理单位
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "SupervisionUnitExcelImport对象", description = "监理单位", discriminator = "supervisionUnit")
@SearchBean(tables = "T_SUPERVISIONUNIT")
@EqualsAndHashCode(callSuper = false)
public class SupervisionUnitExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"唯一标识"}, index = 0)
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

    @ApiModelProperty(value = "关联业绩id")
    @DbField("PERFORMANCEID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联业绩id"}, index = 5)
    // @Range(max = Long.MAX_VALUE, message = "performanceId长度不在有效范围内")
    private Long performanceId;

    @ApiModelProperty(value = "名称")
    @DbField("NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"名称"}, index = 6)
    // @Length(max = 32, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "总监理工程师名称")
    @DbField("ENGINEERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"总监理工程师名称"}, index = 7)
    // @Length(max = 32, message = "engineerName长度不在有效范围内")
    private String engineerName;

    @ApiModelProperty(value = "电话")
    @DbField("PHONE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"电话"}, index = 8)
    // @Length(max = 32, message = "phone长度不在有效范围内")
    private String phone;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
