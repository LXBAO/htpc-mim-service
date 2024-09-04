package com.uwdp.module.api.vo.dto;

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
 * 定时任务
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "TimedTaskDTO对象", description = "定时任务", discriminator = "timedTask")
@SearchBean(tables = "T_TIMEDTASK")
public class TimedTaskDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

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

    @ApiModelProperty(value = "任务名称")
    @DbField("TASKNAME")
    // @Length(max = 255, message = "taskName长度不在有效范围内")
    private String taskName;

    @ApiModelProperty(value = "运行频率")
    @DbField("RUNFREQUENCY")
    // @Length(max = 50, message = "runFrequency长度不在有效范围内")
    private String runFrequency;

    @ApiModelProperty(value = "运行时间")
    @DbField("RUNTIME")
    // @Length(max = 255, message = "runTime长度不在有效范围内")
    private String runTime;

    @ApiModelProperty(value = "url")
    @DbField("URL")
    // @Length(max = 255, message = "url长度不在有效范围内")
    private String url;

    @ApiModelProperty(value = "描述")
    @DbField("description")
    // @Length(max = 500, message = "description长度不在有效范围内")
    private String description;

    @ApiModelProperty(value = "状态")
    @DbField("state")
    // @Length(max = 500, message = "state长度不在有效范围内")
    private String state;
}
