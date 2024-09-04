package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PERFORMANCELEDGER")
@ApiModel(value = "PerformanceLedgerDo entity对象", description = "业绩台账")
public class PerformanceLedgerDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "FDID", type = IdType.ASSIGN_ID)
    private Long fdId;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("年度")
    @TableField("FDYEAR")
    private String fdYear;

    @ApiModelProperty("项目名称")
    @TableField("FDPROJECTNAME")
    private String fdProjectName;

    @ApiModelProperty("项目所在地")
    @TableField("FDPROJECTADDR")
    private String fdProjectAddr;

    @ApiModelProperty("发包人（业主）名称")
    @TableField("FDPROPRIETORNAME")
    private String fdProprietorName;

    @ApiModelProperty("发包人地址")
    @TableField("FDPROPRIETORADDR")
    private String fdProprietorAddr;

    @ApiModelProperty("发包人联系方式")
    @TableField("FDPROPRIETORNUMBER")
    private String fdProprietorNumber;

    @ApiModelProperty("合同金额(万元)")
    @TableField("FDCONTRACTAMOUNT ")
    private BigDecimal fdContractAmount ;

    @ApiModelProperty("建设规模")
    @TableField("FDBUILDINGSIZE")
    private String fdBuildingSize;

    @ApiModelProperty("合同开工日期")
    @TableField("FDCONTRACTSTARTUPDATE")
    private LocalDateTime fdContractStartUpDate;

    @ApiModelProperty("合同竣工日期")
    @TableField("FDCONTRACTCOMPLETIONDATE")
    private LocalDateTime fdContractCompletionDate;

    @ApiModelProperty("实际开工日期")
    @TableField("FDREALITYSTARTUPDATE")
    private LocalDateTime fdRealityStartUpDate;

    @ApiModelProperty("实际竣工日期")
    @TableField("FDREALITYCOMPLETIONDATE")
    private LocalDateTime fdRealityCompletionDate;

    @ApiModelProperty("主要工作内容(工程范围）")
    @TableField("FDGROUNDWORK")
    private String fdGroundWork;

    @ApiModelProperty("工程质量")
    @TableField("FDPROJECTQUALITY")
    private String fdProjectQuality;

    @ApiModelProperty("投标项目经理")
    @TableField("FDBIDDINGPM")
    private String fdBiddingPM;

    @ApiModelProperty("项目执行经理")
    @TableField("FDEXECUTIVEMANAGER")
    private String fdExecutiveManager;

    @ApiModelProperty("项目技术负责人")
    @TableField("FDTECHNICALLEADER")
    private String fdTechnicalLeader;

    @ApiModelProperty("监理单位")
    @TableField("FDSUPERVISINGUNIT")
    private String fdSupervisingUnit;

    @ApiModelProperty("监理工程师及电话")
    @TableField("FDENGINEERANDTELEPHONE")
    private String fdEngineerAndTelephone;

    @ApiModelProperty("项目描述")
    @TableField("FDPROJECTDESCRIPTION")
    private String fdProjectDescription;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEDNAME")
    private String createdName;
}
