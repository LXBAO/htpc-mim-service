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
 * 项目中止
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectSuspensionDTO对象", description = "项目中止", discriminator = "projectSuspension")
@SearchBean(tables = "T_PROJECTSUSPENSION")
public class ProjectSuspensionDto implements Serializable {

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

    @ApiModelProperty(value = "项目名称")
    @DbField("PROJECTNAME")
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "项目编号")
    @DbField("PROJECTNO")
    // @Length(max = 255, message = "projectNo长度不在有效范围内")
    private String projectNo;

    @ApiModelProperty(value = "关联项目id")
    @DbField("PROJECTID")
    // @Length(max = 36, message = "projectId长度不在有效范围内")
    private String projectId;

    @ApiModelProperty(value = "中止人")
    @DbField("DISCONTINUER")
    // @Length(max = 255, message = "discontinuer长度不在有效范围内")
    private String discontinuer;

    @ApiModelProperty(value = "中止人编码")
    @DbField("DISCONTINUERNO")
    // @Length(max = 255, message = "discontinuerNo长度不在有效范围内")
    private String discontinuerNo;

    @ApiModelProperty(value = "中止时间")
    @DbField("SUSPENSIONTIME ")
    private String suspensionTime ;

    @ApiModelProperty(value = "中止原因")
    @DbField("REASONSUSPEND")
    // @Length(max = 500, message = "reasonSuspend长度不在有效范围内")
    private String reasonSuspend;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("GROUPFULLCODE")
    private String GroupFullCode;

}
