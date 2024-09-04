package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uwdp.module.api.vo.enums.*;
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
@ApiModel(value = "LmcWorkflowTimelineDTO对象", description = "流程时间线", discriminator = "LmcWorkflowTimeline")
@SearchBean(tables = "T_LMCWORKFLOWTIMELINE")
public class LmcWorkflowTimelineDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
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

    @ApiModelProperty(value = "流程id")
    @DbField("WORKFLOWID")
    // @Length(max = 32, message = "workflowId长度不在有效范围内")
    private String workflowId;

    @ApiModelProperty(value = "流程状态(数据字典)")
    @DbField("WORKFLOWSTATUS")
    // @Length(max = 20, message = "workflowStatus长度不在有效范围内")
    private String workflowStatus;

    @ApiModelProperty(value = "回调/触发时间")
    @DbField("TRIGGERTIME")
    private LocalDateTime triggerTime;

    @ApiModelProperty(value = "流程编码")
    @DbField("PROCESSCODE")
    // @Length(max = 50, message = "processCode长度不在有效范围内")
    private String processCode;

    @ApiModelProperty(value = "流程名称")
    @DbField("PROCESSNAME")
    // @Length(max = 255, message = "processName长度不在有效范围内")
    private String processName;

    @ApiModelProperty(value = "流程实例id")
    @DbField("PROCESSINSTANCEID")
    // @Length(max = 36, message = "processInstanceId长度不在有效范围内")
    private String processInstanceId;

    @ApiModelProperty(value = "流程回调id")
    @DbField("PROCESSCALLEVENTID")
    // @Length(max = 36, message = "processCallEventId长度不在有效范围内")
    private String processCallEventId;

    @ApiModelProperty(value = "AppId")
    @DbField("APPID")
    // @Length(max = 50, message = "appId长度不在有效范围内")
    private String appId;

    @ApiModelProperty(value = "操作人编码")
    @DbField("OPERATORCODE")
    // @Length(max = 50, message = "operatorCode长度不在有效范围内")
    private String operatorCode;

    @ApiModelProperty(value = "操作人名称")
    @DbField("OPERATORNAME")
    // @Length(max = 100, message = "operatorName长度不在有效范围内")
    private String operatorName;

    @ApiModelProperty(value = "事件在哪个任务发生的任务id")
    @DbField("CURRENTTASKID")
    // @Length(max = 36, message = "currentTaskId长度不在有效范围内")
    private String currentTaskId;

    @ApiModelProperty(value = "事件在哪个任务发生的任务编码")
    @DbField("CURRENTTASKKEY")
    // @Length(max = 50, message = "currentTaskKey长度不在有效范围内")
    private String currentTaskKey;

    @ApiModelProperty(value = "事件在哪个任务发生的任务名称")
    @DbField("CURRENTTASKNAME")
    // @Length(max = 255, message = "currentTaskName长度不在有效范围内")
    private String currentTaskName;

    @ApiModelProperty(value = "业务类型")
    @DbField("BIZ_CODE")
    // @Length(max = 50, message = "bizCode长度不在有效范围内")
    private String bizCode;

    @ApiModelProperty(value = "业务唯一标识")
    @DbField("BIZ_ID")
    // @Length(max = 36, message = "bizId长度不在有效范围内")
    private String bizId;

    @ApiModelProperty(value = "操作原因")
    @DbField("REASON")
    // @Length(max = 1024, message = "reason长度不在有效范围内")
    private String reason;

    @ApiModelProperty(value = "是否成功(0:否;1:是)")
    @DbField("SUCCEED")
    // @Range(max = 10L, message = "succeed长度不在有效范围内")
    private Integer succeed;

    @ApiModelProperty(value = "错误信息")
    @DbField("ERRORMSG")
    // @Length(max = 1024, message = "errorMsg长度不在有效范围内")
    private String errorMsg;

    @ApiModelProperty(value = "错误详细信息")
    @DbField("ERRORTEXT")
    // @Length(max = 1024, message = "errorText长度不在有效范围内")
    private String errorText;

    @ApiModelProperty(value = "数据版本号, 默认为1 ,变更一次,加1")
    @DbField("VERSION")
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private Long version;

    @ApiModelProperty(value = "是否删除(0否1是)")
    @DbField("DELETED")
    // @Range(max = 10L, message = "deleted长度不在有效范围内")
    private Integer deleted;

    @ApiModelProperty(value = "创建人编码")
    @DbField("CREATEUSERCODE")
    // @Length(max = 50, message = "createUserCode长度不在有效范围内")
    private String createUserCode;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEUSERNAME")
    // @Length(max = 100, message = "createUserName长度不在有效范围内")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATETIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人编码")
    @DbField("UPDATEUSERCODE")
    // @Length(max = 50, message = "updateUserCode长度不在有效范围内")
    private String updateUserCode;

    @ApiModelProperty(value = "更新人名称")
    @DbField("UPDATEUSERNAME")
    // @Length(max = 100, message = "updateUserName长度不在有效范围内")
    private String updateUserName;

    @ApiModelProperty(value = "更新时间", required = true)
    @DbField("UPDATETIME")
    private LocalDateTime updateTime;

}
