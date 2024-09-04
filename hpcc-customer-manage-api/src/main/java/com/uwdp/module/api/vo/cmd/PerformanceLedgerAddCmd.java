package com.uwdp.module.api.vo.cmd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 业绩台账
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "PerformanceLedgerAddCmd对象", description = "业绩台账", discriminator = "performanceLedger")
public class PerformanceLedgerAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
    private Long fdId;

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

    @ApiModelProperty(value = "年度")
    // @Length(max = 36, message = "fdYear长度不在有效范围内")
    private String fdYear;

    @ApiModelProperty(value = "项目名称")
    // @Length(max = 200, message = "fdProjectName长度不在有效范围内")
    private String fdProjectName;

    @ApiModelProperty(value = "项目所在地")
    // @Length(max = 255, message = "fdProjectAddr长度不在有效范围内")
    private String fdProjectAddr;

    @ApiModelProperty(value = "发包人（业主）名称")
    // @Length(max = 36, message = "fdProprietorName长度不在有效范围内")
    private String fdProprietorName;

    @ApiModelProperty(value = "发包人地址")
    // @Length(max = 200, message = "fdProprietorAddr长度不在有效范围内")
    private String fdProprietorAddr;

    @ApiModelProperty(value = "发包人联系方式")
    // @Length(max = 36, message = "fdProprietorNumber长度不在有效范围内")
    private String fdProprietorNumber;

    @ApiModelProperty(value = "合同金额(万元)")
    // @Range(max = Long.MAX_VALUE, message = "fdContractAmount 长度不在有效范围内")
    private BigDecimal fdContractAmount ;

    @ApiModelProperty(value = "建设规模")
    // @Length(max = 255, message = "fdBuildingSize长度不在有效范围内")
    private String fdBuildingSize;

    @ApiModelProperty(value = "合同开工日期")
    private LocalDateTime fdContractStartUpDate;

    @ApiModelProperty(value = "合同竣工日期")
    private LocalDateTime fdContractCompletionDate;

    @ApiModelProperty(value = "实际开工日期")
    private LocalDateTime fdRealityStartUpDate;

    @ApiModelProperty(value = "实际竣工日期")
    private LocalDateTime fdRealityCompletionDate;

    @ApiModelProperty(value = "主要工作内容(工程范围）")
    // @Length(max = 500, message = "fdGroundWork长度不在有效范围内")
    private String fdGroundWork;

    @ApiModelProperty(value = "工程质量")
    // @Length(max = 200, message = "fdProjectQuality长度不在有效范围内")
    private String fdProjectQuality;

    @ApiModelProperty(value = "投标项目经理")
    // @Length(max = 200, message = "fdBiddingPM长度不在有效范围内")
    private String fdBiddingPM;

    @ApiModelProperty(value = "项目执行经理")
    // @Length(max = 200, message = "fdExecutiveManager长度不在有效范围内")
    private String fdExecutiveManager;

    @ApiModelProperty(value = "项目技术负责人")
    // @Length(max = 200, message = "fdTechnicalLeader长度不在有效范围内")
    private String fdTechnicalLeader;

    @ApiModelProperty(value = "监理单位")
    // @Length(max = 200, message = "fdSupervisingUnit长度不在有效范围内")
    private String fdSupervisingUnit;

    @ApiModelProperty(value = "监理工程师及电话")
    // @Length(max = 255, message = "fdEngineerAndTelephone长度不在有效范围内")
    private String fdEngineerAndTelephone;

    @ApiModelProperty(value = "项目描述")
    // @Length(max = 2000, message = "fdProjectDescription长度不在有效范围内")
    private String fdProjectDescription;


}
