package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PROJECTENABLEDETAIL")
@ApiModel(value = "ProjectEnableDetailDo entity对象", description = "项目赋能明细")
public class ProjectEnableDetailDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
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

    @ApiModelProperty("主文档id")
    @TableField("PARENTID")
    private Long parentId;

    @ApiModelProperty("跟踪日期")
    @TableField("TRACKDATE")
    private LocalDateTime trackDate;

    @ApiModelProperty("内容")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty("给予板块公司市场赋能说明")
    @TableField("ENABLEEXPLAIN")
    private String enableExplain;

    @ApiModelProperty("创建者名称")
    @TableField("CREATEDBYNAME")
    private String createdByName;

}
