package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import com.uwdp.module.api.vo.enums.DictVisitPlanActivityWayEnums;
import com.uwdp.module.api.vo.enums.WorkflowStatusEnums;
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
@ApiModel(value = "VisitPlanExcelExport对象", description = "公关关系计划", discriminator = "visitPlan")
@SearchBean(tables = "T_VISITPLAN p left join T_LMCWORKFLOW l on p.ID =  l.BIZID")
@EqualsAndHashCode(callSuper = false)
public class VisitPlanExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "更新者")
//    @DbField("p.UPDATED_BY")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新者"}, index = 3)
//    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
//    private String updatedBy;
//
//    @ApiModelProperty(value = "更新时间")
//    @DbField("p.UPDATED_TIME")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新时间"}, index = 4)
//    private String updatedTime;

    @ApiModelProperty(value = "关联客户信息唯一标识")
    @DbField("p.CLIENTINFOID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联客户信息唯一标识"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "clientInfoId长度不在有效范围内")
    private String clientInfoId;

    @ApiModelProperty(value = "建议日期")
    @DbField("p.ADVICEDATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建议日期"}, index = 1)
    private String adviceDate;

    @ApiModelProperty(value = "出访省市")
    @DbField("p.VISITPROVINCE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"出访省市"}, index = 2)
    // @Length(max = 10, message = "visitProvince长度不在有效范围内")
    private String visitProvince;

    @ApiModelProperty(value = "出访省市名称")
    @DbField("VISITPROVINCENAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"出访省市名称"}, index = 3)
    // @Length(max = 10, message = "visitProvinceName长度不在有效范围内")
    private String visitProvinceName;

//    @ApiModelProperty(value = "出访市")
//    @DbField("p.VISITCITY")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"出访市"}, index = 8)
//    // @Length(max = 10, message = "visitCity长度不在有效范围内")
//    private String visitCity;

    @ApiModelProperty(value = "所属区域总部")
    @DbField("p.REGIONALHEADQUARTER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属区域总部"}, index = 4)
    // @Length(max = 100, message = "regionalHeadquarter长度不在有效范围内")
    private String regionalHeadquarter;

    @ApiModelProperty(value = "所属区域总部名称")
    @DbField("REGIONALHEADQUARTERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属区域总部"}, index = 5)
    // @Length(max = 100, message = "regionalHeadquarterName长度不在有效范围内")
    private String regionalHeadquarterName;

    @ApiModelProperty(value = "活动地点")
    @DbField("p.ACTIVITYADDRESS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动地点"}, index = 6)
    // @Length(max = 500, message = "activityAddress长度不在有效范围内")
    private String activityAddress;

//    @ApiModelProperty(value = "出行领导")
//    @DbField("p.TRAVELLEADER")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"出行领导"}, index = 11)
//    // @Length(max = 500, message = "travelLeader长度不在有效范围内")
//    private String travelLeader;

    @ApiModelProperty(value = "出行领导姓名")
    @DbField("p.TRAVELLEADERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"出行领导姓名"}, index = 7)
    // @Length(max = 500, message = "travelLeaderName长度不在有效范围内")
    private String travelLeaderName;

    @ApiModelProperty(value = "牵头单位")
    @DbField("p.LEADUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"牵头单位"}, index = 8)
    // @Length(max = 500, message = "leadUnit长度不在有效范围内")
    private String leadUnit;

    @ApiModelProperty(value = "牵头单位联系人")
    @DbField("p.LEADUNITCONTACTPERSON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"牵头单位联系人"}, index = 9)
    // @Length(max = 50, message = "leadUnitContactPerson长度不在有效范围内")
    private String leadUnitContactPerson;

//    @ApiModelProperty(value = "牵头单位联系人")
//    @DbField("p.LEADUNITCONTACT")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"牵头单位联系人"}, index = 14)
//    // @Length(max = 100, message = "leadUnitContact长度不在有效范围内")
//    private String leadUnitContact;

    @ApiModelProperty(value = "活动方式")
    @DbField("p.ACTIVITYWAY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动方式"}, index = 10)
    // @Length(max = 50, message = "activityWay长度不在有效范围内")
    private String activityWay;
    public String getActivityWay(){
        return DictVisitPlanActivityWayEnums.getName(activityWay);
    }

    @ApiModelProperty(value = "事业部负责人")
    @DbField("p.BUSINESSHEADER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"事业部负责人"}, index = 11)
    // @Length(max = 50, message = "businessHeader长度不在有效范围内")
    private String businessHeader;

    @ApiModelProperty(value = "政府方联系人")
    @DbField("p.GOVERNCONTACTPEROSN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"政府方联系人"}, index = 12)
    // @Length(max = 50, message = "governContactPerosn长度不在有效范围内")
    private String governContactPerosn;

    @ApiModelProperty(value = "活动目的")
    @DbField("p.ACTIVITYPURPOSE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动目的"}, index = 13)
    // @Length(max = 500, message = "activityPurpose长度不在有效范围内")
    private String activityPurpose;

//    @ApiModelProperty(value = "备注")
//    @DbField("p.MEMO")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"备注"}, index = 19)
//    // @Length(max = 500, message = "memo长度不在有效范围内")
//    private String memo;

//    @ApiModelProperty(value = "主题")
//    @DbField("p.TITLE")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"主题"}, index = 20)
//    // @Length(max = 500, message = "title长度不在有效范围内")
//    private String title;

//    @ApiModelProperty(value = "是否参加会议")
//    @DbField("p.IFCONFERENCE")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"是否参加会议"}, index = 21)
//    // @Length(max = 50, message = "ifConference长度不在有效范围内")
//    private String ifConference;

//    @ApiModelProperty(value = "牵头单位编码")
//    @DbField("p.LEADUNITCODE")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"牵头单位编码"}, index = 22)
//    // @Length(max = 500, message = "leadUnitCode长度不在有效范围内")
//    private String leadUnitCode;

    @ApiModelProperty(value = "创建者名称")
    @DbField("p.CREATED_BY_NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 14)
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    @DbField("p.CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 15)
    private String createdTime;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("p.GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织全编码"}, index = 16)
    // @Length(max = 64, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

    @ColumnWidth(16)
    @ExcelProperty(value = {"审批状态"}, index = 17)
    // @Length(max = 15, message = "createdName长度不在有效范围内")
    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;
    public String getWorkflowStatus(){
        return WorkflowStatusEnums.getName(workflowStatus);
    }

    @ExcelIgnore
    @DbField("p.CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 18)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;
}
