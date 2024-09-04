package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
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
@ApiModel(value = "VisitPlan UpdateCmd对象", description = "公关关系计划", discriminator = "visitPlan")
public class VisitPlanUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识", required = true)
    @NotNull(message = "主键不能为空")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者姓名")
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "关联客户信息唯一标识")
    // @Range(max = Long.MAX_VALUE, message = "clientInfoId长度不在有效范围内")
    private Long clientInfoId;

    @ApiModelProperty(value = "建议日期")
    private String adviceDate;

    @ApiModelProperty(value = "出访省市")
    // @Length(max = 10, message = "visitProvince长度不在有效范围内")
    private String visitProvince;

    @ApiModelProperty(value = "出访省市名称")
    // @Length(max = 10, message = "visitProvinceName长度不在有效范围内")
    private String visitProvinceName;

    @ApiModelProperty(value = "所属区域总部名称")
    // @Length(max = 100, message = "regionalHeadquarterName长度不在有效范围内")
    private String regionalHeadquarterName;

    @ApiModelProperty(value = "出访市")
    // @Length(max = 10, message = "visitCity长度不在有效范围内")
    private String visitCity;

    @ApiModelProperty(value = "所属区域总部")
    // @Length(max = 100, message = "regionalHeadquarter长度不在有效范围内")
    private String regionalHeadquarter;

    @ApiModelProperty(value = "活动地点")
    // @Length(max = 500, message = "activityAddress长度不在有效范围内")
    private String activityAddress;

    @ApiModelProperty(value = "出行领导")
    // @Length(max = 500, message = "travelLeader长度不在有效范围内")
    private String travelLeader;

    @ApiModelProperty(value = "出行领导名称")
    // @Length(max = 500, message = "travelLeaderName长度不在有效范围内")
    private String travelLeaderName;

    @ApiModelProperty(value = "确认参会的出行领导名称")
    // @Length(max = 500, message = "conferenceLeaderName长度不在有效范围内")
    private String conferenceLeaderName;

    @ApiModelProperty(value = "当前即将确认参会的出行领导名称")
    // @Length(max = 500, message = "currentConferenceLeaderName长度不在有效范围内")
    private String currentConferenceLeaderName;

    @ApiModelProperty(value = "牵头单位")
    // @Length(max = 500, message = "leadUnit长度不在有效范围内")
    private String leadUnit;

    @ApiModelProperty(value = "牵头单位联系人")
    // @Length(max = 50, message = "leadUnitContactPerson长度不在有效范围内")
    private String leadUnitContactPerson;

    @ApiModelProperty(value = "牵头单位联系人")
    // @Length(max = 100, message = "leadUnitContact长度不在有效范围内")
    private String leadUnitContact;

    @ApiModelProperty(value = "活动方式")
    // @Length(max = 50, message = "activityWay长度不在有效范围内")
    private String activityWay;

    @ApiModelProperty(value = "事业部负责人")
    // @Length(max = 50, message = "businessHeader长度不在有效范围内")
    private String businessHeader;

    @ApiModelProperty(value = "政府方联系人")
    // @Length(max = 50, message = "governContactPerosn长度不在有效范围内")
    private String governContactPerosn;

    @ApiModelProperty(value = "活动目的")
    // @Length(max = 500, message = "activityPurpose长度不在有效范围内")
    private String activityPurpose;

    @ApiModelProperty(value = "备注")
    // @Length(max = 500, message = "memo长度不在有效范围内")
    private String memo;

    @ApiModelProperty(value = "主题")
    // @Length(max = 500, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "是否参加会议")
    // @Length(max = 50, message = "ifConference长度不在有效范围内")
    private String ifConference;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    // @Length(max = 500, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "牵头单位编码")
    // @Length(max = 500, message = "leadUnitCode长度不在有效范围内")
    private String leadUnitCode;

    @ApiModelProperty(value = "引用状态")
    // @Length(max = 36, message = "citeStatus长度不在有效范围内")
    private String citeStatus;
}
