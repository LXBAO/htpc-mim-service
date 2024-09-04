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
 * 更新查看
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "UpdateViewExcelImport对象", description = "更新查看", discriminator = "updateView")
@SearchBean(tables = "T_UPDATEVIEW")
@EqualsAndHashCode(callSuper = false)
public class UpdateViewExcelImport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "更新人")
    @DbField("UPDATEPERSON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新人"}, index = 5)
    // @Length(max = 50, message = "updatePerson长度不在有效范围内")
    private String updatePerson;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATEDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 6)
    // @Length(max = 50, message = "updateDate长度不在有效范围内")
    private String updateDate;

    @ApiModelProperty(value = "行信息id")
    @DbField("ROWID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"行信息id"}, index = 7)
    // @Length(max = 100, message = "rowId长度不在有效范围内")
    private String rowId;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
