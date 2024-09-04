package com.uwdp.module.api.vo.cmd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@ApiModel(value = "PerformanceManagementAddCmd对象", description = "业绩管理", discriminator = "performanceManagement")
public class PerformanceManagementAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "工程项目名称", required = true)
    // @Length(max = 32, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "工程项目名称(外文)")
    // @Length(max = 32, message = "projectNameEnglish长度不在有效范围内")
    private String projectNameEnglish;

    @ApiModelProperty(value = "实施单位", required = true)
    // @Length(max = 32, message = "implementingUnit长度不在有效范围内")
    private String implementingUnit;

    @ApiModelProperty(value = "使用资质", required = true)
    // @Length(max = 32, message = "useQualification长度不在有效范围内")
    private String useQualification;

    @ApiModelProperty(value = "合同金额(万元)", required = true)
    // @Length(max = 32, message = "money长度不在有效范围内")
    private String money;

    @ApiModelProperty(value = "合同金额(万美元)")
    // @Length(max = 32, message = "moneyUS长度不在有效范围内")
    private String moneyUS;

    @ApiModelProperty(value = "竣/完工结算金额(万元)")
    // @Length(max = 32, message = "finishedWorkerMoney长度不在有效范围内")
    private String finishedWorkerMoney;

    @ApiModelProperty(value = "产业领域类别", required = true)
    // @Length(max = 32, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "工程等级")
    // @Length(max = 32, message = "engineeringGrade长度不在有效范围内")
    private String engineeringGrade;

    @ApiModelProperty(value = "产业链类别")
    // @Length(max = 32, message = "industryChainCategory长度不在有效范围内")
    private String industryChainCategory;

    @ApiModelProperty(value = "新产业类别")
    // @Length(max = 32, message = "newIndustryCategory长度不在有效范围内")
    private String newIndustryCategory;

    @ApiModelProperty(value = "商业模式", required = true)
    // @Length(max = 32, message = "businessModel长度不在有效范围内")
    private String businessModel;

    @ApiModelProperty(value = "所在国别(地区)", required = true)
    // @Length(max = 32, message = "nationality长度不在有效范围内")
    private String nationality;

    @ApiModelProperty(value = "项目所在地省(国名)", required = true)
    // @Length(max = 32, message = "projectLandProvince长度不在有效范围内")
    private String projectLandProvince;

    @ApiModelProperty(value = "项目所在地市(国外省市)", required = true)
    // @Length(max = 32, message = "projectCity长度不在有效范围内")
    private String projectCity;

    @ApiModelProperty(value = "合同签订日期", required = true)
    private String dateOfSigning;

    @ApiModelProperty(value = "合同工期开始时间", required = true)
    private String startTime;

    @ApiModelProperty(value = "合同工期结束时间", required = true)
    private String endTime;

    @ApiModelProperty(value = "实物量指标1")
    // @Length(max = 32, message = "physicalIndicatorOne长度不在有效范围内")
    private String physicalIndicatorOne;

    @ApiModelProperty(value = "主要实物量1")
    // @Length(max = 32, message = "mainphysicalIndicatorOne长度不在有效范围内")
    private String mainphysicalIndicatorOne;

    @ApiModelProperty(value = "实施工期开工时间", required = true)
    private String startOfConstructionPeriod;

    @ApiModelProperty(value = "实物量指标2")
    // @Length(max = 32, message = "physicalIndicatorTwo长度不在有效范围内")
    private String physicalIndicatorTwo;

    @ApiModelProperty(value = "主要实物量2")
    // @Length(max = 32, message = "mainphysicalIndicatorTwo长度不在有效范围内")
    private String mainphysicalIndicatorTwo;

    @ApiModelProperty(value = "实施工期完工时间")
    private String completionOfProject;

    @ApiModelProperty(value = "完工验收时间")
    private String acceptanceTime;

    @ApiModelProperty(value = "竣工验收时间")
    private String completionAcceptanceTime;

    @ApiModelProperty(value = "是否已办理竣工结算")
    // @Length(max = 32, message = "completionOfCompletion长度不在有效范围内")
    private String completionOfCompletion;

    @ApiModelProperty(value = "交工/竣工质量评定等级")
    // @Length(max = 32, message = "qualityAssessment长度不在有效范围内")
    private String qualityAssessment;

    @ApiModelProperty(value = "档案归档时间")
    private String filingTime;

    @ApiModelProperty(value = "工程获奖情况家级")
    // @Length(max = 32, message = "winnerlCass长度不在有效范围内")
    private String winnerlCass;

    @ApiModelProperty(value = "工程获奖时间省级")
    // @Length(max = 32, message = "winningProvince长度不在有效范围内")
    private String winningProvince;

    @ApiModelProperty(value = "工程获奖时间市级")
    // @Length(max = 32, message = "winningCity长度不在有效范围内")
    private String winningCity;

    @ApiModelProperty(value = "实物量指标(其他)文字描述")
    // @Length(max = 32, message = "literalDescription长度不在有效范围内")
    private String literalDescription;

    @ApiModelProperty(value = "项目基本情况或工程概况")
    // @Length(max = 32, message = "engineeringCondition长度不在有效范围内")
    private String engineeringCondition;

    @ApiModelProperty(value = "主要技术指标")
    // @Length(max = 32, message = "technicalIndex长度不在有效范围内")
    private String technicalIndex;

    @ApiModelProperty(value = "导致工程竣工延期情况")
    // @Length(max = 32, message = "postponement长度不在有效范围内")
    private String postponement;

    @ApiModelProperty(value = "工程业绩录入情况")
    // @Length(max = 32, message = "inputInformation长度不在有效范围内")
    private String inputInformation;

    @ApiModelProperty(value = "档案保存录入和地点")
    // @Length(max = 32, message = "saveEntryLocation长度不在有效范围内")
    private String saveEntryLocation;

    @ApiModelProperty(value = "备注")
    // @Length(max = 32, message = "remark长度不在有效范围内")
    private String remark;

    @ApiModelProperty(value = "中标通知书")
    // @Length(max = 32, message = "noticeOfAward长度不在有效范围内")
    private String noticeOfAward;

    @ApiModelProperty(value = "合同文件")
    // @Length(max = 32, message = "contractDocument长度不在有效范围内")
    private String contractDocument;

    @ApiModelProperty(value = "项目完工证明或移交证书")
    // @Length(max = 32, message = "certificate长度不在有效范围内")
    private String certificate;

    @ApiModelProperty(value = "业主或相关方评价证明文件")
    // @Length(max = 32, message = "supportingDocument长度不在有效范围内")
    private String supportingDocument;

    @ApiModelProperty(value = "项目经理,项目技术负责人(总工)聘任文件")
    // @Length(max = 32, message = "appointmentDocument长度不在有效范围内")
    private String appointmentDocument;

    @ApiModelProperty(value = "项目获奖证书或文件")
    // @Length(max = 32, message = "awardCertificate长度不在有效范围内")
    private String awardCertificate;

    @ApiModelProperty(value = "权限id")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

}
