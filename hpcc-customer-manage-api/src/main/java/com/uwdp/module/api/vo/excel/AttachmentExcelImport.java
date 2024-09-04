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
 * 附件表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "AttachmentExcelImport对象", description = "附件表", discriminator = "attachment")
@SearchBean(tables = "T_ATTACHMENT")
@EqualsAndHashCode(callSuper = false)
public class AttachmentExcelImport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "文件名")
    @DbField("NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"文件名"}, index = 5)
    // @Length(max = 100, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "文件真实路径")
    @DbField("URL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"文件真实路径"}, index = 6)
    // @Length(max = 300, message = "url长度不在有效范围内")
    private String url;

    @ApiModelProperty(value = "后缀")
    @DbField("SUFFIX")
    @ColumnWidth(16)
    @ExcelProperty(value = {"后缀"}, index = 7)
    // @Length(max = 5, message = "suffix长度不在有效范围内")
    private String suffix;

    @ApiModelProperty(value = "文件大小")
    @DbField("SIZE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"文件大小"}, index = 8)
    // @Range(max = Long.MAX_VALUE, message = "size长度不在有效范围内")
    private Long size;

    @ApiModelProperty(value = "是否有效")
    @DbField("VALIDFLAG")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否有效"}, index = 9)
    // @Range(max = 10000000000L, message = "validFlag长度不在有效范围内")
    private Integer validFlag;

    @ApiModelProperty(value = "作者")
    @DbField("orderType")
    @ColumnWidth(16)
    @ExcelProperty(value = {"作者"}, index = 10)
    // @Range(max = Long.MAX_VALUE, message = "orderType长度不在有效范围内")
    private Long orderType;

    @ApiModelProperty(value = "码值不需要和控件绑定就为空")
    @DbField("PUBCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"码值不需要和控件绑定就为空"}, index = 11)
    // @Length(max = 16, message = "pubCode长度不在有效范围内")
    private String pubCode;

    @ApiModelProperty(value = "单子id")
    @DbField("ORDERID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"单子id"}, index = 12)
    // @Length(max = 30, message = "orderId长度不在有效范围内")
    private String orderId;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
