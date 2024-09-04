package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PERFORMANCEMANAGEMENT")
@ApiModel(value = "PerformanceManagementDo entity对象", description = "业绩管理")
public class PerformanceManagementDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一标识")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("工程项目名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty("工程项目名称(外文)")
    @TableField("PROJECTNAMEENGLISH")
    private String projectNameEnglish;

    @ApiModelProperty("实施单位")
    @TableField("IMPLEMENTINGUNIT")
    private String implementingUnit;

    @ApiModelProperty("使用资质")
    @TableField("USEQUALIFICATION")
    private String useQualification;

    @ApiModelProperty("合同金额(万元)")
    @TableField("MONEY")
    private String money;

    @ApiModelProperty("合同金额(万美元)")
    @TableField("MONEYUS")
    private String moneyUS;

    @ApiModelProperty("竣/完工结算金额(万元)")
    @TableField("FINISHEDWORKERMONEY")
    private String finishedWorkerMoney;

    @ApiModelProperty("产业领域类别")
    @TableField("INDUSTRYCATEGORY")
    private String industryCategory;

    @ApiModelProperty("工程等级")
    @TableField("ENGINEERINGGRADE")
    private String engineeringGrade;

    @ApiModelProperty("产业链类别")
    @TableField("INDUSTRYCHAINCATEGORY")
    private String industryChainCategory;

    @ApiModelProperty("新产业类别")
    @TableField("NEWINDUSTRYCATEGORY")
    private String newIndustryCategory;

    @ApiModelProperty("商业模式")
    @TableField("BUSINESSMODEL")
    private String businessModel;

    @ApiModelProperty("所在国别(地区)")
    @TableField("NATIONALITY")
    private String nationality;

    @ApiModelProperty("项目所在地省(国名)")
    @TableField("PROJECTLANDPROVINCE")
    private String projectLandProvince;

    @ApiModelProperty("项目所在地市(国外省市)")
    @TableField("PROJECTCITY")
    private String projectCity;

    @ApiModelProperty("合同签订日期")
    @TableField("DATEOFSIGNING")
    private String dateOfSigning;

    @ApiModelProperty("合同工期开始时间")
    @TableField("STARTTIME")
    private String startTime;

    @ApiModelProperty("合同工期结束时间")
    @TableField("ENDTIME")
    private String endTime;

    @ApiModelProperty("实物量指标1")
    @TableField("PHYSICALINDICATORONE")
    private String physicalIndicatorOne;

    @ApiModelProperty("主要实物量1")
    @TableField("MAINPHYSICALINDICATORONE")
    private String mainphysicalIndicatorOne;

    @ApiModelProperty("实施工期开工时间")
    @TableField("STARTOFCONSTRUCTIONPERIOD")
    private String startOfConstructionPeriod;

    @ApiModelProperty("实物量指标2")
    @TableField("PHYSICALINDICATORTWO")
    private String physicalIndicatorTwo;

    @ApiModelProperty("主要实物量2")
    @TableField("MAINPHYSICALINDICATORTWO")
    private String mainphysicalIndicatorTwo;

    @ApiModelProperty("实施工期完工时间")
    @TableField("COMPLETIONOFPROJECT")
    private String completionOfProject;

    @ApiModelProperty("完工验收时间")
    @TableField("ACCEPTANCETIME")
    private String acceptanceTime;

    @ApiModelProperty("竣工验收时间")
    @TableField("COMPLETIONACCEPTANCETIME")
    private String completionAcceptanceTime;

    @ApiModelProperty("是否已办理竣工结算")
    @TableField("COMPLETIONOFCOMPLETION")
    private String completionOfCompletion;

    @ApiModelProperty("交工/竣工质量评定等级")
    @TableField("QUALITYASSESSMENT")
    private String qualityAssessment;

    @ApiModelProperty("档案归档时间")
    @TableField("FILINGTIME")
    private String filingTime;

    @ApiModelProperty("工程获奖情况家级")
    @TableField("WINNERLCASS")
    private String winnerlCass;

    @ApiModelProperty("工程获奖时间省级")
    @TableField("WINNINGPROVINCE")
    private String winningProvince;

    @ApiModelProperty("工程获奖时间市级")
    @TableField("WINNINGCITY")
    private String winningCity;

    @ApiModelProperty("实物量指标(其他)文字描述")
    @TableField("LITERALDESCRIPTION")
    private String literalDescription;

    @ApiModelProperty("项目基本情况或工程概况")
    @TableField("ENGINEERINGCONDITION")
    private String engineeringCondition;

    @ApiModelProperty("主要技术指标")
    @TableField("TECHNICALINDEX")
    private String technicalIndex;

    @ApiModelProperty("导致工程竣工延期情况")
    @TableField("POSTPONEMENT")
    private String postponement;

    @ApiModelProperty("工程业绩录入情况")
    @TableField("INPUTINFORMATION")
    private String inputInformation;

    @ApiModelProperty("档案保存录入和地点")
    @TableField("SAVEENTRYLOCATION")
    private String saveEntryLocation;

    @ApiModelProperty("备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty("中标通知书")
    @TableField("NOTICEOFAWARD")
    private String noticeOfAward;

    @ApiModelProperty("合同文件")
    @TableField("CONTRACTDOCUMENT")
    private String contractDocument;

    @ApiModelProperty("项目完工证明或移交证书")
    @TableField("CERTIFICATE")
    private String certificate;

    @ApiModelProperty("业主或相关方评价证明文件")
    @TableField("SUPPORTINGDOCUMENT")
    private String supportingDocument;

    @ApiModelProperty("项目经理,项目技术负责人(总工)聘任文件")
    @TableField("APPOINTMENTDOCUMENT")
    private String appointmentDocument;

    @ApiModelProperty("项目获奖证书或文件")
    @TableField("AWARDCERTIFICATE")
    private String awardCertificate;

    @ApiModelProperty(value = "权限id")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEDNAME")
    private String createdName;
}
