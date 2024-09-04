package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 周报明细表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeeklyReportDetailDo Query对象", description = "周报明细表", discriminator = "weekly_Report_Detail")
public class WeeklyReportDetailQuery extends BasePageQuery {

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

    @ApiModelProperty(value = "日期")
    private String dateStr;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "工作计划")
    private String workPlan;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "周报主表id")
    private Long parentId;

    @ApiModelProperty(value = "状态1，0")
    private Integer status;
}
