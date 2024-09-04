package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_LMCWORKFLOWTIMELINE")
@ApiModel(value = "LmcWorkflowTimelineDo entity对象", description = "流程时间线")
public class LmcWorkflowTimelineDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
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

    @ApiModelProperty("流程id")
    @TableField("WORKFLOWID")
    private String workflowId;

    @ApiModelProperty("流程状态(数据字典)")
    @TableField("WORKFLOWSTATUS")
    private String workflowStatus;

    @ApiModelProperty("回调/触发时间")
    @TableField("TRIGGERTIME")
    private LocalDateTime triggerTime;

    @ApiModelProperty("流程编码")
    @TableField("PROCESSCODE")
    private String processCode;

    @ApiModelProperty("流程名称")
    @TableField("PROCESSNAME")
    private String processName;

    @ApiModelProperty("流程实例id")
    @TableField("PROCESSINSTANCEID")
    private String processInstanceId;

    @ApiModelProperty("流程回调id")
    @TableField("PROCESSCALLEVENTID")
    private String processCallEventId;

    @ApiModelProperty("AppId")
    @TableField("APPID")
    private String appId;

    @ApiModelProperty("操作人编码")
    @TableField("OPERATORCODE")
    private String operatorCode;

    @ApiModelProperty("操作人名称")
    @TableField("OPERATORNAME")
    private String operatorName;

    @ApiModelProperty("事件在哪个任务发生的任务id")
    @TableField("CURRENTTASKID")
    private String currentTaskId;

    @ApiModelProperty("事件在哪个任务发生的任务编码")
    @TableField("CURRENTTASKKEY")
    private String currentTaskKey;

    @ApiModelProperty("事件在哪个任务发生的任务名称")
    @TableField("CURRENTTASKNAME")
    private String currentTaskName;

    @ApiModelProperty("业务类型")
    @TableField("BIZ_CODE")
    private String bizCode;

    @ApiModelProperty("业务唯一标识")
    @TableField("BIZ_ID")
    private String bizId;

    @ApiModelProperty("操作原因")
    @TableField("REASON")
    private String reason;

    @ApiModelProperty("是否成功(0:否;1:是)")
    @TableField("SUCCEED")
    private Integer succeed;

    @ApiModelProperty("错误信息")
    @TableField("ERRORMSG")
    private String errorMsg;

    @ApiModelProperty("错误详细信息")
    @TableField("ERRORTEXT")
    private String errorText;

    @ApiModelProperty("数据版本号, 默认为1 ,变更一次,加1")
    @TableField("VERSION")
    private Long version;

    @ApiModelProperty("是否删除(0否1是)")
    @TableField("DELETED")
    private Integer deleted;

    @ApiModelProperty("创建人编码")
    @TableField("CREATEUSERCODE")
    private String createUserCode;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEUSERNAME")
    private String createUserName;

    @ApiModelProperty("创建时间")
    @TableField("CREATETIME")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人编码")
    @TableField("UPDATEUSERCODE")
    private String updateUserCode;

    @ApiModelProperty("更新人名称")
    @TableField("UPDATEUSERNAME")
    private String updateUserName;

    @ApiModelProperty("更新时间")
    @TableField("UPDATETIME")
    private LocalDateTime updateTime;

}
