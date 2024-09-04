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
import java.time.LocalDateTime;

/**
 * <p>
 * 项目赋能明细
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectEnableDetailDTO对象", description = "项目赋能明细", discriminator = "projectEnableDetail")
@SearchBean(tables = "T_PROJECTENABLEDETAIL")
public class ProjectEnableDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
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

    @ApiModelProperty(value = "主文档id")
    @DbField("PARENTID")
    // @Range(max = Long.MAX_VALUE, message = "parentId长度不在有效范围内")
    private Long parentId;

    @ApiModelProperty(value = "跟踪日期")
    @DbField("TRACKDATE")
    private LocalDateTime trackDate;

    @ApiModelProperty(value = "内容")
    @DbField("CONTENT")
    // @Length(max = 2000, message = "content长度不在有效范围内")
    private String content;

    @ApiModelProperty(value = "给予板块公司市场赋能说明")
    @DbField("ENABLEEXPLAIN")
    // @Length(max = 2000, message = "enableExplain长度不在有效范围内")
    private String enableExplain;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATEDBYNAME")
    // @Length(max = 100, message = "createdByName长度不在有效范围内")
    private String createdByName;

}
