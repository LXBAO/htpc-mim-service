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
 * 下拉列表维护
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_DATA")
@ApiModel(value = "DataDo entity对象", description = "下拉列表维护")
public class DataDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "ID", type = IdType.AUTO)
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

    @ApiModelProperty("父级ID")
    @TableField("PARENT_ID")
    private Integer parentId;

    @ApiModelProperty("类型")
    @TableField("TYPE")
    private Integer type;

    @ApiModelProperty("类型名称")
    @TableField("TYPENAME")
    private String typeName;

    @ApiModelProperty("排序")
    @TableField("REMARK")
    private Integer remark;

    @ApiModelProperty("值")
    @TableField("VALUE")
    private String value;

}
