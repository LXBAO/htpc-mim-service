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
 * 客户相关联系人
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "RelevantContactAddCmd对象", description = "客户相关联系人", discriminator = "relevantContact")
public class RelevantContactAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
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

    @ApiModelProperty(value = "关联客户ID", required = true)
    // @Range(max = Long.MAX_VALUE, message = "clientId长度不在有效范围内")
    private Long clientId;

    @ApiModelProperty(value = "联系人姓名", required = true)
    // @Length(max = 50, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "联系人职位")
    // @Length(max = 100, message = "position 长度不在有效范围内")
    private String position ;

    @ApiModelProperty(value = "联系方式", required = true)
    // @Length(max = 100, message = "contact长度不在有效范围内")
    private String contact;

    @ApiModelProperty(value = "联系地址")
    // @Length(max = 255, message = "contactAddress长度不在有效范围内")
    private String contactAddress;


}
