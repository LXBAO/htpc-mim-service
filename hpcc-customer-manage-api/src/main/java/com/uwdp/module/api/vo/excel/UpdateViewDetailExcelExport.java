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
 * 更新查看详情
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "UpdateViewDetailExcelExport对象", description = "更新查看详情", discriminator = "updateViewDetail")
@SearchBean(tables = "T_UPDATEVIEWDETAIL")
@EqualsAndHashCode(callSuper = false)
public class UpdateViewDetailExcelExport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "公司名称")
    @DbField("COMPANYNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"公司名称"}, index = 5)
    // @Length(max = 60, message = "companyName长度不在有效范围内")
    private String companyName;

    @ApiModelProperty(value = "力争金额")
    @DbField("TOAMT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"力争金额"}, index = 6)
    // @Length(max = 60, message = "toAmt长度不在有效范围内")
    private String toAmt;

    @ApiModelProperty(value = "确保金额")
    @DbField("FORMAMT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"确保金额"}, index = 7)
    // @Length(max = 60, message = "formAmt长度不在有效范围内")
    private String formAmt;

    @ApiModelProperty(value = "更新表详情id")
    @DbField("MDTDETAILID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新表详情id"}, index = 8)
    // @Length(max = 60, message = "mdtDetailId长度不在有效范围内")
    private String mdtDetailId;


}
