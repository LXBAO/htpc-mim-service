package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@ApiModel(value = "ClientInfoDTO对象", description = "客户信息总表", discriminator = "clientInfo")
@SearchBean(tables = "T_CLIENTINFO")
public class ClientInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @DbField("FDID")
    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
    private Long fdId;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATED_BY_NAME")
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

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

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty(value = "姓名", required = true)
    @DbField("FDNAME")
    // @Length(max = 36, message = "fdName长度不在有效范围内")
    private String fdName;

    @ApiModelProperty(value = "联系方式", required = true)
    @DbField("FDNUMBER")
    // @Length(max = 200, message = "fdNumber长度不在有效范围内")
    private String fdNumber;

    @ApiModelProperty(value = "客户属性")
    @DbField("FDCLIENTPROPERTY")
    // @Length(max = 200, message = "fdClientProperty长度不在有效范围内")
    private String fdClientProperty;

    @ApiModelProperty(value = "单位", required = true)
    @DbField("FDUNIT")
    // @Length(max = 255, message = "fdUnit长度不在有效范围内")
    private String fdUnit;

    @ApiModelProperty(value = "职务", required = true)
    @DbField("FDJOB")
    // @Length(max = 200, message = "fdJob长度不在有效范围内")
    private String fdJob;

    @ApiModelProperty(value = "关联资源人", required = true)
    @DbField("FDAFFILIATEDUSER")
    // @Length(max = 200, message = "fdAffiliatedUser长度不在有效范围内")
    private String fdAffiliatedUser;

    @ApiModelProperty(value = "跟踪项目")
    @DbField("FDPROJECT")
    // @Length(max = 200, message = "fdProject长度不在有效范围内")
    private String fdProject;

    @ApiModelProperty(value = "客户分类")
    @DbField("FDCLIENTCLASSIFY")
    // @Length(max = 200, message = "fdClientClassify长度不在有效范围内")
    private String fdClientClassify;

    @ApiModelProperty(value = "客户层级")
    @DbField("FDCLIENTTIER")
    // @Length(max = 200, message = "fdClientTier长度不在有效范围内")
    private String fdClientTier;

    @ApiModelProperty(value = "对接人")
    @DbField("FDCONTACTPERSON")
    // @Length(max = 200, message = "fdContactPerson长度不在有效范围内")
    private String fdContactPerson;

    @ApiModelProperty(value = "填报单位", required = true)
    @DbField("FDREPORTINGUNIT")
    // @Length(max = 200, message = "fdReportingUnit长度不在有效范围内")
    private String fdReportingUnit;

    @ApiModelProperty(value = "社会信用代码 ")
    @DbField("SOCIAL_CREDIT_CODE")
    private String socialCreditCode;

    @ApiModelProperty(value = "维护人", required = true)
    @DbField("FDACCENDANT")
    // @Length(max = 200, message = "fdAccendant长度不在有效范围内")
    private String fdAccendant;

    @ApiModelProperty(value = "籍贯")
    @DbField("FDNATIVEPLACE")
    // @Length(max = 200, message = "fdNativePlace长度不在有效范围内")
    private String fdNativePlace;

    @ApiModelProperty(value = "年龄")
    @DbField("FDAGE")
    // @Range(max = Long.MAX_VALUE, message = "fdAge长度不在有效范围内")
    private Long fdAge;

    @ApiModelProperty(value = "学历")
    @DbField("FDEDUCATION")
    // @Length(max = 200, message = "fdEducation长度不在有效范围内")
    private String fdEducation;

    @ApiModelProperty(value = "性别")
    @DbField("FDSEX")
    // @Length(max = 200, message = "fdSex长度不在有效范围内")
    private String fdSex;

    @ApiModelProperty(value = "经历")
    @DbField("FDEXPERIENCE")
    // @Length(max = 500, message = "fdExperience长度不在有效范围内")
    private String fdExperience;

    @ApiModelProperty(value = "公司名称")
    @DbField("FDCOMPANYNAME")
    // @Length(max = 200, message = "fdCompanyName长度不在有效范围内")
    private String fdCompanyName;

    @ApiModelProperty(value = "公司地址")
    @DbField("FDCOMPANYADDRESS")
    // @Length(max = 500, message = "fdCompanyAddress长度不在有效范围内")
    private String fdCompanyAddress;

    @ApiModelProperty(value = "毕业院校")
    @DbField("FDSCHOOL")
    // @Length(max = 200, message = "fdSchool长度不在有效范围内")
    private String fdSchool;

    @ApiModelProperty(value = "政治面貌")
    @DbField("POLITICSTATUS")
    // @Length(max = 200, message = "politicStatus长度不在有效范围内")
    private String politicStatus;

    @ApiModelProperty(value = "固定联系方式")
    @DbField("FDCONTACTWAY")
    private String fdContactWay;

    @ApiModelProperty(value = "公司维护责任主体")
    @DbField("FDRESPONSIBLE")
    // @Length(max = 200, message = "fdResponsible长度不在有效范围内")
    private String fdResponsible;

    @ApiModelProperty(value = "项目情况")
    @DbField("FDPROJECTCASE")
    private String fdProjectCase;

    @ApiModelProperty(value = "其他")
    @DbField("FDOTHER")
    private String fdOther;

    @ApiModelProperty(value = "省市")
    @DbField("LOCATION")
    // @Length(max = 500, message = "fdOther长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "所属区域")
    @DbField("OWNINGREGION")
    // @Length(max = 500, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    @ApiModelProperty("是否国际项目")
    @DbField("ISFORIEN")
    // @Length(max = 10, message = "isForien长度不在有效范围内")
    private String isForien;
}
