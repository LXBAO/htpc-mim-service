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
 * 历史记录表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LogSheetExcelExport对象", description = "历史记录表", discriminator = "logSheet")
@SearchBean(tables = "T_LOGSHEET")
@EqualsAndHashCode(callSuper = false)
public class LogSheetExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"唯一标识"}, index = 0)
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

    @ApiModelProperty(value = "客户id")
    @DbField("CLIENTID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"客户id"}, index = 5)
    // @Length(max = 100, message = "clientId长度不在有效范围内")
    private String clientId;

    @ApiModelProperty(value = "版本编号")
    @DbField("VERSION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"版本编号"}, index = 6)
    // @Length(max = 100, message = "version长度不在有效范围内")
    private String version;

    @ApiModelProperty(value = "更新人")
    @DbField("UPDATEPERSON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新人"}, index = 7)
    // @Length(max = 100, message = "updatePerson长度不在有效范围内")
    private String updatePerson;

    @ApiModelProperty(value = "客户信息")
    @DbField("CLIENTINFORMATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"客户信息"}, index = 8)
    private String clientInformation;


}
