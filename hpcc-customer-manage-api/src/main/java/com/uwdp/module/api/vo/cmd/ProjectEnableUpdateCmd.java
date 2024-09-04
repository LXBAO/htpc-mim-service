package com.uwdp.module.api.vo.cmd;

import com.uwdp.module.api.vo.enums.ScoreTableFields;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目赋能
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectEnable UpdateCmd对象", description = "项目赋能", discriminator = "projectEnable")
public class ProjectEnableUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID", required = true)
    @NotNull(message = "主键不能为空")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "关联项目名称")
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "关联项目id")
    // @Range(max = Long.MAX_VALUE, message = "projectId长度不在有效范围内")
    private Long projectId;

    @ApiModelProperty(value = "信息来源时间")
    private LocalDateTime infoDate;

    @ApiModelProperty(value = "相关方联系人")
    // @Length(max = 255, message = "relatedLinkman长度不在有效范围内")
    private String relatedLinkman;

    @ApiModelProperty(value = "分部牵头人")
    // @Length(max = 255, message = "divisionLeader长度不在有效范围内")
    private String divisionLeader;

    @ApiModelProperty(value = "分部参与人")
    // @Length(max = 2000, message = "participant长度不在有效范围内")
    private String participant;

    @ApiModelProperty(value = "责任板块公司")
    // @Length(max = 2000, message = "responsibleCompany长度不在有效范围内")
    private String responsibleCompany;

    @ApiModelProperty(value = "项目信息简介")
    // @Length(max = 2000, message = "projectInfo长度不在有效范围内")
    private String projectInfo;

    @ApiModelProperty(value = "主要合作关系说明")
    // @Length(max = 2000, message = "cooperationExplain长度不在有效范围内")
    private String cooperationExplain;

    @ApiModelProperty(value = "项目实现路径")
    // @Length(max = 1000, message = "projectWay长度不在有效范围内")
    private String projectWay;

    @ApiModelProperty(value = "评分")
    // @Range(max = Long.MAX_VALUE, message = "score长度不在有效范围内")
    private Double score;

    @ApiModelProperty(value = "建议")
    // @Length(max = 2000, message = "suggest长度不在有效范围内")
    private String suggest;

    @ApiModelProperty(value = "创建者名称")
    // @Length(max = 100, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "组织全编码")
    // @Length(max = 255, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "相关联系人编码")
    // @Length(max = 255, message = "relatedLinkmanNo长度不在有效范围内")
    private String relatedLinkmanNo;

    @ApiModelProperty(value = "分部牵头人编码")
    // @Length(max = 255, message = "divisionLeaderNo长度不在有效范围内")
    private String divisionLeaderNo;

    @ApiModelProperty(value = "分部参与人编码")
    // @Length(max = 2000, message = "participantNo长度不在有效范围内")
    private String participantNo;

    @ApiModelProperty(value = "责任板块公司编码")
    // @Length(max = 2000, message = "responsibleCompanyNo长度不在有效范围内")
    private String responsibleCompanyNo;

    @ApiModelProperty(value = "明细")
    private List<ProjectEnableDetailAddCmd> projectEnableDetailList;

    @ApiModelProperty(value = "领导评分")
    private List<Map<ScoreTableFields,String>> scoreList;
}
