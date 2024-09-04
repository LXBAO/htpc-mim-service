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
 * 业绩台账
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "PerformanceLedgerExcelExport对象", description = "业绩台账", discriminator = "performanceLedger")
@SearchBean(tables = "T_PERFORMANCELEDGER")
@EqualsAndHashCode(callSuper = false)
public class PerformanceLedgerExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @DbField("FDID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"ID"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
    private String fdId;

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

    @ApiModelProperty(value = "年度")
    @DbField("FDYEAR")
    @ColumnWidth(16)
    @ExcelProperty(value = {"年度"}, index = 5)
    // @Length(max = 36, message = "fdYear长度不在有效范围内")
    private String fdYear;

    @ApiModelProperty(value = "项目名称")
    @DbField("FDPROJECTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目名称"}, index = 6)
    // @Length(max = 200, message = "fdProjectName长度不在有效范围内")
    private String fdProjectName;

    @ApiModelProperty(value = "项目所在地")
    @DbField("FDPROJECTADDR")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目所在地"}, index = 7)
    // @Length(max = 255, message = "fdProjectAddr长度不在有效范围内")
    private String fdProjectAddr;

    @ApiModelProperty(value = "发包人（业主）名称")
    @DbField("FDPROPRIETORNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"发包人（业主）名称"}, index = 8)
    // @Length(max = 36, message = "fdProprietorName长度不在有效范围内")
    private String fdProprietorName;

    @ApiModelProperty(value = "发包人地址")
    @DbField("FDPROPRIETORADDR")
    @ColumnWidth(16)
    @ExcelProperty(value = {"发包人地址"}, index = 9)
    // @Length(max = 200, message = "fdProprietorAddr长度不在有效范围内")
    private String fdProprietorAddr;

    @ApiModelProperty(value = "发包人联系方式")
    @DbField("FDPROPRIETORNUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"发包人联系方式"}, index = 10)
    // @Length(max = 36, message = "fdProprietorNumber长度不在有效范围内")
    private String fdProprietorNumber;

    @ApiModelProperty(value = "合同金额(万元)")
    @DbField("FDCONTRACTAMOUNT ")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同金额(万元)"}, index = 11)
    // @Range(max = Long.MAX_VALUE, message = "fdContractAmount 长度不在有效范围内")
    private String fdContractAmount ;

    @ApiModelProperty(value = "建设规模")
    @DbField("FDBUILDINGSIZE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建设规模"}, index = 12)
    // @Length(max = 255, message = "fdBuildingSize长度不在有效范围内")
    private String fdBuildingSize;

    @ApiModelProperty(value = "合同开工日期")
    @DbField("FDCONTRACTSTARTUPDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同开工日期"}, index = 13)
    private String fdContractStartUpDate;

    @ApiModelProperty(value = "合同竣工日期")
    @DbField("FDCONTRACTCOMPLETIONDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同竣工日期"}, index = 14)
    private String fdContractCompletionDate;

    @ApiModelProperty(value = "实际开工日期")
    @DbField("FDREALITYSTARTUPDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"实际开工日期"}, index = 15)
    private String fdRealityStartUpDate;

    @ApiModelProperty(value = "实际竣工日期")
    @DbField("FDREALITYCOMPLETIONDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"实际竣工日期"}, index = 16)
    private String fdRealityCompletionDate;

    @ApiModelProperty(value = "主要工作内容(工程范围）")
    @DbField("FDGROUNDWORK")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要工作内容(工程范围）"}, index = 17)
    // @Length(max = 500, message = "fdGroundWork长度不在有效范围内")
    private String fdGroundWork;

    @ApiModelProperty(value = "工程质量")
    @DbField("FDPROJECTQUALITY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"工程质量"}, index = 18)
    // @Length(max = 200, message = "fdProjectQuality长度不在有效范围内")
    private String fdProjectQuality;

    @ApiModelProperty(value = "投标项目经理")
    @DbField("FDBIDDINGPM")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投标项目经理"}, index = 19)
    // @Length(max = 200, message = "fdBiddingPM长度不在有效范围内")
    private String fdBiddingPM;

    @ApiModelProperty(value = "项目执行经理")
    @DbField("FDEXECUTIVEMANAGER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目执行经理"}, index = 20)
    // @Length(max = 200, message = "fdExecutiveManager长度不在有效范围内")
    private String fdExecutiveManager;

    @ApiModelProperty(value = "项目技术负责人")
    @DbField("FDTECHNICALLEADER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目技术负责人"}, index = 21)
    // @Length(max = 200, message = "fdTechnicalLeader长度不在有效范围内")
    private String fdTechnicalLeader;

    @ApiModelProperty(value = "监理单位")
    @DbField("FDSUPERVISINGUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"监理单位"}, index = 22)
    // @Length(max = 200, message = "fdSupervisingUnit长度不在有效范围内")
    private String fdSupervisingUnit;

    @ApiModelProperty(value = "监理工程师及电话")
    @DbField("FDENGINEERANDTELEPHONE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"监理工程师及电话"}, index = 23)
    // @Length(max = 255, message = "fdEngineerAndTelephone长度不在有效范围内")
    private String fdEngineerAndTelephone;

    @ApiModelProperty(value = "项目描述")
    @DbField("FDPROJECTDESCRIPTION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目描述"}, index = 24)
    // @Length(max = 2000, message = "fdProjectDescription长度不在有效范围内")
    private String fdProjectDescription;


}
