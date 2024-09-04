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
 * 联系人
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LinkmanExcelImport对象", description = "联系人", discriminator = "linkman")
@SearchBean(tables = "T_LINKMAN")
@EqualsAndHashCode(callSuper = false)
public class LinkmanExcelImport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "岗位")
    @DbField("JOB")
    @ColumnWidth(16)
    @ExcelProperty(value = {"岗位"}, index = 5)
    // @Length(max = 100, message = "job长度不在有效范围内")
    private String job;

    @ApiModelProperty(value = "姓名")
    @DbField("NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"姓名"}, index = 6)
    // @Length(max = 100, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "状态")
    @DbField("STATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态"}, index = 7)
    // @Length(max = 100, message = "state长度不在有效范围内")
    private String state;

    @ApiModelProperty(value = "投标id")
    @DbField("BIDDINGID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投标id"}, index = 8)
    // @Length(max = 100, message = "biddingId长度不在有效范围内")
    private String biddingId;

    @ApiModelProperty(value = "项目经理id")
    @DbField("nameId")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目经理id"}, index = 9)
    // @Length(max = 100, message = "nameId长度不在有效范围内")
    private String nameId;

    @ApiModelProperty(value = "关联人资id")
    @DbField("hrId")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联人资id"}, index = 10)
    // @Length(max = 100, message = "hrId长度不在有效范围内")
    private String hrId;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
