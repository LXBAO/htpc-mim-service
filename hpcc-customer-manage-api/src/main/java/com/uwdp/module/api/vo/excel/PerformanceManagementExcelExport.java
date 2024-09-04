package com.uwdp.module.api.vo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
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
 * 业绩管理
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "PerformanceManagementExcelExport对象", description = "业绩管理", discriminator = "performanceManagement")
@SearchBean(tables = "T_PERFORMANCEMANAGEMENT")
@EqualsAndHashCode(callSuper = false)
public class PerformanceManagementExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工程项目名称", required = true)
    @DbField("PROJECTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"工程项目名称"}, index = 0)
    // @Length(max = 32, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "工程项目名称(外文)")
    @DbField("PROJECTNAMEENGLISH")
    @ColumnWidth(16)
    @ExcelProperty(value = {"工程项目名称(外文)"}, index = 1)
    // @Length(max = 32, message = "projectNameEnglish长度不在有效范围内")
    private String projectNameEnglish;

    @ApiModelProperty(value = "实施单位", required = true)
    @DbField("IMPLEMENTINGUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"实施单位"}, index = 2)
    // @Length(max = 32, message = "implementingUnit长度不在有效范围内")
    private String implementingUnit;

    @ApiModelProperty(value = "使用资质", required = true)
    @DbField("USEQUALIFICATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"使用资质"}, index = 3)
    // @Length(max = 32, message = "useQualification长度不在有效范围内")
    private String useQualification;

    @ApiModelProperty(value = "合同金额(万元)", required = true)
    @DbField("MONEY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同金额(万元)"}, index = 4)
    // @Length(max = 32, message = "money长度不在有效范围内")
    private String money;

    @ApiModelProperty(value = "合同金额(万美元)")
    @DbField("MONEYUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同金额(万美元)"}, index = 5)
    // @Length(max = 32, message = "moneyUS长度不在有效范围内")
    private String moneyUS;

    @ApiModelProperty(value = "竣/完工结算金额(万元)")
    @DbField("FINISHEDWORKERMONEY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"竣/完工结算金额(万元)"}, index = 6)
    // @Length(max = 32, message = "finishedWorkerMoney长度不在有效范围内")
    private String finishedWorkerMoney;

    @ApiModelProperty(value = "产业领域类别", required = true)
    @DbField("INDUSTRYCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"产业领域类别"}, index = 7)
    // @Length(max = 32, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "工程等级")
    @DbField("ENGINEERINGGRADE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"工程等级"}, index = 8)
    // @Length(max = 32, message = "engineeringGrade长度不在有效范围内")
    private String engineeringGrade;

    @ApiModelProperty(value = "产业链类别", required = true)
    @DbField("INDUSTRYCHAINCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"产业链类别"}, index = 9)
    // @Length(max = 32, message = "industryChainCategory长度不在有效范围内")
    private String industryChainCategory;

    @ApiModelProperty(value = "新产业类别")
    @DbField("NEWINDUSTRYCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"新产业类别"}, index = 10)
    // @Length(max = 32, message = "newIndustryCategory长度不在有效范围内")
    private String newIndustryCategory;

    @ApiModelProperty(value = "商业模式", required = true)
    @DbField("BUSINESSMODEL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"商业模式"}, index = 11)
    // @Length(max = 32, message = "businessModel长度不在有效范围内")
    private String businessModel;

    @ApiModelProperty(value = "所在国别(地区)", required = true)
    @DbField("NATIONALITY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所在国别(地区)"}, index = 12)
    // @Length(max = 32, message = "nationality长度不在有效范围内")
    private String nationality;

    @ApiModelProperty(value = "项目所在地省(国名)", required = true)
    @DbField("PROJECTLANDPROVINCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目所在地省(国名)"}, index = 13)
    // @Length(max = 32, message = "projectLandProvince长度不在有效范围内")
    private String projectLandProvince;

    @ApiModelProperty(value = "项目所在地市(国外省市)", required = true)
    @DbField("PROJECTCITY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目所在地市(国外省市)"}, index = 14)
    // @Length(max = 32, message = "projectCity长度不在有效范围内")
    private String projectCity;

    @ApiModelProperty(value = "合同签订日期", required = true)
    @DbField("DATEOFSIGNING")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同签订日期(例:2023-08-02)"}, index = 15)
    private String dateOfSigning;

    @ApiModelProperty(value = "合同工期开始时间", required = true)
    @DbField("STARTTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同工期开始时间(例:2023-08-02)"}, index = 16)
    private String startTime;

    @ApiModelProperty(value = "合同工期结束时间", required = true)
    @DbField("ENDTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同工期结束时间(例:2023-08-02)"}, index = 17)
    private String endTime;

    @ApiModelProperty(value = "实物量指标1")
    @DbField("PHYSICALINDICATORONE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"实物量指标1"}, index = 18)
    // @Length(max = 32, message = "physicalIndicatorOne长度不在有效范围内")
    private String physicalIndicatorOne;

    @ApiModelProperty(value = "主要实物量1")
    @DbField("MAINPHYSICALINDICATORONE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要实物量1"}, index = 19)
    // @Length(max = 32, message = "mainphysicalIndicatorOne长度不在有效范围内")
    private String mainphysicalIndicatorOne;

    @ApiModelProperty(value = "实施工期开工时间", required = true)
    @DbField("STARTOFCONSTRUCTIONPERIOD")
    @ColumnWidth(16)
    @ExcelProperty(value = {"实施工期开工时间(例:2023-08-02)"}, index = 20)
    private String startOfConstructionPeriod;

    @ApiModelProperty(value = "实物量指标2")
    @DbField("PHYSICALINDICATORTWO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"实物量指标2"}, index = 21)
    // @Length(max = 32, message = "physicalIndicatorTwo长度不在有效范围内")
    private String physicalIndicatorTwo;

    @ApiModelProperty(value = "主要实物量2")
    @DbField("MAINPHYSICALINDICATORTWO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要实物量2"}, index = 22)
    // @Length(max = 32, message = "mainphysicalIndicatorTwo长度不在有效范围内")
    private String mainphysicalIndicatorTwo;

    @ApiModelProperty(value = "实施工期完工时间")
    @DbField("COMPLETIONOFPROJECT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"实施工期完工时间(例:2023-08-02)"}, index = 23)
    private String completionOfProject;

    @ApiModelProperty(value = "完工验收时间")
    @DbField("ACCEPTANCETIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"完工验收时间(例:2023-08-02)"}, index = 24)
    private String acceptanceTime;

    @ApiModelProperty(value = "竣工验收时间")
    @DbField("COMPLETIONACCEPTANCETIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"竣工验收时间(例:2023-08-02)"}, index = 25)
    private String completionAcceptanceTime;

    @ApiModelProperty(value = "是否已办理竣工结算")
    @DbField("COMPLETIONOFCOMPLETION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否已办理竣工结算(0:否;1:是)"}, index = 26)
    // @Length(max = 32, message = "completionOfCompletion长度不在有效范围内")
    private String completionOfCompletion;

    @ApiModelProperty(value = "交工/竣工质量评定等级")
    @DbField("QUALITYASSESSMENT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"交工/竣工质量评定等级"}, index = 27)
    // @Length(max = 32, message = "qualityAssessment长度不在有效范围内")
    private String qualityAssessment;

    @ApiModelProperty(value = "档案归档时间")
    @DbField("FILINGTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"档案归档时间(例:2023-08-02)"}, index = 28)
    private String filingTime;

    @ApiModelProperty(value = "工程获奖情况家级")
    @DbField("WINNERLCASS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"工程获奖情况家级"}, index = 29)
    // @Length(max = 32, message = "winnerlCass长度不在有效范围内")
    private String winnerlCass;

    @ApiModelProperty(value = "工程获奖时间省级")
    @DbField("WINNINGPROVINCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"工程获奖时间省级"}, index = 30)
    // @Length(max = 32, message = "winningProvince长度不在有效范围内")
    private String winningProvince;

    @ApiModelProperty(value = "工程获奖时间市级")
    @DbField("WINNINGCITY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"工程获奖时间市级"}, index = 31)
    // @Length(max = 32, message = "winningCity长度不在有效范围内")
    private String winningCity;

    @ApiModelProperty(value = "实物量指标(其他)文字描述")
    @DbField("LITERALDESCRIPTION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"实物量指标(其他)文字描述"}, index = 32)
    // @Length(max = 32, message = "literalDescription长度不在有效范围内")
    private String literalDescription;

    @ApiModelProperty(value = "项目基本情况或工程概况")
    @DbField("ENGINEERINGCONDITION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目基本情况或工程概况"}, index = 33)
    // @Length(max = 32, message = "engineeringCondition长度不在有效范围内")
    private String engineeringCondition;

    @ApiModelProperty(value = "主要技术指标")
    @DbField("TECHNICALINDEX")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要技术指标"}, index = 34)
    // @Length(max = 32, message = "technicalIndex长度不在有效范围内")
    private String technicalIndex;

    @ApiModelProperty(value = "导致工程竣工延期情况")
    @DbField("POSTPONEMENT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"导致工程竣工延期情况"}, index = 35)
    // @Length(max = 32, message = "postponement长度不在有效范围内")
    private String postponement;

    @ApiModelProperty(value = "工程业绩录入情况")
    @DbField("INPUTINFORMATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"工程业绩录入情况"}, index = 36)
    // @Length(max = 32, message = "inputInformation长度不在有效范围内")
    private String inputInformation;

    @ApiModelProperty(value = "档案保存录入和地点")
    @DbField("SAVEENTRYLOCATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"档案保存录入和地点"}, index = 37)
    // @Length(max = 32, message = "saveEntryLocation长度不在有效范围内")
    private String saveEntryLocation;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人名称"}, index = 38)
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;


    //    @ApiModelProperty(value = "中标通知书")
//    @DbField("NOTICEOFAWARD")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"中标通知书"}, index = 40)
//    // @Length(max = 32, message = "noticeOfAward长度不在有效范围内")
//    private String noticeOfAward;
//
//    @ApiModelProperty(value = "合同文件")
//    @DbField("CONTRACTDOCUMENT")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"合同文件"}, index = 41)
//    // @Length(max = 32, message = "contractDocument长度不在有效范围内")
//    private String contractDocument;
//
//    @ApiModelProperty(value = "项目完工证明或移交证书")
//    @DbField("CERTIFICATE")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"项目完工证明或移交证书"}, index = 42)
//    // @Length(max = 32, message = "certificate长度不在有效范围内")
//    private String certificate;
//
//    @ApiModelProperty(value = "业主或相关方评价证明文件")
//    @DbField("SUPPORTINGDOCUMENT")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"业主或相关方评价证明文件"}, index = 43)
//    // @Length(max = 32, message = "supportingDocument长度不在有效范围内")
//    private String supportingDocument;
//
//    @ApiModelProperty(value = "项目经理,项目技术负责人(总工)聘任文件")
//    @DbField("APPOINTMENTDOCUMENT")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"项目经理,项目技术负责人(总工)聘任文件"}, index = 44)
//    // @Length(max = 32, message = "appointmentDocument长度不在有效范围内")
//    private String appointmentDocument;
//
//    @ApiModelProperty(value = "项目获奖证书或文件")
//    @DbField("AWARDCERTIFICATE")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"项目获奖证书或文件"}, index = 45)
//    // @Length(max = 32, message = "awardCertificate长度不在有效范围内")
//    private String awardCertificate;
    @ApiModelProperty(value = "备注")
    @DbField("REMARK")
    @ColumnWidth(16)
    @ExcelProperty(value = {"备注"}, index = 39)
    // @Length(max = 32, message = "remark长度不在有效范围内")
    private String remark;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属人员(集团工号)"}, index = 40)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "更新者(集团工号)")
    @DbField("UPDATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新者(集团工号)"}, index = 41)
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ExcelIgnore
    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    @ColumnWidth(16)
    private String createdTime;



    @ExcelIgnore
    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    @ColumnWidth(16)
    private String updatedTime;
}
