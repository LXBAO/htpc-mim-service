package com.uwdp.module.api.vo.query;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * <p>
 * 项目跟踪
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProjectTrackingDo Query对象", description = "项目跟踪", discriminator = "projectTracking")
public class ProjectTrackingQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Long id;

    @ApiModelProperty(value = "创建者编号")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "跟踪项目名称", required = true)
    private String trackItemName;

    @ApiModelProperty(value = "跟踪日期", required = true)
    private String trackDate;

    @ApiModelProperty(value = "责任人", required = true)
    private String personName;

    @ApiModelProperty(value = "业主及投资方", required = true)
    private String owner;

    @ApiModelProperty(value = "创建者")
    private String creator;

    @ApiModelProperty(value = "合作模式")
    private String cooperationMode;

    @ApiModelProperty(value = "项目规模及地点")
    private String scaleAndLocation;

    @ApiModelProperty(value = "主要建设内容")
    private String constructionContent;

    @ApiModelProperty(value = "参与资质或其他")
    private String qualificationOrOtherwise;

    @ApiModelProperty(value = "资金落实情况及付款方式")
    private String fundImplementation;

    @ApiModelProperty(value = "主要合作边界条件")
    private String boundaryCondition;

    @ApiModelProperty(value = "主要合作关系说明(股权交易结构)")
    private String relationDeclaration;

    @ApiModelProperty(value = "相关方联系人")
    private String partyContact;

    @ApiModelProperty(value = "其他说明")
    private String other;

    @ApiModelProperty(value = "项目立项申请情况")
    private String applicationStatus;

    @ApiModelProperty(value = "项目模式")
    private String projectMode;

    @ApiModelProperty(value = "产业链类别")
    private String industryChainCategory;

    @ApiModelProperty(value = "项目所在战略区域")
    private String strategicArea;

    @ApiModelProperty(value = "标段名称")
    private String sectionName;

    @ApiModelProperty(value = "项目总投资(万)")
    private String projectInvestment;

    @ApiModelProperty(value = "预计招标时间")
    private String biddingTime;

    @ApiModelProperty(value = "预计中标概率")
    private String winningProbability;

    @ApiModelProperty(value = "拟使用资源(万元)")
    private Long useResources;

    @ApiModelProperty(value = "标段拟投资金额(万元)")
    private Long lnvestmentAmount;

    @ApiModelProperty(value = "二级上报单位")
    private String twoReporting;

    @ApiModelProperty(value = "三级上报单位")
    private String threeReporting;

    @ApiModelProperty(value = "建设单位分类")
    private String constructionClassification;

    @ApiModelProperty(value = "建设单位")
    private String construction;

    @ApiModelProperty(value = "设计单位")
    private String design;

    @ApiModelProperty(value = "重点关注项目是否编辑")
    private String focusOn;

    @ApiModelProperty(value = "是否重大项目")
    private String majorConcern;

    @ApiModelProperty(value = "项目负责人")
    private String projectLeader;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "是否需要集团引领")
    private String needGroupLeadership;

    @ApiModelProperty(value = "集团引领原因")
    private String leadingCause;

    @ApiModelProperty(value = "是否必须以集团名义运作")
    private String groupOperation;

    @ApiModelProperty(value = "集团名义运作原因")
    private String operationalReason;

    @ApiModelProperty(value = "是否获取新能源指标")
    private String newEnergyIndex;

    @ApiModelProperty(value = "新能源指标类别")
    private String indexClass;

    @ApiModelProperty(value = "指标获取时间")
    private String indexTime;

    @ApiModelProperty(value = "当前状态")
    private String currentState;

    @ApiModelProperty(value = "指标获取证明上传")
    private String proofUpload;

    @ApiModelProperty(value = "持股占比")
    private String shareholdingRatio;

    @ApiModelProperty(value = "是否三交九直")
    private String threeJiaoNine;

    @ApiModelProperty(value = "网端项目/源端项目")
    private String project;

    @ApiModelProperty(value = "通道项目")
    private String channelItem;

    @ApiModelProperty(value = "包名称")
    private String packetName;

    @ApiModelProperty(value = "是否需要专项评估")
    private String specialEvaluation;

    @ApiModelProperty(value = "评估层级")
    private String evaluationLevel;

    @ApiModelProperty(value = "已专项评估次数")
    private String number;

    @ApiModelProperty(value = "下次专项评估时间")
    private String evaluationTime;

    @ApiModelProperty(value = "是否已完成评估")
    private String complete;

    @ApiModelProperty(value = "未完成评估说明")
    private String evaluationStatement;

    @ApiModelProperty(value = "跟踪单位分管领导")
    private String leader;

    @ApiModelProperty(value = "内部配合单位")
    private String internalMatingUnit;

    @ApiModelProperty(value = "外部配合单位")
    private String externalMatingUnit;

    @ApiModelProperty(value = "是否有合作单位")
    private String cooperativeUnit;

    @ApiModelProperty(value = "项目推进时间节点")
    private String advanceTimeNode;

    @ApiModelProperty(value = "节点完成情况")
    private String nodeCompletion;

    @ApiModelProperty(value = "是否需要集团协调")
    private String groupCoordination;

    @ApiModelProperty(value = "集团协调原因")
    private String coordinationReason;

    @ApiModelProperty(value = "股份结构")
    private String shareStructure;

    @ApiModelProperty(value = "同时跟踪的集团内部企业")
    private String enterprise;

    @ApiModelProperty(value = "主要竞争对手情况")
    private String competitor;

    @ApiModelProperty(value = "项目基本情况")
    private String projectSituation;

    @ApiModelProperty(value = "项目跟踪进展情况")
    private String trackingSituation;

    @ApiModelProperty(value = "申报单位主要优势")
    private String declarationAdvantage;

    @ApiModelProperty(value = "总部关注情况")
    private String headquartersSituation;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "上传文件")
    private String uploadFile;

    @ApiModelProperty(value = "项目阶段")
    private String projectPhase;

    @ApiModelProperty(value = "项目登记编号")
    private String projectNumber;

    @ApiModelProperty(value = "根据内部协助单位")
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    private String groupFullCode;

    @ApiModelProperty(value = "项目模式名称")
    private String projectModeName;

    @ApiModelProperty(value = "项目规模单位")
    private String unit;

    @ApiModelProperty(value = "业务领域名称")
    private String industryChainCategoryName;
}
