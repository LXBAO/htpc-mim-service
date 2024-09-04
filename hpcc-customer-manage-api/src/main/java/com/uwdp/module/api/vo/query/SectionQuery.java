package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 标段
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SectionDo Query对象", description = "标段", discriminator = "section")
public class SectionQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "标段名称")
    private String sectionName;

    @ApiModelProperty(value = "拦标价/预计金额（万元）")
    private String estimatedAmount;

    @ApiModelProperty(value = "标段内容")
    private String sectionContent;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "预计中标概况")
    private String bidProbability;
}
