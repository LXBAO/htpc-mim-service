package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uwdp.module.api.vo.enums.*;
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
@ApiModel(value = "RequestLogDTO对象", description = "流程日志", discriminator = "request_log")
@SearchBean(tables = "T_REQUEST_LOG")
public class RequestLogDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @DbField("ID")
    // @Range(max = 100000000000L, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "请求地址", required = true)
    @DbField("REQUEST_URL")
    // @Length(max = 128, message = "requestUrl长度不在有效范围内")
    private String requestUrl;

    @ApiModelProperty(value = "请求参数")
    @DbField("REQUEST_PARAM")
    // @Length(max = 255, message = "requestParam长度不在有效范围内")
    private String requestParam;

    @ApiModelProperty(value = "相应参数")
    @DbField("RESPONSE_PARAM")
    // @Length(max = 255, message = "responseParam长度不在有效范围内")
    private String responseParam;

    @ApiModelProperty(value = "创建时间", required = true)
    @DbField("CTIME")
    private LocalDateTime ctime;

}
