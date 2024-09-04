package com.uwdp.module.api.vo.dto;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.uwdp.module.api.vo.enums.ScoreTableFields;
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
@ApiModel(value = "ProjectEnableDTO对象", description = "项目赋能", discriminator = "projectEnable")
//@SearchBean(tables = "T_PROJECTENABLE")
@SearchBean(
        tables = "T_PROJECTENABLE p left join T_LMCWORKFLOW l on p.ID =  l.BIZID"
)
public class ProjectEnableDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("流程实例id")
    @DbField("l.PROCESSINSTANCEID")
    private String processInstanceId;

    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;

    @ApiModelProperty(value = "ID")
    @DbField("p.ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("p.CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("p.CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("p.UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("p.UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "关联项目名称")
    @DbField("p.PROJECTNAME")
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "关联项目id")
    @DbField("p.PROJECTID")
    // @Range(max = Long.MAX_VALUE, message = "projectId长度不在有效范围内")
    private Long projectId;

    @ApiModelProperty(value = "信息来源时间")
    @DbField("p.INFODATE")
    private LocalDateTime infoDate;

    @ApiModelProperty(value = "相关方联系人")
    @DbField("p.RELATEDLINKMAN")
    // @Length(max = 255, message = "relatedLinkman长度不在有效范围内")
    private String relatedLinkman;

    @ApiModelProperty(value = "分部牵头人")
    @DbField("p.DIVISIONLEADER")
    // @Length(max = 255, message = "divisionLeader长度不在有效范围内")
    private String divisionLeader;

    @ApiModelProperty(value = "分部参与人")
    @DbField("p.PARTICIPANT")
    // @Length(max = 2000, message = "participant长度不在有效范围内")
    private String participant;

    @ApiModelProperty(value = "责任板块公司")
    @DbField("p.RESPONSIBLECOMPANY")
    // @Length(max = 2000, message = "responsibleCompany长度不在有效范围内")
    private String responsibleCompany;

    @ApiModelProperty(value = "项目信息简介")
    @DbField("p.PROJECTINFO")
    // @Length(max = 2000, message = "projectInfo长度不在有效范围内")
    private String projectInfo;

    @ApiModelProperty(value = "主要合作关系说明")
    @DbField("p.COOPERATIONEXPLAIN")
    // @Length(max = 2000, message = "cooperationExplain长度不在有效范围内")
    private String cooperationExplain;

    @ApiModelProperty(value = "项目实现路径")
    @DbField("p.PROJECTWAY")
    // @Length(max = 1000, message = "projectWay长度不在有效范围内")
    private String projectWay;

    @ApiModelProperty(value = "评分")
    @DbField("p.SCORE")
    // @Range(max = Long.MAX_VALUE, message = "score长度不在有效范围内")
    private Double score;

    @ApiModelProperty(value = "建议")
    @DbField("p.SUGGEST")
    // @Length(max = 2000, message = "suggest长度不在有效范围内")
    private String suggest;

    @ApiModelProperty(value = "创建者名称")
    @DbField("p.CREATEDBYNAME")
    // @Length(max = 100, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "组织全编码")
    @DbField("p.GROUPFULLCODE")
    // @Length(max = 255, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "相关联系人编码")
    @DbField("p.RELATEDLINKMANNO")
    // @Length(max = 255, message = "relatedLinkmanNo长度不在有效范围内")
    private String relatedLinkmanNo;

    @ApiModelProperty(value = "分部牵头人编码")
    @DbField("p.DIVISIONLEADERNO")
    // @Length(max = 255, message = "divisionLeaderNo长度不在有效范围内")
    private String divisionLeaderNo;

    @ApiModelProperty(value = "分部参与人编码")
    @DbField("p.PARTICIPANTNO")
    // @Length(max = 2000, message = "participantNo长度不在有效范围内")
    private String participantNo;

    @ApiModelProperty(value = "责任板块公司编码")
    @DbField("p.RESPONSIBLECOMPANYNO")
    // @Length(max = 2000, message = "responsibleCompanyNo长度不在有效范围内")
    private String responsibleCompanyNo;

    @DbIgnore
    private List<ProjectEnableDetailDto> projectEnableDetailDto;

    @ApiModelProperty(value = "领导评分")
    private List<Map<ScoreTableFields,String>> scoreList;
}
