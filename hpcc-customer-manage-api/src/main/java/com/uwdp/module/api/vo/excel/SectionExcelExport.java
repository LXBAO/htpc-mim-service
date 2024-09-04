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

/**
 * <p>
 * 标段
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "SectionExcelExport对象", description = "标段", discriminator = "section")
@SearchBean(tables = "T_SECTION")
@EqualsAndHashCode(callSuper = false)
public class SectionExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主键"}, index = 0)
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

    @ApiModelProperty(value = "标段名称")
    @DbField("SECTIONNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"标段名称"}, index = 5)
    // @Length(max = 255, message = "sectionName长度不在有效范围内")
    private String sectionName;

    @ApiModelProperty(value = "拦标价/预计金额（万元）")
    @DbField("ESTIMATEDAMOUNT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"拦标价/预计金额（万元）"}, index = 6)
    // @Length(max = 50, message = "estimatedAmount长度不在有效范围内")
    private String estimatedAmount;

    @ApiModelProperty(value = "标段内容")
    @DbField("SECTIONCONTENT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"标段内容"}, index = 7)
    // @Length(max = 255, message = "sectionContent长度不在有效范围内")
    private String sectionContent;

    @ApiModelProperty(value = "项目id")
    @DbField("PROJECTID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目id"}, index = 8)
    // @Length(max = 100, message = "projectId长度不在有效范围内")
    private String projectId;


}
