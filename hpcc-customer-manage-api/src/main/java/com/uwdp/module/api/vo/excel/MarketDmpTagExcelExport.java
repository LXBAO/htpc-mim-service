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
 * 市场部分公司年度指标
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "MarketDmpTagExcelExport对象", description = "市场部分公司年度指标", discriminator = "market_dmp_tag")
@SearchBean(tables = "T_MARKET_DMP_TAG")
@EqualsAndHashCode(callSuper = false)
public class MarketDmpTagExcelExport extends BaseExcelDTO {

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


    @ApiModelProperty(value = "填报人名字")
    @DbField("USER_NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"填报人名字"}, index = 6)
    // @Length(max = 20, message = "userName长度不在有效范围内")
    private String userName;

    @ApiModelProperty(value = "部门id")
    @DbField("DMPT_ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门id"}, index = 7)
    // @Range(max = Long.MAX_VALUE, message = "dmptId长度不在有效范围内")
    private String dmptId;

    @ApiModelProperty(value = "部门名称")
    @DbField("DMPT_NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门名称"}, index = 8)
    // @Length(max = 30, message = "dmptName长度不在有效范围内")
    private String dmptName;

    @ApiModelProperty(value = "标题")
    @DbField("TITLE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"标题"}, index = 9)
    // @Length(max = 50, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "年度")
    @DbField("YEAR")
    @ColumnWidth(16)
    @ExcelProperty(value = {"年度"}, index = 10)
    // @Length(max = 12, message = "year长度不在有效范围内")
    private String year;

    @ApiModelProperty(value = "力争指标金额总计")
    @DbField("TOAMTTOTAL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"力争指标金额总计"}, index = 11)
    // @Range(max = Long.MAX_VALUE, message = "toAmt长度不在有效范围内")
    private Double toAmtTotal;

    @ApiModelProperty(value = "确保指标金额总计")
    @DbField("FORMAMTTOTAL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"确保指标金额总计"}, index = 12)
    // @Range(max = Long.MAX_VALUE, message = "formAmt长度不在有效范围内")
    private Double formAmtTotal;


}
