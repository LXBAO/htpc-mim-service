package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 标段
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "Section UpdateCmd对象", description = "标段", discriminator = "section")
public class SectionUpdateCmd implements Serializable {

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

    @ApiModelProperty(value = "标段名称")
    // @Length(max = 255, message = "sectionName长度不在有效范围内")
    private String sectionName;

    @ApiModelProperty(value = "拦标价/预计金额（万元）")
    // @Length(max = 50, message = "estimatedAmount长度不在有效范围内")
    private String estimatedAmount;

    @ApiModelProperty(value = "标段内容")
    // @Length(max = 255, message = "sectionContent长度不在有效范围内")
    private String sectionContent;

    @ApiModelProperty(value = "项目id")
    // @Length(max = 100, message = "projectId长度不在有效范围内")
    private String projectId;

    @ApiModelProperty(value = "预计中标过滤")
    // @Length(max = 100, message = "bidProbability长度不在有效范围内")
    private String bidProbability;
}
