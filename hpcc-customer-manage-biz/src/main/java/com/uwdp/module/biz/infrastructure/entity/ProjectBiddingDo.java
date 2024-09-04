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
 * 项目投标
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PROJECTBIDDING")
@ApiModel(value = "ProjectBiddingDo entity对象", description = "项目投标")
public class ProjectBiddingDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建者名称")
    @TableField("CREATEDBYNAME")
    private String createdByName;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("项目及标段名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty("投标时间")
    @TableField("PROJECTDATE")
    private String projectDate;

    @ApiModelProperty("项目地点")
    @TableField("PROJECTLOCATION")
    private String projectLocation;

    @ApiModelProperty("业主名称")
    @TableField("BUSINESSNAME")
    private String businessName;

    @ApiModelProperty("投标单位")
    @TableField("TENDERER")
    private String tenderer;

    @ApiModelProperty("拦标价（万元）")
    @TableField("BIDBAR")
    private BigDecimal bidBar;

    @ApiModelProperty("拦标价（万元）字符串类型")
    @TableField("BIDBARSTRING")
    private String bidBarString;

    @ApiModelProperty("投标地点")
    @TableField("PLACEOFTENDER")
    private String placeOfTender;

    @ApiModelProperty("评价办法")
    @TableField("EVALUATIONMETHOD")
    private String evaluationMethod;

    @ApiModelProperty("备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty("预计金额（万元）")
    @TableField("ESTIMATEDAMOUNT")
    private BigDecimal estimatedAmount;

    @ApiModelProperty("项目阶段")
    @TableField("PROJECTPHASE")
    private String projectPhase;

    @ApiModelProperty("设计单位（设计项目不填）")
    @TableField("DESIGNUNIT")
    private String designUnit;

    @ApiModelProperty("项目id")
    @TableField("projectNumber")
    private String projectNumber;

    @ApiModelProperty(value = "权限id")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty(value = "协助单位id")
    @TableField("assistanceUnitId")
    private String assistanceUnitId;

    @ApiModelProperty(value = "协助单位")
    @TableField("assistanceUnit")
    private String assistanceUnit;

    @ApiModelProperty(value = "协助单位的负责人")
    @TableField("assistanceUnitByName")
    private String assistanceUnitByName;

    @ApiModelProperty(value = "投标项目经理")
    @TableField("bidManager")
    private String bidManager;

    @ApiModelProperty(value = "投标平台")
    @TableField("bidPlatform")
    private String bidPlatform;

    @ApiModelProperty(value = "ca证书")
    @TableField("certificate")
    private String certificate;

    @ApiModelProperty(value = "投标项目九大员")
    @TableField("bidPeople")
    private String bidPeople;

    @ApiModelProperty(value = "牵头人")
    @TableField("initiator")
    private String initiator;

    @ApiModelProperty(value = "招标机构")
    @TableField("tenderAgency")
    private String tenderAgency;

    @ApiModelProperty(value = "澄清截止日期")
    @TableField("clarifyDate")
    private String clarifyDate;

    @ApiModelProperty(value = "投保证金(万元)")
    @TableField("insuredAmount")
    private BigDecimal insuredAmount;

    @ApiModelProperty(value = "开标方式")
    @TableField("bidOpeningWay")
    private String bidOpeningWay;

    @ApiModelProperty(value = "提交文件")
    @TableField("submitFile")
    private String submitFile;

    @ApiModelProperty(value = "是否为配合项目")
    @TableField("coordinateProject")
    private String coordinateProject;

    @ApiModelProperty(value = "平台id")
    @TableField("bidPlatformId")
    private String bidPlatformId;

    @ApiModelProperty(value = "参与标段")
    @TableField("participationSection")
    private String participationSection;

    @ApiModelProperty(value = "保证金方式")
    @TableField("marginMethod")
    private String marginMethod;

    @ApiModelProperty(value = "递交时间")
    @TableField("deliveryTime")
    private String deliveryTime;
}
