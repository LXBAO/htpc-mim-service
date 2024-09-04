package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uwdp.module.api.vo.enums.*;
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
@ApiModel(value = "VisitPlanDTO对象", description = "公关关系计划", discriminator = "visitPlan")
@SearchBean(tables = "T_VISITPLAN")
public class VisitPlanDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATED_BY_NAME")
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "关联客户信息唯一标识")
    @DbField("CLIENTINFOID")
    // @Range(max = Long.MAX_VALUE, message = "clientInfoId长度不在有效范围内")
    private Long clientInfoId;

    @ApiModelProperty(value = "建议日期")
    @DbField("ADVICEDATE")
    private String adviceDate;

    @ApiModelProperty(value = "出访省市")
    @DbField("VISITPROVINCE")
    // @Length(max = 10, message = "visitProvince长度不在有效范围内")
    private String visitProvince;

    @ApiModelProperty(value = "出访省市名称")
    @DbField("VISITPROVINCENAME")
    // @Length(max = 10, message = "visitProvinceName长度不在有效范围内")
    private String visitProvinceName;

    @ApiModelProperty(value = "所属区域总部名称")
    @DbField("REGIONALHEADQUARTERNAME")
    // @Length(max = 100, message = "regionalHeadquarterName长度不在有效范围内")
    private String regionalHeadquarterName;

    @ApiModelProperty(value = "出访市")
    @DbField("VISITCITY")
    // @Length(max = 10, message = "visitCity长度不在有效范围内")
    private String visitCity;

    @ApiModelProperty(value = "所属区域总部")
    @DbField("REGIONALHEADQUARTER")
    // @Length(max = 100, message = "regionalHeadquarter长度不在有效范围内")
    private String regionalHeadquarter;

    @ApiModelProperty(value = "活动地点")
    @DbField("ACTIVITYADDRESS")
    // @Length(max = 500, message = "activityAddress长度不在有效范围内")
    private String activityAddress;

    @ApiModelProperty(value = "出行领导")
    @DbField("TRAVELLEADER")
    // @Length(max = 500, message = "travelLeader长度不在有效范围内")
    private String travelLeader;

    @ApiModelProperty(value = "出行领导名称")
    @DbField("TRAVELLEADERNAME")
    // @Length(max = 500, message = "travelLeaderName长度不在有效范围内")
    private String travelLeaderName;

    @ApiModelProperty(value = "确认参会的出行领导名称")
    @DbField("CONFERENCELEADERNAME")
    // @Length(max = 500, message = "conferenceLeaderName长度不在有效范围内")
    private String conferenceLeaderName;

    @ApiModelProperty(value = "牵头单位")
    @DbField("LEADUNIT")
    // @Length(max = 500, message = "leadUnit长度不在有效范围内")
    private String leadUnit;

    @ApiModelProperty(value = "牵头单位联系人")
    @DbField("LEADUNITCONTACTPERSON")
    // @Length(max = 50, message = "leadUnitContactPerson长度不在有效范围内")
    private String leadUnitContactPerson;

    @ApiModelProperty(value = "牵头单位联系人")
    @DbField("LEADUNITCONTACT")
    // @Length(max = 100, message = "leadUnitContact长度不在有效范围内")
    private String leadUnitContact;

    @ApiModelProperty(value = "活动方式")
    @DbField("ACTIVITYWAY")
    // @Length(max = 50, message = "activityWay长度不在有效范围内")
    private String activityWay;

    @ApiModelProperty(value = "事业部负责人")
    @DbField("BUSINESSHEADER")
    // @Length(max = 50, message = "businessHeader长度不在有效范围内")
    private String businessHeader;

    @ApiModelProperty(value = "政府方联系人")
    @DbField("GOVERNCONTACTPEROSN")
    // @Length(max = 50, message = "governContactPerosn长度不在有效范围内")
    private String governContactPerosn;

    @ApiModelProperty(value = "活动目的")
    @DbField("ACTIVITYPURPOSE")
    // @Length(max = 500, message = "activityPurpose长度不在有效范围内")
    private String activityPurpose;

    @ApiModelProperty(value = "备注")
    @DbField("MEMO")
    // @Length(max = 500, message = "memo长度不在有效范围内")
    private String memo;

    @ApiModelProperty(value = "主题")
    @DbField("TITLE")
    // @Length(max = 500, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "是否参加会议")
    @DbField("IFCONFERENCE")
    // @Length(max = 50, message = "ifConference长度不在有效范围内")
    private String ifConference;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("GROUPFULLCODE")
    // @Length(max = 500, message = "组织全编码长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "牵头单位编码")
    @DbField("LEADUNITCODE")
    // @Length(max = 500, message = "牵头单位编码长度不在有效范围内")
    private String leadUnitCode;

    @ApiModelProperty(value = "引用状态")
    @DbField("CITE_STATUS")
    // @Length(max = 36, message = "citeStatus长度不在有效范围内")
    private String citeStatus;
}
