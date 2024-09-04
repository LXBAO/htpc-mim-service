package com.uwdp.module.api.vo.dto;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
@ToString
@ApiModel(value = "PromptDTO对象", description = "信息提示", discriminator = "prompt")
@SearchBean(tables = "T_PROMPT")
public class PromptDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATEDNAME")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

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

    @ApiModelProperty(value = "提示信息标题  ")
    @DbField("PROMPTTITLE")
    // @Length(max = 255, message = "promptTitle长度不在有效范围内")
    private String promptTitle;

    @ApiModelProperty(value = "提示信息详情")
    @DbField("PROMPTDETAILS")
    // @Length(max = 255, message = "promptDetails长度不在有效范围内")
    private String promptDetails;

    @ApiModelProperty(value = "过期时间")
    @DbField("EXPIREDATE")
    private LocalDate expireDate;

    @ApiModelProperty(value = "需要提示的工号")
    @DbField("PROMPTID")
    // @Length(max = 100, message = "promptId长度不在有效范围内")
    private String promptId;

    @ApiModelProperty(value = "已读/未读")
    @DbField("PROMPTSTATUS")
    // @Length(max = 100, message = "promptStatus长度不在有效范围内")
    private String promptStatus;

    @ApiModelProperty(value = "删除/未删")
    @DbField("DELETESTATUS")
    // @Length(max = 100, message = "deleteStatus长度不在有效范围内")
    private String deleteStatus;

    @ApiModelProperty(value = "QID")
    @DbField("QID")
    // @Range(max = Long.MAX_VALUE, message = "qid长度不在有效范围内")
    private Long qid;


    @ApiModelProperty(value = "跳转路径")
    @DbField("PROMPTPATH")
    // @Length(max = 255, message = "promptPath长度不在有效范围内")
    private String promptPath;



}
