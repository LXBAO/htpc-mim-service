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
 * 周报
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "WeeklyReportExcelExport对象", description = "周报", discriminator = "weeklyReport")
@SearchBean(tables = "T_WEEKLYREPORT")
@EqualsAndHashCode(callSuper = false)
public class WeeklyReportExcelExport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "总结类型", required = true)
    @DbField("SUMUPTYPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"总结类型"}, index = 5)
    // @Length(max = 6, message = "sumUpType长度不在有效范围内")
    private String sumUpType;

    @ApiModelProperty(value = "所属开始时间", required = true)
    @DbField("STARTDATETIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属开始时间"}, index = 6)
    private String startDateTime;

    @ApiModelProperty(value = "所属结束时间", required = true)
    @DbField("ENDDATETIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属结束时间"}, index = 7)
    private String endDateTime;

    @ApiModelProperty(value = "主题")
    @DbField("TOPIC")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主题"}, index = 8)
    // @Length(max = 100, message = "topIc长度不在有效范围内")
    private String topIc;

    @ApiModelProperty(value = "用户名称")
    @DbField("USERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"用户名称"}, index = 9)
    // @Length(max = 30, message = "userName长度不在有效范围内")
    private String userName;

    @ApiModelProperty(value = "部门id")
    @DbField("DEPARTMENTID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门id"}, index = 10)
    // @Range(max = Long.MAX_VALUE, message = "departmentId长度不在有效范围内")
    private String departmentId;

    @ApiModelProperty(value = "部门名称")
    @DbField("DEPARTMENTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门名称"}, index = 11)
    // @Length(max = 30, message = "departmentName长度不在有效范围内")
    private String departmentName;

    @ApiModelProperty(value = "总结内容")
    @DbField("CONTENTSTR")
    @ColumnWidth(16)
    @ExcelProperty(value = {"总结内容"}, index = 12)
    // @Length(max = 50000, message = "contentStr长度不在有效范围内")
    private String contentStr;

    @ApiModelProperty(value = "状态")
    @DbField("STATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态"}, index = 13)
    // @Range(max = 1000L, message = "state长度不在有效范围内")
    private String state;

    @ApiModelProperty(value = "作者id")
    @DbField("USERID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"作者id"}, index = 14)
    // @Range(max = Long.MAX_VALUE, message = "userId长度不在有效范围内")
    private String userId;

    @ApiModelProperty(value = "下周计划")
    @DbField("NEXTPLAN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"下周计划"}, index = 15)
    // @Length(max = 50000, message = "nextPlan长度不在有效范围内")
    private String nextPlan;

    @ApiModelProperty(value = "行政区域", required = true)
    @DbField("ADMINISTRAREA")
    @ColumnWidth(16)
    @ExcelProperty(value = {"行政区域"}, index = 16)
    // @Length(max = 20, message = "administrArea长度不在有效范围内")
    private String administrArea;

    @ApiModelProperty(value = "周报状态", required = true)
    @DbField("WEEKLYSTATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"周报状态"}, index = 17)
    // @Length(max = 20, message = "weeklyStatus长度不在有效范围内")
    private String weeklyStatus;


}
