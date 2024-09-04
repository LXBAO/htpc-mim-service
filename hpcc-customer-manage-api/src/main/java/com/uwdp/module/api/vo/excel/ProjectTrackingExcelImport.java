package com.uwdp.module.api.vo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
@ToString
@ApiModel(value = "ProjectTrackingExcelImport对象", description = "项目跟踪", discriminator = "projectTracking")
@SearchBean(tables = "T_PROJECTTRACKING")
@EqualsAndHashCode(callSuper = false)
public class ProjectTrackingExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"唯一标识"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建编号")
    @DbField("CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 1)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;



    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 2)
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新者"}, index = 3)
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 4)
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "跟踪项目名称", required = true)
    @DbField("TRACKITEMNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"跟踪项目名称"}, index = 5)
    // @Length(max = 50, message = "trackItemName长度不在有效范围内")
    private String trackItemName;

    @ApiModelProperty(value = "跟踪日期", required = true)
    @DbField("TRACKDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"跟踪日期"}, index = 6)
    private String trackDate;

    @ApiModelProperty(value = "责任人", required = true)
    @DbField("PERSONNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"责任人"}, index = 7)
    // @Length(max = 50, message = "personName长度不在有效范围内")
    private String personName;

    @ApiModelProperty(value = "业主及投资方", required = true)
    @DbField("OWNER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业主及投资方"}, index = 8)
    // @Length(max = 50, message = "owner长度不在有效范围内")
    private String owner;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATOR")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 9)
    // @Length(max = 50, message = "creator长度不在有效范围内")
    private String creator;

    @ApiModelProperty(value = "合作模式")
    @DbField("COOPERATIONMODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合作模式"}, index = 10)
    // @Length(max = 50, message = "cooperationMode长度不在有效范围内")
    private String cooperationMode;

    @ApiModelProperty(value = "项目规模及地点")
    @DbField("SCALEANDLOCATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目规模及地点"}, index = 11)
    // @Length(max = 50, message = "scaleAndLocation长度不在有效范围内")
    private String scaleAndLocation;

    @ApiModelProperty(value = "主要建设内容")
    @DbField("CONSTRUCTIONCONTENT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要建设内容"}, index = 12)
    // @Length(max = 50, message = "constructionContent长度不在有效范围内")
    private String constructionContent;

    @ApiModelProperty(value = "参与资质或其他")
    @DbField("QUALIFICATIONOROTHERWISE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"参与资质或其他"}, index = 13)
    // @Length(max = 50, message = "qualificationOrOtherwise长度不在有效范围内")
    private String qualificationOrOtherwise;

    @ApiModelProperty(value = "资金落实情况及付款方式")
    @DbField("FUNDIMPLEMENTATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"资金落实情况及付款方式"}, index = 14)
    // @Length(max = 50, message = "fundImplementation长度不在有效范围内")
    private String fundImplementation;

    @ApiModelProperty(value = "主要合作边界条件")
    @DbField("BOUNDARYCONDITION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要合作边界条件"}, index = 15)
    // @Length(max = 50, message = "boundaryCondition长度不在有效范围内")
    private String boundaryCondition;

    @ApiModelProperty(value = "主要合作关系说明(股权交易结构)")
    @DbField("RELATIONDECLARATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要合作关系说明(股权交易结构)"}, index = 16)
    // @Length(max = 100, message = "relationDeclaration长度不在有效范围内")
    private String relationDeclaration;

    @ApiModelProperty(value = "相关方联系人")
    @DbField("PARTYCONTACT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"相关方联系人"}, index = 17)
    // @Length(max = 100, message = "partyContact长度不在有效范围内")
    private String partyContact;

    @ApiModelProperty(value = "其他说明")
    @DbField("OTHER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"其他说明"}, index = 18)
    // @Length(max = 100, message = "other长度不在有效范围内")
    private String other;

    @ApiModelProperty(value = "项目立项申请情况")
    @DbField("APPLICATIONSTATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目立项申请情况"}, index = 19)
    // @Length(max = 50, message = "applicationStatus长度不在有效范围内")
    private String applicationStatus;

    @ApiModelProperty(value = "项目模式")
    @DbField("PROJECTMODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目模式"}, index = 20)
    // @Length(max = 50, message = "projectMode长度不在有效范围内")
    private String projectMode;

    @ApiModelProperty(value = "产业链类别")
    @DbField("INDUSTRYCHAINCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"产业链类别"}, index = 21)
    // @Length(max = 50, message = "industryChainCategory长度不在有效范围内")
    private String industryChainCategory;

    @ApiModelProperty(value = "项目所在战略区域")
    @DbField("STRATEGICAREA")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目所在战略区域"}, index = 22)
    // @Length(max = 50, message = "strategicArea长度不在有效范围内")
    private String strategicArea;

    @ApiModelProperty(value = "标段名称")
    @DbField("SECTIONNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"标段名称"}, index = 23)
    // @Length(max = 50, message = "sectionName长度不在有效范围内")
    private String sectionName;

    @ApiModelProperty(value = "项目总投资(万)")
    @DbField("PROJECTINVESTMENT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目总投资(万)"}, index = 24)
    // @Length(max = 50, message = "projectInvestment长度不在有效范围内")
    private String projectInvestment;

    @ApiModelProperty(value = "预计招标时间")
    @DbField("BIDDINGTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"预计招标时间"}, index = 25)
    private String biddingTime;

    @ApiModelProperty(value = "预计中标概率")
    @DbField("WINNINGPROBABILITY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"预计中标概率"}, index = 26)
    // @Length(max = 50, message = "winningProbability长度不在有效范围内")
    private String winningProbability;

    @ApiModelProperty(value = "拟使用资源(万元)")
    @DbField("USERESOURCES")
    @ColumnWidth(16)
    @ExcelProperty(value = {"拟使用资源(万元)"}, index = 27)
    // @Range(max = Long.MAX_VALUE, message = "useResources长度不在有效范围内")
    private Long useResources;

    @ApiModelProperty(value = "标段拟投资金额(万元)")
    @DbField("LNVESTMENTAMOUNT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"标段拟投资金额(万元)"}, index = 28)
    // @Range(max = Long.MAX_VALUE, message = "lnvestmentAmount长度不在有效范围内")
    private Long lnvestmentAmount;

    @ApiModelProperty(value = "二级上报单位")
    @DbField("TWOREPORTING")
    @ColumnWidth(16)
    @ExcelProperty(value = {"二级上报单位"}, index = 29)
    // @Length(max = 50, message = "twoReporting长度不在有效范围内")
    private String twoReporting;

    @ApiModelProperty(value = "三级上报单位")
    @DbField("THREEREPORTING")
    @ColumnWidth(16)
    @ExcelProperty(value = {"三级上报单位"}, index = 30)
    // @Length(max = 50, message = "threeReporting长度不在有效范围内")
    private String threeReporting;

    @ApiModelProperty(value = "建设单位分类")
    @DbField("CONSTRUCTIONCLASSIFICATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建设单位分类"}, index = 31)
    // @Length(max = 50, message = "constructionClassification长度不在有效范围内")
    private String constructionClassification;

    @ApiModelProperty(value = "建设单位")
    @DbField("CONSTRUCTION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建设单位"}, index = 32)
    // @Length(max = 50, message = "construction长度不在有效范围内")
    private String construction;

    @ApiModelProperty(value = "设计单位")
    @DbField("DESIGN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"设计单位"}, index = 33)
    // @Length(max = 50, message = "design长度不在有效范围内")
    private String design;

    @ApiModelProperty(value = "重点关注项目是否编辑")
    @DbField("FOCUSON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"重点关注项目是否编辑"}, index = 34)
    // @Length(max = 50, message = "focusOn长度不在有效范围内")
    private String focusOn;

    @ApiModelProperty(value = "是否重大项目")
    @DbField("MAJORCONCERN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否重大项目"}, index = 35)
    // @Length(max = 50, message = "majorConcern长度不在有效范围内")
    private String majorConcern;

    @ApiModelProperty(value = "项目负责人")
    @DbField("PROJECTLEADER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目负责人"}, index = 36)
    // @Length(max = 50, message = "projectLeader长度不在有效范围内")
    private String projectLeader;

    @ApiModelProperty(value = "联系方式")
    @DbField("PHONE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"联系方式"}, index = 37)
    // @Length(max = 50, message = "phone长度不在有效范围内")
    private String phone;

    @ApiModelProperty(value = "是否需要集团引领")
    @DbField("NEEDGROUPLEADERSHIP")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否需要集团引领"}, index = 38)
    // @Length(max = 50, message = "needGroupLeadership长度不在有效范围内")
    private String needGroupLeadership;

    @ApiModelProperty(value = "集团引领原因")
    @DbField("LEADINGCAUSE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"集团引领原因"}, index = 39)
    // @Length(max = 100, message = "leadingCause长度不在有效范围内")
    private String leadingCause;

    @ApiModelProperty(value = "是否必须以集团名义运作")
    @DbField("GROUPOPERATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否必须以集团名义运作"}, index = 40)
    // @Length(max = 50, message = "groupOperation长度不在有效范围内")
    private String groupOperation;

    @ApiModelProperty(value = "集团名义运作原因")
    @DbField("OPERATIONALREASON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"集团名义运作原因"}, index = 41)
    // @Length(max = 50, message = "operationalReason长度不在有效范围内")
    private String operationalReason;

    @ApiModelProperty(value = "是否获取新能源指标")
    @DbField("NEWENERGYINDEX")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否获取新能源指标"}, index = 42)
    // @Length(max = 50, message = "newEnergyIndex长度不在有效范围内")
    private String newEnergyIndex;

    @ApiModelProperty(value = "新能源指标类别")
    @DbField("INDEXCLASS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"新能源指标类别"}, index = 43)
    // @Length(max = 50, message = "indexClass长度不在有效范围内")
    private String indexClass;

    @ApiModelProperty(value = "指标获取时间")
    @DbField("INDEXTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"指标获取时间"}, index = 44)
    private String indexTime;

    @ApiModelProperty(value = "当前状态")
    @DbField("CURRENTSTATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"当前状态"}, index = 45)
    // @Length(max = 50, message = "currentState长度不在有效范围内")
    private String currentState;

    @ApiModelProperty(value = "指标获取证明上传")
    @DbField("PROOFUPLOAD")
    @ColumnWidth(16)
    @ExcelProperty(value = {"指标获取证明上传"}, index = 46)
    // @Length(max = 100, message = "proofUpload长度不在有效范围内")
    private String proofUpload;

    @ApiModelProperty(value = "持股占比")
    @DbField("SHAREHOLDINGRATIO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"持股占比"}, index = 47)
    // @Length(max = 50, message = "shareholdingRatio长度不在有效范围内")
    private String shareholdingRatio;

    @ApiModelProperty(value = "是否三交九直")
    @DbField("THREEJIAONINE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否三交九直"}, index = 48)
    // @Length(max = 50, message = "threeJiaoNine长度不在有效范围内")
    private String threeJiaoNine;

    @ApiModelProperty(value = "网端项目/源端项目")
    @DbField("PROJECT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"网端项目/源端项目"}, index = 49)
    // @Length(max = 50, message = "project长度不在有效范围内")
    private String project;

    @ApiModelProperty(value = "通道项目")
    @DbField("CHANNELITEM")
    @ColumnWidth(16)
    @ExcelProperty(value = {"通道项目"}, index = 50)
    // @Length(max = 50, message = "channelItem长度不在有效范围内")
    private String channelItem;

    @ApiModelProperty(value = "包名称")
    @DbField("PACKETNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"包名称"}, index = 51)
    // @Length(max = 50, message = "packetName长度不在有效范围内")
    private String packetName;

    @ApiModelProperty(value = "是否需要专项评估")
    @DbField("SPECIALEVALUATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否需要专项评估"}, index = 52)
    // @Length(max = 50, message = "specialEvaluation长度不在有效范围内")
    private String specialEvaluation;

    @ApiModelProperty(value = "评估层级")
    @DbField("EVALUATIONLEVEL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"评估层级"}, index = 53)
    // @Length(max = 50, message = "evaluationLevel长度不在有效范围内")
    private String evaluationLevel;

    @ApiModelProperty(value = "已专项评估次数")
    @DbField("NUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"已专项评估次数"}, index = 54)
    // @Length(max = 50, message = "number长度不在有效范围内")
    private String number;

    @ApiModelProperty(value = "下次专项评估时间")
    @DbField("EVALUATIONTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"下次专项评估时间"}, index = 55)
    private String evaluationTime;

    @ApiModelProperty(value = "是否已完成评估")
    @DbField("COMPLETE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否已完成评估"}, index = 56)
    // @Length(max = 50, message = "complete长度不在有效范围内")
    private String complete;

    @ApiModelProperty(value = "未完成评估说明")
    @DbField("EVALUATIONSTATEMENT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"未完成评估说明"}, index = 57)
    // @Length(max = 50, message = "evaluationStatement长度不在有效范围内")
    private String evaluationStatement;

    @ApiModelProperty(value = "跟踪单位分管领导")
    @DbField("LEADER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"跟踪单位分管领导"}, index = 58)
    // @Length(max = 50, message = "leader长度不在有效范围内")
    private String leader;

    @ApiModelProperty(value = "内部配合单位")
    @DbField("INTERNALMATINGUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"内部配合单位"}, index = 59)
    // @Length(max = 50, message = "internalMatingUnit长度不在有效范围内")
    private String internalMatingUnit;

    @ApiModelProperty(value = "外部配合单位")
    @DbField("EXTERNALMATINGUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"外部配合单位"}, index = 60)
    // @Length(max = 50, message = "externalMatingUnit长度不在有效范围内")
    private String externalMatingUnit;

    @ApiModelProperty(value = "是否有合作单位")
    @DbField("COOPERATIVEUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否有合作单位"}, index = 61)
    // @Length(max = 50, message = "cooperativeUnit长度不在有效范围内")
    private String cooperativeUnit;

    @ApiModelProperty(value = "项目推进时间节点")
    @DbField("ADVANCETIMENODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目推进时间节点"}, index = 62)
    // @Length(max = 50, message = "advanceTimeNode长度不在有效范围内")
    private String advanceTimeNode;

    @ApiModelProperty(value = "节点完成情况")
    @DbField("NODECOMPLETION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"节点完成情况"}, index = 63)
    // @Length(max = 100, message = "nodeCompletion长度不在有效范围内")
    private String nodeCompletion;

    @ApiModelProperty(value = "是否需要集团协调")
    @DbField("GROUPCOORDINATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否需要集团协调"}, index = 64)
    // @Length(max = 50, message = "groupCoordination长度不在有效范围内")
    private String groupCoordination;

    @ApiModelProperty(value = "集团协调原因")
    @DbField("COORDINATIONREASON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"集团协调原因"}, index = 65)
    // @Length(max = 100, message = "coordinationReason长度不在有效范围内")
    private String coordinationReason;

    @ApiModelProperty(value = "股份结构")
    @DbField("SHARESTRUCTURE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"股份结构"}, index = 66)
    // @Length(max = 100, message = "shareStructure长度不在有效范围内")
    private String shareStructure;

    @ApiModelProperty(value = "同时跟踪的集团内部企业")
    @DbField("ENTERPRISE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"同时跟踪的集团内部企业"}, index = 67)
    // @Length(max = 100, message = "enterprise长度不在有效范围内")
    private String enterprise;

    @ApiModelProperty(value = "主要竞争对手情况")
    @DbField("COMPETITOR")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要竞争对手情况"}, index = 68)
    // @Length(max = 100, message = "competitor长度不在有效范围内")
    private String competitor;

    @ApiModelProperty(value = "项目基本情况")
    @DbField("PROJECTSITUATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目基本情况"}, index = 69)
    // @Length(max = 100, message = "projectSituation长度不在有效范围内")
    private String projectSituation;

    @ApiModelProperty(value = "项目跟踪进展情况")
    @DbField("TRACKINGSITUATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目跟踪进展情况"}, index = 70)
    // @Length(max = 100, message = "trackingSituation长度不在有效范围内")
    private String trackingSituation;

    @ApiModelProperty(value = "申报单位主要优势")
    @DbField("DECLARATIONADVANTAGE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"申报单位主要优势"}, index = 71)
    // @Length(max = 100, message = "declarationAdvantage长度不在有效范围内")
    private String declarationAdvantage;

    @ApiModelProperty(value = "总部关注情况")
    @DbField("HEADQUARTERSSITUATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"总部关注情况"}, index = 72)
    // @Length(max = 100, message = "headquartersSituation长度不在有效范围内")
    private String headquartersSituation;

    @ApiModelProperty(value = "备注")
    @DbField("REMARK")
    @ColumnWidth(16)
    @ExcelProperty(value = {"备注"}, index = 73)
    // @Length(max = 100, message = "remark长度不在有效范围内")
    private String remark;

    @ApiModelProperty(value = "上传文件")
    @DbField("UPLOADFILE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"上传文件"}, index = 74)
    // @Length(max = 100, message = "uploadFile长度不在有效范围内")
    private String uploadFile;

    @ApiModelProperty(value = "项目阶段")
    @DbField("PROJECTPHASE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目阶段"}, index = 75)
    // @Length(max = 100, message = "projectPhase长度不在有效范围内")
    private String projectPhase;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATEDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 76)
    // @Length(max = 64, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "项目登记编号")
    @DbField("PROJECTNUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目登记编号"}, index = 77)
    // @Length(max = 64, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织全编码"}, index = 78)
    // @Length(max = 255, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("INTASSISTANCEUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"根据内部协助单位"}, index = 79)
    // @Length(max = 255)
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("INTASSISTANCEUNITNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"根据内部协助单位"}, index = 80)
    // @Length(max = 255)
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "项目模式名称")
    @DbField("p.PROJECTMODENAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目模式名称"}, index = 81)
    // @Length(max = 255)
    private String projectModeName;

    @ApiModelProperty(value = "业务领域名称")
    @DbField("p.INDUSTRYCHAINCATEGORYNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业务领域名称"}, index = 82)
    // @Length(max = 255)
    private String industryChainCategoryName;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;


}
