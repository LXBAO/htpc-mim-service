package com.uwdp.module.api.vo.dto.searcher;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;



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
@ApiModel(value = "VisitPlanCliNameDto对象", description = "公关关系计划", discriminator = "visitPlan")
@SearchBean(
        tables = "(t_visitplan v left join t_clientinfo c on c.fdid = v.CLIENTINFOID) left join T_LMCWORKFLOW l on v.ID =  l.BIZID"
) 
public class VisitPlanCliNameDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("流程实例id")
    @DbField("l.PROCESSINSTANCEID")
    private String processInstanceId;

    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;

    @ApiModelProperty(value = "唯一标识")
    @DbField("v.ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("v.CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    @DbField("v.CREATED_BY_NAME")
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    @DbField("v.CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("v.UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("v.UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "关联客户信息唯一标识")
    @DbField("v.CLIENTINFOID")
    // @Range(max = Long.MAX_VALUE, message = "clientInfoId长度不在有效范围内")
    private Long clientInfoId;

    @ApiModelProperty(value = "姓名", required = true)
    @DbField("c.FDNAME")
    // @Length(max = 36, message = "fdName长度不在有效范围内")
    private String fdName;

    @ApiModelProperty(value = "建议日期")
    @DbField("v.ADVICEDATE")
    private String adviceDate;

    @ApiModelProperty(value = "出访省市")
    @DbField("v.VISITPROVINCE")
    // @Length(max = 10, message = "visitProvince长度不在有效范围内")
    private String visitProvince;

    @ApiModelProperty(value = "出访省市名称")
    @DbField("v.VISITPROVINCENAME")
    // @Length(max = 10, message = "visitProvinceName长度不在有效范围内")
    private String visitProvinceName;

    @ApiModelProperty(value = "所属区域总部名称")
    @DbField("v.REGIONALHEADQUARTERNAME")
    // @Length(max = 100, message = "regionalHeadquarterName长度不在有效范围内")
    private String regionalHeadquarterName;

    @ApiModelProperty(value = "出访市")
    @DbField("v.VISITCITY")
    // @Length(max = 10, message = "visitCity长度不在有效范围内")
    private String visitCity;

    @ApiModelProperty(value = "所属区域总部")
    @DbField("v.REGIONALHEADQUARTER")
    // @Length(max = 100, message = "regionalHeadquarter长度不在有效范围内")
    private String regionalHeadquarter;

    @ApiModelProperty(value = "活动地点")
    @DbField("v.ACTIVITYADDRESS")
    // @Length(max = 500, message = "activityAddress长度不在有效范围内")
    private String activityAddress;

    @ApiModelProperty(value = "出行领导")
    @DbField("v.TRAVELLEADER")
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
    @DbField("v.LEADUNIT")
    // @Length(max = 500, message = "leadUnit长度不在有效范围内")
    private String leadUnit;

    @ApiModelProperty(value = "牵头单位联系人")
    @DbField("v.LEADUNITCONTACTPERSON")
    // @Length(max = 50, message = "leadUnitContactPerson长度不在有效范围内")
    private String leadUnitContactPerson;

    @ApiModelProperty(value = "牵头单位联系人")
    @DbField("v.LEADUNITCONTACT")
    // @Length(max = 100, message = "leadUnitContact长度不在有效范围内")
    private String leadUnitContact;

    @ApiModelProperty(value = "活动方式")
    @DbField("v.ACTIVITYWAY")
    // @Length(max = 50, message = "activityWay长度不在有效范围内")
    private String activityWay;

    @ApiModelProperty(value = "事业部负责人")
    @DbField("v.BUSINESSHEADER")
    // @Length(max = 50, message = "businessHeader长度不在有效范围内")
    private String businessHeader;

    @ApiModelProperty(value = "政府方联系人")
    @DbField("v.GOVERNCONTACTPEROSN")
    // @Length(max = 50, message = "governContactPerosn长度不在有效范围内")
    private String governContactPerosn;

    @ApiModelProperty(value = "活动目的")
    @DbField("v.ACTIVITYPURPOSE")
    // @Length(max = 500, message = "activityPurpose长度不在有效范围内")
    private String activityPurpose;

    @ApiModelProperty(value = "备注")
    @DbField("v.MEMO")
    // @Length(max = 500, message = "memo长度不在有效范围内")
    private String memo;

    @ApiModelProperty(value = "主题")
    @DbField("v.TITLE")
    // @Length(max = 500, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "是否参加会议")
    @DbField("IFCONFERENCE")
    // @Length(max = 50, message = "ifConference长度不在有效范围内")
    private String ifConference;

    @ApiModelProperty(value = "牵头单位编码")
    @DbField("v.LEADUNITCODE")
    // @Length(max = 500, message = "leadUnitCode长度不在有效范围内")
    private String leadUnitCode;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("v.GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty(value = "引用状态")
    @DbField("v.CITE_STATUS")
    // @Length(max = 36, message = "citeStatus长度不在有效范围内")
    private String citeStatus;
}
