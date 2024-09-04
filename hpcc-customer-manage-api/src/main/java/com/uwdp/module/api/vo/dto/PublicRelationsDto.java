package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uwdp.module.api.vo.cmd.AttachmentAddCmd;
import com.uwdp.module.api.vo.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 公关实施
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "PublicRelationsDTO对象", description = "公关实施", discriminator = "publicRelations")
@SearchBean(tables = "T_PUBLICRELATIONS")
public class PublicRelationsDto implements Serializable {

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

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("GROUPFULLCODE")
    private String groupFullCode;

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

    @ApiModelProperty(value = "关联计划", required = true)
    @DbField("VISITPLANID")
    // @Range(max = Long.MAX_VALUE, message = "visitPlanId长度不在有效范围内")
    private Long visitPlanId;

    @ApiModelProperty(value = "责任单位", required = true)
    @DbField("DUTYUNIT")
    // @Length(max = 100, message = "dutyUnit长度不在有效范围内")
    private String dutyUnit;

    @ApiModelProperty(value = "公关时间")
    @DbField("DATE")
    private LocalDateTime date;

    @ApiModelProperty(value = "活动方式")
    @DbField("ACTIVITYWAY")
    // @Length(max = 100, message = "activityWay长度不在有效范围内")
    private String activityWay;

    @ApiModelProperty(value = "活动省市")
    @DbField("ACTIVITYPROVINCEANDCITY")
    // @Length(max = 100, message = "activityProvinceAndCity长度不在有效范围内")
    private String activityProvinceAndCity;

    @ApiModelProperty(value = "活动省市名称")
    @DbField("ACTIVITYPROVINCEANDCITYNAME")
    // @Length(max = 100, message = "activityProvinceAndCityName长度不在有效范围内")
    private String activityProvinceAndCityName;

    @ApiModelProperty(value = "活动地点")
    @DbField("ACTIVITYADDRESS")
    // @Length(max = 250, message = "activityAddress长度不在有效范围内")
    private String activityAddress;

    @ApiModelProperty(value = "主要参会人")
    @DbField("MAINPERSON")
    // @Length(max = 250, message = "mainPerson长度不在有效范围内")
    private String mainPerson;

    @ApiModelProperty(value = "主要参会人id")
    @DbField("MAINPERSONIDS")
    // @Length(max = 250, message = "mainPersonIds长度不在有效范围内")
    private String mainPersonIds;

    @ApiModelProperty(value = "备注")
    @DbField("MEMO")
    // @Length(max = 500, message = "memo长度不在有效范围内")
    private String memo;

    @ApiModelProperty(value = "公关成果")
    @DbField("RESULTS")
    // @Length(max = 500, message = "results长度不在有效范围内")
    private String results;

    @ApiModelProperty(value = "后续事项跟踪人")
    @DbField("FOLLOWERS")
    // @Length(max = 200, message = "followers长度不在有效范围内")
    private String followers;

    @ApiModelProperty(value = "文件路径")
    @DbField("FILE")
    // @Length(max = 256, message = "file长度不在有效范围内")
    private String file;

    //附件表
    @DbIgnore
    List<AttachmentDto> attachmentDtos;

}
