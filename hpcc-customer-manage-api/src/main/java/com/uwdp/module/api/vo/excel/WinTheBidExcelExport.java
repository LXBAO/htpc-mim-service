package com.uwdp.module.api.vo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import com.uwdp.module.api.vo.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * <p>
 * 项目中标
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "WinTheBidExcelExport对象", description = "项目中标", discriminator = "WinTheBid")
@SearchBean(tables = "T_WINTHEBID p left join T_LMCWORKFLOW l on p.ID =  l.BIZID")
@EqualsAndHashCode(callSuper = false)
public class WinTheBidExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "唯一标识")
//    @DbField("p.ID")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"唯一标识"}, index = 0)
//    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
//    private String id;

    @ExcelIgnore
    @DbField("p.CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 1)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;



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

    @ApiModelProperty(value = "项目名称")
    @DbField("p.PROJECTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目名称"}, index = 0)
    // @Length(max = 50, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "所属区域")
    @DbField("p.OWNINGREGION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属区域"}, index = 1)
    // @Length(max = 50, message = "owningRegion长度不在有效范围内")
    private String owningRegion;
    public String getOwningRegion(){
       return DictRegionalHeadquarterEnums.getName(owningRegion);
    }

    @ApiModelProperty(value = "合同额(万元)")
    @DbField("p.MONEY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"合同额(万元)"}, index = 2)
    // @Range(max = Long.MAX_VALUE, message = "money长度不在有效范围内")
    private String money;

    @ApiModelProperty(value = "登记单位")
    @DbField("p.REGISTRATIONUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"登记单位"}, index = 3)
    // @Length(max = 50, message = "registrationUnit长度不在有效范围内")
    private String registrationUnit;

    @ApiModelProperty(value = "商业模式")
    @DbField("p.BUSINESSMODEL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"商业模式"}, index = 4)
    // @Length(max = 50, message = "businessModel长度不在有效范围内")
    private String businessModel;
    public String getBusinessModel(){
        return ProjectModeEnums.getName(businessModel);
    }

    @ApiModelProperty(value = "业务类别")
    @DbField("p.INDUSTRYCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业务类别"}, index = 5)
    // @Length(max = 50, message = "industryCategory长度不在有效范围内")
    private String industryCategory;
    public String getIndustryCategory(){
        return IndustryCategoryOptions.getName(industryCategory);
    }

    @ApiModelProperty(value = "中标日期")
    @DbField("p.DATA")
    @ColumnWidth(16)
    @ExcelProperty(value = {"中标日期"}, index = 6)
    private String data;

    @ApiModelProperty(value = "项目阶段")
    @DbField("p.PROJECTPHASE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目阶段"}, index = 7)
    // @Length(max = 50, message = "projectPhase长度不在有效范围内")
    private String projectPhase;
    public String getProjectPhase(){
        return DictProjectStageEnums.getName(projectPhase);
    }

    @ApiModelProperty(value = "项目编号")
    @DbField("p.ITEMNUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目编号"}, index = 8)
    // @Length(max = 50, message = "itemNumber长度不在有效范围内")
    private String itemNumber;

    @ApiModelProperty(value = "建设地点")
    @DbField("p.HOMEPROVINCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所在省份"}, index = 9)
    // @Length(max = 50, message = "homeProvince长度不在有效范围内")
    private String homeProvince;

    @ApiModelProperty(value = "建设地点名称")
    @DbField("p.HOMEPROVINCENAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建设地点名称"}, index = 10)
    // @Length(max = 50, message = "homeProvince长度不在有效范围内")
    private String homeProvinceName;

    @ApiModelProperty(value = "预计签约时间")
    @DbField("p.SIGNINGTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"预计签约时间"}, index = 11)
    private String signingTime;

    @ApiModelProperty(value = "登记时间")
    @DbField("p.REGISTRATIONTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"登记时间"}, index = 12)
    private String registrationTime;

    @ApiModelProperty(value = "是否重大项目")
    @DbField("p.MAJORPROJECT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否重大项目"}, index = 13)
    // @Length(max = 50, message = "majorProject长度不在有效范围内")
    private String majorProject;

    @ApiModelProperty(value = "是否需要专业评估")
    @DbField("p.PROFESSIONALEVALUATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否需要专业评估"}, index = 14)
    // @Length(max = 50, message = "professionalEvaluation长度不在有效范围内")
    private String professionalEvaluation;

    @ApiModelProperty(value = "是否获取新能源指标")
    @DbField("p.NEWENERGY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否获取新能源指标"}, index = 15)
    // @Length(max = 50, message = "newEnergy长度不在有效范围内")
    private String newEnergy;

    @ApiModelProperty(value = "是否三交九直")
    @DbField("p.WHETHERTHREE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否三交九直"}, index = 16)
    // @Length(max = 50, message = "whetherThree长度不在有效范围内")
    private String whetherThree;

    @ApiModelProperty(value = "创建人名称")
    @DbField("p.CREATEDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人名称"}, index = 17)
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "所属公司名称")
    @DbField("p.groupBelongUnitName")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属公司名称"}, index = 18)
    // @Length(max = 200, message = "GROUPBELONGUNITNAME长度不在有效范围内")
    private String groupBelongUnitName;

//    @ApiModelProperty(value = "审核状态")
//    @DbField("p.AUDITSTATUS")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"审核状态"}, index = 21)
//    // @Length(max = 50, message = "auditStatus长度不在有效范围内")
//    private String auditStatus;

    @ApiModelProperty(value = "创建时间")
    @DbField("p.CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 19)
    private String createdTime;

    /*@ApiModelProperty(value = "是否国际项目")
    @DbField("p.ISFORIEN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否国际项目"}, index = 19)
    // @Length(max = 10, message = "isForien长度不在有效范围内")
    private String isForien;*/

    @ColumnWidth(16)
    @ExcelProperty(value = {"审批状态"}, index = 20)
    // @Length(max = 15, message = "createdName长度不在有效范围内")
    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;
    public String getWorkflowStatus(){
        return WorkflowStatusEnums.getName(workflowStatus);
    }

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("p.GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织全编码"}, index = 21)
    // @Length(max = 64, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;
}
