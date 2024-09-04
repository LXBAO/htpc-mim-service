package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 企查查记录
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "EnterpriseCheckRecordExcelExport对象", description = "企查查记录", discriminator = "enterpriseCheckRecord")
@SearchBean(tables = "T_ENTERPRISECHECKRECORD")
@EqualsAndHashCode(callSuper = false)
public class EnterpriseCheckRecordExcelExport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "主键")
    @DbField("KEYNO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主键"}, index = 5)
    // @Length(max = 100, message = "keyNo长度不在有效范围内")
    private String keyNo;

    @ApiModelProperty(value = "企业名称")
    @DbField("NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"企业名称"}, index = 6)
    // @Length(max = 1000, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "统一社会信用代码")
    @DbField("CREDITCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"统一社会信用代码"}, index = 7)
    // @Length(max = 100, message = "creditCode长度不在有效范围内")
    private String creditCode;

    @ApiModelProperty(value = "成立日期")
    @DbField("STARTDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"成立日期"}, index = 8)
    // @Length(max = 100, message = "startDate长度不在有效范围内")
    private String startDate;

    @ApiModelProperty(value = "法定代表人姓名")
    @DbField("OPERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"法定代表人姓名"}, index = 9)
    // @Length(max = 100, message = "operName长度不在有效范围内")
    private String operName;

    @ApiModelProperty(value = "状态")
    @DbField("STATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态"}, index = 10)
    // @Length(max = 100, message = "status长度不在有效范围内")
    private String status;

    @ApiModelProperty(value = "注册号")
    @DbField("NO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"注册号"}, index = 11)
    // @Length(max = 100, message = "no长度不在有效范围内")
    private String no;

    @ApiModelProperty(value = "注册地址")
    @DbField("ADDRESS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"注册地址"}, index = 12)
    // @Length(max = 128, message = "address长度不在有效范围内")
    private String address;


}
