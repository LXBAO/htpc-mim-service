package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目投标
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProjectBiddingDo Query对象", description = "项目投标", discriminator = "ProjectBidding")
public class ProjectBiddingQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目及标段名称", required = true)
    private String projectName;

    @ApiModelProperty(value = "投标时间", required = true)
    private String projectDate;

    @ApiModelProperty(value = "项目地点", required = true)
    private String projectLocation;

    @ApiModelProperty(value = "业主名称", required = true)
    private String businessName;

    @ApiModelProperty(value = "投标单位", required = true)
    private String tenderer;

//    @ApiModelProperty(value = "拦标价（万元）")
//    private BigDecimal bidBar;

    @ApiModelProperty(value = "拦标价（万元）字符串类型")
    private String bidBarString;

    @ApiModelProperty(value = "投标地点")
    private String placeOfTender;

    @ApiModelProperty(value = "评价办法")
    private String evaluationMethod;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "预计金额（万元）")
    private BigDecimal estimatedAmount;

    @ApiModelProperty(value = "项目阶段")
    private String projectPhase;

    @ApiModelProperty(value = "项目id")
    private String projectNumber;

    @ApiModelProperty(value = "设计单位（设计项目不填）")
    private String designUnit;

    @ApiModelProperty(value = "权限id")
    private String groupFullCode;

    @ApiModelProperty(value = "协助单位id")
    private String assistanceUnitId;

    @ApiModelProperty(value = "协助单位")
    private String assistanceUnit;

    @ApiModelProperty(value = "协助单位的负责人")
    private String assistanceUnitByName;

    @ApiModelProperty(value = "投标项目经理")
    private String bidManager;

    @ApiModelProperty(value = "投标平台")
    private String bidPlatform;

    @ApiModelProperty(value = "ca证书")
    private String certificate;

    @ApiModelProperty(value = "投标项目九大员")
    private String bidPeople;

    @ApiModelProperty(value = "牵头人")
    private String initiator;

    @ApiModelProperty(value = "招标机构")
    private String tenderAgency;

    @ApiModelProperty(value = "澄清截止日期")
    private String clarifyDate;

    @ApiModelProperty(value = "投保证金(万元)")
    private BigDecimal insuredAmount;

    @ApiModelProperty(value = "开标方式")
    private String bidOpeningWay;

    @ApiModelProperty(value = "提交文件")
    private String submitFile;

    @ApiModelProperty(value = "是否为配合项目")
    private String coordinateProject;

    @ApiModelProperty(value = "平台id")
    private String bidPlatformId;

    @ApiModelProperty(value = "参与标段")
    private String participationSection;

    @ApiModelProperty(value = "递交时间")
    private String deliveryTime;

    @ApiModelProperty(value = "保证金方式")
    private String marginMethod;
}
