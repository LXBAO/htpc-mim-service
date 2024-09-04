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
 * 流程表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_LMCWORKFLOW")
@ApiModel(value = "LmcWorkflowDo entity对象", description = "流程表")
public class LmcWorkflowDo implements Serializable {

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

    @ApiModelProperty("业务类型")
    @TableField("BIZCODE")
    private String bizCode;

    @ApiModelProperty("业务唯一标识")
    @TableField("BIZID")
    private String bizId;

    @ApiModelProperty("流程编码")
    @TableField("PROCESSCODE")
    private String processCode;

    @ApiModelProperty("流程实例id")
    @TableField("PROCESSINSTANCEID")
    private String processInstanceId;

    @ApiModelProperty("流程标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty("AppId")
    @TableField("APPID")
    private String appId;

    @ApiModelProperty("发起人编码")
    @TableField("SUBMITTER_CODE")
    private String submitterCode;

    @ApiModelProperty("发起人名称")
    @TableField("SUBMITTERNAME")
    private String submitterName;

    @ApiModelProperty("发起时间")
    @TableField("SUBMITTIME")
    private LocalDateTime submitTime;

    @ApiModelProperty("流程结束时间")
    @TableField("ENDTIME")
    private LocalDateTime endTime;

    @ApiModelProperty("流程状态(数据字典)")
    @TableField("WORKFLOWSTATUS")
    private String workflowStatus;

    @ApiModelProperty("操作原因")
    @TableField("REASON")
    private String reason;

    @ApiModelProperty("发起序号")
    @TableField("SORTNUMBER")
    private Integer sortNumber;

    @ApiModelProperty("数据版本号 , 默认为1 , 变更一次 , 加1")
    @TableField("VERSION")
    private Long version;

    @ApiModelProperty("是否删除(0: 否; 1: 是)")
    @TableField("ISDELETED")
    private Integer isDeleted;

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
