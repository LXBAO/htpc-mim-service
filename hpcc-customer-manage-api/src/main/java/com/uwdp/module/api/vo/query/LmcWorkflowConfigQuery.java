package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 流程配置表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LmcWorkflowConfigDo Query对象", description = "流程配置表", discriminator = "LmcWorkflowConfig")
public class LmcWorkflowConfigQuery extends BasePageQuery {

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

    @ApiModelProperty(value = "业务类型", required = true)
    private String bizCode;

    @ApiModelProperty(value = "流程编码", required = true)
    private String processCode;

    @ApiModelProperty(value = "流程名称")
    private String processName;

    @ApiModelProperty(value = "流程回调编码 , 根据该编码实现具体的回调逻辑 , 多个逗号分隔", required = true)
    private String processCallCode;

    @ApiModelProperty(value = "数据版本号 , 默认为1 , 变更一次 ,加 1", required = true)
    private Long version;

    @ApiModelProperty(value = "是否删除(0: 否 ,1: 是)")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建人编码")
    private String createUserCode;

    @ApiModelProperty(value = "创建人名称")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    private LocalTime createTime;

    @ApiModelProperty(value = "更新人编码")
    private String updateUserCode;

    @ApiModelProperty(value = "更新人名称")
    private String updateUserName;

    @ApiModelProperty(value = "更新时间", required = true)
    private LocalDateTime updateTime;
}
