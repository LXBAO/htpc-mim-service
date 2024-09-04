package com.uwdp.module.api.vo.query;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 市场部分公司年度指标
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MarketDmpTagDo Query对象", description = "市场部分公司年度指标", discriminator = "market_dmp_tag")
public class MarketDmpTagQuery extends BasePageQuery {

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

    @ApiModelProperty(value = "填报人id", required = true)
    private Long userId;

    @ApiModelProperty(value = "填报人名字")
    private String userName;

    @ApiModelProperty(value = "部门id")
    private Long dmptId;

    @ApiModelProperty(value = "部门名称")
    private String dmptName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "创建人名称")
    private String createdName;

    @ApiModelProperty(value = "力争指标金额总计")
    private Double toAmtTotal;

    @ApiModelProperty(value = "确保指标金额总计")
    private Double formAmtTotal;
}
