package com.uwdp.module.api.vo.cmd;

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
 * 定时任务
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "TimedTaskAddCmd对象", description = "定时任务", discriminator = "timedTask")
public class TimedTaskAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "任务名称")
    // @Length(max = 255, message = "taskName长度不在有效范围内")
    private String taskName;

    @ApiModelProperty(value = "运行频率")
    // @Length(max = 50, message = "runFrequency长度不在有效范围内")
    private String runFrequency;

    @ApiModelProperty(value = "运行时间")
    // @Length(max = 255, message = "runTime长度不在有效范围内")
    private String runTime;

    @ApiModelProperty(value = "url")
    // @Length(max = 255, message = "url长度不在有效范围内")
    private String url;

    @ApiModelProperty(value = "描述")
    // @Length(max = 500, message = "description长度不在有效范围内")
    private String description;

    @ApiModelProperty(value = "状态")
    // @Length(max = 500, message = "state长度不在有效范围内")
    private String state;
}
