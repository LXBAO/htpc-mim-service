package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 附件表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AttachmentDo Query对象", description = "附件表", discriminator = "attachment")
public class AttachmentQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "文件真实路径")
    private String url;

    @ApiModelProperty(value = "后缀")
    private String suffix;

    @ApiModelProperty(value = "文件大小")
    private Long size;

    @ApiModelProperty(value = "是否有效")
    private Integer validFlag;

    @ApiModelProperty(value = "作者")
    private Long orderType;

    @ApiModelProperty(value = "码值不需要和控件绑定就为空")
    private String pubCode;

    @ApiModelProperty(value = "单子id")
    private String orderId;
}
