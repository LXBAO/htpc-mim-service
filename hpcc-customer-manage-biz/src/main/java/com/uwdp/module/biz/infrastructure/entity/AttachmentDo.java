package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_ATTACHMENT")
@ApiModel(value = "AttachmentDo entity对象", description = "附件表")
public class AttachmentDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("文件名")
    @TableField("`NAME`")
    private String name;

    @ApiModelProperty("文件真实路径")
    @TableField("URL")
    private String url;

    @ApiModelProperty("后缀")
    @TableField("SUFFIX")
    private String suffix;

    @ApiModelProperty("文件大小")
    @TableField("SIZE")
    private Long size;

    @ApiModelProperty("是否有效")
    @TableField("VALID_FLAG")
    private Integer validFlag;

    @ApiModelProperty("订单类型")
    @TableField("ORDER_TYPE")
    private Long orderType;

    @ApiModelProperty("码值不需要和控件绑定就为空")
    @TableField("PUB_CODE")
    private String pubCode;

    @ApiModelProperty("单子id")
    @TableField("ORDER_ID")
    private String orderId;

}
