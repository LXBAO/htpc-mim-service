package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 公关实施
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PublicRelationsDo Query对象", description = "公关实施", discriminator = "publicRelations")
public class PublicRelationsQuery extends BasePageQuery {

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

    @ApiModelProperty(value = "关联计划", required = true)
    private Long visitPlanId;

    @ApiModelProperty(value = "责任单位", required = true)
    private String dutyUnit;

    @ApiModelProperty(value = "公关时间")
    private LocalDateTime date;

    @ApiModelProperty(value = "活动方式")
    private String activityWay;

    @ApiModelProperty(value = "活动省市")
    private String activityProvinceAndCity;

    @ApiModelProperty(value = "活动地点")
    private String activityAddress;

    @ApiModelProperty(value = "主要参会人")
    private String mainPerson;

    @ApiModelProperty(value = "主要参会人id")
    private String mainPersonIds;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "公关成果")
    private String results;

    @ApiModelProperty(value = "后续事项跟踪人")
    private String followers;

    @ApiModelProperty(value = "文件路径")
    private String file;

    @ApiModelProperty(value = "活动省市名称")
    private String activityProvinceAndCityName;
}
