package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 项目赋能明细
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProjectEnableDetailDo Query对象", description = "项目赋能明细", discriminator = "projectEnableDetail")
public class ProjectEnableDetailQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "主文档id")
    private Long parentId;

    @ApiModelProperty(value = "跟踪日期")
    private LocalDateTime trackDate;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "给予板块公司市场赋能说明")
    private String enableExplain;

    @ApiModelProperty(value = "创建者名称")
    private String createdByName;
}
