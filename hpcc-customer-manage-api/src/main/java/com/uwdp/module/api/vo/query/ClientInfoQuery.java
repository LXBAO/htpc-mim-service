package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
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
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ClientInfoDo Query对象", description = "客户信息总表", discriminator = "clientInfo")
public class ClientInfoQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long fdId;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    private String createdByName;


    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "姓名", required = true)
    private String fdName;

    @ApiModelProperty(value = "联系方式", required = true)
    private String fdNumber;

    @ApiModelProperty(value = "客户属性")
    private String fdClientProperty;

    @ApiModelProperty(value = "单位", required = true)
    private String fdUnit;

    @ApiModelProperty(value = "职务", required = true)
    private String fdJob;

    @ApiModelProperty(value = "关联资源人", required = true)
    private String fdAffiliatedUser;

    @ApiModelProperty(value = "跟踪项目")
    private String fdProject;

    @ApiModelProperty(value = "客户分类")
    private String fdClientClassify;

    @ApiModelProperty(value = "客户层级")
    private String fdClientTier;

    @ApiModelProperty(value = "对接人")
    private String fdContactPerson;

    @ApiModelProperty(value = "填报单位", required = true)
    private String fdReportingUnit;

    @ApiModelProperty(value = "维护人", required = true)
    private String fdAccendant;

    @ApiModelProperty(value = "籍贯")
    private String fdNativePlace;

    @ApiModelProperty(value = "年龄")
    private Long fdAge;

    @ApiModelProperty(value = "学历")
    private String fdEducation;

    @ApiModelProperty(value = "性别")
    private String fdSex;

    @ApiModelProperty(value = "经历")
    private String fdExperience;

    @ApiModelProperty(value = "公司名称")
    private String fdCompanyName;

    @ApiModelProperty(value = "公司地址")
    private String fdCompanyAddress;

    @ApiModelProperty(value = "毕业院校")
    private String fdSchool;

    @ApiModelProperty(value = "政治面貌")
    private String politicStatus;

    @ApiModelProperty(value = "公司维护责任主体")
    private String fdResponsible;

    @ApiModelProperty(value = "项目情况")
    private String fdProjectCase;

    @ApiModelProperty(value = "其他")
    private String fdOther;

    @ApiModelProperty("固定联系方式")
    private String fdContactWay;

    @ApiModelProperty(value = "是否国际项目")
    private String isForien;

    @ApiModelProperty(value = "省市")
    private String location;

    @ApiModelProperty(value = "所属区域")
    private String owningRegion;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    private String groupFullCode;
}
