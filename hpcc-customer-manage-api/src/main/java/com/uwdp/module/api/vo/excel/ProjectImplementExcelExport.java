package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import com.uwdp.module.api.vo.enums.DictImportanceDegreeEnums;
import com.uwdp.module.api.vo.enums.IndustryCategoryOptions;
import com.uwdp.module.api.vo.enums.ProjectStateEnums;
import com.uwdp.module.api.vo.enums.WorkflowStatusEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 项目实施
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectImplementExcelExport对象", description = "项目实施", discriminator = "projectImplement")
@SearchBean(tables = "T_PROJECTIMPLEMENT p left join T_LMCWORKFLOW l on p.ID =  l.BIZID")
@EqualsAndHashCode(callSuper = false)
public class ProjectImplementExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "主键")
//    @DbField("p.ID")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"主键"}, index = 0)
//    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
//    private String id;

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
    // @Length(max = 100, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "项目状态")
    @DbField("p.PROJECTSTATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目状态"}, index = 1)
    // @Length(max = 32, message = "projectState长度不在有效范围内")
    private String projectState;
    public String getProjectState(){
        return ProjectStateEnums.getName(projectState);
    }

    @ApiModelProperty(value = "开工时间")
    @DbField("p.WORKDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"开工时间"}, index = 2)
    private String workDate;

    @ApiModelProperty(value = "投产时间")
    @DbField("p.COMMISSIONINGDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投产时间"}, index = 3)
    private String commissioningDate;

    @ApiModelProperty(value = "未开工原因")
    @DbField("p.NONWORKINGCAUSE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"未开工原因"}, index = 4)
    // @Length(max = 200, message = "nonWorkingCause长度不在有效范围内")
    private String nonWorkingCause;

//    @ApiModelProperty(value = "项目阶段")
//    @DbField("p.PROJECTSTAGE")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"项目阶段"}, index = 5)
//    // @Length(max = 100, message = "projectStage长度不在有效范围内")
//    private String projectStage;

//    @ApiModelProperty(value = "项目编号")
//    @DbField("p.PROJECTNUMBER")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"项目编号"}, index = 11)
//    // @Length(max = 200, message = "projectNumber长度不在有效范围内")
//    private String projectNumber;

    @ApiModelProperty(value = "登记单位")
    @DbField("p.REGISTRATIONUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"登记单位"}, index = 5)
    // @Length(max = 100, message = "registrationUnit长度不在有效范围内")
    private String registrationUnit;

    @ApiModelProperty(value = "产品领域类别")
    @DbField("p.INDUSTRYCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"产品领域类别"}, index = 6)
    // @Length(max = 200, message = "industryCategory长度不在有效范围内")
    private String industryCategory;
    public String getIndustryCategory(){
        return IndustryCategoryOptions.getName(industryCategory);
    }

    @ApiModelProperty(value = "登记时间")
    @DbField("p.REGISTERDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"登记时间"}, index = 7)
    private String registerDate;

//    @ApiModelProperty(value = "附件")
//    @DbField("p.FILE")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"附件"}, index = 15)
//    // @Length(max = 200, message = "file长度不在有效范围内")
//    private String file;

    @ApiModelProperty(value = "创建者名称")
    @DbField("p.CREATEDBYNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 8)
    // @Length(max = 100, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "入档状态")
    // @Length(max = 200, message = "inGear长度不在有效范围内")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 9)
    @DbField("p.INGEAR")
    private String inGear;

    @ExcelIgnore
    @DbField("p.CREATED_BY")
    @ColumnWidth(16)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("p.CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 10)
    private String createdTime;

    @ApiModelProperty(value = "组织全编码")
    @DbField("p.GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"权限id"}, index = 11)
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ColumnWidth(16)
    @ExcelProperty(value = {"审批状态"}, index = 12)
    // @Length(max = 15, message = "createdName长度不在有效范围内")
    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;
    public String getWorkflowStatus(){
        return WorkflowStatusEnums.getName(workflowStatus);
    }


}
