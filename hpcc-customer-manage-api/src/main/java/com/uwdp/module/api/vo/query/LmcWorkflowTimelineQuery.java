package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 流程时间线
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LmcWorkflowTimelineDo Query对象", description = "流程时间线", discriminator = "LmcWorkflowTimeline")
public class LmcWorkflowTimelineQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "流程id")
    private String workflowId;

    @ApiModelProperty(value = "流程状态(数据字典)")
    private String workflowStatus;

    @ApiModelProperty(value = "回调/触发时间")
    private LocalDateTime triggerTime;

    @ApiModelProperty(value = "流程编码")
    private String processCode;

    @ApiModelProperty(value = "流程名称")
    private String processName;

    @ApiModelProperty(value = "流程实例id")
    private String processInstanceId;

    @ApiModelProperty(value = "流程回调id")
    private String processCallEventId;

    @ApiModelProperty(value = "AppId")
    private String appId;

    @ApiModelProperty(value = "操作人编码")
    private String operatorCode;

    @ApiModelProperty(value = "操作人名称")
    private String operatorName;

    @ApiModelProperty(value = "事件在哪个任务发生的任务id")
    private String currentTaskId;

    @ApiModelProperty(value = "事件在哪个任务发生的任务编码")
    private String currentTaskKey;

    @ApiModelProperty(value = "事件在哪个任务发生的任务名称")
    private String currentTaskName;

    @ApiModelProperty(value = "业务类型")
    private String bizCode;

    @ApiModelProperty(value = "业务唯一标识")
    private String bizId;

    @ApiModelProperty(value = "操作原因")
    private String reason;

    @ApiModelProperty(value = "是否成功(0:否;1:是)")
    private Integer succeed;

    @ApiModelProperty(value = "错误信息")
    private String errorMsg;

    @ApiModelProperty(value = "错误详细信息")
    private String errorText;

    @ApiModelProperty(value = "数据版本号, 默认为1 ,变更一次,加1")
    private Long version;

    @ApiModelProperty(value = "是否删除(0否1是)")
    private Integer deleted;

    @ApiModelProperty(value = "创建人编码")
    private String createUserCode;

    @ApiModelProperty(value = "创建人名称")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人编码")
    private String updateUserCode;

    @ApiModelProperty(value = "更新人名称")
    private String updateUserName;

    @ApiModelProperty(value = "更新时间", required = true)
    private LocalDateTime updateTime;
}
