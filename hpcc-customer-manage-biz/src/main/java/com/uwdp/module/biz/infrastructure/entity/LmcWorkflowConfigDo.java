package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 流程配置表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_LMCWORKFLOWCONFIG")
@ApiModel(value = "LmcWorkflowConfigDo entity对象", description = "流程配置表")
public class LmcWorkflowConfigDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
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

    @ApiModelProperty("业务类型")
    @TableField("BIZCODE")
    private String bizCode;

    @ApiModelProperty("流程编码")
    @TableField("PROCESSCODE")
    private String processCode;

    @ApiModelProperty("流程名称")
    @TableField("PROCESSNAME")
    private String processName;

    @ApiModelProperty("流程回调编码 , 根据该编码实现具体的回调逻辑 , 多个逗号分隔")
    @TableField("PROCESSCALLCODE")
    private String processCallCode;

    @ApiModelProperty("数据版本号 , 默认为1 , 变更一次 ,加 1")
    @TableField("VERSION")
    private Long version;

    @ApiModelProperty("是否删除(0: 否 ,1: 是)")
    @TableField("ISDELETED")
    private Integer isDeleted;

    @ApiModelProperty("创建人编码")
    @TableField("CREATEUSERCODE")
    private String createUserCode;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEUSERNAME")
    private String createUserName;

    @ApiModelProperty("创建时间")
    @TableField("CREATETIME")
    private LocalTime createTime;

    @ApiModelProperty("更新人编码")
    @TableField("UPDATEUSERCODE")
    private String updateUserCode;

    @ApiModelProperty("更新人名称")
    @TableField("UPDATEUSERNAME")
    private String updateUserName;

    @ApiModelProperty("更新时间")
    @TableField("UPDATETIME")
    private LocalDateTime updateTime;

}
