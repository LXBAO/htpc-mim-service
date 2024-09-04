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
 * 下拉列表维护
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "DataExcelImport对象", description = "下拉列表维护", discriminator = "data")
@SearchBean(tables = "T_DATA")
@EqualsAndHashCode(callSuper = false)
public class DataExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @DbField("ID")
    @ExcelIgnore
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 0)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 1)
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新者"}, index = 2)
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 3)
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "父级ID")
    @DbField("PARENT_ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"父级ID"}, index = 4)
    // @Range(max = Long.MAX_VALUE, message = "parentId长度不在有效范围内")
    private Integer parentId;

    @ApiModelProperty(value = "类型")
    @DbField("TYPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"类型"}, index = 5)
    // @Range(max = Long.MAX_VALUE, message = "type长度不在有效范围内")
    private Integer type;

    @ApiModelProperty(value = "类型名称")
    @DbField("TYPENAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"类型名称"}, index = 6)
    // @Length(max = 255, message = "typeName长度不在有效范围内")
    private String typeName;

    @ApiModelProperty(value = "排序")
    @DbField("REMARK")
    @ColumnWidth(16)
    @ExcelProperty(value = {"排序"}, index = 7)
    // @Range(max = Long.MAX_VALUE, message = "rank长度不在有效范围内")
    private Integer remark;

    @ApiModelProperty(value = "值")
    @DbField("VALUE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"值"}, index = 8)
    // @Length(max = 255, message = "value长度不在有效范围内")
    private String value;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
