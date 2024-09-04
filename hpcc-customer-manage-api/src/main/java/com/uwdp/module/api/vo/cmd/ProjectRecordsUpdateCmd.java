package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 项目登记
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectRecords UpdateCmd对象", description = "项目登记", discriminator = "projectRecords")
public class ProjectRecordsUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识", required = true)
    @NotNull(message = "主键不能为空")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目工程名称")
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "所在地(省市)")
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "产业领域类别", required = true)
    // @Length(max = 30, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "装机规模(MW)")
    // @Length(max = 100, message = "installedCapacity长度不在有效范围内")
    private String installedCapacity;

    @ApiModelProperty(value = "承包模式")
    // @Length(max = 100, message = "contractingMode长度不在有效范围内")
    private String contractingMode;

    @ApiModelProperty(value = "预计合同金额(亿)")
    // @Range(max = Long.MAX_VALUE, message = "estimatedContractAmount长度不在有效范围内")
    private Double estimatedContractAmount;

    @ApiModelProperty(value = "预计投标时间")
    private LocalDateTime estimatedBiddingTime;

    @ApiModelProperty(value = "项目编号", required = true)
    // @Length(max = 255, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "登记单位")
    // @Length(max = 255, message = "registerUnit长度不在有效范围内")
    private String registerUnit;

    @ApiModelProperty(value = "项目阶段")
    // @Length(max = 255, message = "projectPhase长度不在有效范围内")
    private String projectPhase;

    @ApiModelProperty(value = "项目阶段old")
    // @Length(max = 255, message = "projectPhaseOld长度不在有效范围内")
    private String projectPhaseOld;

    @ApiModelProperty(value = "业主单位")
    // @Length(max = 255, message = "ownerUnit长度不在有效范围内")
    private String ownerUnit;

    @ApiModelProperty(value = "业主单位/人", required = true)
    // @Length(max = 255, message = "owner长度不在有效范围内")
    private String owner;

    @ApiModelProperty(value = "重要程度")
    // @Length(max = 255, message = "importantType长度不在有效范围内")
    private String importantType;

    @ApiModelProperty(value = "所属区域", required = true)
    // @Length(max = 255, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    @ApiModelProperty(value = "项目来源", required = true)
    // @Length(max = 255, message = "projectSource长度不在有效范围内")
    private String projectSource;

    @ApiModelProperty(value = "投资规模(万元)")
    // @Range(max = Long.MAX_VALUE, message = "investmentScale长度不在有效范围内")
    private Double investmentScale;

    @ApiModelProperty(value = "项目金额")
    // @Range(max = Long.MAX_VALUE, message = "itemAmount长度不在有效范围内")
    private Double itemAmount;

    @ApiModelProperty(value = "请求")
    // @Length(max = 300, message = "demand长度不在有效范围内")
    private String demand;

    @ApiModelProperty(value = "建设内容")
    // @Length(max = 1000, message = "constructionContent长度不在有效范围内")
    private String constructionContent;

    @ApiModelProperty(value = "项目背景")
    // @Length(max = 1000, message = "projectContext长度不在有效范围内")
    private String projectContext;

    @ApiModelProperty(value = "项目状态", required = true)
    // @Length(max = 30, message = "projectStatus长度不在有效范围内")
    private String projectStatus;

    @ApiModelProperty(value = "项目类别")
    // @Length(max = 100, message = "projectCategory长度不在有效范围内")
    private String projectCategory;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    // @Length(max = 500, message = "group_code长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "客户ID")
    // @Length(max = 100, message = " fdId长度不在有效范围内")
    private String  fdId;

    @ApiModelProperty(value = "客户名称")
    // @Length(max = 100, message = " fdName长度不在有效范围内")
    private String  fdName;

    @ApiModelProperty(value = "原因")
    // @Length(max = 200, message = "cause长度不在有效范围内")
    private String cause;

    @ApiModelProperty(value = "创建人名称")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty("隐藏状态")
    // @Length(max = 100)
    private String hideState;

    @ApiModelProperty("是否国际项目")
    // @Length(max = 10)
    private String isForien;

    @ApiModelProperty(value = "产业领域")
    // @Length(max = 30)
    private String industry;

    @ApiModelProperty(value = "根据内部协助单位")
    // @Length(max = 255)
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    // @Length(max = 255)
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "建设单位名称")
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String locationName;

    @ApiModelProperty(value = "承包模式名称")
    // @Length(max = 100, message = "contractingMode长度不在有效范围内")
    private String contractingModeName;

    @ApiModelProperty(value = "项目规模单位")
    // @Length(max = 50)
    private String unit;

    @ApiModelProperty(value = "登记通过时间")
    private LocalDate successTime;
}
