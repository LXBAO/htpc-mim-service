package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ejlchina.searcher.bean.DbField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目跟踪
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PROJECTTRACKING")
@ApiModel(value = "ProjectTrackingDo entity对象", description = "项目跟踪")
public class ProjectTrackingDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一标识")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    @TableField(value = "CREATEDNAME")
    private String createdName;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("跟踪项目名称")
    @TableField("TRACKITEMNAME")
    private String trackItemName;

    @ApiModelProperty("跟踪日期")
    @TableField("TRACKDATE")
    private String trackDate;

    @ApiModelProperty("责任人")
    @TableField("PERSONNAME")
    private String personName;

    @ApiModelProperty("业主及投资方")
    @TableField("`OWNER`")
    private String owner;

    @ApiModelProperty("创建者")
    @TableField("CREATOR")
    private String creator;

    @ApiModelProperty("合作模式")
    @TableField("COOPERATIONMODE")
    private String cooperationMode;

    @ApiModelProperty("项目规模及地点")
    @TableField("SCALEANDLOCATION")
    private String scaleAndLocation;

    @ApiModelProperty("主要建设内容")
    @TableField("CONSTRUCTIONCONTENT")
    private String constructionContent;

    @ApiModelProperty("参与资质或其他")
    @TableField("QUALIFICATIONOROTHERWISE")
    private String qualificationOrOtherwise;

    @ApiModelProperty("资金落实情况及付款方式")
    @TableField("FUNDIMPLEMENTATION")
    private String fundImplementation;

    @ApiModelProperty("主要合作边界条件")
    @TableField("BOUNDARYCONDITION")
    private String boundaryCondition;

    @ApiModelProperty("主要合作关系说明(股权交易结构)")
    @TableField("RELATIONDECLARATION")
    private String relationDeclaration;

    @ApiModelProperty("相关方联系人")
    @TableField("PARTYCONTACT")
    private String partyContact;

    @ApiModelProperty("其他说明")
    @TableField("OTHER")
    private String other;

    @ApiModelProperty("项目立项申请情况")
    @TableField("APPLICATIONSTATUS")
    private String applicationStatus;

    @ApiModelProperty("项目模式")
    @TableField("PROJECTMODE")
    private String projectMode;

    @ApiModelProperty("产业链类别")
    @TableField("INDUSTRYCHAINCATEGORY")
    private String industryChainCategory;

    @ApiModelProperty("项目所在战略区域")
    @TableField("STRATEGICAREA")
    private String strategicArea;

    @ApiModelProperty("标段名称")
    @TableField("SECTIONNAME")
    private String sectionName;

    @ApiModelProperty("项目总投资(万)")
    @TableField("PROJECTINVESTMENT")
    private String projectInvestment;

    @ApiModelProperty("预计招标时间")
    @TableField("BIDDINGTIME")
    private String biddingTime;

    @ApiModelProperty("预计中标概率")
    @TableField("WINNINGPROBABILITY")
    private String winningProbability;

    @ApiModelProperty("拟使用资源(万元)")
    @TableField("USERESOURCES")
    private Long useResources;

    @ApiModelProperty("标段拟投资金额(万元)")
    @TableField("LNVESTMENTAMOUNT")
    private Long lnvestmentAmount;

    @ApiModelProperty("二级上报单位")
    @TableField("TWOREPORTING")
    private String twoReporting;

    @ApiModelProperty("三级上报单位")
    @TableField("THREEREPORTING")
    private String threeReporting;

    @ApiModelProperty("建设单位分类")
    @TableField("CONSTRUCTIONCLASSIFICATION")
    private String constructionClassification;

    @ApiModelProperty("建设单位")
    @TableField("CONSTRUCTION")
    private String construction;

    @ApiModelProperty("设计单位")
    @TableField("DESIGN")
    private String design;

    @ApiModelProperty("重点关注项目是否编辑")
    @TableField("FOCUSON")
    private String focusOn;

    @ApiModelProperty("是否重大项目")
    @TableField("MAJORCONCERN")
    private String majorConcern;

    @ApiModelProperty("项目负责人")
    @TableField("PROJECTLEADER")
    private String projectLeader;

    @ApiModelProperty("联系方式")
    @TableField("PHONE")
    private String phone;

    @ApiModelProperty("是否需要集团引领")
    @TableField("NEEDGROUPLEADERSHIP")
    private String needGroupLeadership;

    @ApiModelProperty("集团引领原因")
    @TableField("LEADINGCAUSE")
    private String leadingCause;

    @ApiModelProperty("是否必须以集团名义运作")
    @TableField("GROUPOPERATION")
    private String groupOperation;

    @ApiModelProperty("集团名义运作原因")
    @TableField("OPERATIONALREASON")
    private String operationalReason;

    @ApiModelProperty("是否获取新能源指标")
    @TableField("NEWENERGYINDEX")
    private String newEnergyIndex;

    @ApiModelProperty("新能源指标类别")
    @TableField("INDEXCLASS")
    private String indexClass;

    @ApiModelProperty("指标获取时间")
    @TableField("INDEXTIME")
    private String indexTime;

    @ApiModelProperty("当前状态")
    @TableField("CURRENTSTATE")
    private String currentState;

    @ApiModelProperty("指标获取证明上传")
    @TableField("PROOFUPLOAD")
    private String proofUpload;

    @ApiModelProperty("持股占比")
    @TableField("SHAREHOLDINGRATIO")
    private String shareholdingRatio;

    @ApiModelProperty("是否三交九直")
    @TableField("THREEJIAONINE")
    private String threeJiaoNine;

    @ApiModelProperty("网端项目/源端项目")
    @TableField("PROJECT")
    private String project;

    @ApiModelProperty("通道项目")
    @TableField("CHANNELITEM")
    private String channelItem;

    @ApiModelProperty("包名称")
    @TableField("PACKETNAME")
    private String packetName;

    @ApiModelProperty("是否需要专项评估")
    @TableField("SPECIALEVALUATION")
    private String specialEvaluation;

    @ApiModelProperty("评估层级")
    @TableField("EVALUATIONLEVEL")
    private String evaluationLevel;

    @ApiModelProperty("已专项评估次数")
    @TableField("`NUMBER`")
    private String number;

    @ApiModelProperty("下次专项评估时间")
    @TableField("EVALUATIONTIME")
    private String evaluationTime;

    @ApiModelProperty("是否已完成评估")
    @TableField("COMPLETE")
    private String complete;

    @ApiModelProperty("未完成评估说明")
    @TableField("EVALUATIONSTATEMENT")
    private String evaluationStatement;

    @ApiModelProperty("跟踪单位分管领导")
    @TableField("LEADER")
    private String leader;

    @ApiModelProperty("内部配合单位")
    @TableField("INTERNALMATINGUNIT")
    private String internalMatingUnit;

    @ApiModelProperty("外部配合单位")
    @TableField("EXTERNALMATINGUNIT")
    private String externalMatingUnit;

    @ApiModelProperty("是否有合作单位")
    @TableField("COOPERATIVEUNIT")
    private String cooperativeUnit;

    @ApiModelProperty("项目推进时间节点")
    @TableField("ADVANCETIMENODE")
    private String advanceTimeNode;

    @ApiModelProperty("节点完成情况")
    @TableField("NODECOMPLETION")
    private String nodeCompletion;

    @ApiModelProperty("是否需要集团协调")
    @TableField("GROUPCOORDINATION")
    private String groupCoordination;

    @ApiModelProperty("集团协调原因")
    @TableField("COORDINATIONREASON")
    private String coordinationReason;

    @ApiModelProperty("股份结构")
    @TableField("SHARESTRUCTURE")
    private String shareStructure;

    @ApiModelProperty("同时跟踪的集团内部企业")
    @TableField("ENTERPRISE")
    private String enterprise;

    @ApiModelProperty("主要竞争对手情况")
    @TableField("COMPETITOR")
    private String competitor;

    @ApiModelProperty("项目基本情况")
    @TableField("PROJECTSITUATION")
    private String projectSituation;

    @ApiModelProperty("项目跟踪进展情况")
    @TableField("TRACKINGSITUATION")
    private String trackingSituation;

    @ApiModelProperty("申报单位主要优势")
    @TableField("DECLARATIONADVANTAGE")
    private String declarationAdvantage;

    @ApiModelProperty("总部关注情况")
    @TableField("HEADQUARTERSSITUATION")
    private String headquartersSituation;

    @ApiModelProperty("备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty("上传文件")
    @TableField("UPLOADFILE")
    private String uploadFile;

    @ApiModelProperty("项目阶段")
    @TableField("PROJECTPHASE")
    private String projectPhase;

    @ApiModelProperty("项目登记编号")
    @TableField("PROJECTNUMBER")
    private String projectNumber;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty(value = "根据内部协助单位")
    @TableField("INTASSISTANCEUNIT")
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    @TableField("INTASSISTANCEUNITNAME")
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "项目模式名称")
    @TableField("PROJECTMODENAME")
    // @Length(max = 255)
    private String projectModeName;

    @ApiModelProperty(value = "业务领域名称")
    @TableField("INDUSTRYCHAINCATEGORYNAME")
    // @Length(max = 255)
    private String industryChainCategoryName;

    @ApiModelProperty(value = "项目规模单位")
    @TableField("unit")
    private String unit;
}
