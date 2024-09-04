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
 * 公关实施
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "PublicRelationsExcelImport对象", description = "公关实施", discriminator = "publicRelations")
@SearchBean(tables = "T_PUBLICRELATIONS")
@EqualsAndHashCode(callSuper = false)
public class PublicRelationsExcelImport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATED_BY_NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 1)
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

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

    @ApiModelProperty(value = "关联计划", required = true)
    @DbField("VISITPLANID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联计划"}, index = 5)
    // @Range(max = Long.MAX_VALUE, message = "visitPlanId长度不在有效范围内")
    private Long visitPlanId;

    @ApiModelProperty(value = "责任单位", required = true)
    @DbField("DUTYUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"责任单位"}, index = 6)
    // @Length(max = 100, message = "dutyUnit长度不在有效范围内")
    private String dutyUnit;

    @ApiModelProperty(value = "公关时间")
    @DbField("DATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"公关时间"}, index = 7)
    private LocalDateTime date;

    @ApiModelProperty(value = "活动方式")
    @DbField("ACTIVITYWAY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动方式"}, index = 8)
    // @Length(max = 100, message = "activityWay长度不在有效范围内")
    private String activityWay;

    @ApiModelProperty(value = "活动省市")
    @DbField("ACTIVITYPROVINCEANDCITY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动省市"}, index = 9)
    // @Length(max = 100, message = "activityProvinceAndCity长度不在有效范围内")
    private String activityProvinceAndCity;

    @ApiModelProperty(value = "活动地点")
    @DbField("ACTIVITYADDRESS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动地点"}, index = 10)
    // @Length(max = 250, message = "activityAddress长度不在有效范围内")
    private String activityAddress;

    @ApiModelProperty(value = "主要参会人")
    @DbField("MAINPERSON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要参会人"}, index = 11)
    // @Length(max = 250, message = "mainPerson长度不在有效范围内")
    private String mainPerson;

    @ApiModelProperty(value = "主要参会人id")
    @DbField("MAINPERSONIDS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要参会人id"}, index = 11)
    // @Length(max = 250, message = "mainPersonIds长度不在有效范围内")
    private String mainPersonIds;

    @ApiModelProperty(value = "备注")
    @DbField("MEMO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"备注"}, index = 12)
    // @Length(max = 500, message = "memo长度不在有效范围内")
    private String memo;

    @ApiModelProperty(value = "公关成果")
    @DbField("RESULTS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"公关成果"}, index = 13)
    // @Length(max = 500, message = "results长度不在有效范围内")
    private String results;

    @ApiModelProperty(value = "后续事项跟踪人")
    @DbField("FOLLOWERS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"后续事项跟踪人"}, index = 14)
    // @Length(max = 200, message = "followers长度不在有效范围内")
    private String followers;

    @ApiModelProperty(value = "文件路径")
    @DbField("FILE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"文件路径"}, index = 14)
    // @Length(max = 256, message = "file长度不在有效范围内")
    private String file;

    @ApiModelProperty(value = "活动省市名称")
    @DbField("ACTIVITYPROVINCEANDCITYNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动省市名称"}, index = 15)
    // @Length(max = 100, message = "activityProvinceAndCityName长度不在有效范围内")
    private String activityProvinceAndCityName;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
