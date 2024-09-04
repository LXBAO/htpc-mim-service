package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDate;
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
 * 项目登记
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectRecordsDTO对象", description = "项目登记", discriminator = "projectRecords")
@SearchBean(tables = "T_PROJECTRECORDS")
public class ProjectRecordsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("GROUPFULLCODE")
    private String groupFullCode;

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

    @ApiModelProperty(value = "项目工程名称")
    @DbField("PROJECTNAME")
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "所在地(省市)")
    @DbField("LOCATION")
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "产业领域类别", required = true)
    @DbField("INDUSTRYCATEGORY")
    // @Length(max = 30, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "装机规模(MW)")
    @DbField("INSTALLEDCAPACITY")
    // @Length(max = 100, message = "installedCapacity长度不在有效范围内")
    private String installedCapacity;

    @ApiModelProperty(value = "承包模式")
    @DbField("CONTRACTINGMODE")
    // @Length(max = 100, message = "contractingMode长度不在有效范围内")
    private String contractingMode;

    @ApiModelProperty(value = "预计合同金额(亿)")
    @DbField("ESTIMATEDCONTRACTAMOUNT")
    // @Range(max = Long.MAX_VALUE, message = "estimatedContractAmount长度不在有效范围内")
    private Double estimatedContractAmount;

    @ApiModelProperty(value = "预计投标时间")
    @DbField("ESTIMATEDBIDDINGTIME")
    private LocalDateTime estimatedBiddingTime;

    @ApiModelProperty(value = "项目编号", required = true)
    @DbField("PROJECTNUMBER")
    // @Length(max = 255, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "登记单位")
    @DbField("REGISTERUNIT")
    // @Length(max = 255, message = "registerUnit长度不在有效范围内")
    private String registerUnit;

    @ApiModelProperty(value = "项目阶段")
    @DbField("PROJECTPHASE")
    // @Length(max = 255, message = "projectPhase长度不在有效范围内")
    private String projectPhase;

    @ApiModelProperty(value = "项目阶段old")
    @DbField("PROJECTPHASEOLD")
    // @Length(max = 255, message = "projectPhaseOld长度不在有效范围内")
    private String projectPhaseOld;

    @ApiModelProperty(value = "业主单位")
    @DbField("OWNERUNIT")
    // @Length(max = 255, message = "ownerUnit长度不在有效范围内")
    private String ownerUnit;

    @ApiModelProperty(value = "业主单位/人", required = true)
    @DbField("OWNER")
    // @Length(max = 255, message = "owner长度不在有效范围内")
    private String owner;

    @ApiModelProperty(value = "重要程度")
    @DbField("IMPORTANTTYPE")
    // @Length(max = 255, message = "importantType长度不在有效范围内")
    private String importantType;

    @ApiModelProperty(value = "所属区域", required = true)
    @DbField("OWNINGREGION")
    // @Length(max = 255, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    @ApiModelProperty(value = "项目来源", required = true)
    @DbField("PROJECTSOURCE")
    // @Length(max = 255, message = "projectSource长度不在有效范围内")
    private String projectSource;

    @ApiModelProperty(value = "投资规模(万元)")
    @DbField("INVESTMENTSCALE")
    // @Range(max = Long.MAX_VALUE, message = "investmentScale长度不在有效范围内")
    private Double investmentScale;

    @ApiModelProperty(value = "项目金额")
    @DbField("ITEMAMOUNT")
    // @Range(max = Long.MAX_VALUE, message = "itemAmount长度不在有效范围内")
    private Double itemAmount;

    @ApiModelProperty(value = "请求")
    @DbField("DEMAND")
    // @Length(max = 300, message = "demand长度不在有效范围内")
    private String demand;

    @ApiModelProperty(value = "建设内容")
    @DbField("CONSTRUCTIONCONTENT")
    // @Length(max = 500, message = "constructionContent长度不在有效范围内")
    private String constructionContent;

    @ApiModelProperty(value = "项目背景")
    @DbField("PROJECTCONTEXT")
    // @Length(max = 500, message = "projectContext长度不在有效范围内")
    private String projectContext;

    @ApiModelProperty(value = "项目状态", required = true)
    @DbField("PROJECTSTATUS")
    // @Length(max = 30, message = "projectStatus长度不在有效范围内")
    private String projectStatus;

    @ApiModelProperty(value = "项目类别")
    @DbField("PROJECTCATEGORY")
    // @Length(max = 100, message = "projectCategory长度不在有效范围内")
    private String projectCategory;

    @ApiModelProperty(value = "客户ID")
    @DbField("FDID")
    // @Length(max = 100, message = " fdId长度不在有效范围内")
    private String  fdId;

    @ApiModelProperty(value = "客户名称")
    @DbField("FDNAME")
    // @Length(max = 100, message = " fdName长度不在有效范围内")
    private String  fdName;

    @ApiModelProperty(value = "原因")
    @DbField("CAUSE")
    // @Length(max = 200, message = "cause长度不在有效范围内")
    private String cause;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEDNAME")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty("隐藏状态")
    @DbField("hideState")
    // @Length(max = 100, message = "hideState长度不在有效范围内")
    private String hideState;

    @ApiModelProperty("是否国际项目")
    @DbField("ISFORIEN")
    // @Length(max = 10, message = "isForien长度不在有效范围内")
    private String isForien;

    @ApiModelProperty(value = "产业领域")
    @DbField("INDUSTRY")
    // @Length(max = 30, message = "industry长度不在有效范围内")
    private String industry;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("INTASSISTANCEUNIT")
    // @Length(max = 255)
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("INTASSISTANCEUNITNAME")
    // @Length(max = 255)
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "建设单位名称")
    @DbField("LOCATIONNAME")
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String locationName;

    @ApiModelProperty(value = "项目规模单位")
    @DbField("unit")
    // @Length(max = 50, message = "unit长度不在有效范围内")
    private String unit;

    @ApiModelProperty(value = "承包模式名称")
    @DbField("CONTRACTINGMODENAME")
    // @Length(max = 100, message = "contractingMode长度不在有效范围内")
    private String contractingModeName;

    @ApiModelProperty(value = "登记通过时间")
    @DbField("SUCCESSTIME")
    private LocalDate successTime;
}
