package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
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
@ToString
@ApiModel(value = "RequestLog UpdateCmd对象", description = "流程日志", discriminator = "request_log")
public class RequestLogUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @NotNull(message = "主键不能为空")
    // @Range(max = 100000000000L, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "请求地址", required = true)
    // @Length(max = 128, message = "requestUrl长度不在有效范围内")
    private String requestUrl;

    @ApiModelProperty(value = "请求参数")
    // @Length(max = 255, message = "requestParam长度不在有效范围内")
    private String requestParam;

    @ApiModelProperty(value = "相应参数")
    // @Length(max = 255, message = "responseParam长度不在有效范围内")
    private String responseParam;

    @ApiModelProperty(value = "创建时间", required = true)
    private LocalDateTime ctime;


}
