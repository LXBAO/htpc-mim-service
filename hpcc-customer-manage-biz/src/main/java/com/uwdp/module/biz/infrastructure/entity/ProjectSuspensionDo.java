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
 * 项目中止
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PROJECTSUSPENSION")
@ApiModel(value = "ProjectSuspensionDo entity对象", description = "项目中止")
public class ProjectSuspensionDo implements Serializable {

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

    @ApiModelProperty("项目名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty("项目编号")
    @TableField("PROJECTNO")
    private String projectNo;

    @ApiModelProperty("关联项目id")
    @TableField("PROJECTID")
    private String projectId;

    @ApiModelProperty("中止人")
    @TableField("DISCONTINUER")
    private String discontinuer;

    @ApiModelProperty("中止人编码")
    @TableField("DISCONTINUERNO")
    private String discontinuerNo;

    @ApiModelProperty("中止时间")
    @TableField("SUSPENSIONTIME ")
    private String suspensionTime ;

    @ApiModelProperty("中止原因")
    @TableField("REASONSUSPEND")
    private String reasonSuspend;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @TableField("GROUPFULLCODE")
    private String GroupFullCode;

}
