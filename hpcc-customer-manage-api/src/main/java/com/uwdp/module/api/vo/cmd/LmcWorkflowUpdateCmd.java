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
 * 流程表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LmcWorkflow UpdateCmd对象", description = "流程表", discriminator = "LmcWorkflow")
public class LmcWorkflowUpdateCmd implements Serializable {

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

    @ApiModelProperty(value = "业务类型")
    // @Length(max = 50, message = "bizCode长度不在有效范围内")
    private String bizCode;

    @ApiModelProperty(value = "业务唯一标识")
    // @Length(max = 36, message = "bizId长度不在有效范围内")
    private String bizId;

    @ApiModelProperty(value = "流程编码")
    // @Length(max = 50, message = "processCode长度不在有效范围内")
    private String processCode;

    @ApiModelProperty(value = "流程实例id", required = true)
    // @Length(max = 36, message = "processInstanceId长度不在有效范围内")
    private String processInstanceId;

    @ApiModelProperty(value = "流程标题")
    // @Length(max = 255, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "AppId")
    // @Length(max = 50, message = "appId长度不在有效范围内")
    private String appId;

    @ApiModelProperty(value = "发起人编码")
    // @Length(max = 50, message = "submitterCode长度不在有效范围内")
    private String submitterCode;

    @ApiModelProperty(value = "发起人名称")
    // @Length(max = 100, message = "submitterName长度不在有效范围内")
    private String submitterName;

    @ApiModelProperty(value = "发起时间")
    private LocalDateTime submitTime;

    @ApiModelProperty(value = "流程结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "流程状态(数据字典)")
    // @Length(max = 20, message = "workflowStatus长度不在有效范围内")
    private String workflowStatus;

    @ApiModelProperty(value = "操作原因")
    // @Length(max = 1024, message = "reason长度不在有效范围内")
    private String reason;

    @ApiModelProperty(value = "发起序号")
    // @Range(max = Long.MAX_VALUE, message = "sortNumber长度不在有效范围内")
    private Integer sortNumber;

    @ApiModelProperty(value = "数据版本号 , 默认为1 , 变更一次 , 加1")
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private Long version;

    @ApiModelProperty(value = "是否删除(0: 否; 1: 是)")
    // @Range(max = 10L, message = "isDeleted长度不在有效范围内")
    private Integer isDeleted;

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
