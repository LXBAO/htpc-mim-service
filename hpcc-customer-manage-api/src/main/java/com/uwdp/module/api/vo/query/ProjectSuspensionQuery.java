package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * <p>
 * 项目中止
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProjectSuspensionDo Query对象", description = "项目中止", discriminator = "projectSuspension")
public class ProjectSuspensionQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private String updatedTime;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "关联项目id")
    private String projectId;

    @ApiModelProperty(value = "中止人")
    private String discontinuer;

    @ApiModelProperty(value = "中止人编码")
    private String discontinuerNo;

    @ApiModelProperty(value = "中止时间")
    private String suspensionTime ;

    @ApiModelProperty(value = "中止原因")
    private String reasonSuspend;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    private String GroupFullCode;
}
