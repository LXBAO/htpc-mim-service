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
 * 企查查记录
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "EnterpriseCheckRecord UpdateCmd对象", description = "企查查记录", discriminator = "enterpriseCheckRecord")
public class EnterpriseCheckRecordUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @NotNull(message = "主键不能为空")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

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

    @ApiModelProperty(value = "主键")
    // @Length(max = 100, message = "keyNo长度不在有效范围内")
    private String keyNo;

    @ApiModelProperty(value = "企业名称")
    // @Length(max = 1000, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "统一社会信用代码")
    // @Length(max = 100, message = "creditCode长度不在有效范围内")
    private String creditCode;

    @ApiModelProperty(value = "成立日期")
    // @Length(max = 100, message = "startDate长度不在有效范围内")
    private String startDate;

    @ApiModelProperty(value = "法定代表人姓名")
    // @Length(max = 100, message = "operName长度不在有效范围内")
    private String operName;

    @ApiModelProperty(value = "状态")
    // @Length(max = 100, message = "status长度不在有效范围内")
    private String status;

    @ApiModelProperty(value = "注册号")
    // @Length(max = 100, message = "no长度不在有效范围内")
    private String no;

    @ApiModelProperty(value = "注册地址")
    // @Length(max = 128, message = "address长度不在有效范围内")
    private String address;


}
