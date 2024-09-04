package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 流程配置表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LmcWorkflowConfigAddCmd对象", description = "流程配置表", discriminator = "LmcWorkflowConfig")
public class LmcWorkflowConfigAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
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

    @ApiModelProperty(value = "业务类型", required = true)
    // @Length(max = 50, message = "bizCode长度不在有效范围内")
    private String bizCode;

    @ApiModelProperty(value = "流程编码", required = true)
    // @Length(max = 50, message = "processCode长度不在有效范围内")
    private String processCode;

    @ApiModelProperty(value = "流程名称")
    // @Length(max = 255, message = "processName长度不在有效范围内")
    private String processName;

    @ApiModelProperty(value = "流程回调编码 , 根据该编码实现具体的回调逻辑 , 多个逗号分隔", required = true)
    // @Length(max = 255, message = "processCallCode长度不在有效范围内")
    private String processCallCode;

    @ApiModelProperty(value = "数据版本号 , 默认为1 , 变更一次 ,加 1", required = true)
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private Long version;

    @ApiModelProperty(value = "是否删除(0: 否 ,1: 是)")
    // @Range(max = 10L, message = "isDeleted长度不在有效范围内")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建人编码")
    // @Length(max = 50, message = "createUserCode长度不在有效范围内")
    private String createUserCode;

    @ApiModelProperty(value = "创建人名称")
    // @Length(max = 100, message = "createUserName长度不在有效范围内")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    private LocalTime createTime;

    @ApiModelProperty(value = "更新人编码")
    // @Length(max = 50, message = "updateUserCode长度不在有效范围内")
    private String updateUserCode;

    @ApiModelProperty(value = "更新人名称")
    // @Length(max = 100, message = "updateUserName长度不在有效范围内")
    private String updateUserName;

    @ApiModelProperty(value = "更新时间", required = true)
    private LocalDateTime updateTime;


}
