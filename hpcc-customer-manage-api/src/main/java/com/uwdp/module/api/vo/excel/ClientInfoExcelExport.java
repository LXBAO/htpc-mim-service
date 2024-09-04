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
 * 客户信息总表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ClientInfoExcelExport对象", description = "客户信息总表", discriminator = "clientInfo")
@SearchBean(tables = "T_CLIENTINFO p left join T_LMCWORKFLOW l on p.FDID =  l.BIZID")
@EqualsAndHashCode(callSuper = false)
public class ClientInfoExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "ID")
//    @DbField("p.FDID")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"ID"}, index = 0)
//    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
//    private String fdId;

    @ExcelIgnore
    @DbField("p.CREATED_BY")
    @ColumnWidth(16)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "姓名", required = true)
    @DbField("p.FDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"姓名"}, index = 0)
    // @Length(max = 36, message = "fdName长度不在有效范围内")
    private String fdName;

    @ApiModelProperty(value = "联系方式", required = true)
    @DbField("p.FDNUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"联系方式"}, index = 1)
    // @Length(max = 36, message = "fdNumber长度不在有效范围内")
    private String fdNumber;

    @ApiModelProperty(value = "客户属性")
    @DbField("p.FDCLIENTPROPERTY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"客户属性"}, index = 2)
    // @Length(max = 36, message = "fdClientProperty长度不在有效范围内")
    private String fdClientProperty;

    public String getFdClientProperty(){
        return DictClientEnterEnums.getName(this.fdClientProperty);
    }

    @ApiModelProperty(value = "单位", required = true)
    @DbField("p.FDUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"单位"}, index = 3)
    // @Length(max = 36, message = "fdUnit长度不在有效范围内")
    private String fdUnit;

    @ApiModelProperty(value = "职务", required = true)
    @DbField("p.FDJOB")
    @ColumnWidth(16)
    @ExcelProperty(value = {"职务"}, index = 4)
    // @Length(max = 36, message = "fdJob长度不在有效范围内")
    private String fdJob;

//    @ApiModelProperty(value = "关联资源人", required = true)
//    @DbField("p.FDAFFILIATEDUSER")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"关联资源人"}, index = 11)
//    // @Length(max = 36, message = "fdAffiliatedUser长度不在有效范围内")
//    private String fdAffiliatedUser;

//    @ApiModelProperty(value = "跟踪项目")
//    @DbField("p.FDPROJECT")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"跟踪项目"}, index = 12)
//    // @Length(max = 36, message = "fdProject长度不在有效范围内")
//    private String fdProject;

    @ApiModelProperty(value = "客户分类")
    @DbField("p.FDCLIENTCLASSIFY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"客户分类"}, index = 5)
    // @Length(max = 36, message = "fdClientClassify长度不在有效范围内")
    private String fdClientClassify;
    public String getFdClientClassify(){
        return DictFdClientClassifyEnums.getName(this.fdClientClassify);
    }


    @ApiModelProperty(value = "客户层级")
    @DbField("p.FDCLIENTTIER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"客户层级"}, index = 6)
    // @Length(max = 2, message = "fdClientTier长度不在有效范围内")
    private String  fdClientTier;
    public String getFdClientTier(){
        return DictFdClientTierEnums.getName(this.fdClientTier);
    }

    @ApiModelProperty(value = "社会信用代码")
    @DbField("p.SOCIAL_CREDIT_CODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"社会信用代码"}, index = 7)
    // @Length(max = 64, message = "socialCreditCode长度不在有效范围内")
    private String socialCreditCode;

    @ApiModelProperty(value = "对接人")
    @DbField("p.FDCONTACTPERSON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"对接人"}, index = 8)
    // @Length(max = 36, message = "fdContactPerson长度不在有效范围内")
    private String fdContactPerson;

    @ApiModelProperty(value = "填报单位", required = true)
    @DbField("p.FDREPORTINGUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"填报单位"}, index = 9)
    // @Length(max = 200, message = "fdReportingUnit长度不在有效范围内")
    private String fdReportingUnit;

    @ApiModelProperty(value = "维护人", required = true)
    @DbField("p.FDACCENDANT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"维护人"}, index = 10)
    // @Length(max = 200, message = "fdAccendant长度不在有效范围内")
    private String fdAccendant;

    @ApiModelProperty(value = "籍贯")
    @DbField("p.FDNATIVEPLACE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"籍贯"}, index = 11)
    // @Length(max = 200, message = "fdNativePlace长度不在有效范围内")
    private String fdNativePlace;

    @ApiModelProperty(value = "年龄")
    @DbField("p.FDAGE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"年龄"}, index = 12)
    // @Range(max = Long.MAX_VALUE, message = "fdAge长度不在有效范围内")
    private String fdAge;

    @ApiModelProperty(value = "学历")
    @DbField("p.FDEDUCATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"学历"}, index = 13)
    // @Length(max = 200, message = "fdEducation长度不在有效范围内")
    private String fdEducation;
    public String getFdEducation(){
        return EducationEnums.getName(this.fdEducation);
    }


    @ApiModelProperty(value = "性别")
    @DbField("p.FDSEX")
    @ColumnWidth(16)
    @ExcelProperty(value = {"性别"}, index = 14)
    // @Length(max = 200, message = "fdSex长度不在有效范围内")
    private String fdSex;

    @ApiModelProperty(value = "经历")
    @DbField("p.FDEXPERIENCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"经历"}, index = 15)
    // @Length(max = 500, message = "fdExperience长度不在有效范围内")
    private String fdExperience;

//    @ApiModelProperty(value = "公司名称")
//    @DbField("p.FDCOMPANYNAME")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"公司名称"}, index = 24)
//    // @Length(max = 200, message = "fdCompanyName长度不在有效范围内")
//    private String fdCompanyName;

    @ApiModelProperty(value = "公司地址")
    @DbField("p.FDCOMPANYADDRESS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"公司地址"}, index = 16)
    // @Length(max = 500, message = "fdCompanyAddress长度不在有效范围内")
    private String fdCompanyAddress;

    @ApiModelProperty(value = "毕业院校")
    @DbField("p.FDSCHOOL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"毕业院校"}, index = 17)
    // @Length(max = 200, message = "fdSchool长度不在有效范围内")
    private String fdSchool;

//    @ApiModelProperty(value = "公司维护责任主体")
//    @DbField("p.FDRESPONSIBLE")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"公司维护责任主体"}, index = 27)
//    // @Length(max = 200, message = "fdResponsible长度不在有效范围内")
//    private String fdResponsible;

    @ApiModelProperty(value = "项目情况")
    @DbField("p.FDPROJECTCASE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目情况"}, index = 18)
    // @Length(max = 500, message = "fdProjectCase长度不在有效范围内")
    private String fdProjectCase;

    @ApiModelProperty(value = "其他")
    @DbField("p.FDOTHER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"其他"}, index = 19)
    // @Length(max = 500, message = "fdOther长度不在有效范围内")
    private String fdOther;

    @ApiModelProperty(value = "政治面貌")
    @DbField("p.POLITICSTATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"政治面貌"}, index = 20)
    // @Length(max = 500, message = "politicStatus长度不在有效范围内")
    private String politicStatus;

    @ApiModelProperty(value = "固定联系方式")
    @DbField("p.FDCONTACTWAY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"固定联系方式"}, index = 21)
    // @Length(max = 500, message = "fdContactWay长度不在有效范围内")
    private String fdContactWay;

    public String getPoliticStatus(){
        return PoliticsStatusEnums.getName(this.politicStatus);
    }

    @ApiModelProperty(value = "创建者名称")
    @DbField("p.CREATED_BY_NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 22)
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    @DbField("p.CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 23)
    private String createdTime;

    /*@ApiModelProperty(value = "省市")
    @DbField("p.LOCATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"省市"}, index = 24)
    // @Length(max = 500, message = "location长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "所属区域")
    @DbField("p.OWNINGREGION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属区域"}, index = 25)
    // @Length(max = 500, message = "owningRegion长度不在有效范围内")
    private String owningRegion;*/

//    @ApiModelProperty(value = "更新者")
//    @DbField("p.UPDATED_BY")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新者"}, index = 4)
//    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
//    private String updatedBy;
//
//    @ApiModelProperty(value = "更新时间")
//    @DbField("p.UPDATED_TIME")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新时间"}, index = 5)
//    private String updatedTime;
//    @ColumnWidth(16)

    @ApiModelProperty(value = "是否国际项目")
    @DbField("p.ISFORIEN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否国际项目"}, index = 24)
    // @Length(max = 10, message = "isForien长度不在有效范围内")
    private String isForien;
    public String getIsForien(){
        return DictIsForienEnums.getName(isForien);
    }

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("p.GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织全编码"}, index = 25)
    // @Length(max = 64, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

    @ExcelProperty(value = {"审批状态"}, index = 26)
    // @Length(max = 15, message = "createdName长度不在有效范围内")
    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;
    public String getWorkflowStatus(){
        return WorkflowStatusEnums.getName(workflowStatus);
    }


}
