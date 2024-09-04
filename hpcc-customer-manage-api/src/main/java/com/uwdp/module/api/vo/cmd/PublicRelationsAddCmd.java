package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import com.gientech.lcds.workflow.sdk.core.runtime.CandidateInfoDto;
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
@ApiModel(value = "PublicRelationsAddCmd对象", description = "公关实施", discriminator = "publicRelations")
public class PublicRelationsAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
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

    @ApiModelProperty(value = "关联计划", required = true)
    // @Range(max = Long.MAX_VALUE, message = "visitPlanId长度不在有效范围内")
    private Long visitPlanId;

    @ApiModelProperty(value = "责任单位", required = true)
    // @Length(max = 100, message = "dutyUnit长度不在有效范围内")
    private String dutyUnit;

    @ApiModelProperty(value = "公关时间")
    private LocalDateTime date;

    @ApiModelProperty(value = "活动方式")
    // @Length(max = 100, message = "activityWay长度不在有效范围内")
    private String activityWay;

    @ApiModelProperty(value = "活动省市")
    // @Length(max = 100, message = "activityProvinceAndCity长度不在有效范围内")
    private String activityProvinceAndCity;

    @ApiModelProperty(value = "活动省市名称")
    // @Length(max = 100, message = "activityProvinceAndCityName长度不在有效范围内")
    private String activityProvinceAndCityName;


    @ApiModelProperty(value = "活动地点")
    // @Length(max = 250, message = "activityAddress长度不在有效范围内")
    private String activityAddress;

    @ApiModelProperty(value = "主要参会人")
    // @Length(max = 250, message = "mainPerson长度不在有效范围内")
    private String mainPerson;

    @ApiModelProperty(value = "主要参会人id")
    // @Length(max = 500, message = "mainPersonIds长度不在有效范围内")
    private String mainPersonIds;

    @ApiModelProperty(value = "备注")
    // @Length(max = 500, message = "memo长度不在有效范围内")
    private String memo;

    @ApiModelProperty(value = "公关成果")
    // @Length(max = 500, message = "results长度不在有效范围内")
    private String results;

    @ApiModelProperty(value = "后续事项跟踪人")
    // @Length(max = 200, message = "followers长度不在有效范围内")
    private String followers;

    @ApiModelProperty(value = "文件路径")
    // @Length(max = 256, message = "file长度不在有效范围内")
    private String file;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    // @Length(max = 500, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "工作流指定审批人")
    protected Map<String, List<CandidateInfoDto>> approveUser;
    //附件表
    List<AttachmentAddCmd> addCmdList;
}
