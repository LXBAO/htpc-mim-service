package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 业绩管理
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PerformanceManagementDo Query对象", description = "业绩管理", discriminator = "performanceManagement")
public class PerformanceManagementQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "工程项目名称", required = true)
    private String projectName;

    @ApiModelProperty(value = "工程项目名称(外文)")
    private String projectNameEnglish;

    @ApiModelProperty(value = "实施单位", required = true)
    private String implementingUnit;

    @ApiModelProperty(value = "使用资质", required = true)
    private String useQualification;

    @ApiModelProperty(value = "合同金额(万元)", required = true)
    private String money;

    @ApiModelProperty(value = "合同金额(万美元)")
    private String moneyUS;

    @ApiModelProperty(value = "竣/完工结算金额(万元)")
    private String finishedWorkerMoney;

    @ApiModelProperty(value = "产业领域类别", required = true)
    private String industryCategory;

    @ApiModelProperty(value = "工程等级")
    private String engineeringGrade;

    @ApiModelProperty(value = "产业链类别", required = true)
    private String industryChainCategory;

    @ApiModelProperty(value = "新产业类别")
    private String newIndustryCategory;

    @ApiModelProperty(value = "商业模式", required = true)
    private String businessModel;

    @ApiModelProperty(value = "所在国别(地区)", required = true)
    private String nationality;

    @ApiModelProperty(value = "项目所在地省(国名)", required = true)
    private String projectLandProvince;

    @ApiModelProperty(value = "项目所在地市(国外省市)", required = true)
    private String projectCity;

    @ApiModelProperty(value = "合同签订日期", required = true)
    private String dateOfSigning;

    @ApiModelProperty(value = "合同工期开始时间", required = true)
    private String startTime;

    @ApiModelProperty(value = "合同工期结束时间", required = true)
    private String endTime;

    @ApiModelProperty(value = "实物量指标1")
    private String physicalIndicatorOne;

    @ApiModelProperty(value = "主要实物量1")
    private String mainphysicalIndicatorOne;

    @ApiModelProperty(value = "实施工期开工时间", required = true)
    private String startOfConstructionPeriod;

    @ApiModelProperty(value = "实物量指标2")
    private String physicalIndicatorTwo;

    @ApiModelProperty(value = "主要实物量2")
    private String mainphysicalIndicatorTwo;

    @ApiModelProperty(value = "实施工期完工时间")
    private String completionOfProject;

    @ApiModelProperty(value = "完工验收时间")
    private String acceptanceTime;

    @ApiModelProperty(value = "竣工验收时间")
    private String completionAcceptanceTime;

    @ApiModelProperty(value = "是否已办理竣工结算")
    private String completionOfCompletion;

    @ApiModelProperty(value = "交工/竣工质量评定等级")
    private String qualityAssessment;

    @ApiModelProperty(value = "档案归档时间")
    private String filingTime;

    @ApiModelProperty(value = "工程获奖情况家级")
    private String winnerlCass;

    @ApiModelProperty(value = "工程获奖时间省级")
    private String winningProvince;

    @ApiModelProperty(value = "工程获奖时间市级")
    private String winningCity;

    @ApiModelProperty(value = "实物量指标(其他)文字描述")
    private String literalDescription;

    @ApiModelProperty(value = "项目基本情况或工程概况")
    private String engineeringCondition;

    @ApiModelProperty(value = "主要技术指标")
    private String technicalIndex;

    @ApiModelProperty(value = "导致工程竣工延期情况")
    private String postponement;

    @ApiModelProperty(value = "工程业绩录入情况")
    private String inputInformation;

    @ApiModelProperty(value = "档案保存录入和地点")
    private String saveEntryLocation;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "中标通知书")
    private String noticeOfAward;

    @ApiModelProperty(value = "合同文件")
    private String contractDocument;

    @ApiModelProperty(value = "项目完工证明或移交证书")
    private String certificate;

    @ApiModelProperty(value = "业主或相关方评价证明文件")
    private String supportingDocument;

    @ApiModelProperty(value = "项目经理,项目技术负责人(总工)聘任文件")
    private String appointmentDocument;

    @ApiModelProperty(value = "项目获奖证书或文件")
    private String awardCertificate;

    @ApiModelProperty(value = "权限id")
    private String groupFullCode;

    @ApiModelProperty(value = "创建人名称")
    private String createdName;
}
