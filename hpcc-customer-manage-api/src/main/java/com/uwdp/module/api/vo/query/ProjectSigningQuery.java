package com.uwdp.module.api.vo.query;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目签约
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProjectSigningDo Query对象", description = "项目签约", discriminator = "ProjectSigning")
public class ProjectSigningQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目编号")
    private String projectNumber;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "产业领域类别", required = true)
    private String industryCategory;

    @ApiModelProperty(value = "建设地点")
    private String location;

    @ApiModelProperty(value = "项目金额")
    private BigDecimal itemAmount;

    @ApiModelProperty(value = "投资规模(万元)")
    private Double investmentScale;

    @ApiModelProperty(value = "装机规模(MW)")
    private String installedCapacity;

    @ApiModelProperty(value = "项目来源", required = true)
    private String projectSource;

    @ApiModelProperty(value = "中标日期")
    private String winDate;

    @ApiModelProperty(value = "建设内容")
    private String constructionContent;

    @ApiModelProperty(value = "项目背景")
    private String projectContext;

    @ApiModelProperty(value = "合同名称", required = true)
    private String contracName;

    @ApiModelProperty(value = "合同金额(万元)")
    private Long contractAmount;

    @ApiModelProperty(value = "合同签订时间")
    private String timeOfSigning;

    @ApiModelProperty(value = "合同开始时间")
    private String commencementOfContract;

    @ApiModelProperty(value = "合同结束时间")
    private String contractEndTime;

    @ApiModelProperty(value = "合同工期(天)")
    private String contractProjectTime;

    @ApiModelProperty(value = "产业链类别")
    private String category;

    @ApiModelProperty(value = "新产业类别")
    private String newIndustry;

    @ApiModelProperty(value = "商业模式类别")
    private String businessModel;

    @ApiModelProperty(value = "项目级别")
    private String projectLevel;

    @ApiModelProperty(value = "合同状态")
    private String contractStatus;

    @ApiModelProperty(value = "合同甲方")
    private String contractParty;

    @ApiModelProperty(value = "建设单位")
    private String constructionUnit;

    @ApiModelProperty(value = "建设单位分类")
    private String classificationOfConstructionUnit;

    @ApiModelProperty(value = "合同乙方")
    private String contractPartyB;

    @ApiModelProperty(value = "是否直接从建设单位承揽")
    private String unitContract;

    @ApiModelProperty(value = "公司内部协同")
    private String internalAssistance;

    @ApiModelProperty(value = "录入顺序指标")
    private String sequenceIndex;

    @ApiModelProperty(value = "集团专贡备注栏")
    private String groupTribute;

    @ApiModelProperty(value = "报表专贡备注栏")
    private String statementTribute;

    @ApiModelProperty(value = "是否为新能源指标转化")
    private String indexTransformation;

    @ApiModelProperty(value = "EPC签约单位")
    private String contractingUnit;

    @ApiModelProperty(value = "EPC签约额(亿元)")
    private Long epcContractAmount;

    @ApiModelProperty(value = "是否已报送跟踪及中标项目信息")
    private String winningProject;

    @ApiModelProperty(value = "所属类型")
    private String typeOfOwnership;

    @ApiModelProperty(value = "创建人名称")
    private String createdName;

    @ApiModelProperty(value = "组织全编码")
    private String groupFullCode;

    @ApiModelProperty(value = "相关证明文件上传")
    private String relatedDocument;

    @ApiModelProperty(value = "上传附件")
    private String attachmentUploading;

    @ApiModelProperty(value = "主要实物量指标1")
    private String mainPhysicalQuantityIndexOne;

    @ApiModelProperty(value = "主要实物量指标2")
    private String mainPhysicalQuantityIndexTwo;

    @ApiModelProperty(value = "主要实物量1")
    private String principalPhysicalQuantityOne;

    @ApiModelProperty(value = "主要实物量2")
    private String principalPhysicalQuantityTwo;

    @ApiModelProperty(value = "合同所在地点")
    private String placeOfContract;

    @ApiModelProperty(value = "集团专责备注栏")
    private String groupSpecificRemarksColumn;

    @ApiModelProperty(value = "报表专责备注栏")
    private String reportSpecializedRemarksField;

    @ApiModelProperty(value = "产业领域")
    private String industry;

    @ApiModelProperty(value = "所属公司名称")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "项目规模单位")
    private String unit;

    @ApiModelProperty(value = "商业模式类别名称")
    private String businessModelName;
}
