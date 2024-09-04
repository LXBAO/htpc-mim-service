package com.uwdp.module.biz.infrastructure.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ejlchina.searcher.bean.DbField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 信息提示
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PROMPT")
@ApiModel(value = "PromptDo entity对象", description = "信息提示")
public class PromptDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建者")
    @TableField("CREATEDNAME")
    private String createdName;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("提示信息标题  ")
    @TableField("PROMPTTITLE")
    private String promptTitle;

    @ApiModelProperty("提示信息详情")
    @TableField("PROMPTDETAILS")
    private String promptDetails;

    @ApiModelProperty("过期时间")
    @TableField("EXPIREDATE")
    private LocalDate expireDate;

    @ApiModelProperty("需要提示的工号")
    @TableField("PROMPTID")
    private String promptId;

    @ApiModelProperty("已读/未读")
    @TableField("PROMPTSTATUS")
    private String promptStatus;

    @ApiModelProperty("删除/未删")
    @TableField("DELETESTATUS")
    private String deleteStatus;

    @ApiModelProperty("QID")
    @TableField(value = "QID")
    private Long qid;

    @ApiModelProperty(value = "跳转路径")
    @TableField("PROMPTPATH")
    private String promptPath;

}
