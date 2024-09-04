package com.uwdp.module.biz.infrastructure.entity.searcher;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ClientInfoWorkflowDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("l.WORKFLOWSTATUS")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;

    @ApiModelProperty("创建者")
    @DbField("c.CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @DbField("c.CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @DbField("c.UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @DbField("c.UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("姓名")
    @DbField("c.FDNAME")
    private String fdName;

    @ApiModelProperty("联系方式")
    @DbField("c.FDNUMBER")
    private String fdNumber;

    @ApiModelProperty("客户属性")
    @DbField("c.FDCLIENTPROPERTY")
    private String fdClientProperty;

    @ApiModelProperty("单位")
    @DbField("c.FDUNIT")
    private String fdUnit;

    @ApiModelProperty("职务")
    @DbField("c.FDJOB")
    private String fdJob;

    @ApiModelProperty("关联资源人")
    @DbField("c.FDAFFILIATEDUSER")
    private String fdAffiliatedUser;

    @ApiModelProperty("跟踪项目")
    @DbField("c.FDPROJECT")
    private String fdProject;

    @ApiModelProperty("客户分类")
    @DbField("c.FDCLIENTCLASSIFY")
    private String fdClientClassify;

    @ApiModelProperty("客户层级")
    @DbField("c.FDCLIENTTIER")
    private String fdClientTier;

    @ApiModelProperty("对接人")
    @DbField("c.FDCONTACTPERSON")
    private String fdContactPerson;

    @ApiModelProperty("填报单位")
    @DbField("c.FDREPORTINGUNIT")
    private String fdReportingUnit;

    @ApiModelProperty("维护人")
    @DbField("c.FDACCENDANT")
    private String fdAccendant;

    @ApiModelProperty("籍贯")
    @DbField("c.FDNATIVEPLACE")
    private String fdNativePlace;

    @ApiModelProperty("年龄")
    @DbField("c.FDAGE")
    private Long fdAge;

    @ApiModelProperty("学历")
    @DbField("c.FDEDUCATION")
    private String fdEducation;

    @ApiModelProperty("性别")
    @DbField("c.FDSEX")
    private String fdSex;

    @ApiModelProperty("经历")
    @DbField("c.FDEXPERIENCE")
    private String fdExperience;

    @ApiModelProperty("公司名称")
    @DbField("c.FDCOMPANYNAME")
    private String fdCompanyName;

    @ApiModelProperty("公司地址")
    @DbField("c.FDCOMPANYADDRESS")
    private String fdCompanyAddress;

    @ApiModelProperty("毕业院校")
    @DbField("c.FDSCHOOL")
    private String fdSchool;

    @ApiModelProperty("公司维护责任主体")
    @DbField("c.FDRESPONSIBLE")
    private String fdResponsible;

    @ApiModelProperty("项目情况")
    @DbField("c.FDPROJECTCASE")
    private String fdProjectCase;

    @ApiModelProperty("其他")
    @DbField("c.FDOTHER")
    private String fdOther;

    @ApiModelProperty(value = "政治面貌")
    @DbField("c.POLITICSTATUS")
    private String politicStatus;

    @ApiModelProperty(value = "固定联系方式")
    @TableField("c.FDCONTACTWAY")
    private String fdContactWay;

    @ApiModelProperty(value = "省市")
    @TableField("c.LOCATION")
    private String location;

    @ApiModelProperty(value = "所属区域")
    @TableField("c.OWNINGREGION")
    private String owningRegion;
}
