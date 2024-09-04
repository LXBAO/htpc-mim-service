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
 * 下拉列表维护
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "DataAddCmd对象", description = "下拉列表维护", discriminator = "data")
public class DataAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", hidden = true)
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

    @ApiModelProperty(value = "父级ID")
    // @Range(max = Long.MAX_VALUE, message = "parentId长度不在有效范围内")
    private Integer parentId;

    @ApiModelProperty(value = "类型")
    // @Range(max = Long.MAX_VALUE, message = "type长度不在有效范围内")
    private Integer type;

    @ApiModelProperty(value = "类型名称")
    // @Length(max = 255, message = "typeName长度不在有效范围内")
    private String typeName;

    @ApiModelProperty(value = "排序")
    // @Range(max = Long.MAX_VALUE, message = "rank长度不在有效范围内")
    private Integer remark;

    @ApiModelProperty(value = "值")
    // @Length(max = 255, message = "value长度不在有效范围内")
    private String value;


}
