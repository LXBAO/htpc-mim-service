package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 公关关系计划
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "VisitPlanExcelImport对象", description = "公关关系计划", discriminator = "visitPlan")
@SearchBean(tables = "T_VISITPLAN")
@EqualsAndHashCode(callSuper = false)
public class VisitPlanExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"唯一标识"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 1)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATED_BY_NAME")
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

    @ApiModelProperty(value = "关联客户信息唯一标识")
    @DbField("CLIENTINFOID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联客户信息唯一标识"}, index = 5)
    // @Range(max = Long.MAX_VALUE, message = "clientInfoId长度不在有效范围内")
    private Long clientInfoId;

    @ApiModelProperty(value = "建议日期")
    @DbField("ADVICEDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建议日期"}, index = 6)
    private String adviceDate;

    @ApiModelProperty(value = "出访省市")
    @DbField("VISITPROVINCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"出访省市"}, index = 7)
    // @Length(max = 10, message = "visitProvince长度不在有效范围内")
    private String visitProvince;

    @ApiModelProperty(value = "出访市")
    @DbField("VISITCITY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"出访市"}, index = 8)
    // @Length(max = 10, message = "visitCity长度不在有效范围内")
    private String visitCity;

    @ApiModelProperty(value = "所属区域总部")
    @DbField("REGIONALHEADQUARTER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属区域总部"}, index = 9)
    // @Length(max = 100, message = "regionalHeadquarter长度不在有效范围内")
    private String regionalHeadquarter;

    @ApiModelProperty(value = "活动地点")
    @DbField("ACTIVITYADDRESS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动地点"}, index = 10)
    // @Length(max = 500, message = "activityAddress长度不在有效范围内")
    private String activityAddress;

    @ApiModelProperty(value = "出行领导")
    @DbField("TRAVELLEADER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"出行领导"}, index = 11)
    // @Length(max = 500, message = "travelLeader长度不在有效范围内")
    private String travelLeader;

    @ApiModelProperty(value = "出行领导姓名")
    @DbField("TRAVELLEADERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"出行领导姓名"}, index = 11)
    // @Length(max = 500, message = "travelLeaderName长度不在有效范围内")
    private String travelLeaderName;

    @ApiModelProperty(value = "牵头单位")
    @DbField("LEADUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"牵头单位"}, index = 12)
    // @Length(max = 500, message = "leadUnit长度不在有效范围内")
    private String leadUnit;

    @ApiModelProperty(value = "牵头单位联系人")
    @DbField("LEADUNITCONTACTPERSON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"牵头单位联系人"}, index = 13)
    // @Length(max = 50, message = "leadUnitContactPerson长度不在有效范围内")
    private String leadUnitContactPerson;

    @ApiModelProperty(value = "牵头单位联系人")
    @DbField("LEADUNITCONTACT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"牵头单位联系人"}, index = 14)
    // @Length(max = 100, message = "leadUnitContact长度不在有效范围内")
    private String leadUnitContact;

    @ApiModelProperty(value = "活动方式")
    @DbField("ACTIVITYWAY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动方式"}, index = 15)
    // @Length(max = 50, message = "activityWay长度不在有效范围内")
    private String activityWay;

    @ApiModelProperty(value = "事业部负责人")
    @DbField("BUSINESSHEADER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"事业部负责人"}, index = 16)
    // @Length(max = 50, message = "businessHeader长度不在有效范围内")
    private String businessHeader;

    @ApiModelProperty(value = "政府方联系人")
    @DbField("GOVERNCONTACTPEROSN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"政府方联系人"}, index = 17)
    // @Length(max = 50, message = "governContactPerosn长度不在有效范围内")
    private String governContactPerosn;

    @ApiModelProperty(value = "活动目的")
    @DbField("ACTIVITYPURPOSE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动目的"}, index = 18)
    // @Length(max = 500, message = "activityPurpose长度不在有效范围内")
    private String activityPurpose;

    @ApiModelProperty(value = "备注")
    @DbField("MEMO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"备注"}, index = 19)
    // @Length(max = 500, message = "memo长度不在有效范围内")
    private String memo;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;

    @ApiModelProperty(value = "主题")
    @DbField("TITLE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主题"}, index = 20)
    // @Length(max = 500, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "是否参加会议")
    @DbField("IFCONFERENCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否参加会议"}, index = 21)
    // @Length(max = 50, message = "ifConference长度不在有效范围内")
    private String ifConference;

    @ApiModelProperty(value = "牵头单位编码")
    @DbField("LEADUNITCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"牵头单位编码"}, index = 22)
    // @Length(max = 50, message = "leadUnitCode长度不在有效范围内")
    private String leadUnitCode;

    @ApiModelProperty(value = "出访省市名称")
    @DbField("VISITPROVINCENAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"牵头单位编码"}, index = 23)
    // @Length(max = 10, message = "visitProvinceName长度不在有效范围内")
    private String visitProvinceName;

    @ApiModelProperty(value = "所属区域总部名称")
    @DbField("REGIONALHEADQUARTERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"牵头单位编码"}, index = 24)
    // @Length(max = 100, message = "regionalHeadquarterName长度不在有效范围内")
    private String regionalHeadquarterName;
}
