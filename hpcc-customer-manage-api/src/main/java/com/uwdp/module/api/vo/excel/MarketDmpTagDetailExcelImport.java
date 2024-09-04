package com.uwdp.module.api.vo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

/**
 * <p>
 * 指标明细
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "MarketDmpTagDetailExcelImport对象", description = "指标明细", discriminator = "market_dmp_tag_detail")
@SearchBean(tables = "T_MARKET_DMP_TAG_DETAIL")
@EqualsAndHashCode(callSuper = false)
public class MarketDmpTagDetailExcelImport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "市场指标主表id", required = true)
    @DbField("PARENT_ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"市场指标主表id"}, index = 5)
    // @Range(max = Long.MAX_VALUE, message = "parentId长度不在有效范围内")
    private Long parentId;

    @ApiModelProperty(value = "公司id")
    @DbField("COMPANY_ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"公司id"}, index = 6)
    // @Range(max = 100, message = "companyId长度不在有效范围内")
    private String companyId;

    @ApiModelProperty(value = "公司名称")
    @DbField("COMPANY_NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"公司名称"}, index = 7)
    // @Length(max = 60, message = "companyName长度不在有效范围内")
    private String companyName;

    @ApiModelProperty(value = "指标金额")
    @DbField("AMOUNT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"指标金额"}, index = 8)
    // @Range(max = Long.MAX_VALUE, message = "amount长度不在有效范围内")
    private Double amount;

    @ApiModelProperty(value = "力争金额")
    @DbField("TO_AMT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"力争金额"}, index = 9)
    // @Range(max = Long.MAX_VALUE, message = "toAmt长度不在有效范围内")
    private Double toAmt;

    @ApiModelProperty(value = "确保金额")
    @DbField("FORM_AMT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"确保金额"}, index = 10)
    // @Range(max = Long.MAX_VALUE, message = "formAmt长度不在有效范围内")
    private Double formAmt;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
