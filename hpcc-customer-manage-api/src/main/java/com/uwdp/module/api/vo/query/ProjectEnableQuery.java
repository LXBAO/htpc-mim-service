package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 项目赋能
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProjectEnableDo Query对象", description = "项目赋能", discriminator = "projectEnable")
public class ProjectEnableQuery extends BasePageQuery {

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
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "关联项目名称")
    private String projectName;

    @ApiModelProperty(value = "关联项目id")
    private Long projectId;

    @ApiModelProperty(value = "信息来源时间")
    private LocalDateTime infoDate;

    @ApiModelProperty(value = "相关方联系人")
    private String relatedLinkman;

    @ApiModelProperty(value = "分部牵头人")
    private String divisionLeader;

    @ApiModelProperty(value = "分部参与人")
    private String participant;

    @ApiModelProperty(value = "责任板块公司")
    private String responsibleCompany;

    @ApiModelProperty(value = "项目信息简介")
    private String projectInfo;

    @ApiModelProperty(value = "主要合作关系说明")
    private String cooperationExplain;

    @ApiModelProperty(value = "项目实现路径")
    private String projectWay;

    @ApiModelProperty(value = "评分")
    private Double score;

    @ApiModelProperty(value = "建议")
    private String suggest;

    @ApiModelProperty(value = "创建者名称")
    private String createdByName;

    @ApiModelProperty(value = "组织全编码")
    private String groupFullCode;

    @ApiModelProperty(value = "相关联系人编码")
    private String relatedLinkmanNo;

    @ApiModelProperty(value = "分部牵头人编码")
    private String divisionLeaderNo;

    @ApiModelProperty(value = "分部参与人编码")
    private String participantNo;

    @ApiModelProperty(value = "责任板块公司编码")
    private String responsibleCompanyNo;
}
