package com.uwdp.module.api.vo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import com.uwdp.module.api.vo.enums.IndustryCategoryOptions;
import com.uwdp.module.api.vo.enums.WorkflowStatusEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
@ApiModel(value = "ProjectSigningExcelExport对象", description = "项目签约", discriminator = "ProjectSigning")
@SearchBean(tables = "T_PROJECTSIGNING p left join T_LMCWORKFLOW l on p.ID =  l.BIZID")
@EqualsAndHashCode(callSuper = false)
public class ProjectSigningExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @DbField("p.ID")
    @ColumnWidth(16)
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private String id;

    @ExcelIgnore
    @DbField("p.CREATED_BY")
    @ColumnWidth(16)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ExcelIgnore
    @ApiModelProperty(value = "创建时间")
    @DbField("p.CREATED_TIME")
    @ColumnWidth(16)
    private String createdTime;

    @ExcelIgnore
    @DbField("p.UPDATED_BY")
    @ColumnWidth(16)
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ExcelIgnore
    @DbField("p.UPDATED_TIME")
    @ColumnWidth(16)
    private String updatedTime;

    @ApiModelProperty(value = "合同名称", required = true)
    @DbField("p.CONTRACNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同名称"}, index = 0)
    // @Length(max = 50, message = "contracName长度不在有效范围内")
    private String contracName;

    @ApiModelProperty(value = "合同金额(万元)")
    @DbField("p.CONTRACTAMOUNT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同金额(万元)"}, index = 1)
    // @Range(max = Long.MAX_VALUE, message = "contractAmount长度不在有效范围内")
    private String contractAmount;

    @ApiModelProperty(value = "合同签订时间")
    @DbField("p.TIMEOFSIGNING")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同签订时间"}, index = 2)
    // @Length(max = 255, message = "timeOfSigning长度不在有效范围内")
    private String timeOfSigning;

    @ApiModelProperty(value = "合同开始时间")
    @DbField("p.COMMENCEMENTOFCONTRACT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同开始时间"}, index = 3)
    // @Length(max = 255, message = "commencementOfContract长度不在有效范围内")
    private String commencementOfContract;

    @ApiModelProperty(value = "合同结束时间")
    @DbField("p.CONTRACTENDTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同结束时间"}, index = 4)
    // @Length(max = 253, message = "contractEndTime长度不在有效范围内")
    private String contractEndTime;

    @ApiModelProperty(value = "合同工期(天)")
    @DbField("p.CONTRACTPROJECTTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同工期(天)"}, index = 5)
    // @Length(max = 255, message = "contractProjectTime长度不在有效范围内")
    private String contractProjectTime;

    /*@ApiModelProperty(value = "产业链类别")
    @DbField("p.CATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"产业链类别"}, index = 6)
    // @Length(max = 50, message = "category长度不在有效范围内")
    private String category;*/

    @ApiModelProperty(value = "新产业类别")
    @DbField("p.NEWINDUSTRY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"新产业类别"}, index = 6)
    // @Length(max = 50, message = "newIndustry长度不在有效范围内")
    private String newIndustry;

    @ApiModelProperty(value = "商业模式类别")
    @DbField("p.BUSINESSMODEL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"商业模式类别"}, index = 7)
    // @Length(max = 50, message = "businessModel长度不在有效范围内")
    private String businessModel;

    @ApiModelProperty(value = "商业模式类别名称")
    @DbField("p.BUSINESSMODELNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"商业模式类别名称"}, index = 8)
    // @Length(max = 50, message = "businessModelName长度不在有效范围内")
    private String businessModelName;

    @ApiModelProperty(value = "项目级别")
    @DbField("p.PROJECTLEVEL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目级别"}, index = 9)
    // @Length(max = 50, message = "projectLevel长度不在有效范围内")
    private String projectLevel;

    @ApiModelProperty(value = "合同状态")
    @DbField("p.CONTRACTSTATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同状态"}, index = 10)
    // @Length(max = 50, message = "contractStatus长度不在有效范围内")
    private String contractStatus;

    @ApiModelProperty(value = "合同甲方")
    @DbField("p.CONTRACTPARTY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同甲方"}, index = 11)
    // @Length(max = 50, message = "contractParty长度不在有效范围内")
    private String contractParty;

    @ApiModelProperty(value = "建设单位")
    @DbField("p.CONSTRUCTIONUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建设单位"}, index = 12)
    // @Length(max = 50, message = "constructionUnit长度不在有效范围内")
    private String constructionUnit;

    @ApiModelProperty(value = "建设单位分类")
    @DbField("p.CLASSIFICATIONOFCONSTRUCTIONUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建设单位分类"}, index = 13)
    // @Length(max = 50, message = "classificationOfConstructionUnit长度不在有效范围内")
    private String classificationOfConstructionUnit;

    @ApiModelProperty(value = "合同乙方")
    @DbField("p.CONTRACTPARTYB")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同乙方"}, index = 14)
    // @Length(max = 50, message = "contractPartyB长度不在有效范围内")
    private String contractPartyB;

    @ApiModelProperty(value = "是否直接从建设单位承揽")
    @DbField("p.UNITCONTRACT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否直接从建设单位承揽"}, index = 15)
    // @Length(max = 50, message = "unitContract长度不在有效范围内")
    private String unitContract;

    @ApiModelProperty(value = "公司内部协同")
    @DbField("p.INTERNALASSISTANCE")
    @ColumnWidth(15)
    @ExcelProperty(value = {"公司内部协同"}, index = 16)
    // @Length(max = 50, message = "internalAssistance长度不在有效范围内")
    private String internalAssistance;

    @ApiModelProperty(value = "录入顺序指标")
    @DbField("p.SEQUENCEINDEX")
    @ColumnWidth(16)
    @ExcelProperty(value = {"录入顺序指标"}, index = 17)
    // @Length(max = 50, message = "sequenceIndex长度不在有效范围内")
    private String sequenceIndex;

    @ApiModelProperty(value = "集团专贡备注栏")
    @DbField("p.GROUPTRIBUTE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"集团专贡备注栏"}, index = 18)
    // @Length(max = 50, message = "groupTribute长度不在有效范围内")
    private String groupTribute;

    @ApiModelProperty(value = "报表专贡备注栏")
    @DbField("p.STATEMENTTRIBUTE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"报表专贡备注栏"}, index = 19)
    // @Length(max = 50, message = "statementTribute长度不在有效范围内")
    private String statementTribute;

    @ApiModelProperty(value = "是否为新能源指标转化")
    @DbField("p.INDEXTRANSFORMATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否为新能源指标转化"}, index = 20)
    // @Length(max = 50, message = "indexTransformation长度不在有效范围内")
    private String indexTransformation;

    @ApiModelProperty(value = "EPC签约单位")
    @DbField("p.CONTRACTINGUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"EPC签约单位"}, index = 21)
    // @Length(max = 50, message = "contractingUnit长度不在有效范围内")
    private String contractingUnit;

    @ApiModelProperty(value = "EPC签约额(亿元)")
    @DbField("p.EPCCONTRACTAMOUNT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"EPC签约额(亿元)"}, index = 22)
    // @Range(max = Long.MAX_VALUE, message = "epcContractAmount长度不在有效范围内")
    private String epcContractAmount;

    @ApiModelProperty(value = "是否已报送跟踪及中标项目信息")
    @DbField("p.WINNINGPROJECT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否已报送跟踪及中标项目信息"}, index = 23)
    // @Length(max = 50, message = "winningProject长度不在有效范围内")
    private String winningProject;

    @ApiModelProperty(value = "所属类型")
    @DbField("p.TYPEOFOWNERSHIP")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属类型"}, index = 24)
    // @Length(max = 50, message = "typeOfOwnership长度不在有效范围内")
    private String typeOfOwnership;

    @ApiModelProperty(value = "创建人名称")
    @DbField("p.CREATEDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人名称"}, index = 25)
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "组织全编码")
    @DbField("p.GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织全编码"}, index = 26)
    // @Length(max = 255, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

//    @ApiModelProperty(value = "项目编号")
//    @DbField("p.PROJECTNUMBER")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"项目编号"}, index = 32)
//    // @Length(max = 255, message = "projectNumber长度不在有效范围内")
//    private String projectNumber;

    @ApiModelProperty(value = "项目名称")
    @DbField("p.PROJECTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目名称"}, index = 27)
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

//    @ApiModelProperty(value = "相关证明文件上传")
//    @DbField("p.RELATEDDOCUMENT")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"相关证明文件上传"}, index = 34)
//    // @Length(max = 500, message = "relatedDocument长度不在有效范围内")
//    private String relatedDocument;

//    @ApiModelProperty(value = "上传附件")
//    @DbField("p.ATTACHMENTUPLOADING")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"上传附件"}, index = 35)
//    // @Length(max = 500, message = "attachmentUploading长度不在有效范围内")
//    private String attachmentUploading;

    @ApiModelProperty(value = "主要实物量指标1")
    @DbField("p.MAINPHYSICALQUANTITYINDEXONE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要实物量指标1"}, index = 28)
    // @Length(max = 255, message = "mainPhysicalQuantityIndexOne长度不在有效范围内")
    private String mainPhysicalQuantityIndexOne;

    @ApiModelProperty(value = "主要实物量指标2")
    @DbField("p.MAINPHYSICALQUANTITYINDEXTWO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要实物量指标2"}, index = 29)
    // @Length(max = 255, message = "mainPhysicalQuantityIndexTwo长度不在有效范围内")
    private String mainPhysicalQuantityIndexTwo;

    @ApiModelProperty(value = "主要实物量1")
    @DbField("p.PRINCIPALPHYSICALQUANTITYONE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要实物量1"}, index = 30)
    // @Length(max = 255, message = "principalPhysicalQuantityOne长度不在有效范围内")
    private String principalPhysicalQuantityOne;

    @ApiModelProperty(value = "主要实物量2")
    @DbField("p.PRINCIPALPHYSICALQUANTITYTWO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要实物量2"}, index = 31)
    // @Length(max = 255, message = "principalPhysicalQuantityTwo长度不在有效范围内")
    private String principalPhysicalQuantityTwo;

    @ApiModelProperty(value = "合同所在地点")
    @DbField("p.PLACEOFCONTRACT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同所在地点"}, index = 32)
    // @Length(max = 255, message = "placeOfContract长度不在有效范围内")
    private String placeOfContract;

    @ApiModelProperty(value = "集团专责备注栏")
    @DbField("p.GROUPSPECIFICREMARKSCOLUMN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"集团专责备注栏"}, index = 33)
    // @Length(max = 2555, message = "groupSpecificRemarksColumn长度不在有效范围内")
    private String groupSpecificRemarksColumn;

    @ApiModelProperty(value = "报表专责备注栏")
    @DbField("p.REPORTSPECIALIZEDREMARKSFIELD")
    @ColumnWidth(16)
    @ExcelProperty(value = {"报表专责备注栏"}, index = 34)
    // @Length(max = 2555, message = "reportSpecializedRemarksField长度不在有效范围内")
    private String reportSpecializedRemarksField;

    @ApiModelProperty(value = "产业领域类别", required = true)
    @DbField("p.INDUSTRYCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"产业领域类别"}, index = 35)
    // @Length(max = 255, message = "industryCategory长度不在有效范围内")
    private String industryCategory;
    public String getIndustryCategory(){
        return IndustryCategoryOptions.getName(industryCategory);
    }

    @ApiModelProperty(value = "建设地点")
    @DbField("p.LOCATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建设地点(区域编号)"}, index = 36)
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "项目金额")
    @DbField("p.ITEMAMOUNT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目金额"}, index = 37)
    // @Range(max = Long.MAX_VALUE, message = "itemAmount长度不在有效范围内")
    private Double itemAmount;

    @ApiModelProperty(value = "投资规模(万元)")
    @DbField("p.INVESTMENTSCALE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投资规模(万元)"}, index = 38)
    // @Range(max = Long.MAX_VALUE, message = "investmentScale长度不在有效范围内")
    private Double investmentScale;

    @ApiModelProperty(value = "装机规模(MW)")
    @DbField("p.INSTALLEDCAPACITY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"装机规模(MW)"}, index = 39)
    // @Length(max = 255, message = "installedCapacity长度不在有效范围内")
    private String installedCapacity;

    @ApiModelProperty(value = "项目来源", required = true)
    @DbField("p.PROJECTSOURCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目来源"}, index = 40)
    // @Length(max = 255, message = "projectSource长度不在有效范围内")
    private String projectSource;

    @ApiModelProperty(value = "建设内容")
    @DbField("p.CONSTRUCTIONCONTENT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建设内容"}, index = 41)
    // @Length(max = 500, message = "constructionContent长度不在有效范围内")
    private String constructionContent;

    @ApiModelProperty(value = "项目背景")
    @DbField("p.PROJECTCONTEXT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目背景"}, index = 42)
    // @Length(max = 500, message = "projectContext长度不在有效范围内")
    private String projectContext;

    @ApiModelProperty(value = "中标日期")
    @DbField("p.WINDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"中标日期"}, index = 43)
    // @Length(max = 255, message = "winDate长度不在有效范围内")
    private String winDate;

    @ApiModelProperty(value = "产业领域")
    @DbField("p.INDUSTRY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"产业领域"}, index = 44)
    // @Length(max = 30, message = "industry长度不在有效范围内")
    private String industry;

    /*@ApiModelProperty(value = "是否国际项目")
    @DbField("p.ISFORIEN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否国际项目"}, index = 44)
    // @Length(max = 10, message = "isForien长度不在有效范围内")
    private String isForien;*/

    @ColumnWidth(16)
    @ExcelProperty(value = {"审批状态"}, index = 45)
    // @Length(max = 15, message = "createdName长度不在有效范围内")
    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;
    public String getWorkflowStatus(){
        return WorkflowStatusEnums.getName(workflowStatus);
    }

    /*@ApiModelProperty(value = "所属公司名称")
    @DbField("p.GROUPBELONGUNITNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属公司名称"}, index = 44)
    // @Length(max = 200, message = "GROUPBELONGUNITNAME长度不在有效范围内")
    private String groupBelongUnitName;*/



}
