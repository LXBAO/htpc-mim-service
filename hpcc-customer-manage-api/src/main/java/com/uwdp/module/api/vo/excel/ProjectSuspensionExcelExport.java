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
 * 项目中止
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectSuspensionExcelExport对象", description = "项目中止", discriminator = "projectSuspension")
@SearchBean(tables = "T_PROJECTSUSPENSION")
@EqualsAndHashCode(callSuper = false)
public class ProjectSuspensionExcelExport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "项目名称")
    @DbField("PROJECTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目名称"}, index = 5)
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "项目编号")
    @DbField("PROJECTNO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目编号"}, index = 6)
    // @Length(max = 255, message = "projectNo长度不在有效范围内")
    private String projectNo;

    @ApiModelProperty(value = "关联项目id")
    @DbField("PROJECTID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联项目id"}, index = 7)
    // @Length(max = 36, message = "projectId长度不在有效范围内")
    private String projectId;

    @ApiModelProperty(value = "中止人")
    @DbField("DISCONTINUER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"中止人"}, index = 8)
    // @Length(max = 255, message = "discontinuer长度不在有效范围内")
    private String discontinuer;

    @ApiModelProperty(value = "中止人编码")
    @DbField("DISCONTINUERNO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"中止人编码"}, index = 9)
    // @Length(max = 255, message = "discontinuerNo长度不在有效范围内")
    private String discontinuerNo;

    @ApiModelProperty(value = "中止时间")
    @DbField("SUSPENSIONTIME ")
    @ColumnWidth(16)
    @ExcelProperty(value = {"中止时间"}, index = 10)
    private String suspensionTime ;

    @ApiModelProperty(value = "中止原因")
    @DbField("REASONSUSPEND")
    @ColumnWidth(16)
    @ExcelProperty(value = {"中止原因"}, index = 11)
    // @Length(max = 500, message = "reasonSuspend长度不在有效范围内")
    private String reasonSuspend;


}
