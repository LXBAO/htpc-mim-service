package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 数据库连接配置
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "DBConfigDo Query对象", description = "数据库连接配置", discriminator = "DBConfig")
public class DBConfigQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long fdId;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "名称")
    private String dbName;

    @ApiModelProperty(value = "数据库类型")
    private String dBType;

    @ApiModelProperty(value = "JDBC驱动")
    private String jDBCDriver;

    @ApiModelProperty(value = "连接URL")
    private String connectUrl;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "描述")
    private String description;
}
