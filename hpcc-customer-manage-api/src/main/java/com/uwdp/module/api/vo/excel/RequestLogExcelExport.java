package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
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
@ApiModel(value = "RequestLogExcelExport对象", description = "流程日志", discriminator = "request_log")
@SearchBean(tables = "T_REQUEST_LOG")
@EqualsAndHashCode(callSuper = false)
public class RequestLogExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主键"}, index = 0)
    // @Range(max = 100000000000L, message = "id长度不在有效范围内")
    private String id;

    @ApiModelProperty(value = "请求地址", required = true)
    @DbField("REQUEST_URL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"请求地址"}, index = 1)
    // @Length(max = 128, message = "requestUrl长度不在有效范围内")
    private String requestUrl;

    @ApiModelProperty(value = "请求参数")
    @DbField("REQUEST_PARAM")
    @ColumnWidth(16)
    @ExcelProperty(value = {"请求参数"}, index = 2)
    // @Length(max = 255, message = "requestParam长度不在有效范围内")
    private String requestParam;

    @ApiModelProperty(value = "相应参数")
    @DbField("RESPONSE_PARAM")
    @ColumnWidth(16)
    @ExcelProperty(value = {"相应参数"}, index = 3)
    // @Length(max = 255, message = "responseParam长度不在有效范围内")
    private String responseParam;

    @ApiModelProperty(value = "创建时间", required = true)
    @DbField("CTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 4)
    private String ctime;


}
