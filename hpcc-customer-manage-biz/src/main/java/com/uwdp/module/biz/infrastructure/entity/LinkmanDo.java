package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 联系人
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_LINKMAN")
@ApiModel(value = "LinkmanDo entity对象", description = "联系人")
public class LinkmanDo implements Serializable {

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

    @ApiModelProperty("岗位")
    @TableField("JOB")
    private String job;

    @ApiModelProperty("姓名")
    @TableField("`NAME`")
    private String name;

    @ApiModelProperty("状态")
    @TableField("STATE")
    private String state;

    @ApiModelProperty("投标id")
    @TableField("BIDDINGID")
    private String biddingId;

    @ApiModelProperty("项目经理id")
    @TableField("nameId")
    private String nameId;

    @ApiModelProperty(value = "关联人资id")
    @TableField("hrId")
    private String hrId;
}
