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
 * 定时任务
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "TimedTaskExcelExport对象", description = "定时任务", discriminator = "timedTask")
@SearchBean(tables = "T_TIMEDTASK")
@EqualsAndHashCode(callSuper = false)
public class TimedTaskExcelExport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "任务名称")
    @DbField("TASKNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"任务名称"}, index = 5)
    // @Length(max = 255, message = "taskName长度不在有效范围内")
    private String taskName;

    @ApiModelProperty(value = "运行频率")
    @DbField("RUNFREQUENCY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"运行频率"}, index = 6)
    // @Length(max = 50, message = "runFrequency长度不在有效范围内")
    private String runFrequency;

    @ApiModelProperty(value = "运行时间")
    @DbField("RUNTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"运行时间"}, index = 7)
    // @Length(max = 255, message = "runTime长度不在有效范围内")
    private String runTime;

    @ApiModelProperty(value = "url")
    @DbField("URL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"url"}, index = 8)
    // @Length(max = 255, message = "url长度不在有效范围内")
    private String url;

    @ApiModelProperty(value = "描述")
    @DbField("description")
    @ColumnWidth(16)
    @ExcelProperty(value = {"描述"}, index = 9)
    // @Length(max = 500, message = "description长度不在有效范围内")
    private String description;

    @ApiModelProperty(value = "状态")
    @DbField("state")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态"}, index = 10)
    // @Length(max = 500, message = "state长度不在有效范围内")
    private String state;
}
