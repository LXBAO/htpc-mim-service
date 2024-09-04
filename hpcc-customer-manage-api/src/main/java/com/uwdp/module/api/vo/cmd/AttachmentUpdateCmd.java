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
 * 附件表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "Attachment UpdateCmd对象", description = "附件表", discriminator = "attachment")
public class AttachmentUpdateCmd implements Serializable {

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

    @ApiModelProperty(value = "文件名")
    // @Length(max = 100, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "文件真实路径")
    // @Length(max = 300, message = "url长度不在有效范围内")
    private String url;

    @ApiModelProperty(value = "后缀")
    // @Length(max = 5, message = "suffix长度不在有效范围内")
    private String suffix;

    @ApiModelProperty(value = "文件大小")
    // @Range(max = Long.MAX_VALUE, message = "size长度不在有效范围内")
    private Long size;

    @ApiModelProperty(value = "是否有效")
    // @Range(max = 10000000000L, message = "validFlag长度不在有效范围内")
    private Integer validFlag;

    @ApiModelProperty(value = "作者")
    // @Range(max = Long.MAX_VALUE, message = "orderType长度不在有效范围内")
    private Long orderType;

    @ApiModelProperty(value = "码值不需要和控件绑定就为空")
    // @Length(max = 16, message = "pubCode长度不在有效范围内")
    private String pubCode;

    @ApiModelProperty(value = "单子id")
    // @Length(max = 30, message = "orderId长度不在有效范围内")
    private String orderId;


}
