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
@ToString
@ApiModel(value = "ClientRoleEntityAddCmd对象", description = "权限表", discriminator = "ClientRoleEntity")
public class ClientRoleEntityAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "部门id（多个,分隔）")
    // @Length(max = 5000, message = "deptId长度不在有效范围内")
    private String deptId;

    @ApiModelProperty(value = "部门名称（多个，分隔）")
    // @Length(max = 5000, message = "deptName长度不在有效范围内")
    private String deptName;

    @ApiModelProperty(value = "用户编码")
    // @Length(max = 5000, message = "userId长度不在有效范围内")
    private String userId;

    @ApiModelProperty(value = "姓名")
    // @Length(max = 5000, message = "userName长度不在有效范围内")
    private String userName;

    @ApiModelProperty(value = "角色")
    // @Range(max = Long.MAX_VALUE, message = "role长度不在有效范围内")
    private Integer role;

    @ApiModelProperty(value = "角色名称")
    // @Length(max = 100, message = "roleName长度不在有效范围内")
    private String roleName;

    @ApiModelProperty(value = "状态")
    // @Range(max = Long.MAX_VALUE, message = "status长度不在有效范围内")
    private Integer status;

    @ApiModelProperty(value = "ctime")
    private Date ctime;


}
