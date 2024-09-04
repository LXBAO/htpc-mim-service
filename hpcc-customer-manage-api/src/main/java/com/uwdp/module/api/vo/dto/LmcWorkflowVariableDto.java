package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uwdp.module.api.vo.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
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
@ToString
@ApiModel(value = "LmcWorkflowVariableDTO对象", description = "流程表表单数据", discriminator = "LmcWorkflowVariable")
@SearchBean(tables = "T_LMCWORKFLOWVARIABLE")
public class LmcWorkflowVariableDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "流程id")
    @DbField("WORKFLOW_ID")
    private Long workflowId;

    @ApiModelProperty(value = "表单id")
    @DbField("MAIN_ID")
    private Long mainId;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "表单数据（json）")
    @DbField("FORM_DATA")
    private String formData;

    @ApiModelProperty(value = "流程发起数据（json）")
    @DbField("PROCESS_DATA")
    private String processData;

    @ApiModelProperty(value = "备注")
    @DbField("MEMO")
    private String memo;

}
