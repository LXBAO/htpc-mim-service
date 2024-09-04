package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 公关关系计划
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "VisitPlanDo Query对象", description = "公关关系计划", discriminator = "visitPlan")
public class VisitPlanQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
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

    @ApiModelProperty(value = "关联客户信息唯一标识")
    private Long clientInfoId;

    @ApiModelProperty(value = "建议日期")
    private String adviceDate;

    @ApiModelProperty(value = "出访省市")
    private String visitProvince;

    @ApiModelProperty(value = "出访市")
    private String visitCity;

    @ApiModelProperty(value = "所属区域总部")
    private String regionalHeadquarter;

    @ApiModelProperty(value = "活动地点")
    private String activityAddress;

    @ApiModelProperty(value = "出行领导")
    private String travelLeader;

    @ApiModelProperty(value = "出行领导姓名")
    private String travelLeaderName;

    @ApiModelProperty(value = "牵头单位")
    private String leadUnit;

    @ApiModelProperty(value = "牵头单位联系人")
    private String leadUnitContactPerson;

    @ApiModelProperty(value = "牵头单位联系人")
    private String leadUnitContact;

    @ApiModelProperty(value = "活动方式")
    private String activityWay;

    @ApiModelProperty(value = "牵头单位编码")
    private String leadUnitCode;

    @ApiModelProperty(value = "事业部负责人")
    private String businessHeader;

    @ApiModelProperty(value = "政府方联系人")
    private String governContactPerosn;

    @ApiModelProperty(value = "活动目的")
    private String activityPurpose;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "主题")
    private String title;

    @ApiModelProperty(value = "是否参加会议")
    private String ifConference;

    @ApiModelProperty(value = "引用状态")
    private String citeStatus;

    @ApiModelProperty(value = "出访省市名称")
    private String visitProvinceName;

    @ApiModelProperty(value = "所属区域总部名称")
    private String regionalHeadquarterName;
}
