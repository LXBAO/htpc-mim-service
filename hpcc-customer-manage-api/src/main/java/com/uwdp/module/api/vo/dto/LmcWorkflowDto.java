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
 * 流程表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LmcWorkflowDTO对象", description = "流程表", discriminator = "LmcWorkflow")
@SearchBean(tables = "T_LMCWORKFLOW")
public class LmcWorkflowDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

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

    @ApiModelProperty(value = "业务类型")
    @DbField("BIZCODE")
    // @Length(max = 50, message = "bizCode长度不在有效范围内")
    private String bizCode;

    @ApiModelProperty(value = "业务唯一标识")
    @DbField("BIZID")
    // @Length(max = 36, message = "bizId长度不在有效范围内")
    private String bizId;

    @ApiModelProperty(value = "流程编码")
    @DbField("PROCESSCODE")
    // @Length(max = 50, message = "processCode长度不在有效范围内")
    private String processCode;

    @ApiModelProperty(value = "流程实例id", required = true)
    @DbField("PROCESSINSTANCEID")
    // @Length(max = 36, message = "processInstanceId长度不在有效范围内")
    private String processInstanceId;

    @ApiModelProperty(value = "流程标题")
    @DbField("TITLE")
    // @Length(max = 255, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "AppId")
    @DbField("APPID")
    // @Length(max = 50, message = "appId长度不在有效范围内")
    private String appId;

    @ApiModelProperty(value = "发起人编码")
    @DbField("SUBMITTER_CODE")
    // @Length(max = 50, message = "submitterCode长度不在有效范围内")
    private String submitterCode;

    @ApiModelProperty(value = "发起人名称")
    @DbField("SUBMITTERNAME")
    // @Length(max = 100, message = "submitterName长度不在有效范围内")
    private String submitterName;

    @ApiModelProperty(value = "发起时间")
    @DbField("SUBMITTIME")
    private LocalDateTime submitTime;

    @ApiModelProperty(value = "流程结束时间")
    @DbField("ENDTIME")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "流程状态(数据字典)")
    @DbField("WORKFLOWSTATUS")
    // @Length(max = 20, message = "workflowStatus长度不在有效范围内")
    private String workflowStatus;

    @ApiModelProperty(value = "操作原因")
    @DbField("REASON")
    // @Length(max = 1024, message = "reason长度不在有效范围内")
    private String reason;

    @ApiModelProperty(value = "发起序号")
    @DbField("SORTNUMBER")
    // @Range(max = Long.MAX_VALUE, message = "sortNumber长度不在有效范围内")
    private Integer sortNumber;

    @ApiModelProperty(value = "数据版本号 , 默认为1 , 变更一次 , 加1")
    @DbField("VERSION")
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private Long version;

    @ApiModelProperty(value = "是否删除(0: 否; 1: 是)")
    @DbField("ISDELETED")
    // @Range(max = 10L, message = "isDeleted长度不在有效范围内")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建人编码")
    @DbField("CREATEUSERCODE")
    // @Length(max = 50, message = "createUserCode长度不在有效范围内")
    private String createUserCode;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEUSERNAME")
    // @Length(max = 100, message = "createUserName长度不在有效范围内")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATETIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人编码")
    @DbField("UPDATEUSERCODE")
    // @Length(max = 50, message = "updateUserCode长度不在有效范围内")
    private String updateUserCode;

    @ApiModelProperty(value = "更新人名称")
    @DbField("UPDATEUSERNAME")
    // @Length(max = 100, message = "updateUserName长度不在有效范围内")
    private String updateUserName;

    @ApiModelProperty(value = "更新时间", required = true)
    @DbField("UPDATETIME")
    private LocalDateTime updateTime;

}
