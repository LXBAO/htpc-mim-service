package com.uwdp.module.biz.infrastructure.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.ejlchina.searcher.bean.DbField;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_VISITPLAN")
@ApiModel(value = "VisitPlanDo entity对象", description = "公关关系计划")
public class VisitPlanDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一标识")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建者名称")
    @TableField("CREATED_BY_NAME")
    private String createdByName;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("关联客户信息唯一标识")
    @TableField("CLIENTINFOID")
    private Long clientInfoId;

    @ApiModelProperty("建议日期")
    @TableField("ADVICEDATE")
    private String adviceDate;

    @ApiModelProperty("出访省市")
    @TableField("VISITPROVINCE")
    private String visitProvince;

    @ApiModelProperty("出访市")
    @TableField("VISITCITY")
    private String visitCity;

    @ApiModelProperty("所属区域总部")
    @TableField("REGIONALHEADQUARTER")
    private String regionalHeadquarter;

    @ApiModelProperty("活动地点")
    @TableField("ACTIVITYADDRESS")
    private String activityAddress;

    @ApiModelProperty("出行领导")
    @TableField("TRAVELLEADER")
    private String travelLeader;

    @ApiModelProperty("出行领导姓名")
    @TableField("TRAVELLEADERNAME")
    private String travelLeaderName;

    @ApiModelProperty("确认参会的出行领导姓名")
    @TableField("CONFERENCELEADERNAME")
    private String conferenceLeaderName;

    @ApiModelProperty("牵头单位")
    @TableField("LEADUNIT")
    private String leadUnit;

    @ApiModelProperty("牵头单位联系人")
    @TableField("LEADUNITCONTACTPERSON")
    private String leadUnitContactPerson;

    @ApiModelProperty("牵头单位联系人")
    @TableField("LEADUNITCONTACT")
    private String leadUnitContact;

    @ApiModelProperty("活动方式")
    @TableField("ACTIVITYWAY")
    private String activityWay;

    @ApiModelProperty("事业部负责人")
    @TableField("BUSINESSHEADER")
    private String businessHeader;

    @ApiModelProperty("政府方联系人")
    @TableField("GOVERNCONTACTPEROSN")
    private String governContactPerosn;

    @ApiModelProperty("活动目的")
    @TableField("ACTIVITYPURPOSE")
    private String activityPurpose;

    @ApiModelProperty("备注")
    @TableField("MEMO")
    private String memo;

    @ApiModelProperty("主题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty("组织全编码（group_code，分隔符：/）（集团）")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty("是否参加会议")
    @TableField("IFCONFERENCE")
    private String ifConference;

    @ApiModelProperty("牵头单位编码")
    @TableField("LEADUNITCODE")
    private String leadUnitCode;

    @ApiModelProperty(value = "引用状态")
    @TableField("CITE_STATUS")
    private String citeStatus;

    @ApiModelProperty(value = "出访省市名称")
    @TableField("VISITPROVINCENAME")
    private String visitProvinceName;

    @ApiModelProperty(value = "所属区域总部名称")
    @TableField("REGIONALHEADQUARTERNAME")
    private String regionalHeadquarterName;
}
