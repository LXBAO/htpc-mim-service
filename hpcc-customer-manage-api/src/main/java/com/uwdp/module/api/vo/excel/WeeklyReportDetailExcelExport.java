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
 * 周报明细表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "WeeklyReportDetailExcelExport对象", description = "周报明细表", discriminator = "weekly_Report_Detail")
@SearchBean(tables = "T_WEEKLY_REPORT_DETAIL")
@EqualsAndHashCode(callSuper = false)
public class WeeklyReportDetailExcelExport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "日期")
    @DbField("DATESTR")
    @ColumnWidth(16)
    @ExcelProperty(value = {"日期"}, index = 5)
    // @Length(max = 10, message = "dateStr长度不在有效范围内")
    private String dateStr;

    @ApiModelProperty(value = "省")
    @DbField("PROVINCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"省"}, index = 6)
    // @Length(max = 100, message = "province长度不在有效范围内")
    private String province;

    @ApiModelProperty(value = "工作计划")
    @DbField("WORKPLAN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"工作计划"}, index = 7)
    // @Length(max = 5000, message = "workPlan长度不在有效范围内")
    private String workPlan;

    @ApiModelProperty(value = "项目名称")
    @DbField("PROJECTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目名称"}, index = 8)
    // @Length(max = 200, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "周报主表id")
    @DbField("PARENTID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"周报主表id"}, index = 9)
    // @Range(max = Long.MAX_VALUE, message = "parentId长度不在有效范围内")
    private String parentId;

    @ApiModelProperty(value = "状态1，0")
    @DbField("STATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态1，0"}, index = 10)
    // @Range(max = 1000L, message = "status长度不在有效范围内")
    private String status;


}
