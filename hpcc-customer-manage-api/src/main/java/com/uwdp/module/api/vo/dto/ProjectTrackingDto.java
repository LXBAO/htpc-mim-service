package com.uwdp.module.api.vo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
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

/**
 * <p>
 * 项目跟踪
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectTrackingDTO对象", description = "项目跟踪", discriminator = "projectTracking")
@SearchBean(tables = "T_PROJECTTRACKING")
public class ProjectTrackingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATEDNAME")
    // @Length(max = 64, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "跟踪项目名称", required = true)
    @DbField("TRACKITEMNAME")
    // @Length(max = 50, message = "trackItemName长度不在有效范围内")
    private String trackItemName;

    @ApiModelProperty(value = "跟踪日期", required = true)
    @DbField("TRACKDATE")
    private String trackDate;

    @ApiModelProperty(value = "责任人", required = true)
    @DbField("PERSONNAME")
    // @Length(max = 50, message = "personName长度不在有效范围内")
    private String personName;

    @ApiModelProperty(value = "业主及投资方", required = true)
    @DbField("OWNER")
    // @Length(max = 50, message = "owner长度不在有效范围内")
    private String owner;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATOR")
    // @Length(max = 50, message = "creator长度不在有效范围内")
    private String creator;

    @ApiModelProperty(value = "合作模式")
    @DbField("COOPERATIONMODE")
    // @Length(max = 50, message = "cooperationMode长度不在有效范围内")
    private String cooperationMode;

    @ApiModelProperty(value = "项目规模及地点")
    @DbField("SCALEANDLOCATION")
    // @Length(max = 50, message = "scaleAndLocation长度不在有效范围内")
    private String scaleAndLocation;

    @ApiModelProperty(value = "主要建设内容")
    @DbField("CONSTRUCTIONCONTENT")
    // @Length(max = 50, message = "constructionContent长度不在有效范围内")
    private String constructionContent;

    @ApiModelProperty(value = "参与资质或其他")
    @DbField("QUALIFICATIONOROTHERWISE")
    // @Length(max = 50, message = "qualificationOrOtherwise长度不在有效范围内")
    private String qualificationOrOtherwise;

    @ApiModelProperty(value = "资金落实情况及付款方式")
    @DbField("FUNDIMPLEMENTATION")
    // @Length(max = 50, message = "fundImplementation长度不在有效范围内")
    private String fundImplementation;

    @ApiModelProperty(value = "主要合作边界条件")
    @DbField("BOUNDARYCONDITION")
    // @Length(max = 50, message = "boundaryCondition长度不在有效范围内")
    private String boundaryCondition;

    @ApiModelProperty(value = "主要合作关系说明(股权交易结构)")
    @DbField("RELATIONDECLARATION")
    private String relationDeclaration;

    @ApiModelProperty(value = "相关方联系人")
    @DbField("PARTYCONTACT")
    private String partyContact;

    @ApiModelProperty(value = "其他说明")
    @DbField("OTHER")
    private String other;

    @ApiModelProperty(value = "项目立项申请情况")
    @DbField("APPLICATIONSTATUS")
    // @Length(max = 50, message = "applicationStatus长度不在有效范围内")
    private String applicationStatus;

    @ApiModelProperty(value = "项目模式")
    @DbField("PROJECTMODE")
    // @Length(max = 50, message = "projectMode长度不在有效范围内")
    private String projectMode;

    @ApiModelProperty(value = "产业链类别")
    @DbField("INDUSTRYCHAINCATEGORY")
    // @Length(max = 50, message = "industryChainCategory长度不在有效范围内")
    private String industryChainCategory;

    @ApiModelProperty(value = "项目所在战略区域")
    @DbField("STRATEGICAREA")
    // @Length(max = 50, message = "strategicArea长度不在有效范围内")
    private String strategicArea;

    @ApiModelProperty(value = "标段名称")
    @DbField("SECTIONNAME")
    // @Length(max = 50, message = "sectionName长度不在有效范围内")
    private String sectionName;

    @ApiModelProperty(value = "项目总投资(万)")
    @DbField("PROJECTINVESTMENT")
    // @Length(max = 50, message = "projectInvestment长度不在有效范围内")
    private String projectInvestment;

    @ApiModelProperty(value = "预计招标时间")
    @DbField("BIDDINGTIME")
    private String biddingTime;

    @ApiModelProperty(value = "预计中标概率")
    @DbField("WINNINGPROBABILITY")
    // @Length(max = 50, message = "winningProbability长度不在有效范围内")
    private String winningProbability;

    @ApiModelProperty(value = "拟使用资源(万元)")
    @DbField("USERESOURCES")
    // @Range(max = Long.MAX_VALUE, message = "useResources长度不在有效范围内")
    private Long useResources;

    @ApiModelProperty(value = "标段拟投资金额(万元)")
    @DbField("LNVESTMENTAMOUNT")
    // @Range(max = Long.MAX_VALUE, message = "lnvestmentAmount长度不在有效范围内")
    private Long lnvestmentAmount;

    @ApiModelProperty(value = "二级上报单位")
    @DbField("TWOREPORTING")
    // @Length(max = 50, message = "twoReporting长度不在有效范围内")
    private String twoReporting;

    @ApiModelProperty(value = "三级上报单位")
    @DbField("THREEREPORTING")
    // @Length(max = 50, message = "threeReporting长度不在有效范围内")
    private String threeReporting;

    @ApiModelProperty(value = "建设单位分类")
    @DbField("CONSTRUCTIONCLASSIFICATION")
    // @Length(max = 50, message = "constructionClassification长度不在有效范围内")
    private String constructionClassification;

    @ApiModelProperty(value = "建设单位")
    @DbField("CONSTRUCTION")
    // @Length(max = 50, message = "construction长度不在有效范围内")
    private String construction;

    @ApiModelProperty(value = "设计单位")
    @DbField("DESIGN")
    // @Length(max = 50, message = "design长度不在有效范围内")
    private String design;

    @ApiModelProperty(value = "重点关注项目是否编辑")
    @DbField("FOCUSON")
    // @Length(max = 50, message = "focusOn长度不在有效范围内")
    private String focusOn;

    @ApiModelProperty(value = "是否重大项目")
    @DbField("MAJORCONCERN")
    // @Length(max = 50, message = "majorConcern长度不在有效范围内")
    private String majorConcern;

    @ApiModelProperty(value = "项目负责人")
    @DbField("PROJECTLEADER")
    // @Length(max = 50, message = "projectLeader长度不在有效范围内")
    private String projectLeader;

    @ApiModelProperty(value = "联系方式")
    @DbField("PHONE")
    // @Length(max = 50, message = "phone长度不在有效范围内")
    private String phone;

    @ApiModelProperty(value = "是否需要集团引领")
    @DbField("NEEDGROUPLEADERSHIP")
    // @Length(max = 50, message = "needGroupLeadership长度不在有效范围内")
    private String needGroupLeadership;

    @ApiModelProperty(value = "集团引领原因")
    @DbField("LEADINGCAUSE")
    // @Length(max = 100, message = "leadingCause长度不在有效范围内")
    private String leadingCause;

    @ApiModelProperty(value = "是否必须以集团名义运作")
    @DbField("GROUPOPERATION")
    // @Length(max = 50, message = "groupOperation长度不在有效范围内")
    private String groupOperation;

    @ApiModelProperty(value = "集团名义运作原因")
    @DbField("OPERATIONALREASON")
    // @Length(max = 50, message = "operationalReason长度不在有效范围内")
    private String operationalReason;

    @ApiModelProperty(value = "是否获取新能源指标")
    @DbField("NEWENERGYINDEX")
    // @Length(max = 50, message = "newEnergyIndex长度不在有效范围内")
    private String newEnergyIndex;

    @ApiModelProperty(value = "新能源指标类别")
    @DbField("INDEXCLASS")
    // @Length(max = 50, message = "indexClass长度不在有效范围内")
    private String indexClass;

    @ApiModelProperty(value = "指标获取时间")
    @DbField("INDEXTIME")
    private String indexTime;

    @ApiModelProperty(value = "当前状态")
    @DbField("CURRENTSTATE")
    // @Length(max = 50, message = "currentState长度不在有效范围内")
    private String currentState;

    @ApiModelProperty(value = "指标获取证明上传")
    @DbField("PROOFUPLOAD")
    // @Length(max = 100, message = "proofUpload长度不在有效范围内")
    private String proofUpload;

    @ApiModelProperty(value = "持股占比")
    @DbField("SHAREHOLDINGRATIO")
    // @Length(max = 50, message = "shareholdingRatio长度不在有效范围内")
    private String shareholdingRatio;

    @ApiModelProperty(value = "是否三交九直")
    @DbField("THREEJIAONINE")
    // @Length(max = 50, message = "threeJiaoNine长度不在有效范围内")
    private String threeJiaoNine;

    @ApiModelProperty(value = "网端项目/源端项目")
    @DbField("PROJECT")
    // @Length(max = 50, message = "project长度不在有效范围内")
    private String project;

    @ApiModelProperty(value = "通道项目")
    @DbField("CHANNELITEM")
    // @Length(max = 50, message = "channelItem长度不在有效范围内")
    private String channelItem;

    @ApiModelProperty(value = "包名称")
    @DbField("PACKETNAME")
    // @Length(max = 50, message = "packetName长度不在有效范围内")
    private String packetName;

    @ApiModelProperty(value = "是否需要专项评估")
    @DbField("SPECIALEVALUATION")
    // @Length(max = 50, message = "specialEvaluation长度不在有效范围内")
    private String specialEvaluation;

    @ApiModelProperty(value = "评估层级")
    @DbField("EVALUATIONLEVEL")
    // @Length(max = 50, message = "evaluationLevel长度不在有效范围内")
    private String evaluationLevel;

    @ApiModelProperty(value = "已专项评估次数")
    @DbField("NUMBER")
    // @Length(max = 50, message = "number长度不在有效范围内")
    private String number;

    @ApiModelProperty(value = "下次专项评估时间")
    @DbField("EVALUATIONTIME")
    private String evaluationTime;

    @ApiModelProperty(value = "是否已完成评估")
    @DbField("COMPLETE")
    // @Length(max = 50, message = "complete长度不在有效范围内")
    private String complete;

    @ApiModelProperty(value = "未完成评估说明")
    @DbField("EVALUATIONSTATEMENT")
    // @Length(max = 50, message = "evaluationStatement长度不在有效范围内")
    private String evaluationStatement;

    @ApiModelProperty(value = "跟踪单位分管领导")
    @DbField("LEADER")
    // @Length(max = 50, message = "leader长度不在有效范围内")
    private String leader;

    @ApiModelProperty(value = "内部配合单位")
    @DbField("INTERNALMATINGUNIT")
    // @Length(max = 50, message = "internalMatingUnit长度不在有效范围内")
    private String internalMatingUnit;

    @ApiModelProperty(value = "外部配合单位")
    @DbField("EXTERNALMATINGUNIT")
    // @Length(max = 50, message = "externalMatingUnit长度不在有效范围内")
    private String externalMatingUnit;

    @ApiModelProperty(value = "是否有合作单位")
    @DbField("COOPERATIVEUNIT")
    // @Length(max = 50, message = "cooperativeUnit长度不在有效范围内")
    private String cooperativeUnit;

    @ApiModelProperty(value = "项目推进时间节点")
    @DbField("ADVANCETIMENODE")
    // @Length(max = 50, message = "advanceTimeNode长度不在有效范围内")
    private String advanceTimeNode;

    @ApiModelProperty(value = "节点完成情况")
    @DbField("NODECOMPLETION")
    // @Length(max = 100, message = "nodeCompletion长度不在有效范围内")
    private String nodeCompletion;

    @ApiModelProperty(value = "是否需要集团协调")
    @DbField("GROUPCOORDINATION")
    // @Length(max = 50, message = "groupCoordination长度不在有效范围内")
    private String groupCoordination;

    @ApiModelProperty(value = "集团协调原因")
    @DbField("COORDINATIONREASON")
    // @Length(max = 100, message = "coordinationReason长度不在有效范围内")
    private String coordinationReason;

    @ApiModelProperty(value = "股份结构")
    @DbField("SHARESTRUCTURE")
    // @Length(max = 100, message = "shareStructure长度不在有效范围内")
    private String shareStructure;

    @ApiModelProperty(value = "同时跟踪的集团内部企业")
    @DbField("ENTERPRISE")
    // @Length(max = 100, message = "enterprise长度不在有效范围内")
    private String enterprise;

    @ApiModelProperty(value = "主要竞争对手情况")
    @DbField("COMPETITOR")
    private String competitor;

    @ApiModelProperty(value = "项目基本情况")
    @DbField("PROJECTSITUATION")
    private String projectSituation;

    @ApiModelProperty(value = "项目跟踪进展情况")
    @DbField("TRACKINGSITUATION")
    private String trackingSituation;

    @ApiModelProperty(value = "申报单位主要优势")
    @DbField("DECLARATIONADVANTAGE")
    private String declarationAdvantage;

    @ApiModelProperty(value = "总部关注情况")
    @DbField("HEADQUARTERSSITUATION")
    // @Length(max = 100, message = "headquartersSituation长度不在有效范围内")
    private String headquartersSituation;

    @ApiModelProperty(value = "备注")
    @DbField("REMARK")
    // @Length(max = 100, message = "remark长度不在有效范围内")
    private String remark;

    @ApiModelProperty(value = "项目规模单位")
    @DbField("unit")
    // @Length(max = 50, message = "unit长度不在有效范围内")
    private String unit;

    @ApiModelProperty(value = "上传文件")
    @DbField("UPLOADFILE")
    // @Length(max = 100, message = "uploadFile长度不在有效范围内")
    private String uploadFile;

    @ApiModelProperty(value = "项目阶段")
    @DbField("PROJECTPHASE")
    // @Length(max = 100, message = "projectPhase长度不在有效范围内")
    private String projectPhase;

    @ApiModelProperty(value = "项目登记编号")
    @DbField("PROJECTNUMBER")
    // @Length(max = 64, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("GROUPFULLCODE")
    // @Length(max = 255, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("INTASSISTANCEUNIT")
    // @Length(max = 255)
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("INTASSISTANCEUNITNAME")
    // @Length(max = 255)
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "项目模式名称")
    @DbField("PROJECTMODENAME")
    // @Length(max = 255)
    private String projectModeName;

    @ApiModelProperty(value = "业务领域名称")
    @DbField("INDUSTRYCHAINCATEGORYNAME")
    // @Length(max = 255)
    private String industryChainCategoryName;

    //附件表
    @DbIgnore
    List<AttachmentDto> attachmentDtos;
}
