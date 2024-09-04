package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 客户相关联系人
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "RelevantContactExcelImport对象", description = "客户相关联系人", discriminator = "relevantContact")
@SearchBean(tables = "T_RELEVANTCONTACT")
@EqualsAndHashCode(callSuper = false)
public class RelevantContactExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"唯一标识"}, index = 0)
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

    @ApiModelProperty(value = "关联客户ID", required = true)
    @DbField("CLIENTID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联客户ID"}, index = 5)
    // @Range(max = Long.MAX_VALUE, message = "clientId长度不在有效范围内")
    private Long clientId;

    @ApiModelProperty(value = "联系人姓名", required = true)
    @DbField("NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"联系人姓名"}, index = 6)
    // @Length(max = 50, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "联系人职位")
    @DbField("POSITION ")
    @ColumnWidth(16)
    @ExcelProperty(value = {"联系人职位"}, index = 7)
    // @Length(max = 100, message = "position 长度不在有效范围内")
    private String position ;

    @ApiModelProperty(value = "联系方式", required = true)
    @DbField("CONTACT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"联系方式"}, index = 8)
    // @Length(max = 100, message = "contact长度不在有效范围内")
    private String contact;

    @ApiModelProperty(value = "联系地址")
    @DbField("CONTACTADDRESS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"联系地址"}, index = 9)
    // @Length(max = 255, message = "contactAddress长度不在有效范围内")
    private String contactAddress;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
