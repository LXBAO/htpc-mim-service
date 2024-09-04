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
 * 信息提示
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "PromptExcelExport对象", description = "信息提示", discriminator = "prompt")
@SearchBean(tables = "T_PROMPT")
@EqualsAndHashCode(callSuper = false)
public class PromptExcelExport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "提示信息标题  ")
    @DbField("PROMPTTITLE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"提示信息标题  "}, index = 5)
    // @Length(max = 255, message = "promptTitle长度不在有效范围内")
    private String promptTitle;

    @ApiModelProperty(value = "提示信息详情")
    @DbField("PROMPTDETAILS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"提示信息详情"}, index = 6)
    // @Length(max = 255, message = "promptDetails长度不在有效范围内")
    private String promptDetails;

    @ApiModelProperty(value = "过期时间")
    @DbField("EXPIREDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"过期时间"}, index = 7)
    private String expireDate;

    @ApiModelProperty(value = "需要提示的工号")
    @DbField("PROMPTID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"需要提示的工号"}, index = 8)
    // @Length(max = 100, message = "promptId长度不在有效范围内")
    private String promptId;

    @ApiModelProperty(value = "已读/未读")
    @DbField("PROMPTSTATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"已读/未读"}, index = 9)
    // @Length(max = 100, message = "promptStatus长度不在有效范围内")
    private String promptStatus;

    @ApiModelProperty(value = "删除/未删")
    @DbField("DELETESTATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"删除/未删"}, index = 10)
    // @Length(max = 100, message = "deleteStatus长度不在有效范围内")
    private String deleteStatus;

    @ApiModelProperty(value = "QID")
    @DbField("QID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"QID"}, index = 11)
    // @Range(max = Long.MAX_VALUE, message = "qid长度不在有效范围内")
    private String qid;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATEDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 12)
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "跳转路径")
    @DbField("PROMPTPATH")
    @ColumnWidth(16)
    @ExcelProperty(value = {"跳转路径"}, index = 13)
    // @Length(max = 255, message = "promptPath长度不在有效范围内")
    private String promptPath;




}
