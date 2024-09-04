package com.uwdp.module.api.vo.dto.searcher;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.uwdp.module.api.vo.dto.AttachmentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
@ApiModel(value = "ProjectSigningDTO对象", description = "项目签约", discriminator = "ProjectSigning")
@SearchBean(tables = "T_PROJECTSIGNING s left join T_PROJECTRECORDS r on s.PROJECTNUMBER = r.ID left join T_LMCWORKFLOW l on s.ID =  l.BIZID")
public class ProjectSigningRecordsWorkflowDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("流程实例id")
    @DbField("l.PROCESSINSTANCEID")
    private String processInstanceId;

    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;

    @ApiModelProperty(value = "唯一标识")
    @DbField("s.ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("s.CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("s.CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("s.UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("s.UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目编号")
    @DbField("s.PROJECTNUMBER")
    // @Length(max = 255, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "项目名称")
    @DbField("s.PROJECTNAME")
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "产业领域类别", required = true)
    @DbField("r.INDUSTRYCATEGORY")
    // @Length(max = 255, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "建设地点")
    @DbField("r.LOCATION")
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "项目金额")
    @DbField("r.ITEMAMOUNT")
    // @Range(max = Long.MAX_VALUE, message = "itemAmount长度不在有效范围内")
    private Double itemAmount;

    @ApiModelProperty(value = "投资规模(万元)")
    @DbField("r.INVESTMENTSCALE")
    // @Range(max = Long.MAX_VALUE, message = "investmentScale长度不在有效范围内")
    private Double investmentScale;

    @ApiModelProperty(value = "装机规模(MW)")
    @DbField("r.INSTALLEDCAPACITY")
    // @Length(max = 255, message = "installedCapacity长度不在有效范围内")
    private String installedCapacity;

    @ApiModelProperty(value = "项目来源", required = true)
    @DbField("r.PROJECTSOURCE")
    // @Length(max = 255, message = "projectSource长度不在有效范围内")
    private String projectSource;

    @ApiModelProperty("是否国际项目")
    @DbField("r.ISFORIEN")
    // @Length(max = 100, message = "isForien长度不在有效范围内")
    private String isForien;

    @ApiModelProperty(value = "中标日期")
    @DbField("s.WINDATE")
    // @Length(max = 255, message = "winDate长度不在有效范围内")
    private String winDate;

    @ApiModelProperty(value = "建设内容")
    @DbField("r.CONSTRUCTIONCONTENT")
    // @Length(max = 500, message = "constructionContent长度不在有效范围内")
    private String constructionContent;

    @ApiModelProperty(value = "项目背景")
    @DbField("r.PROJECTCONTEXT")
    // @Length(max = 500, message = "projectContext长度不在有效范围内")
    private String projectContext;



    @ApiModelProperty(value = "合同名称", required = true)
    @DbField("s.CONTRACNAME")
    // @Length(max = 50, message = "contracName长度不在有效范围内")
    private String contracName;

    @ApiModelProperty(value = "合同金额(万元)")
    @DbField("s.CONTRACTAMOUNT")
    // @Range(max = Long.MAX_VALUE, message = "contractAmount长度不在有效范围内")
    private BigDecimal contractAmount;

    @ApiModelProperty(value = "合同签订时间")
    @DbField("s.TIMEOFSIGNING")
    // @Length(max = 255, message = "timeOfSigning长度不在有效范围内")
    private String timeOfSigning;

    @ApiModelProperty(value = "合同开始时间")
    @DbField("s.COMMENCEMENTOFCONTRACT")
    // @Length(max = 255, message = "commencementOfContract长度不在有效范围内")
    private String commencementOfContract;

    @ApiModelProperty(value = "合同结束时间")
    @DbField("s.CONTRACTENDTIME")
    // @Length(max = 253, message = "contractEndTime长度不在有效范围内")
    private String contractEndTime;

    @ApiModelProperty(value = "合同工期(天)")
    @DbField("s.CONTRACTPROJECTTIME")
    // @Length(max = 255, message = "contractProjectTime长度不在有效范围内")
    private String contractProjectTime;

    @ApiModelProperty(value = "产业链类别")
    @DbField("s.CATEGORY")
    // @Length(max = 50, message = "category长度不在有效范围内")
    private String category;

    @ApiModelProperty(value = "新产业类别")
    @DbField("s.NEWINDUSTRY")
    // @Length(max = 50, message = "newIndustry长度不在有效范围内")
    private String newIndustry;

    @ApiModelProperty(value = "商业模式类别")
    @DbField("s.BUSINESSMODEL")
    // @Length(max = 50, message = "businessModel长度不在有效范围内")
    private String businessModel;

    @ApiModelProperty(value = "商业模式类别名称")
    @DbField("s.BUSINESSMODELNAME")
    // @Length(max = 50, message = "businessModel长度不在有效范围内")
    private String businessModelName;

    @ApiModelProperty(value = "项目级别")
    @DbField("s.PROJECTLEVEL")
    // @Length(max = 50, message = "projectLevel长度不在有效范围内")
    private String projectLevel;

    @ApiModelProperty(value = "合同状态")
    @DbField("s.CONTRACTSTATUS")
    // @Length(max = 50, message = "contractStatus长度不在有效范围内")
    private String contractStatus;

    @ApiModelProperty(value = "合同甲方")
    @DbField("s.CONTRACTPARTY")
    // @Length(max = 50, message = "contractParty长度不在有效范围内")
    private String contractParty;

    @ApiModelProperty(value = "建设单位")
    @DbField("s.CONSTRUCTIONUNIT")
    // @Length(max = 50, message = "constructionUnit长度不在有效范围内")
    private String constructionUnit;

    @ApiModelProperty(value = "建设单位分类")
    @DbField("s.CLASSIFICATIONOFCONSTRUCTIONUNIT")
    // @Length(max = 50, message = "classificationOfConstructionUnit长度不在有效范围内")
    private String classificationOfConstructionUnit;

    @ApiModelProperty(value = "合同乙方")
    @DbField("s.CONTRACTPARTYB")
    // @Length(max = 50, message = "contractPartyB长度不在有效范围内")
    private String contractPartyB;

    @ApiModelProperty(value = "是否直接从建设单位承揽")
    @DbField("s.UNITCONTRACT")
    // @Length(max = 50, message = "unitContract长度不在有效范围内")
    private String unitContract;

    @ApiModelProperty(value = "公司内部协同")
    @DbField("s.INTERNALASSISTANCE")
    // @Length(max = 50, message = "internalAssistance长度不在有效范围内")
    private String internalAssistance;

    @ApiModelProperty(value = "录入顺序指标")
    @DbField("s.SEQUENCEINDEX")
    // @Length(max = 50, message = "sequenceIndex长度不在有效范围内")
    private String sequenceIndex;

    @ApiModelProperty(value = "集团专贡备注栏")
    @DbField("s.GROUPTRIBUTE")
    // @Length(max = 50, message = "groupTribute长度不在有效范围内")
    private String groupTribute;

    @ApiModelProperty(value = "报表专贡备注栏")
    @DbField("s.STATEMENTTRIBUTE")
    // @Length(max = 50, message = "statementTribute长度不在有效范围内")
    private String statementTribute;

    @ApiModelProperty(value = "是否为新能源指标转化")
    @DbField("s.INDEXTRANSFORMATION")
    // @Length(max = 50, message = "indexTransformation长度不在有效范围内")
    private String indexTransformation;

    @ApiModelProperty(value = "EPC签约单位")
    @DbField("s.CONTRACTINGUNIT")
    // @Length(max = 50, message = "contractingUnit长度不在有效范围内")
    private String contractingUnit;

    @ApiModelProperty(value = "EPC签约额(亿元)")
    @DbField("s.EPCCONTRACTAMOUNT")
    // @Range(max = Long.MAX_VALUE, message = "epcContractAmount长度不在有效范围内")
    private Long epcContractAmount;

    @ApiModelProperty(value = "是否已报送跟踪及中标项目信息")
    @DbField("s.WINNINGPROJECT")
    // @Length(max = 50, message = "winningProject长度不在有效范围内")
    private String winningProject;

    @ApiModelProperty(value = "所属类型")
    @DbField("s.TYPEOFOWNERSHIP")
    // @Length(max = 50, message = "typeOfOwnership长度不在有效范围内")
    private String typeOfOwnership;

    @ApiModelProperty(value = "创建人名称")
    @DbField("s.CREATEDNAME")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "组织全编码")
    @DbField("s.GROUPFULLCODE")
    // @Length(max = 255, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;



    @ApiModelProperty(value = "相关证明文件上传")
    @DbField("s.RELATEDDOCUMENT")
    // @Length(max = 500, message = "relatedDocument长度不在有效范围内")
    private String relatedDocument;

    @ApiModelProperty(value = "上传附件")
    @DbField("s.ATTACHMENTUPLOADING")
    // @Length(max = 500, message = "attachmentUploading长度不在有效范围内")
    private String attachmentUploading;

    @ApiModelProperty(value = "主要实物量指标1")
    @DbField("s.MAINPHYSICALQUANTITYINDEXONE")
    // @Length(max = 255, message = "mainPhysicalQuantityIndexOne长度不在有效范围内")
    private String mainPhysicalQuantityIndexOne;

    @ApiModelProperty(value = "主要实物量指标2")
    @DbField("s.MAINPHYSICALQUANTITYINDEXTWO")
    // @Length(max = 255, message = "mainPhysicalQuantityIndexTwo长度不在有效范围内")
    private String mainPhysicalQuantityIndexTwo;

    @ApiModelProperty(value = "主要实物量1")
    @DbField("s.PRINCIPALPHYSICALQUANTITYONE")
    // @Length(max = 255, message = "principalPhysicalQuantityOne长度不在有效范围内")
    private String principalPhysicalQuantityOne;

    @ApiModelProperty(value = "主要实物量2")
    @DbField("s.PRINCIPALPHYSICALQUANTITYTWO")
    // @Length(max = 255, message = "principalPhysicalQuantityTwo长度不在有效范围内")
    private String principalPhysicalQuantityTwo;

    @ApiModelProperty(value = "合同所在地点")
    @DbField("s.PLACEOFCONTRACT")
    // @Length(max = 255, message = "placeOfContract长度不在有效范围内")
    private String placeOfContract;

    @ApiModelProperty(value = "集团专责备注栏")
    @DbField("s.GROUPSPECIFICREMARKSCOLUMN")
    // @Length(max = 2555, message = "groupSpecificRemarksColumn长度不在有效范围内")
    private String groupSpecificRemarksColumn;

    @ApiModelProperty(value = "报表专责备注栏")
    @DbField("s.REPORTSPECIALIZEDREMARKSFIELD")
    // @Length(max = 2555, message = "reportSpecializedRemarksField长度不在有效范围内")
    private String reportSpecializedRemarksField;

    @ApiModelProperty(value = "所属公司名称")
    @DbField("s.GROUPBELONGUNITNAME")
    // @Length(max = 200, message = "GROUPBELONGUNITNAME长度不在有效范围内")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("r.INTASSISTANCEUNIT")
    // @Length(max = 255)
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("r.INTASSISTANCEUNITNAME")
    // @Length(max = 255)
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "所属区域", required = true)
    @DbField("r.OWNINGREGION")
    // @Length(max = 255, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    @ApiModelProperty(value = "项目规模单位")
    @DbField("s.unit")
    // @Length(max = 50, message = "unit长度不在有效范围内")
    private String unit;

    //附件表
    @DbIgnore
    List<AttachmentDto> attachmentDtos;
}
