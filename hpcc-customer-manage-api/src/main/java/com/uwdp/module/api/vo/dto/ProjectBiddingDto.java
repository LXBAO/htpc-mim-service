package com.uwdp.module.api.vo.dto;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uwdp.module.api.vo.enums.DictTendererEnums;
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
@ApiModel(value = "ProjectBiddingDTO对象", description = "项目投标", discriminator = "ProjectBidding")
@SearchBean(
        tables = "T_PROJECTBIDDING p left join T_LMCWORKFLOW l  on p.ID =  l.BIZID  left join T_PROJECTRECORDS r  on p.PROJECTNUMBER = r.ID"
)
public class ProjectBiddingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否国际项目")
    @DbField("r.ISFORIEN")
    // @Length(max = 100, message = "isForien长度不在有效范围内")
    private String isForien;

    @ApiModelProperty("流程实例id")
    @DbField("l.PROCESSINSTANCEID")
    private String processInstanceId;

    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;

    @ApiModelProperty(value = "主键id")
    @DbField("p.ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("p.CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    @DbField("p.CREATEDBYNAME")
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    @DbField("p.CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("p.UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("p.UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目及标段名称", required = true)
    @DbField("p.PROJECTNAME")
    // @Length(max = 50, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "投标时间", required = true)
    @DbField("p.PROJECTDATE")
    private String projectDate;

    @ApiModelProperty(value = "项目地点", required = true)
    @DbField("p.PROJECTLOCATION")
    // @Length(max = 50, message = "projectLocation长度不在有效范围内")
    private String projectLocation;

    @ApiModelProperty(value = "业主名称", required = true)
    @DbField("p.BUSINESSNAME")
    // @Length(max = 50, message = "businessName长度不在有效范围内")
    private String businessName;

    @ApiModelProperty(value = "投标单位", required = true)
    @DbField("p.TENDERER")
    // @Length(max = 50, message = "tenderer长度不在有效范围内")
    private String tenderer;

    @ApiModelProperty(value = "拦标价（万元）")
    @DbField("p.BIDBAR")
    // @Range(max = Long.MAX_VALUE, message = "bidBar长度不在有效范围内")
    private BigDecimal bidBar;

    @ApiModelProperty(value = "投标地点")
    @DbField("p.PLACEOFTENDER")
    // @Length(max = 50, message = "placeOfTender长度不在有效范围内")
    private String placeOfTender;

    @ApiModelProperty(value = "评价办法")
    @DbField("p.EVALUATIONMETHOD")
    // @Length(max = 50, message = "evaluationMethod长度不在有效范围内")
    private String evaluationMethod;

    @ApiModelProperty(value = "备注")
    @DbField("p.REMARK")
    // @Length(max = 50, message = "remark长度不在有效范围内")
    private String remark;

    @ApiModelProperty(value = "预计金额（万元）")
    @DbField("p.ESTIMATEDAMOUNT")
    // @Range(max = Long.MAX_VALUE, message = "estimatedAmount长度不在有效范围内")
    private BigDecimal estimatedAmount;

    @ApiModelProperty(value = "项目阶段")
    @DbField("p.PROJECTPHASE")
    // @Length(max = 30, message = "projectPhase长度不在有效范围内")
    private String projectPhase;

    @ApiModelProperty(value = "设计单位（设计项目不填）")
    @DbField("p.DESIGNUNIT")
    // @Length(max = 200, message = "designUnit长度不在有效范围内")
    private String designUnit;

    @ApiModelProperty(value = "项目id")
    @DbField("p.projectNumber")
    // @Length(max = 200, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "权限id")
    @DbField("p.GROUPFULLCODE")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "协助单位id")
    @DbField("p.assistanceUnitId")
    // @Length(max = 200, message = "assistanceUnitId长度不在有效范围内")
    private String assistanceUnitId;

    @ApiModelProperty(value = "协助单位")
    @DbField("p.assistanceUnit")
    // @Length(max = 200, message = "assistanceUnit长度不在有效范围内")
    private String assistanceUnit;

    @ApiModelProperty(value = "协助单位的负责人")
    @DbField("p.assistanceUnitByName")
    // @Length(max = 200, message = "assistanceUnitByName长度不在有效范围内")
    private String assistanceUnitByName;

    @ApiModelProperty(value = "投标项目经理")
    @DbField("p.bidManager")
    // @Length(max = 200, message = "bidManager长度不在有效范围内")
    private String bidManager;

    @ApiModelProperty(value = "投标平台")
    @DbField("p.bidPlatform")
    // @Length(max = 200, message = "bidPlatform长度不在有效范围内")
    private String bidPlatform;

    @ApiModelProperty(value = "ca证书")
    @DbField("p.GROUPFULLCODE")
    // @Length(max = 200, message = "certificate长度不在有效范围内")
    private String certificate;

    @ApiModelProperty(value = "投标项目九大员")
    @DbField("p.bidPeople")
    // @Length(max = 200, message = "bidPeople长度不在有效范围内")
    private String bidPeople;

    @ApiModelProperty(value = "牵头人")
    @DbField("p.initiator")
    // @Length(max = 200, message = "initiator长度不在有效范围内")
    private String initiator;

    @ApiModelProperty(value = "招标机构")
    @DbField("p.tenderAgency")
    // @Length(max = 200, message = "tenderAgency长度不在有效范围内")
    private String tenderAgency;

    @ApiModelProperty(value = "澄清截止日期")
    @DbField("p.clarifyDate")
    // @Length(max = 200, message = "clarifyDate长度不在有效范围内")
    private String clarifyDate;

    @ApiModelProperty(value = "投保证金(万元)")
    @DbField("p.insuredAmount")
    // @Range(max = Long.MAX_VALUE, message = "insuredAmount长度不在有效范围内")
    private BigDecimal insuredAmount;

    @ApiModelProperty(value = "开标方式")
    @DbField("p.bidOpeningWay")
    // @Length(max = 200, message = "bidOpeningWay长度不在有效范围内")
    private String bidOpeningWay;

    @ApiModelProperty(value = "提交文件")
    @DbField("p.submitFile")
    // @Length(max = 200, message = "submitFile长度不在有效范围内")
    private String submitFile;

    @ApiModelProperty(value = "是否为配合项目")
    @DbField("p.coordinateProject")
    // @Length(max = 200, message = "coordinateProject长度不在有效范围内")
    private String coordinateProject;

    @ApiModelProperty(value = "平台id")
    @DbField("p.bidPlatformId")
    // @Length(max = 200, message = "bidPlatformId长度不在有效范围内")
    private String bidPlatformId;

    @ApiModelProperty(value = "拦标价（万元）String类型")
    @DbField("p.BIDBARSTRING")
    // @Length(max = 200, message = "bidBarString长度不在有效范围内")
    private String bidBarString;

    @ApiModelProperty(value = "参与标段")
    @DbField("p.participationSection")
    // @Length(max = 50, message = "participationSection长度不在有效范围内")
    private String participationSection;

    @ApiModelProperty(value = "保证金方式")
    @DbField("p.marginMethod")
    // @Length(max = 50, message = "marginMethod长度不在有效范围内")
    private String marginMethod;

    @ApiModelProperty(value = "递交时间")
    @DbField("p.deliveryTime")
    // @Length(max = 200, message = "deliveryTime长度不在有效范围内")
    private String deliveryTime;

    @JsonProperty(value = "tenderer_desc")
    public String getTenderer_Desc() {
        return DictTendererEnums.getName(tenderer);
    }

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("r.INTASSISTANCEUNIT")
    // @Length(max = 255)
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("r.INTASSISTANCEUNITNAME")
    // @Length(max = 255)
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "所属区域", required = true)
    @DbField("r.OWNINGREGION")
    // @Length(max = 255, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    //附件表
    @DbIgnore
    List<AttachmentDto> attachmentDtos;

}
