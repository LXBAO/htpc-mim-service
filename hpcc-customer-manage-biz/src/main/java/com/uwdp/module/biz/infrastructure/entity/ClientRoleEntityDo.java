package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 权限表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("t_client_role")
@ApiModel(value = "ClientRoleEntityDo entity对象", description = "权限表")
public class ClientRoleEntityDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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


    @ApiModelProperty("部门id（多个,分隔）")
    @TableField("dept_id")
    private String deptId;

    @ApiModelProperty("部门名称（多个，分隔）")
    @TableField("dept_Name")
    private String deptName;

    @ApiModelProperty("用户编码")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("角色")
    @TableField("role")
    private Integer role;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("ctime")
    @TableField("ctime")
    private Date ctime;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEDNAME")
    private String createdName;
}
