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
 * 下拉列表维护
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "DataDTO对象", description = "下拉列表维护", discriminator = "data")
@SearchBean(tables = "T_DATA")
public class DataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @DbField("ID")
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

    @ApiModelProperty(value = "父级ID")
    @DbField("PARENT_ID")
    // @Range(max = Long.MAX_VALUE, message = "parentId长度不在有效范围内")
    private Integer parentId;

    @ApiModelProperty(value = "类型")
    @DbField("TYPE")
    // @Range(max = Long.MAX_VALUE, message = "type长度不在有效范围内")
    private Integer type;

    @ApiModelProperty(value = "类型名称")
    @DbField("TYPENAME")
    // @Length(max = 255, message = "typeName长度不在有效范围内")
    private String typeName;

    @ApiModelProperty(value = "排序")
    @DbField("REMARK")
    // @Range(max = Long.MAX_VALUE, message = "rank长度不在有效范围内")
    private Integer remark;

    @ApiModelProperty(value = "值")
    @DbField("VALUE")
    // @Length(max = 255, message = "value长度不在有效范围内")
    private String value;

}
