package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 权限表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ClientRoleEntityDo Query对象", description = "权限表", discriminator = "ClientRoleEntity")
public class ClientRoleEntityQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "部门id（多个,分隔）")
    private String deptId;

    @ApiModelProperty(value = "部门名称（多个，分隔）")
    private String deptName;

    @ApiModelProperty(value = "用户编码")
    private String userId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "角色")
    private Integer role;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "ctime")
    private Date ctime;

    @ApiModelProperty(value = "创建人名称")
    private String createdName;
}
