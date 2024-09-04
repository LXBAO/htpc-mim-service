package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
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
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LmcWorkflowDo Query对象", description = "流程表", discriminator = "LmcWorkflow")
public class LmcWorkflowQuery extends BasePageQuery {

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

    @ApiModelProperty(value = "业务类型")
    private String bizCode;

    @ApiModelProperty(value = "业务唯一标识")
    private String bizId;

    @ApiModelProperty(value = "流程编码")
    private String processCode;

    @ApiModelProperty(value = "流程实例id", required = true)
    private String processInstanceId;

    @ApiModelProperty(value = "流程标题")
    private String title;

    @ApiModelProperty(value = "AppId")
    private String appId;

    @ApiModelProperty(value = "发起人编码")
    private String submitterCode;

    @ApiModelProperty(value = "发起人名称")
    private String submitterName;

    @ApiModelProperty(value = "发起时间")
    private LocalDateTime submitTime;

    @ApiModelProperty(value = "流程结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "流程状态(数据字典)")
    private String workflowStatus;

    @ApiModelProperty(value = "操作原因")
    private String reason;

    @ApiModelProperty(value = "发起序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "数据版本号 , 默认为1 , 变更一次 , 加1")
    private Long version;

    @ApiModelProperty(value = "是否删除(0: 否; 1: 是)")
    private Integer isDeleted;

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
