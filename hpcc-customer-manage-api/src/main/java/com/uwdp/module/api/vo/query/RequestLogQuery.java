package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
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
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RequestLogDo Query对象", description = "流程日志", discriminator = "request_log")
public class RequestLogQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "请求地址", required = true)
    private String requestUrl;

    @ApiModelProperty(value = "请求参数")
    private String requestParam;

    @ApiModelProperty(value = "相应参数")
    private String responseParam;

    @ApiModelProperty(value = "创建时间", required = true)
    private LocalDateTime ctime;
}
