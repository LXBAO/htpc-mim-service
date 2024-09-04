package com.uwdp.module.api.vo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectBiddingExcelImport对象", description = "项目投标", discriminator = "ProjectBidding")
@SearchBean(tables = "T_PROJECTBIDDING")
@EqualsAndHashCode(callSuper = false)
public class ProjectBiddingExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主键id"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 1)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATEDBYNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 1)
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 2)
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新者"}, index = 3)
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 4)
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目及标段名称", required = true)
    @DbField("PROJECTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目及标段名称"}, index = 5)
    // @Length(max = 50, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "投标时间", required = true)
    @DbField("PROJECTDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投标时间"}, index = 6)
    private String projectDate;

    @ApiModelProperty(value = "项目地点", required = true)
    @DbField("PROJECTLOCATION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目地点"}, index = 7)
    // @Length(max = 50, message = "projectLocation长度不在有效范围内")
    private String projectLocation;

    @ApiModelProperty(value = "业主名称", required = true)
    @DbField("BUSINESSNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业主名称"}, index = 8)
    // @Length(max = 50, message = "businessName长度不在有效范围内")
    private String businessName;

    @ApiModelProperty(value = "投标单位", required = true)
    @DbField("TENDERER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投标单位"}, index = 9)
    // @Length(max = 50, message = "tenderer长度不在有效范围内")
    private String tenderer;

//    @ApiModelProperty(value = "拦标价（万元）")
//    @DbField("BIDBAR")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"拦标价（万元）"}, index = 10)
//    // @Range(max = Long.MAX_VALUE, message = "bidBar长度不在有效范围内")
//    private BigDecimal bidBar;

    @ApiModelProperty(value = "拦标价（万元）")
    @DbField("BIDBARSTRING")
    @ColumnWidth(16)
    @ExcelProperty(value = {"拦标价（万元）"}, index = 10)
    // @Length(max = 200, message = "bidBarString长度不在有效范围内")
    private String bidBar;

    @ApiModelProperty(value = "投标地点")
    @DbField("PLACEOFTENDER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投标地点"}, index = 11)
    // @Length(max = 50, message = "placeOfTender长度不在有效范围内")
    private String placeOfTender;

    @ApiModelProperty(value = "评价办法")
    @DbField("EVALUATIONMETHOD")
    @ColumnWidth(16)
    @ExcelProperty(value = {"评价办法"}, index = 12)
    // @Length(max = 50, message = "evaluationMethod长度不在有效范围内")
    private String evaluationMethod;

    @ApiModelProperty(value = "备注")
    @DbField("REMARK")
    @ColumnWidth(16)
    @ExcelProperty(value = {"备注"}, index = 13)
    // @Length(max = 50, message = "remark长度不在有效范围内")
    private String remark;

    @ApiModelProperty(value = "预计金额（万元）")
    @DbField("ESTIMATEDAMOUNT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"预计金额（万元）"}, index = 14)
    // @Range(max = Long.MAX_VALUE, message = "estimatedAmount长度不在有效范围内")
    private BigDecimal estimatedAmount;

    @ApiModelProperty(value = "项目阶段")
    @DbField("PROJECTPHASE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目阶段"}, index = 15)
    // @Length(max = 30, message = "projectPhase长度不在有效范围内")
    private String projectPhase;

    @ApiModelProperty(value = "设计单位（设计项目不填）")
    @DbField("DESIGNUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"设计单位（设计项目不填）"}, index = 16)
    // @Length(max = 200, message = "designUnit长度不在有效范围内")
    private String designUnit;

    @ApiModelProperty(value = "项目id")
    @DbField("projectNumber")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目id"}, index = 17)
    // @Length(max = 200, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "权限id")
    @DbField("GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"权限id"}, index = 18)
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "协助单位id")
    @DbField("assistanceUnitId")
    @ColumnWidth(16)
    @ExcelProperty(value = {"协助单位id"}, index = 19)
    // @Length(max = 200, message = "assistanceUnitId长度不在有效范围内")
    private String assistanceUnitId;

    @ApiModelProperty(value = "协助单位")
    @DbField("assistanceUnit")
    @ColumnWidth(16)
    @ExcelProperty(value = {"协助单位"}, index = 20)
    // @Length(max = 200, message = "assistanceUnit长度不在有效范围内")
    private String assistanceUnit;

    @ApiModelProperty(value = "协助单位的负责人")
    @DbField("assistanceUnitByName")
    @ColumnWidth(16)
    @ExcelProperty(value = {"协助单位的负责人"}, index = 21)
    // @Length(max = 200, message = "assistanceUnitByName长度不在有效范围内")
    private String assistanceUnitByName;

    @ApiModelProperty(value = "投标项目经理")
    @DbField("投标项目经理")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投标项目经理"}, index = 22)
    // @Length(max = 200, message = "投标项目经理长度不在有效范围内")
    private String bidManager;

    @ApiModelProperty(value = "投标平台")
    @DbField("bidPlatform")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投标平台"}, index = 23)
    // @Length(max = 200, message = "bidPlatform长度不在有效范围内")
    private String bidPlatform;

    @ApiModelProperty(value = "ca证书")
    @DbField("certificate")
    @ColumnWidth(16)
    @ExcelProperty(value = {"ca证书"}, index = 24)
    // @Length(max = 200, message = "certificate长度不在有效范围内")
    private String certificate;

    @ApiModelProperty(value = "投标项目九大员")
    @DbField("bidPeople")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投标项目九大员"}, index = 25)
    // @Length(max = 200, message = "bidPeople长度不在有效范围内")
    private String bidPeople;

    @ApiModelProperty(value = "牵头人")
    @DbField("initiator")
    @ColumnWidth(16)
    @ExcelProperty(value = {"牵头人"}, index = 26)
    // @Length(max = 200, message = "initiator长度不在有效范围内")
    private String initiator;

    @ApiModelProperty(value = "招标机构")
    @DbField("tenderAgency")
    @ColumnWidth(16)
    @ExcelProperty(value = {"招标机构"}, index = 27)
    // @Length(max = 200, message = "tenderAgency长度不在有效范围内")
    private String tenderAgency;

    @ApiModelProperty(value = "澄清截止日期")
    @DbField("clarifyDate")
    @ColumnWidth(16)
    @ExcelProperty(value = {"澄清截止日期"}, index = 28)
    // @Length(max = 200, message = "clarifyDate长度不在有效范围内")
    private String clarifyDate;

    @ApiModelProperty(value = "投保证金(万元)")
    @DbField("insuredAmount")
    @ColumnWidth(16)
    @ExcelProperty(value = {"投保证金(万元)"}, index = 29)
    // @Range(max = Long.MAX_VALUE, message = "insuredAmount长度不在有效范围内")
    private BigDecimal insuredAmount;

    @ApiModelProperty(value = "开标方式")
    @DbField("bidOpeningWay")
    @ColumnWidth(16)
    @ExcelProperty(value = {"开标方式"}, index = 30)
    // @Length(max = 200, message = "bidOpeningWay长度不在有效范围内")
    private String bidOpeningWay;

    @ApiModelProperty(value = "提交文件")
    @DbField("submitFile")
    @ColumnWidth(16)
    @ExcelProperty(value = {"提交文件"}, index = 31)
    // @Length(max = 200, message = "submitFile长度不在有效范围内")
    private String submitFile;

    @ApiModelProperty(value = "是否为配合项目")
    @DbField("coordinateProject")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否为配合项目"}, index = 32)
    // @Length(max = 200, message = "coordinateProject长度不在有效范围内")
    private String coordinateProject;

    @ColumnWidth(16)
    @ExcelProperty(value = {"平台id"}, index = 33)
    // @Length(max = 15, message = "bidPlatformId长度不在有效范围内")
    @ApiModelProperty("平台id")
    @DbField("bidPlatformId")
    private String bidPlatformId;

    @ColumnWidth(16)
    @ExcelProperty(value = {"参与标段"}, index = 34)
    // @Length(max = 15, message = "participationSection长度不在有效范围内")
    @ApiModelProperty("参与标段")
    @DbField("participationSection")
    private String participationSection;

    @ColumnWidth(16)
    @ExcelProperty(value = {"保证金方式"}, index = 35)
    // @Length(max = 15, message = "marginMethod长度不在有效范围内")
    @ApiModelProperty("保证金方式")
    @DbField("marginMethod")
    private String marginMethod;

    @ApiModelProperty(value = "递交时间")
    @DbField("deliveryTime")
    @ColumnWidth(16)
    @ExcelProperty(value = {"递交时间"}, index = 36)
    // @Length(max = 200, message = "deliveryTime长度不在有效范围内")
    private String deliveryTime;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
