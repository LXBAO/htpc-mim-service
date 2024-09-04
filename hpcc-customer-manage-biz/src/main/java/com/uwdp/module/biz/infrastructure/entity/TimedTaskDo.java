package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_TIMEDTASK")
@ApiModel(value = "TimedTaskDo entity对象", description = "定时任务")
public class TimedTaskDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("任务名称")
    @TableField("TASKNAME")
    private String taskName;

    @ApiModelProperty("运行频率")
    @TableField("RUNFREQUENCY")
    private String runFrequency;

    @ApiModelProperty("运行时间")
    @TableField("RUNTIME")
    private String runTime;

    @ApiModelProperty("url")
    @TableField("URL")
    private String url;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("状态")
    @TableField("state")
    private String state;
}
