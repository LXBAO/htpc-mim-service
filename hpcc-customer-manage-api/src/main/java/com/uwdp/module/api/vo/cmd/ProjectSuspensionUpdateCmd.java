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
import java.time.LocalDateTime;

/**
 * <p>
 * 项目中止
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectSuspension UpdateCmd对象", description = "项目中止", discriminator = "projectSuspension")
public class ProjectSuspensionUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID", required = true)
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

    @ApiModelProperty(value = "项目名称")
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "项目编号")
    // @Length(max = 255, message = "projectNo长度不在有效范围内")
    private String projectNo;

    @ApiModelProperty(value = "关联项目id")
    // @Length(max = 36, message = "projectId长度不在有效范围内")
    private String projectId;

    @ApiModelProperty(value = "中止人")
    // @Length(max = 255, message = "discontinuer长度不在有效范围内")
    private String discontinuer;

    @ApiModelProperty(value = "中止人编码")
    // @Length(max = 255, message = "discontinuerNo长度不在有效范围内")
    private String discontinuerNo;

    @ApiModelProperty(value = "中止时间")
    private String suspensionTime ;

    @ApiModelProperty(value = "中止原因")
    // @Length(max = 500, message = "reasonSuspend长度不在有效范围内")
    private String reasonSuspend;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    // @Length(max = 500, message = "group_code长度不在有效范围内")
    private String GroupFullCode;
}
