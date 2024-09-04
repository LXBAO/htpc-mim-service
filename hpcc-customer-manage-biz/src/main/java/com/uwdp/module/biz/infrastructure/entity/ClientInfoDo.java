package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.ejlchina.searcher.bean.DbField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_CLIENTINFO")
@ApiModel(value = "ClientInfoDo entity对象", description = "客户信息总表")
public class ClientInfoDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "FDID", type = IdType.ASSIGN_ID)
    private Long fdId;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建者名称")
    @TableField("CREATED_BY_NAME")
    private String createdByName;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("姓名")
    @TableField("FDNAME")
    private String fdName;

    @ApiModelProperty("联系方式")
    @TableField("FDNUMBER")
    private String fdNumber;

    @ApiModelProperty("客户属性")
    @TableField("FDCLIENTPROPERTY")
    private String fdClientProperty;

    @ApiModelProperty("单位")
    @TableField("FDUNIT")
    private String fdUnit;

    @ApiModelProperty("职务")
    @TableField("FDJOB")
    private String fdJob;

    @ApiModelProperty("关联资源人")
    @TableField("FDAFFILIATEDUSER")
    private String fdAffiliatedUser;

    @ApiModelProperty("跟踪项目")
    @TableField("FDPROJECT")
    private String fdProject;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty("客户分类")
    @TableField("FDCLIENTCLASSIFY")
    private String fdClientClassify;

    @ApiModelProperty("客户层级")
    @TableField("FDCLIENTTIER")
    private String fdClientTier;

    @ApiModelProperty("对接人")
    @TableField("FDCONTACTPERSON")
    private String fdContactPerson;

    @ApiModelProperty("填报单位")
    @TableField("FDREPORTINGUNIT")
    private String fdReportingUnit;

    @ApiModelProperty("是否国际项目")
    @TableField("ISFORIEN")
    private String isForien;

    @ApiModelProperty("维护人")
    @TableField("FDACCENDANT")
    private String fdAccendant;

    @ApiModelProperty("籍贯")
    @TableField("FDNATIVEPLACE")
    private String fdNativePlace;

    @ApiModelProperty("年龄")
    @TableField("FDAGE")
    private Long fdAge;

    @ApiModelProperty("学历")
    @TableField("FDEDUCATION")
    private String fdEducation;

    @ApiModelProperty("性别")
    @TableField("FDSEX")
    private String fdSex;

    @ApiModelProperty("经历")
    @TableField("FDEXPERIENCE")
    private String fdExperience;

    @ApiModelProperty("公司名称")
    @TableField("FDCOMPANYNAME")
    private String fdCompanyName;

    @ApiModelProperty("公司地址")
    @TableField("FDCOMPANYADDRESS")
    private String fdCompanyAddress;

    @ApiModelProperty("毕业院校")
    @TableField("FDSCHOOL")
    private String fdSchool;

    @ApiModelProperty("公司维护责任主体")
    @TableField("FDRESPONSIBLE")
    private String fdResponsible;

    @ApiModelProperty("项目情况")
    @TableField("FDPROJECTCASE")
    private String fdProjectCase;

    @ApiModelProperty("其他")
    @TableField("FDOTHER")
    private String fdOther;

    @ApiModelProperty(value = "政治面貌")
    @TableField("POLITICSTATUS")
    private String politicStatus;

    @ApiModelProperty(value = "社会信用代码")
    @TableField("SOCIAL_CREDIT_CODE")
    private String socialCreditCode;

    @ApiModelProperty(value = "固定联系方式")
    @TableField("FDCONTACTWAY")
    private String fdContactWay;

    @ApiModelProperty(value = "省市")
    @TableField("LOCATION")
    private String location;

    @ApiModelProperty(value = "所属区域")
    @TableField("OWNINGREGION")
    private String owningRegion;

}
