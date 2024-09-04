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
 * 增加标题的公关实施
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "PublicRelationsHeadlineDto对象", description = "增加标题的公关实施", discriminator = "PublicRelationsHeadlineDto")
@SearchBean(
        tables = "t_publicrelations p left join t_visitplan v on v.id = p.visitplanId" +
                " left join T_LMCWORKFLOW l on p.ID=l.BIZID"
)

public class PublicRelationsHeadlineDto implements Serializable {

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

    @ApiModelProperty(value = "创建者姓名")
    @DbField("p.CREATED_BY_NAME")
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

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

    @ApiModelProperty(value = "关联计划", required = true)
    @DbField("p.VISITPLANID")
    // @Range(max = Long.MAX_VALUE, message = "visitPlanId长度不在有效范围内")
    private Long visitPlanId;

    @ApiModelProperty(value = "责任单位", required = true)
    @DbField("p.DUTYUNIT")
    // @Length(max = 100, message = "dutyUnit长度不在有效范围内")
    private String dutyUnit;

    @ApiModelProperty(value = "责任单位", required = true)
    @DbField("v.TITLE")
    // @Length(max = 500, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "公关时间")
    @DbField("p.DATE")
    private LocalDateTime date;

    @ApiModelProperty(value = "活动方式")
    @DbField("p.ACTIVITYWAY")
    // @Length(max = 100, message = "activityWay长度不在有效范围内")
    private String activityWay;

    @ApiModelProperty(value = "活动省市")
    @DbField("p.ACTIVITYPROVINCEANDCITY")
    // @Length(max = 100, message = "activityProvinceAndCity长度不在有效范围内")
    private String activityProvinceAndCity;

    @ApiModelProperty(value = "活动省市名称")
    @DbField("p.ACTIVITYPROVINCEANDCITYNAME")
    // @Length(max = 100, message = "activityProvinceAndCityName长度不在有效范围内")
    private String activityProvinceAndCityName;

    @ApiModelProperty(value = "活动地点")
    @DbField("p.ACTIVITYADDRESS")
    // @Length(max = 250, message = "activityAddress长度不在有效范围内")
    private String activityAddress;

    @ApiModelProperty(value = "主要参会人")
    @DbField("p.MAINPERSON")
    // @Length(max = 250, message = "mainPerson长度不在有效范围内")
    private String mainPerson;

    @ApiModelProperty(value = "主要参会人id")
    @DbField("p.MAINPERSONIDS")
    // @Length(max = 500, message = "mainPersonIds长度不在有效范围内")
    private String mainPersonIds;

    @ApiModelProperty(value = "备注")
    @DbField("p.MEMO")
    // @Length(max = 500, message = "memo长度不在有效范围内")
    private String memo;

    @ApiModelProperty(value = "公关成果")
    @DbField("p.RESULTS")
    // @Length(max = 500, message = "results长度不在有效范围内")
    private String results;

    @ApiModelProperty(value = "后续事项跟踪人")
    @DbField("p.FOLLOWERS")
    // @Length(max = 200, message = "followers长度不在有效范围内")
    private String followers;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("p.GROUPFULLCODE")
    private String groupFullCode;
}
