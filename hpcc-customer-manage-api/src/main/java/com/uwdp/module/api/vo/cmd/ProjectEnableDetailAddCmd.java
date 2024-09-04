package com.uwdp.module.api.vo.cmd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目赋能明细
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectEnableDetailAddCmd对象", description = "项目赋能明细", discriminator = "projectEnableDetail")
public class ProjectEnableDetailAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
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

    @ApiModelProperty(value = "主文档id")
    // @Range(max = Long.MAX_VALUE, message = "parentId长度不在有效范围内")
    private Long parentId;

    @ApiModelProperty(value = "跟踪日期")
    private LocalDateTime trackDate;

    @ApiModelProperty(value = "内容")
    // @Length(max = 2000, message = "content长度不在有效范围内")
    private String content;

    @ApiModelProperty(value = "给予板块公司市场赋能说明")
    // @Length(max = 2000, message = "enableExplain长度不在有效范围内")
    private String enableExplain;

    @ApiModelProperty(value = "创建者名称")
    // @Length(max = 100, message = "createdByName长度不在有效范围内")
    private String createdByName;


}
