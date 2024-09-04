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
 * 流程表表单数据
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_LMCWORKFLOWVARIABLE")
@ApiModel(value = "LmcWorkflowVariableDo entity对象", description = "流程表表单数据")
public class LmcWorkflowVariableDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "流程id")
    @TableField("WORKFLOW_ID")
    private Long workflowId;

    @ApiModelProperty(value = "表单id")
    @TableField("MAIN_ID")
    private Long mainId;

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

    @ApiModelProperty(value = "表单数据（json）")
    @TableField("FORM_DATA")
    private String formData;

    @ApiModelProperty(value = "流程发起数据（json）")
    @TableField("PROCESS_DATA")
    private String processData;

    @ApiModelProperty(value = "备注")
    @TableField("MEMO")
    private String memo;

}
