package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 流程日志
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_REQUEST_LOG")
@ApiModel(value = "RequestLogDo entity对象", description = "流程日志")
public class RequestLogDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("请求地址")
    @TableField("REQUEST_URL")
    private String requestUrl;

    @ApiModelProperty("请求参数")
    @TableField("REQUEST_PARAM")
    private String requestParam;

    @ApiModelProperty("相应参数")
    @TableField("RESPONSE_PARAM")
    private String responseParam;

    @ApiModelProperty("创建时间")
    @TableField("CTIME")
    private LocalDateTime ctime;

}
