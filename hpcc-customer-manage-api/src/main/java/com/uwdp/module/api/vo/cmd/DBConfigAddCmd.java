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
 * 数据库连接配置
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "DBConfigAddCmd对象", description = "数据库连接配置", discriminator = "DBConfig")
public class DBConfigAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
    private Long fdId;

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

    @ApiModelProperty(value = "名称")
    // @Length(max = 100, message = "dbName长度不在有效范围内")
    private String dbName;

    @ApiModelProperty(value = "数据库类型")
    // @Length(max = 36, message = "dBType长度不在有效范围内")
    private String dBType;

    @ApiModelProperty(value = "JDBC驱动")
    // @Length(max = 200, message = "jDBCDriver长度不在有效范围内")
    private String jDBCDriver;

    @ApiModelProperty(value = "连接URL")
    // @Length(max = 500, message = "connectUrl长度不在有效范围内")
    private String connectUrl;

    @ApiModelProperty(value = "用户名")
    // @Length(max = 255, message = "userName长度不在有效范围内")
    private String userName;

    @ApiModelProperty(value = "密码")
    // @Length(max = 500, message = "password长度不在有效范围内")
    private String password;

    @ApiModelProperty(value = "描述")
    // @Length(max = 500, message = "description长度不在有效范围内")
    private String description;


}
