package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 流程时间线
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LmcWorkflowTimeline UpdateCmd对象", description = "流程时间线", discriminator = "LmcWorkflowTimeline")
public class LmcWorkflowTimelineUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID", required = true)
    @NotNull(message = "主键不能为空")
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

    @ApiModelProperty(value = "流程id")
    // @Length(max = 32, message = "workflowId长度不在有效范围内")
    private String workflowId;

    @ApiModelProperty(value = "流程状态(数据字典)")
    // @Length(max = 20, message = "workflowStatus长度不在有效范围内")
    private String workflowStatus;

    @ApiModelProperty(value = "回调/触发时间")
    private LocalDateTime triggerTime;

    @ApiModelProperty(value = "流程编码")
    // @Length(max = 50, message = "processCode长度不在有效范围内")
    private String processCode;

    @ApiModelProperty(value = "流程名称")
    // @Length(max = 255, message = "processName长度不在有效范围内")
    private String processName;

    @ApiModelProperty(value = "流程实例id")
    // @Length(max = 36, message = "processInstanceId长度不在有效范围内")
    private String processInstanceId;

    @ApiModelProperty(value = "流程回调id")
    // @Length(max = 36, message = "processCallEventId长度不在有效范围内")
    private String processCallEventId;

    @ApiModelProperty(value = "AppId")
    // @Length(max = 50, message = "appId长度不在有效范围内")
    private String appId;

    @ApiModelProperty(value = "操作人编码")
    // @Length(max = 50, message = "operatorCode长度不在有效范围内")
    private String operatorCode;

    @ApiModelProperty(value = "操作人名称")
    // @Length(max = 100, message = "operatorName长度不在有效范围内")
    private String operatorName;

    @ApiModelProperty(value = "事件在哪个任务发生的任务id")
    // @Length(max = 36, message = "currentTaskId长度不在有效范围内")
    private String currentTaskId;

    @ApiModelProperty(value = "事件在哪个任务发生的任务编码")
    // @Length(max = 50, message = "currentTaskKey长度不在有效范围内")
    private String currentTaskKey;

    @ApiModelProperty(value = "事件在哪个任务发生的任务名称")
    // @Length(max = 255, message = "currentTaskName长度不在有效范围内")
    private String currentTaskName;

    @ApiModelProperty(value = "业务类型")
    // @Length(max = 50, message = "bizCode长度不在有效范围内")
    private String bizCode;

    @ApiModelProperty(value = "业务唯一标识")
    // @Length(max = 36, message = "bizId长度不在有效范围内")
    private String bizId;

    @ApiModelProperty(value = "操作原因")
    // @Length(max = 1024, message = "reason长度不在有效范围内")
    private String reason;

    @ApiModelProperty(value = "是否成功(0:否;1:是)")
    // @Range(max = 10L, message = "succeed长度不在有效范围内")
    private Integer succeed;

    @ApiModelProperty(value = "错误信息")
    // @Length(max = 1024, message = "errorMsg长度不在有效范围内")
    private String errorMsg;

    @ApiModelProperty(value = "错误详细信息")
    // @Length(max = 1024, message = "errorText长度不在有效范围内")
    private String errorText;

    @ApiModelProperty(value = "数据版本号, 默认为1 ,变更一次,加1")
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private Long version;

    @ApiModelProperty(value = "是否删除(0否1是)")
    // @Range(max = 10L, message = "deleted长度不在有效范围内")
    private Integer deleted;

    @ApiModelProperty(value = "创建人编码")
    // @Length(max = 50, message = "createUserCode长度不在有效范围内")
    private String createUserCode;

    @ApiModelProperty(value = "创建人名称")
    // @Length(max = 100, message = "createUserName长度不在有效范围内")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人编码")
    // @Length(max = 50, message = "updateUserCode长度不在有效范围内")
    private String updateUserCode;

    @ApiModelProperty(value = "更新人名称")
    // @Length(max = 100, message = "updateUserName长度不在有效范围内")
    private String updateUserName;

    @ApiModelProperty(value = "更新时间", required = true)
    private LocalDateTime updateTime;


}
