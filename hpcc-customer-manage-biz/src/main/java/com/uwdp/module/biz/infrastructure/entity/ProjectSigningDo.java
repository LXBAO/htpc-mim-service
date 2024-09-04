package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目签约
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PROJECTSIGNING")
@ApiModel(value = "ProjectSigningDo entity对象", description = "项目签约")
public class ProjectSigningDo implements Serializable {

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

    @ApiModelProperty("项目编号")
    @TableField("PROJECTNUMBER")
    private String projectNumber;

    @ApiModelProperty("项目名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty(value = "产业领域类别", required = true)
    @TableField("INDUSTRYCATEGORY")
    private String industryCategory;

    @ApiModelProperty(value = "建设地点")
    @TableField("LOCATION")
    private String location;

    @ApiModelProperty(value = "项目金额")
    @TableField("ITEMAMOUNT")
    private Double itemAmount;

    @ApiModelProperty(value = "投资规模(万元)")
    @TableField("INVESTMENTSCALE")
    private Double investmentScale;

    @ApiModelProperty(value = "装机规模(MW)")
    @TableField("INSTALLEDCAPACITY")
    private String installedCapacity;

    @ApiModelProperty(value = "项目来源", required = true)
    @TableField("PROJECTSOURCE")
    private String projectSource;

    @ApiModelProperty(value = "中标日期")
    @TableField("WINDATE")
    private String winDate;

    @ApiModelProperty(value = "建设内容")
    @TableField("CONSTRUCTIONCONTENT")
    private String constructionContent;

    @ApiModelProperty(value = "项目背景")
    @TableField("PROJECTCONTEXT")
    private String projectContext;

    @ApiModelProperty("合同名称")
    @TableField("CONTRACNAME")
    private String contracName;

    @ApiModelProperty("合同金额(万元)")
    @TableField("CONTRACTAMOUNT")
    private BigDecimal contractAmount;

    @ApiModelProperty("合同签订时间")
    @TableField("TIMEOFSIGNING")
    private String timeOfSigning;

    @ApiModelProperty("合同开始时间")
    @TableField("COMMENCEMENTOFCONTRACT")
    private String commencementOfContract;

    @ApiModelProperty("合同结束时间")
    @TableField("CONTRACTENDTIME")
    private String contractEndTime;

    @ApiModelProperty("合同工期(天)")
    @TableField("CONTRACTPROJECTTIME")
    private String contractProjectTime;

    @ApiModelProperty("产业链类别")
    @TableField("CATEGORY")
    private String category;

    @ApiModelProperty("新产业类别")
    @TableField("NEWINDUSTRY")
    private String newIndustry;

    @ApiModelProperty("商业模式类别")
    @TableField("BUSINESSMODEL")
    private String businessModel;

    @ApiModelProperty("商业模式类别名称")
    @TableField("BUSINESSMODELNAME")
    private String businessModelName;

    @ApiModelProperty("项目级别")
    @TableField("PROJECTLEVEL")
    private String projectLevel;

    @ApiModelProperty("合同状态")
    @TableField("CONTRACTSTATUS")
    private String contractStatus;

    @ApiModelProperty("合同甲方")
    @TableField("CONTRACTPARTY")
    private String contractParty;

    @ApiModelProperty("建设单位")
    @TableField("CONSTRUCTIONUNIT")
    private String constructionUnit;

    @ApiModelProperty("建设单位分类")
    @TableField("CLASSIFICATIONOFCONSTRUCTIONUNIT")
    private String classificationOfConstructionUnit;

    @ApiModelProperty("合同乙方")
    @TableField("CONTRACTPARTYB")
    private String contractPartyB;

    @ApiModelProperty("是否直接从建设单位承揽")
    @TableField("UNITCONTRACT")
    private String unitContract;

    @ApiModelProperty("公司内部协同")
    @TableField("INTERNALASSISTANCE")
    private String internalAssistance;

    @ApiModelProperty("录入顺序指标")
    @TableField("SEQUENCEINDEX")
    private String sequenceIndex;

    @ApiModelProperty("集团专责备注栏")
    @TableField("GROUPTRIBUTE")
    private String groupTribute;

    @ApiModelProperty("报表专贡备注栏")
    @TableField("STATEMENTTRIBUTE")
    private String statementTribute;

    @ApiModelProperty("是否为新能源指标转化")
    @TableField("INDEXTRANSFORMATION")
    private String indexTransformation;

    @ApiModelProperty("EPC签约单位")
    @TableField("CONTRACTINGUNIT")
    private String contractingUnit;

    @ApiModelProperty("EPC签约额(亿元)")
    @TableField("EPCCONTRACTAMOUNT")
    private Long epcContractAmount;

    @ApiModelProperty("是否已报送跟踪及中标项目信息")
    @TableField("WINNINGPROJECT")
    private String winningProject;

    @ApiModelProperty("所属类型")
    @TableField("TYPEOFOWNERSHIP")
    private String typeOfOwnership;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEDNAME")
    private String createdName;

    @ApiModelProperty("组织全编码")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty("相关证明文件上传")
    @TableField("RELATEDDOCUMENT")
    private String relatedDocument;

    @ApiModelProperty("上传附件")
    @TableField("ATTACHMENTUPLOADING")
    private String attachmentUploading;

    @ApiModelProperty("主要实物量指标1")
    @TableField("MAINPHYSICALQUANTITYINDEXONE")
    private String mainPhysicalQuantityIndexOne;

    @ApiModelProperty("主要实物量指标2")
    @TableField("MAINPHYSICALQUANTITYINDEXTWO")
    private String mainPhysicalQuantityIndexTwo;

    @ApiModelProperty("主要实物量1")
    @TableField("PRINCIPALPHYSICALQUANTITYONE")
    private String principalPhysicalQuantityOne;

    @ApiModelProperty("主要实物量2")
    @TableField("PRINCIPALPHYSICALQUANTITYTWO")
    private String principalPhysicalQuantityTwo;

    @ApiModelProperty("合同所在地点")
    @TableField("PLACEOFCONTRACT")
    private String placeOfContract;

    @ApiModelProperty("集团专责备注栏")
    @TableField("GROUPSPECIFICREMARKSCOLUMN")
    private String groupSpecificRemarksColumn;

    @ApiModelProperty("报表专责备注栏")
    @TableField("REPORTSPECIALIZEDREMARKSFIELD")
    private String reportSpecializedRemarksField;

    @ApiModelProperty(value = "产业领域")
    @TableField("INDUSTRY")
    private String industry;

    @ApiModelProperty(value = "所属公司名称")
    @TableField("GROUPBELONGUNITNAME")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "项目规模单位")
    @TableField("unit")
    private String unit;
}
