package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import com.uwdp.module.api.vo.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 项目登记
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectRecordsExcelExport对象", description = "项目登记", discriminator = "projectRecords")
@SearchBean(tables = "T_PROJECTRECORDS p left join T_LMCWORKFLOW l on p.ID =  l.BIZID")
@EqualsAndHashCode(callSuper = false)
public class ProjectRecordsExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "唯一标识")
//    @DbField("p.ID")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"唯一标识"}, index = 0)
//    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
//    private String id;
//

//
//    @ApiModelProperty(value = "创建时间")
//    @DbField("p.CREATED_TIME")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"创建时间"}, index = 2)
//    private String createdTime;
//
//    @ApiModelProperty(value = "更新者")
//    @DbField("p.UPDATED_BY")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新者"}, index = 3)
//    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
//    private String updatedBy;
//
//    @ApiModelProperty(value = "更新时间")
//    @DbField("p.UPDATED_TIME")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新时间"}, index = 4)
//    private String updatedTime;

    @ApiModelProperty(value = "项目工程名称")
    @DbField("p.PROJECTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目工程名称"}, index = 0)
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "所在地")
    @DbField("p.LOCATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所在地(所在地编号)"}, index = 1)
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "所在地名称")
    @DbField("p.LOCATIONNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所在地(所在地名称)"}, index = 2)
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String locationName;

    @ApiModelProperty(value = "产业领域类别", required = true)
    @DbField("p.INDUSTRYCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"产业领域类别"}, index = 3)
    // @Length(max = 30, message = "industryCategory长度不在有效范围内")
    private String industryCategory;
    public String getIndustryCategory(){
        return IndustryCategoryOptions.getName(industryCategory);
    }

    @ApiModelProperty(value = "装机规模(MW)")
    @DbField("p.INSTALLEDCAPACITY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"装机规模(MW)"}, index = 4)
    // @Length(max = 100, message = "installedCapacity长度不在有效范围内")
    private String installedCapacity;

    @ApiModelProperty(value = "承包模式")
    @DbField("p.CONTRACTINGMODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"承包模式"}, index = 5)
    // @Length(max = 100, message = "contractingMode长度不在有效范围内")
    private String contractingMode;

    @ApiModelProperty(value = "承包模式名称")
    @DbField("p.CONTRACTINGMODENAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"承包模式名称"}, index = 6)
    // @Length(max = 100, message = "contractingModeName长度不在有效范围内")
    private String contractingModeName;

    @ApiModelProperty(value = "预计投标时间")
    @DbField("p.ESTIMATEDBIDDINGTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"预计投标时间"}, index = 7)
    private String estimatedBiddingTime;

    @ApiModelProperty(value = "项目编号", required = true)
    @DbField("p.PROJECTNUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目编号"}, index = 8)
    // @Length(max = 255, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "登记单位")
    @DbField("p.REGISTERUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"登记单位"}, index = 9)
    // @Length(max = 255, message = "registerUnit长度不在有效范围内")
    private String registerUnit;

    @ApiModelProperty(value = "项目阶段")
    @DbField("p.PROJECTPHASE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目阶段"}, index = 10)
    // @Length(max = 255, message = "projectPhase长度不在有效范围内")
    private String projectPhase;
    public String getProjectPhase(){
        return DictProjectStageEnums.getName(projectPhase);
    }

    @ApiModelProperty(value = "业主单位")
    @DbField("p.OWNERUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业主单位"}, index = 11)
    // @Length(max = 255, message = "ownerUnit长度不在有效范围内")
    private String ownerUnit;

    @ApiModelProperty(value = "业主单位/人", required = true)
    @DbField("p.OWNER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业主单位/人"}, index = 12)
    // @Length(max = 255, message = "owner长度不在有效范围内")
    private String owner;

    @ApiModelProperty(value = "重要程度")
    @DbField("p.IMPORTANTTYPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"重要程度"}, index = 13)
    // @Length(max = 255, message = "importantType长度不在有效范围内")
    private String importantType;
    public String getImportantType(){
        return DictImportanceDegreeEnums.getName(importantType);
    }


    @ApiModelProperty(value = "所属区域", required = true)
    @DbField("p.OWNINGREGION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属区域"}, index = 14)
    // @Length(max = 255, message = "owningRegion长度不在有效范围内")
    private String owningRegion;
    public String getOwningRegion(){
        return DictRegionalHeadquarterEnums.getName(owningRegion);
    }
    @ApiModelProperty(value = "项目来源", required = true)
    @DbField("p.PROJECTSOURCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目来源"}, index = 15)
    // @Length(max = 255, message = "projectSource长度不在有效范围内")
    private String projectSource;

    @ApiModelProperty(value = "投资规模(万元)")
    @DbField("p.INVESTMENTSCALE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投资规模(万元)"}, index = 16)
    // @Range(max = Long.MAX_VALUE, message = "investmentScale长度不在有效范围内")
    private String investmentScale;

    @ApiModelProperty(value = "项目金额")
    @DbField("p.ITEMAMOUNT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目金额"}, index = 17)
    // @Range(max = Long.MAX_VALUE, message = "itemAmount长度不在有效范围内")
    private String itemAmount;

//    @ApiModelProperty(value = "请求")
//    @DbField("p.DEMAND")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"请求"}, index = 22)
//    // @Length(max = 300, message = "demand长度不在有效范围内")
//    private String demand;

    @ApiModelProperty(value = "建设内容")
    @DbField("p.CONSTRUCTIONCONTENT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建设内容"}, index = 18)
    // @Length(max = 500, message = "constructionContent长度不在有效范围内")
    private String constructionContent;

    @ApiModelProperty(value = "项目背景")
    @DbField("p.PROJECTCONTEXT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目背景"}, index = 19)
    // @Length(max = 500, message = "projectContext长度不在有效范围内")
    private String projectContext;

//    @ApiModelProperty(value = "项目状态", required = true)
//    @DbField("p.PROJECTSTATUS")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"项目状态"}, index = 25)
//    // @Length(max = 30, message = "projectStatus长度不在有效范围内")
//    private String projectStatus;

    @ApiModelProperty(value = "项目类别")
    @DbField("p.PROJECTCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目类别"}, index = 20)
    // @Length(max = 100, message = "projectCategory长度不在有效范围内")
    private String projectCategory;

    /*@ApiModelProperty(value = "产业领域")
    @DbField("INDUSTRY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 25)
    // @Length(max = 30, message = "industry长度不在有效范围内")
    private String industry;*/

//    @ApiModelProperty(value = "客户ID")
//    @DbField("p.FDID")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"客户ID"}, index = 27)
//    // @Length(max = 100, message = " fdId长度不在有效范围内")
//    private String  fdId;

    @ApiModelProperty(value = "原因")
    @DbField("p.CAUSE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"原因"}, index = 21)
    // @Length(max = 200, message = "cause长度不在有效范围内")
    private String cause;

    @ApiModelProperty(value = "客户名称")
    @DbField("p.FDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"客户名称"}, index = 22)
    // @Length(max = 100, message = " fdName长度不在有效范围内")
    private String  fdName;

    @ApiModelProperty(value = "是否国际项目")
    @DbField("p.ISFORIEN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否国际项目"}, index = 23)
    // @Length(max = 10, message = "isForien长度不在有效范围内")
    private String isForien;
    public String getIsForien(){
        return DictIsForienEnums.getName(isForien);
    }


    @ApiModelProperty(value = "组织全编码")
    @DbField("p.GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织全编码"}, index = 24)
    // @Length(max = 100, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

    @ColumnWidth(16)
    @ExcelProperty(value = {"审批状态"}, index = 25)
    // @Length(max = 15, message = "createdName长度不在有效范围内")
    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;
    public String getWorkflowStatus(){
        return WorkflowStatusEnums.getName(workflowStatus);
    }

    @ApiModelProperty(value = "创建人名称")
    @DbField("p.CREATEDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人名称"}, index = 26)
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    @DbField("p.CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 27)
    private String createdTime;

    @ApiModelProperty(value = "更新时间")
    @DbField("p.UPDATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 28)
    private String updatedTime;

    @ExcelIgnore
    @DbField("p.CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 29)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;




//
//    @ApiModelProperty(value = "隐藏状态")
//    @DbField("p.hideState")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"隐藏状态"}, index = 32)
//    // @Length(max = 100, message = "hideState长度不在有效范围内")
//    private String hideState;
//

}
