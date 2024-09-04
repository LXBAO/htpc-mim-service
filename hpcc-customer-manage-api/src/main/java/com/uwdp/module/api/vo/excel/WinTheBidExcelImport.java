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

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目中标
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "WinTheBidExcelImport对象", description = "项目中标", discriminator = "WinTheBid")
@SearchBean(tables = "T_WINTHEBID")
@EqualsAndHashCode(callSuper = false)
public class WinTheBidExcelImport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "项目名称")
    @DbField("PROJECTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目名称"}, index = 5)
    // @Length(max = 50, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "所属区域")
    @DbField("OWNINGREGION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属区域"}, index = 6)
    // @Length(max = 50, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    @ApiModelProperty(value = "合同额(万元)")
    @DbField("MONEY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同额(万元)"}, index = 7)
    // @Range(max = Long.MAX_VALUE, message = "money长度不在有效范围内")
    private Double money;

    @ApiModelProperty(value = "登记单位")
    @DbField("REGISTRATIONUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"登记单位"}, index = 8)
    // @Length(max = 50, message = "registrationUnit长度不在有效范围内")
    private String registrationUnit;

    @ApiModelProperty(value = "商业模式")
    @DbField("BUSINESSMODEL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"商业模式"}, index = 9)
    // @Length(max = 50, message = "businessModel长度不在有效范围内")
    private String businessModel;

    @ApiModelProperty(value = "产业领域类别")
    @DbField("INDUSTRYCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"产业领域类别"}, index = 10)
    // @Length(max = 50, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "中标日期")
    @DbField("DATA")
    @ColumnWidth(16)
    @ExcelProperty(value = {"中标日期"}, index = 11)
    private String data;

    @ApiModelProperty(value = "项目阶段")
    @DbField("PROJECTPHASE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目阶段"}, index = 12)
    // @Length(max = 50, message = "projectPhase长度不在有效范围内")
    private String projectPhase;

    @ApiModelProperty(value = "项目编号")
    @DbField("ITEMNUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目编号"}, index = 13)
    // @Length(max = 50, message = "itemNumber长度不在有效范围内")
    private String itemNumber;

    @ApiModelProperty(value = "所在省份")
    @DbField("HOMEPROVINCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所在省份"}, index = 14)
    // @Length(max = 50, message = "homeProvince长度不在有效范围内")
    private String homeProvince;

    @ApiModelProperty(value = "预计签约时间")
    @DbField("SIGNINGTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"预计签约时间"}, index = 15)
    private LocalDate signingTime;

    @ApiModelProperty(value = "登记时间")
    @DbField("REGISTRATIONTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"登记时间"}, index = 16)
    private LocalDate registrationTime;

    @ApiModelProperty(value = "是否重大项目")
    @DbField("MAJORPROJECT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否重大项目"}, index = 17)
    // @Length(max = 50, message = "majorProject长度不在有效范围内")
    private String majorProject;

    @ApiModelProperty(value = "是否需要专业评估")
    @DbField("PROFESSIONALEVALUATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否需要专业评估"}, index = 18)
    // @Length(max = 50, message = "professionalEvaluation长度不在有效范围内")
    private String professionalEvaluation;

    @ApiModelProperty(value = "是否获取新能源指标")
    @DbField("NEWENERGY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否获取新能源指标"}, index = 19)
    // @Length(max = 50, message = "newEnergy长度不在有效范围内")
    private String newEnergy;

    @ApiModelProperty(value = "是否三交九直")
    @DbField("WHETHERTHREE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否三交九直"}, index = 20)
    // @Length(max = 50, message = "whetherThree长度不在有效范围内")
    private String whetherThree;

    @ApiModelProperty(value = "审核状态")
    @DbField("AUDITSTATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"审核状态"}, index = 21)
    // @Length(max = 50, message = "auditStatus长度不在有效范围内")
    private String auditStatus;

    @ApiModelProperty(value = "所属公司名称")
    @DbField("groupBelongUnitName")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属公司名称"}, index = 22)
    // @Length(max = 200, message = "GROUPBELONGUNITNAME长度不在有效范围内")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "建设地点名称")
    @DbField("HOMEPROVINCENAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建设地点名称"}, index = 23)
    // @Length(max = 50, message = "homeProvince长度不在有效范围内")
    private String homeProvinceName;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
