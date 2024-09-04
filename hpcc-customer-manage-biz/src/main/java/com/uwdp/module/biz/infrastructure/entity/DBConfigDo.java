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
 * 数据库连接配置
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_DBCONFIG")
@ApiModel(value = "DBConfigDo entity对象", description = "数据库连接配置")
public class DBConfigDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "FDID", type = IdType.ASSIGN_ID)
    private Long fdId;

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

    @ApiModelProperty("名称")
    @TableField("DBNAME")
    private String dbName;

    @ApiModelProperty("数据库类型")
    @TableField("DBTYPE")
    private String dBType;

    @ApiModelProperty("JDBC驱动")
    @TableField("JDBCDRIVER")
    private String jDBCDriver;

    @ApiModelProperty("连接URL")
    @TableField("CONNECTURL")
    private String connectUrl;

    @ApiModelProperty("用户名")
    @TableField("USERNAME")
    private String userName;

    @ApiModelProperty("密码")
    @TableField("`PASSWORD`")
    private String password;

    @ApiModelProperty("描述")
    @TableField("`DESCRIPTION`")
    private String description;

}
