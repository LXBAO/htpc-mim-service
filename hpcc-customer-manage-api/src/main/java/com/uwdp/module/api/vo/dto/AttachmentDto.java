package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ejlchina.searcher.bean.SearchBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "AttachmentDTO对象", description = "附件表", discriminator = "attachment")
@SearchBean(tables = "T_ATTACHMENT")
public class AttachmentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @TableField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @TableField("UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "文件名")
    @TableField("NAME")
    // @Length(max = 100, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "文件真实路径")
    @TableField("URL")
    // @Length(max = 300, message = "url长度不在有效范围内")
    private String url;

    @ApiModelProperty(value = "后缀")
    @TableField("SUFFIX")
    // @Length(max = 5, message = "suffix长度不在有效范围内")
    private String suffix;

    @ApiModelProperty(value = "文件大小")
    @TableField("SIZE")
    // @Range(max = Long.MAX_VALUE, message = "size长度不在有效范围内")
    private Long size;

    @ApiModelProperty(value = "是否有效")
    @TableField("VALID_FLAG")
    // @Range(max = 10000000000L, message = "validFlag长度不在有效范围内")
    private Integer validFlag;

    @ApiModelProperty(value = "作者")
    @TableField("ORDER_TYPE")
    // @Range(max = Long.MAX_VALUE, message = "orderType长度不在有效范围内")
    private Long orderType;

    @ApiModelProperty(value = "码值不需要和控件绑定就为空")
    @TableField("PUB_CODE")
    // @Length(max = 16, message = "pubCode长度不在有效范围内")
    private String pubCode;

    @ApiModelProperty(value = "单子id")
    @TableField("ORDER_ID")
    // @Length(max = 30, message = "orderId长度不在有效范围内")
    private String orderId;

}
