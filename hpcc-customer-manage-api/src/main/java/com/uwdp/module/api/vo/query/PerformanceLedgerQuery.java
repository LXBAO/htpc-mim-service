package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 业绩台账
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PerformanceLedgerDo Query对象", description = "业绩台账", discriminator = "performanceLedger")
public class PerformanceLedgerQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long fdId;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "年度")
    private String fdYear;

    @ApiModelProperty(value = "项目名称")
    private String fdProjectName;

    @ApiModelProperty(value = "项目所在地")
    private String fdProjectAddr;

    @ApiModelProperty(value = "发包人（业主）名称")
    private String fdProprietorName;

    @ApiModelProperty(value = "发包人地址")
    private String fdProprietorAddr;

    @ApiModelProperty(value = "发包人联系方式")
    private String fdProprietorNumber;

    @ApiModelProperty(value = "合同金额(万元)")
    private BigDecimal fdContractAmount ;

    @ApiModelProperty(value = "建设规模")
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
    private String fdGroundWork;

    @ApiModelProperty(value = "工程质量")
    private String fdProjectQuality;

    @ApiModelProperty(value = "投标项目经理")
    private String fdBiddingPM;

    @ApiModelProperty(value = "项目执行经理")
    private String fdExecutiveManager;

    @ApiModelProperty(value = "项目技术负责人")
    private String fdTechnicalLeader;

    @ApiModelProperty(value = "监理单位")
    private String fdSupervisingUnit;

    @ApiModelProperty(value = "监理工程师及电话")
    private String fdEngineerAndTelephone;

    @ApiModelProperty(value = "项目描述")
    private String fdProjectDescription;
}
