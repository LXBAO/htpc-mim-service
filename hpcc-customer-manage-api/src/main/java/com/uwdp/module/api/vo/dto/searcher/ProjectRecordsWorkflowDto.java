package com.uwdp.module.api.vo.dto.searcher;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
@SearchBean(
        tables = "T_PROJECTRECORDS p left join T_LMCWORKFLOW l on p.ID =  l.BIZID"
)
@ToString
@Data
public class ProjectRecordsWorkflowDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("流程实例id")
    @DbField("l.PROCESSINSTANCEID")
    private String processInstanceId;

    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;

    @ApiModelProperty(value = "唯一标识")
    @DbField("p.ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("p.CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("p.GROUPFULLCODE")
    private String groupFullCode;

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

    @ApiModelProperty(value = "项目工程名称")
    @DbField("p.PROJECTNAME")
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "所在地(省市)")
    @DbField("p.LOCATION")
    // @Length(max = 20, message = "location长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "产业领域类别", required = true)
    @DbField("p.INDUSTRYCATEGORY")
    // @Length(max = 30, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "装机规模(MW)")
    @DbField("p.INSTALLEDCAPACITY")
    // @Length(max = 100, message = "installedCapacity长度不在有效范围内")
    private String installedCapacity;

    @ApiModelProperty(value = "承包模式")
    @DbField("p.CONTRACTINGMODE")
    // @Length(max = 100, message = "contractingMode长度不在有效范围内")
    private String contractingMode;

    @ApiModelProperty(value = "预计合同金额(亿)")
    @DbField("p.ESTIMATEDCONTRACTAMOUNT")
    // @Range(max = Long.MAX_VALUE, message = "estimatedContractAmount长度不在有效范围内")
    private Double estimatedContractAmount;

    @ApiModelProperty(value = "预计投标时间")
    @DbField("p.ESTIMATEDBIDDINGTIME")
    private LocalDateTime estimatedBiddingTime;

    @ApiModelProperty(value = "项目编号", required = true)
    @DbField("p.PROJECTNUMBER")
    // @Length(max = 255, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "登记单位")
    @DbField("p.REGISTERUNIT")
    // @Length(max = 255, message = "registerUnit长度不在有效范围内")
    private String registerUnit;

    @ApiModelProperty(value = "项目阶段")
    @DbField("p.PROJECTPHASE")
    // @Length(max = 255, message = "projectPhase长度不在有效范围内")
    private String projectPhase;

    @ApiModelProperty(value = "业主单位")
    @DbField("p.OWNERUNIT")
    // @Length(max = 255, message = "ownerUnit长度不在有效范围内")
    private String ownerUnit;

    @ApiModelProperty(value = "业主单位/人", required = true)
    @DbField("p.OWNER")
    // @Length(max = 255, message = "owner长度不在有效范围内")
    private String owner;

    @ApiModelProperty(value = "重要程度")
    @DbField("p.IMPORTANTTYPE")
    // @Length(max = 255, message = "importantType长度不在有效范围内")
    private String importantType;

    @ApiModelProperty(value = "所属区域", required = true)
    @DbField("p.OWNINGREGION")
    // @Length(max = 255, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    @ApiModelProperty(value = "项目来源", required = true)
    @DbField("p.PROJECTSOURCE")
    // @Length(max = 255, message = "projectSource长度不在有效范围内")
    private String projectSource;

    @ApiModelProperty(value = "投资规模(万元)")
    @DbField("p.INVESTMENTSCALE")
    // @Range(max = Long.MAX_VALUE, message = "investmentScale长度不在有效范围内")
    private Double investmentScale;

    @ApiModelProperty(value = "项目金额")
    @DbField("p.ITEMAMOUNT")
    // @Range(max = Long.MAX_VALUE, message = "itemAmount长度不在有效范围内")
    private Double itemAmount;

    @ApiModelProperty(value = "请求")
    @DbField("p.DEMAND")
    // @Length(max = 300, message = "demand长度不在有效范围内")
    private String demand;

    @ApiModelProperty(value = "建设内容")
    @DbField("p.CONSTRUCTIONCONTENT")
    // @Length(max = 500, message = "constructionContent长度不在有效范围内")
    private String constructionContent;

    @ApiModelProperty(value = "项目背景")
    @DbField("p.PROJECTCONTEXT")
    // @Length(max = 500, message = "projectContext长度不在有效范围内")
    private String projectContext;

    @ApiModelProperty(value = "项目状态", required = true)
    @DbField("p.PROJECTSTATUS")
    // @Length(max = 30, message = "projectStatus长度不在有效范围内")
    private String projectStatus;

    @ApiModelProperty(value = "项目类别")
    @DbField("p.PROJECTCATEGORY")
    // @Length(max = 100, message = "projectCategory长度不在有效范围内")
    private String projectCategory;

    @ApiModelProperty(value = "客户ID")
    @DbField(" p.FDID")
    // @Length(max = 100, message = " fdId长度不在有效范围内")
    private String  fdId;

    @ApiModelProperty(value = "客户名称")
    @DbField(" p.FDNAME")
    // @Length(max = 100, message = " fdName长度不在有效范围内")
    private String  fdName;

    @ApiModelProperty(value = "原因")
    @DbField("p.CAUSE")
    // @Length(max = 200, message = "cause长度不在有效范围内")
    private String cause;

    @ApiModelProperty(value = "创建人名称")
    @DbField("p.CREATEDNAME")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty("隐藏状态")
    @DbField("p.hideState")
    // @Length(max = 100, message = "hideState长度不在有效范围内")
    private String hideState;

    @ApiModelProperty("是否国际项目")
    @DbField("p.ISFORIEN")
    // @Length(max = 100, message = "isForien长度不在有效范围内")
    private String isForien;

    @ApiModelProperty(value = "产业领域")
    @DbField("p.INDUSTRY")
    // @Length(max = 30, message = "industry长度不在有效范围内")
    private String industry;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("p.INTASSISTANCEUNIT")
    // @Length(max = 255)
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("p.INTASSISTANCEUNITNAME")
    // @Length(max = 255)
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "建设单位名称")
    @DbField("p.LOCATIONNAME")
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String locationName;

    @ApiModelProperty(value = "承包模式名称")
    @DbField("p.CONTRACTINGMODENAME")
    // @Length(max = 100, message = "contractingMode长度不在有效范围内")
    private String contractingModeName;

    @ApiModelProperty(value = "项目规模单位")
    @DbField("p.unit")
    // @Length(max = 50, message = "unit长度不在有效范围内")
    private String unit;

    @ApiModelProperty(value = "登记通过时间")
    @DbField("p.SUCCESSTIME")
    private LocalDate successTime;
}
