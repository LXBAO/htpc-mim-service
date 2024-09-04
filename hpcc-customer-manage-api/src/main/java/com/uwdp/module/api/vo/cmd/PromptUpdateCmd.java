package com.uwdp.module.api.vo.cmd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 信息提示
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "Prompt UpdateCmd对象", description = "信息提示", discriminator = "prompt")
public class PromptUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID", required = true)
    @NotNull(message = "主键不能为空")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "提示信息标题  ")
    // @Length(max = 255, message = "promptTitle长度不在有效范围内")
    private String promptTitle;

    @ApiModelProperty(value = "提示信息详情")
    // @Length(max = 255, message = "promptDetails长度不在有效范围内")
    private String promptDetails;

    @ApiModelProperty(value = "过期时间")
    private LocalDate expireDate;

    @ApiModelProperty(value = "需要提示的工号")
    // @Length(max = 100, message = "promptId长度不在有效范围内")
    private String promptId;

    @ApiModelProperty(value = "已读/未读")
    // @Length(max = 100, message = "promptStatus长度不在有效范围内")
    private String promptStatus;

    @ApiModelProperty(value = "删除/未删")
    // @Length(max = 100, message = "deleteStatus长度不在有效范围内")
    private String deleteStatus;

    @ApiModelProperty(value = "QID")
    // @Range(max = Long.MAX_VALUE, message = "qid长度不在有效范围内")
    private Long qid;

    @ApiModelProperty(value = "跳转路径")
    // @Length(max = 255, message = "promptPath长度不在有效范围内")
    private String promptPath;


}
