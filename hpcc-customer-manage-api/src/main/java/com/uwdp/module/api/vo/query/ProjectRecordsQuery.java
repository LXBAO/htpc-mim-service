package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 项目登记
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProjectRecordsDo Query对象", description = "项目登记", discriminator = "projectRecords")
public class ProjectRecordsQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "权限id")
    private String groupFullCode;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目工程名称")
    private String projectName;

    @ApiModelProperty(value = "所在地(省市)")
    private String location;

    @ApiModelProperty(value = "产业领域类别", required = true)
    private String industryCategory;

    @ApiModelProperty(value = "装机规模(MW)")
    private String installedCapacity;

    @ApiModelProperty(value = "承包模式")
    private String contractingMode;

    @ApiModelProperty(value = "预计合同金额(亿)")
    private Double estimatedContractAmount;

    @ApiModelProperty(value = "预计投标时间")
    private LocalDateTime estimatedBiddingTime;

    @ApiModelProperty(value = "项目编号", required = true)
    private String projectNumber;

    @ApiModelProperty(value = "登记单位")
    private String registerUnit;

    @ApiModelProperty(value = "项目阶段")
    private String projectPhase;

    @ApiModelProperty(value = "业主单位")
    private String ownerUnit;

    @ApiModelProperty(value = "业主单位/人", required = true)
    private String owner;

    @ApiModelProperty(value = "重要程度")
    private String importantType;

    @ApiModelProperty(value = "所属区域", required = true)
    private String owningRegion;

    @ApiModelProperty(value = "项目来源", required = true)
    private String projectSource;

    @ApiModelProperty(value = "投资规模(万元)")
    private Double investmentScale;

    @ApiModelProperty(value = "项目金额")
    private Double itemAmount;

    @ApiModelProperty(value = "请求")
    private String demand;

    @ApiModelProperty(value = "建设内容")
    private String constructionContent;

    @ApiModelProperty(value = "项目背景")
    private String projectContext;

    @ApiModelProperty(value = "项目状态", required = true)
    private String projectStatus;

    @ApiModelProperty(value = "项目类别")
    private String projectCategory;

    @ApiModelProperty(value = "客户ID")
    private String  fdId;

    @ApiModelProperty(value = "原因")
    private String cause;

    @ApiModelProperty(value = "客户姓名")
    private String  fdName;

    @ApiModelProperty(value = "创建人名称")
    private String  createdName;

    @ApiModelProperty(value = "隐藏状态")
    private String  hideState;

    @ApiModelProperty(value = "是否国际项目")
    private String  isForien;

    @ApiModelProperty(value = "产业领域")
    private String industry;

    @ApiModelProperty(value = "根据内部协助单位")
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位名称")
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "所在地名称")
    private String locationName;

    @ApiModelProperty(value = "承包模式名称")
    private String contractingModeName;

    @ApiModelProperty(value = "项目规模单位")
    private String unit;

    @ApiModelProperty(value = "登记通过时间")
    private LocalDate successTime;
}
