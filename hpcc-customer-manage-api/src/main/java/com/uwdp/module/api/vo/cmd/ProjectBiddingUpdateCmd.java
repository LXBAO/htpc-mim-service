package com.uwdp.module.api.vo.cmd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 项目投标
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectBidding UpdateCmd对象", description = "项目投标", discriminator = "ProjectBidding")
public class ProjectBiddingUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id", required = true)
    @NotNull(message = "主键不能为空")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目及标段名称", required = true)
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "投标时间", required = true)
    private String projectDate;

    @ApiModelProperty(value = "项目地点", required = true)
    // @Length(max = 255, message = "projectLocation长度不在有效范围内")
    private String projectLocation;

    @ApiModelProperty(value = "业主名称", required = true)
    // @Length(max = 255, message = "businessName长度不在有效范围内")
    private String businessName;

    @ApiModelProperty(value = "投标单位", required = true)
    // @Length(max = 255, message = "tenderer长度不在有效范围内")
    private String tenderer;

    @ApiModelProperty(value = "拦标价（万元）")
    // @Range(max = Long.MAX_VALUE, message = "bidBar长度不在有效范围内")
    private BigDecimal bidBar;

    @ApiModelProperty(value = "投标地点")
    // @Length(max = 255, message = "placeOfTender长度不在有效范围内")
    private String placeOfTender;

    @ApiModelProperty(value = "评价办法")
    // @Length(max = 255, message = "evaluationMethod长度不在有效范围内")
    private String evaluationMethod;

    @ApiModelProperty(value = "备注")
    // @Length(max = 255, message = "remark长度不在有效范围内")
    private String remark;

    @ApiModelProperty(value = "预计金额（万元）")
    // @Range(max = Long.MAX_VALUE, message = "estimatedAmount长度不在有效范围内")
    private BigDecimal estimatedAmount;

    @ApiModelProperty(value = "项目阶段")
    // @Length(max = 30, message = "projectPhase长度不在有效范围内")
    private String projectPhase;

    @ApiModelProperty(value = "设计单位（设计项目不填）")
    // @Length(max = 200, message = "designUnit长度不在有效范围内")
    private String designUnit;

    @ApiModelProperty(value = "项目id")
    // @Length(max = 200, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "权限id")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "协助单位id")
    // @Length(max = 200, message = "assistanceUnitId长度不在有效范围内")
    private String assistanceUnitId;

    @ApiModelProperty(value = "协助单位")
    // @Length(max = 200, message = "assistanceUnit长度不在有效范围内")
    private String assistanceUnit;

    @ApiModelProperty(value = "协助单位的负责人")
    // @Length(max = 200, message = "assistanceUnitByName长度不在有效范围内")
    private String assistanceUnitByName;

    @ApiModelProperty(value = "投标项目经理")
    // @Length(max = 200, message = "bidManager长度不在有效范围内")
    private String bidManager;

    @ApiModelProperty(value = "投标平台")
    // @Length(max = 200, message = "bidPlatform长度不在有效范围内")
    private String bidPlatform;

    @ApiModelProperty(value = "ca证书")
    // @Length(max = 200, message = "certificate长度不在有效范围内")
    private String certificate;

    @ApiModelProperty(value = "投标项目九大员")
    // @Length(max = 200, message = "bidPeople长度不在有效范围内")
    private String bidPeople;

    @ApiModelProperty(value = "牵头人")
    // @Length(max = 200, message = "initiator长度不在有效范围内")
    private String initiator;

    @ApiModelProperty(value = "招标机构")
    // @Length(max = 200, message = "tenderAgency长度不在有效范围内")
    private String tenderAgency;

    @ApiModelProperty(value = "澄清截止日期")
    // @Length(max = 200, message = "clarifyDate长度不在有效范围内")
    private String clarifyDate;

    @ApiModelProperty(value = "投保证金(万元)")
    // @Range(max = Long.MAX_VALUE, message = "insuredAmount长度不在有效范围内")
    private BigDecimal insuredAmount;

    @ApiModelProperty(value = "开标方式")
    // @Length(max = 200, message = "bidOpeningWay长度不在有效范围内")
    private String bidOpeningWay;

    @ApiModelProperty(value = "提交文件")
    // @Length(max = 200, message = "submitFile长度不在有效范围内")
    private String submitFile;

    @ApiModelProperty(value = "是否为配合项目")
    // @Length(max = 200, message = "coordinateProject长度不在有效范围内")
    private String coordinateProject;

    @ApiModelProperty(value = "平台id")
    // @Length(max = 200, message = "bidPlatformId长度不在有效范围内")
    private String bidPlatformId;

    @ApiModelProperty(value = "拦标价（万元）String类型")
    // @Length(max = 200, message = "bidBarString长度不在有效范围内")
    private String bidBarString;

    @ApiModelProperty(value = "参与标段")
    // @Length(max = 50, message = "participationSection长度不在有效范围内")
    private String participationSection;

    @ApiModelProperty(value = "保证金方式")
    // @Length(max = 50, message = "marginMethod长度不在有效范围内")
    private String marginMethod;

    @ApiModelProperty(value = "递交时间")
    // @Length(max = 200, message = "deliveryTime长度不在有效范围内")
    private String deliveryTime;


    //附件表
    List<AttachmentAddCmd> addCmdList;
}
