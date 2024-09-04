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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 标段
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "SectionDTO对象", description = "标段", discriminator = "section")
@SearchBean(tables = "T_SECTION")
public class SectionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
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

    @ApiModelProperty(value = "标段名称")
    @DbField("SECTIONNAME")
    // @Length(max = 255, message = "sectionName长度不在有效范围内")
    private String sectionName;

    @ApiModelProperty(value = "拦标价/预计金额（万元）")
    @DbField("ESTIMATEDAMOUNT")
    // @Length(max = 50, message = "estimatedAmount长度不在有效范围内")
    private String estimatedAmount;

    @ApiModelProperty(value = "标段内容")
    @DbField("SECTIONCONTENT")
    // @Length(max = 255, message = "sectionContent长度不在有效范围内")
    private String sectionContent;

    @ApiModelProperty(value = "项目id")
    @DbField("PROJECTID")
    // @Length(max = 100, message = "projectId长度不在有效范围内")
    private String projectId;

    @ApiModelProperty(value = "预计中标概况")
    @DbField("bidProbability")
    // @Length(max = 100, message = "bidProbability长度不在有效范围内")
    private String bidProbability;

}
