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
 * 项目赋能明细
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectEnableDetailExcelExport对象", description = "项目赋能明细", discriminator = "projectEnableDetail")
@SearchBean(tables = "T_PROJECTENABLEDETAIL")
@EqualsAndHashCode(callSuper = false)
public class ProjectEnableDetailExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"ID"}, index = 0)
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

    @ApiModelProperty(value = "主文档id")
    @DbField("PARENTID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主文档id"}, index = 5)
    // @Range(max = Long.MAX_VALUE, message = "parentId长度不在有效范围内")
    private String parentId;

    @ApiModelProperty(value = "跟踪日期")
    @DbField("TRACKDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"跟踪日期"}, index = 6)
    private String trackDate;

    @ApiModelProperty(value = "内容")
    @DbField("CONTENT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"内容"}, index = 7)
    // @Length(max = 2000, message = "content长度不在有效范围内")
    private String content;

    @ApiModelProperty(value = "给予板块公司市场赋能说明")
    @DbField("ENABLEEXPLAIN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"给予板块公司市场赋能说明"}, index = 8)
    // @Length(max = 2000, message = "enableExplain长度不在有效范围内")
    private String enableExplain;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATEDBYNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 9)
    // @Length(max = 100, message = "createdByName长度不在有效范围内")
    private String createdByName;


}
