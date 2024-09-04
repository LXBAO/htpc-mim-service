package com.uwdp.module.api.vo.query.searcher;

import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 客户信息-审批状态映射表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ClientInfoWorkflow Query对象", description = "客户信息-审批状态映射表", discriminator = "ClientInfoWorkflow")
public class ClientInfoWorkflowQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("l.WORKFLOWSTATUS")
    private String workflowStatus;

    @ApiModelProperty("创建者")
    private String createdBy;

    @ApiModelProperty("创建时间")
    private String createdTime;

    @ApiModelProperty("更新者")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty("姓名")
    private String fdName;

    @ApiModelProperty("联系方式")
    private String fdNumber;

    @ApiModelProperty("客户属性")
    private String fdClientProperty;

    @ApiModelProperty("单位")
    private String fdUnit;

    @ApiModelProperty("职务")
    private String fdJob;

    @ApiModelProperty("关联资源人")
    private String fdAffiliatedUser;

    @ApiModelProperty("跟踪项目")
    private String fdProject;

    @ApiModelProperty("客户分类")
    private String fdClientClassify;

    @ApiModelProperty("客户层级")
    private String fdClientTier;

    @ApiModelProperty("对接人")
    private String fdContactPerson;

    @ApiModelProperty("填报单位")
    private String fdReportingUnit;

    @ApiModelProperty("维护人")
    private String fdAccendant;

    @ApiModelProperty("籍贯")
    private String fdNativePlace;

    @ApiModelProperty("年龄")
    private Long fdAge;

    @ApiModelProperty("学历")
    private String fdEducation;

    @ApiModelProperty("性别")
    private String fdSex;

    @ApiModelProperty("经历")
    private String fdExperience;

    @ApiModelProperty("公司名称")
    private String fdCompanyName;

    @ApiModelProperty("公司地址")
    private String fdCompanyAddress;

    @ApiModelProperty("毕业院校")
    private String fdSchool;

    @ApiModelProperty("公司维护责任主体")
    private String fdResponsible;

    @ApiModelProperty("项目情况")
    private String fdProjectCase;

    @ApiModelProperty("其他")
    private String fdOther;

    @ApiModelProperty("固定联系方式")
    private String fdContactWay;

}
