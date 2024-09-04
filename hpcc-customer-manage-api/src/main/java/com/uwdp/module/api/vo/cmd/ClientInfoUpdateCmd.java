package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
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
@ApiModel(value = "ClientInfo UpdateCmd对象", description = "客户信息总表", discriminator = "clientInfo")
public class ClientInfoUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID", required = true)
    @NotNull(message = "主键不能为空")
    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
    private Long fdId;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者姓名")
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "姓名", required = true)
    // @Length(max = 200, message = "fdName长度不在有效范围内")
    private String fdName;

    @ApiModelProperty(value = "联系方式", required = true)
    // @Length(max = 200, message = "fdNumber长度不在有效范围内")
    private String fdNumber;

    @ApiModelProperty(value = "客户属性")
    // @Length(max = 200, message = "fdClientProperty长度不在有效范围内")
    private String fdClientProperty;

    @ApiModelProperty(value = "单位", required = true)
    // @Length(max = 255, message = "fdUnit长度不在有效范围内")
    private String fdUnit;

    @ApiModelProperty(value = "职务", required = true)
    // @Length(max = 200, message = "fdJob长度不在有效范围内")
    private String fdJob;

    @ApiModelProperty(value = "关联资源人", required = true)
    // @Length(max = 200, message = "fdAffiliatedUser长度不在有效范围内")
    private String fdAffiliatedUser;

    @ApiModelProperty(value = "跟踪项目")
    // @Length(max = 200, message = "fdProject长度不在有效范围内")
    private String fdProject;

    @ApiModelProperty(value = "客户分类")
    // @Length(max = 200, message = "fdClientClassify长度不在有效范围内")
    private String fdClientClassify;

    @ApiModelProperty(value = "客户层级")
    // @Length(max = 200, message = "fdClientTier长度不在有效范围内")
    private String fdClientTier;

    @ApiModelProperty(value = "对接人")
    // @Length(max = 200, message = "fdContactPerson长度不在有效范围内")
    private String fdContactPerson;

    @ApiModelProperty(value = "填报单位", required = true)
    // @Length(max = 200, message = "fdReportingUnit长度不在有效范围内")
    private String fdReportingUnit;


    @ApiModelProperty(value = "社会信用代码 ", required = true)
    // @Length(max = 60, message = "fdReportingUnit长度不在有效范围内")
    private String socialCreditCode;

    @ApiModelProperty(value = "维护人", required = true)
    // @Length(max = 200, message = "fdAccendant长度不在有效范围内")
    private String fdAccendant;

    @ApiModelProperty(value = "籍贯")
    // @Length(max = 200, message = "fdNativePlace长度不在有效范围内")
    private String fdNativePlace;

    @ApiModelProperty(value = "年龄")
    // @Range(max = Long.MAX_VALUE, message = "fdAge长度不在有效范围内")
    private Long fdAge;

    @ApiModelProperty(value = "学历")
    // @Length(max = 200, message = "fdEducation长度不在有效范围内")
    private String fdEducation;

    @ApiModelProperty(value = "性别")
    // @Length(max = 200, message = "fdSex长度不在有效范围内")
    private String fdSex;

    @ApiModelProperty(value = "经历")
    // @Length(max = 500, message = "fdExperience长度不在有效范围内")
    private String fdExperience;

    @ApiModelProperty(value = "公司名称")
    // @Length(max = 200, message = "fdCompanyName长度不在有效范围内")
    private String fdCompanyName;

    @ApiModelProperty(value = "公司地址")
    // @Length(max = 500, message = "fdCompanyAddress长度不在有效范围内")
    private String fdCompanyAddress;

    @ApiModelProperty(value = "毕业院校")
    // @Length(max = 200, message = "fdSchool长度不在有效范围内")
    private String fdSchool;

    @ApiModelProperty(value = "公司维护责任主体")
    // @Length(max = 200, message = "fdResponsible长度不在有效范围内")
    private String fdResponsible;

    @ApiModelProperty(value = "项目情况")
    private String fdProjectCase;

    @ApiModelProperty(value = "其他")
    private String fdOther;

    @ApiModelProperty(value = "政治面貌")
    // @Length(max = 500, message = "politicStatus长度不在有效范围内")
    private String politicStatus;

    @ApiModelProperty(value = "固定联系方式")
    // @Length(max = 500, message = "fdContactWay长度不在有效范围内")
    private String fdContactWay;

    @ApiModelProperty(value = "省市")
    // @Length(max = 500, message = "fdOther长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "所属区域")
    // @Length(max = 255, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    @ApiModelProperty("是否国际项目")
    // @Length(max = 10)
    private String isForien;
}
