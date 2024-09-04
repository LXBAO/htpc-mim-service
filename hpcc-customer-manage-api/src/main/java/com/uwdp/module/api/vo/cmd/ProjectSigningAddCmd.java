package com.uwdp.module.api.vo.cmd;

import com.gientech.lcds.workflow.sdk.core.runtime.CandidateInfoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目签约
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectSigningAddCmd对象", description = "项目签约", discriminator = "ProjectSigning")
public class ProjectSigningAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目编号")
    // @Length(max = 255, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "项目名称")
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "产业领域类别", required = true)
    // @Length(max = 255, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "建设地点")
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "项目金额")
    // @Range(max = Long.MAX_VALUE, message = "itemAmount长度不在有效范围内")
    private Double itemAmount;

    @ApiModelProperty(value = "投资规模(万元)")
    // @Range(max = Long.MAX_VALUE, message = "investmentScale长度不在有效范围内")
    private Double investmentScale;

    @ApiModelProperty(value = "装机规模(MW)")
    // @Length(max = 255, message = "installedCapacity长度不在有效范围内")
    private String installedCapacity;

    @ApiModelProperty(value = "项目来源", required = true)
    // @Length(max = 255, message = "projectSource长度不在有效范围内")
    private String projectSource;

    @ApiModelProperty(value = "中标日期")
    // @Length(max = 255, message = "projectSource长度不在有效范围内")
    private String winDate;

    @ApiModelProperty(value = "建设内容")
    // @Length(max = 500, message = "constructionContent长度不在有效范围内")
    private String constructionContent;

    @ApiModelProperty(value = "项目背景")
    // @Length(max = 500, message = "projectContext长度不在有效范围内")
    private String projectContext;





    @ApiModelProperty(value = "合同名称", required = true)
    // @Length(max = 50, message = "contracName长度不在有效范围内")
    private String contracName;

    @ApiModelProperty(value = "合同金额(万元)")
    // @Range(max = Long.MAX_VALUE, message = "contractAmount长度不在有效范围内")
    private Double contractAmount;

    @ApiModelProperty(value = "合同签订时间")
    // @Length(max = 255, message = "timeOfSigning长度不在有效范围内")
    private String timeOfSigning;

    @ApiModelProperty(value = "合同开始时间")
    // @Length(max = 255, message = "commencementOfContract长度不在有效范围内")
    private String commencementOfContract;

    @ApiModelProperty(value = "合同结束时间")
    // @Length(max = 253, message = "contractEndTime长度不在有效范围内")
    private String contractEndTime;

    @ApiModelProperty(value = "合同工期(天)")
    // @Length(max = 255, message = "contractProjectTime长度不在有效范围内")
    private String contractProjectTime;

    @ApiModelProperty(value = "产业链类别")
    // @Length(max = 50, message = "category长度不在有效范围内")
    private String category;

    @ApiModelProperty(value = "新产业类别")
    // @Length(max = 50, message = "newIndustry长度不在有效范围内")
    private String newIndustry;

    @ApiModelProperty(value = "商业模式类别")
    // @Length(max = 50, message = "businessModel长度不在有效范围内")
    private String businessModel;

    @ApiModelProperty(value = "商业模式类别名称")
    // @Length(max = 200, message = "businessModelName长度不在有效范围内")
    private String businessModelName;

    @ApiModelProperty(value = "项目级别")
    // @Length(max = 50, message = "projectLevel长度不在有效范围内")
    private String projectLevel;

    @ApiModelProperty(value = "合同状态")
    // @Length(max = 50, message = "contractStatus长度不在有效范围内")
    private String contractStatus;

    @ApiModelProperty(value = "合同甲方")
    // @Length(max = 200, message = "contractParty长度不在有效范围内")
    private String contractParty;

    @ApiModelProperty(value = "建设单位")
    // @Length(max = 200, message = "constructionUnit长度不在有效范围内")
    private String constructionUnit;

    @ApiModelProperty(value = "建设单位分类")
    // @Length(max = 200, message = "classificationOfConstructionUnit长度不在有效范围内")
    private String classificationOfConstructionUnit;

    @ApiModelProperty(value = "合同乙方")
    // @Length(max = 200, message = "contractPartyB长度不在有效范围内")
    private String contractPartyB;

    @ApiModelProperty(value = "是否直接从建设单位承揽")
    // @Length(max = 50, message = "unitContract长度不在有效范围内")
    private String unitContract;

    @ApiModelProperty(value = "公司内部协同")
    // @Length(max = 200, message = "internalAssistance长度不在有效范围内")
    private String internalAssistance;

    @ApiModelProperty(value = "录入顺序指标")
    // @Length(max = 200, message = "sequenceIndex长度不在有效范围内")
    private String sequenceIndex;

    @ApiModelProperty(value = "集团专贡备注栏")
    // @Length(max = 200, message = "groupTribute长度不在有效范围内")
    private String groupTribute;

    @ApiModelProperty(value = "报表专贡备注栏")
    // @Length(max = 200, message = "statementTribute长度不在有效范围内")
    private String statementTribute;

    @ApiModelProperty(value = "是否为新能源指标转化")
    // @Length(max = 50, message = "indexTransformation长度不在有效范围内")
    private String indexTransformation;

    @ApiModelProperty(value = "EPC签约单位")
    // @Length(max = 200, message = "contractingUnit长度不在有效范围内")
    private String contractingUnit;

    @ApiModelProperty(value = "EPC签约额(亿元)")
    // @Range(max = Long.MAX_VALUE, message = "epcContractAmount长度不在有效范围内")
    private Double epcContractAmount;

    @ApiModelProperty(value = "是否已报送跟踪及中标项目信息")
    // @Length(max = 50, message = "winningProject长度不在有效范围内")
    private String winningProject;

    @ApiModelProperty(value = "所属类型")
    // @Length(max = 200, message = "typeOfOwnership长度不在有效范围内")
    private String typeOfOwnership;

    @ApiModelProperty(value = "创建人名称")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "组织全编码")
    // @Length(max = 255, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "相关证明文件上传")
    // @Length(max = 500, message = "relatedDocument长度不在有效范围内")
    private String relatedDocument;

    @ApiModelProperty(value = "上传附件")
    // @Length(max = 500, message = "attachmentUploading长度不在有效范围内")
    private String attachmentUploading;

    @ApiModelProperty(value = "主要实物量指标1")
    // @Length(max = 255, message = "mainPhysicalQuantityIndexOne长度不在有效范围内")
    private String mainPhysicalQuantityIndexOne;

    @ApiModelProperty(value = "主要实物量指标2")
    // @Length(max = 255, message = "mainPhysicalQuantityIndexTwo长度不在有效范围内")
    private String mainPhysicalQuantityIndexTwo;

    @ApiModelProperty(value = "主要实物量1")
    // @Length(max = 255, message = "principalPhysicalQuantityOne长度不在有效范围内")
    private String principalPhysicalQuantityOne;

    @ApiModelProperty(value = "主要实物量2")
    // @Length(max = 255, message = "principalPhysicalQuantityTwo长度不在有效范围内")
    private String principalPhysicalQuantityTwo;

    @ApiModelProperty(value = "合同所在地点")
    // @Length(max = 255, message = "placeOfContract长度不在有效范围内")
    private String placeOfContract;

    @ApiModelProperty(value = "集团专责备注栏")
    // @Length(max = 2555, message = "groupSpecificRemarksColumn长度不在有效范围内")
    private String groupSpecificRemarksColumn;

    @ApiModelProperty(value = "报表专责备注栏")
    // @Length(max = 2555, message = "reportSpecializedRemarksField长度不在有效范围内")
    private String reportSpecializedRemarksField;

    @ApiModelProperty(value = "接口-真实姓名")
    // @Length(max = 64)
    private String personName;

    @ApiModelProperty(value = "产业领域")
    // @Length(max = 100)
    private String industry;

    @ApiModelProperty(value = "所属公司名称")
    // @Length(max = 200, message = "GROUPBELONGUNITNAME长度不在有效范围内")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "工作流指定审批人")
    protected Map<String, List<CandidateInfoDto>> approveUser;

    @ApiModelProperty(value = "项目规模单位")
    // @Length(max = 50)
    private String unit;

    //附件表
    List<AttachmentAddCmd> addCmdList;
}
