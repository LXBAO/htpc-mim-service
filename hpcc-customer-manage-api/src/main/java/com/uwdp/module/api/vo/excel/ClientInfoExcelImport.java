package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ApiModel(value = "ClientInfoExcelImport对象", description = "客户信息总表", discriminator = "clientInfo")
@SearchBean(tables = "T_CLIENTINFO")
@EqualsAndHashCode(callSuper = false)
public class ClientInfoExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @DbField("FDID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"ID"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
    private Long fdId;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 1)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATED_BY_NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 1)
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

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

    @ApiModelProperty(value = "姓名", required = true)
    @DbField("FDNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"姓名"}, index = 5)
    // @Length(max = 36, message = "fdName长度不在有效范围内")
    private String fdName;

    @ApiModelProperty(value = "联系方式", required = true)
    @DbField("FDNUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"联系方式"}, index = 6)
    // @Length(max = 36, message = "fdNumber长度不在有效范围内")
    private String fdNumber;

    @ApiModelProperty(value = "客户属性")
    @DbField("FDCLIENTPROPERTY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"客户属性"}, index = 7)
    // @Length(max = 36, message = "fdClientProperty长度不在有效范围内")
    private String fdClientProperty;


    @ApiModelProperty(value = "社会信用代码")
    @DbField("SOCIAL_CREDIT_CODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"社会信用代码"}, index = 3)
    // @Length(max = 64, message = "socialCreditCode长度不在有效范围内")
    private String socialCreditCode;

    @ApiModelProperty(value = "单位", required = true)
    @DbField("FDUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"单位"}, index = 8)
    // @Length(max = 36, message = "fdUnit长度不在有效范围内")
    private String fdUnit;

    @ApiModelProperty(value = "职务", required = true)
    @DbField("FDJOB")
    @ColumnWidth(16)
    @ExcelProperty(value = {"职务"}, index = 9)
    // @Length(max = 36, message = "fdJob长度不在有效范围内")
    private String fdJob;

    @ApiModelProperty(value = "关联资源人", required = true)
    @DbField("FDAFFILIATEDUSER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联资源人"}, index = 10)
    // @Length(max = 36, message = "fdAffiliatedUser长度不在有效范围内")
    private String fdAffiliatedUser;

    @ApiModelProperty(value = "跟踪项目")
    @DbField("FDPROJECT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"跟踪项目"}, index = 11)
    // @Length(max = 36, message = "fdProject长度不在有效范围内")
    private String fdProject;

    @ApiModelProperty(value = "客户分类")
    @DbField("FDCLIENTCLASSIFY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"客户分类"}, index = 12)
    // @Length(max = 36, message = "fdClientClassify长度不在有效范围内")
    private String fdClientClassify;

    @ApiModelProperty(value = "客户层级")
    @DbField("FDCLIENTTIER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"客户层级"}, index = 13)
    // @Length(max = 36, message = "fdClientTier长度不在有效范围内")
    private String fdClientTier;

    @ApiModelProperty(value = "对接人")
    @DbField("FDCONTACTPERSON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"对接人"}, index = 14)
    // @Length(max = 36, message = "fdContactPerson长度不在有效范围内")
    private String fdContactPerson;

    @ApiModelProperty(value = "填报单位", required = true)
    @DbField("FDREPORTINGUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"填报单位"}, index = 15)
    // @Length(max = 200, message = "fdReportingUnit长度不在有效范围内")
    private String fdReportingUnit;

    @ApiModelProperty(value = "维护人", required = true)
    @DbField("FDACCENDANT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"维护人"}, index = 16)
    // @Length(max = 200, message = "fdAccendant长度不在有效范围内")
    private String fdAccendant;

    @ApiModelProperty(value = "籍贯")
    @DbField("FDNATIVEPLACE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"籍贯"}, index = 17)
    // @Length(max = 200, message = "fdNativePlace长度不在有效范围内")
    private String fdNativePlace;

    @ApiModelProperty(value = "年龄")
    @DbField("FDAGE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"年龄"}, index = 18)
    // @Range(max = Long.MAX_VALUE, message = "fdAge长度不在有效范围内")
    private Long fdAge;

    @ApiModelProperty(value = "学历")
    @DbField("FDEDUCATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"学历"}, index = 19)
    // @Length(max = 200, message = "fdEducation长度不在有效范围内")
    private String fdEducation;

    @ApiModelProperty(value = "性别")
    @DbField("FDSEX")
    @ColumnWidth(16)
    @ExcelProperty(value = {"性别"}, index = 20)
    // @Length(max = 200, message = "fdSex长度不在有效范围内")
    private String fdSex;

    @ApiModelProperty(value = "经历")
    @DbField("FDEXPERIENCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"经历"}, index = 21)
    // @Length(max = 500, message = "fdExperience长度不在有效范围内")
    private String fdExperience;

    @ApiModelProperty(value = "公司名称")
    @DbField("FDCOMPANYNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"公司名称"}, index = 22)
    // @Length(max = 200, message = "fdCompanyName长度不在有效范围内")
    private String fdCompanyName;

    @ApiModelProperty(value = "公司地址")
    @DbField("FDCOMPANYADDRESS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"公司地址"}, index = 23)
    // @Length(max = 500, message = "fdCompanyAddress长度不在有效范围内")
    private String fdCompanyAddress;

    @ApiModelProperty(value = "毕业院校")
    @DbField("FDSCHOOL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"毕业院校"}, index = 24)
    // @Length(max = 200, message = "fdSchool长度不在有效范围内")
    private String fdSchool;

    @ApiModelProperty(value = "公司维护责任主体")
    @DbField("FDRESPONSIBLE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"公司维护责任主体"}, index = 25)
    // @Length(max = 200, message = "fdResponsible长度不在有效范围内")
    private String fdResponsible;

    @ApiModelProperty(value = "项目情况")
    @DbField("FDPROJECTCASE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目情况"}, index = 26)
    // @Length(max = 500, message = "fdProjectCase长度不在有效范围内")
    private String fdProjectCase;

    @ApiModelProperty(value = "其他")
    @DbField("FDOTHER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"其他"}, index = 27)
    // @Length(max = 500, message = "fdOther长度不在有效范围内")
    private String fdOther;

    @ApiModelProperty(value = "政治面貌")
    @DbField("POLITICSTATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"政治面貌"}, index = 27)
    // @Length(max = 500, message = "politicStatus长度不在有效范围内")
    private String politicStatus;

    @ApiModelProperty(value = "固定联系方式")
    @DbField("FDCONTACTWAY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"固定联系方式"}, index = 28)
    // @Length(max = 500, message = "fdContactWay长度不在有效范围内")
    private String fdContactWay;

    @ApiModelProperty(value = "省市")
    @DbField("LOCATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"省市"}, index = 29)
    // @Length(max = 500, message = "location长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "所属区域")
    @DbField("OWNINGREGION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"省市"}, index = 30)
    // @Length(max = 500, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    @ApiModelProperty(value = "是否国际项目")
    @DbField("p.ISFORIEN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否国际项目"}, index = 31)
    // @Length(max = 10, message = "isForien长度不在有效范围内")
    private String isForien;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
