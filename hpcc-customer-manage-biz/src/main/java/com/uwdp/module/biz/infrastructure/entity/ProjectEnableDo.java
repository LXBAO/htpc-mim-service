package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目赋能
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PROJECTENABLE")
@ApiModel(value = "ProjectEnableDo entity对象", description = "项目赋能")
public class ProjectEnableDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("关联项目名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty("关联项目id")
    @TableField("PROJECTID")
    private Long projectId;

    @ApiModelProperty("信息来源时间")
    @TableField("INFODATE")
    private LocalDateTime infoDate;

    @ApiModelProperty("相关方联系人")
    @TableField("RELATEDLINKMAN")
    private String relatedLinkman;

    @ApiModelProperty("分部牵头人")
    @TableField("DIVISIONLEADER")
    private String divisionLeader;

    @ApiModelProperty("分部参与人")
    @TableField("PARTICIPANT")
    private String participant;

    @ApiModelProperty("责任板块公司")
    @TableField("RESPONSIBLECOMPANY")
    private String responsibleCompany;

    @ApiModelProperty("项目信息简介")
    @TableField("PROJECTINFO")
    private String projectInfo;

    @ApiModelProperty("主要合作关系说明")
    @TableField("COOPERATIONEXPLAIN")
    private String cooperationExplain;

    @ApiModelProperty("项目实现路径")
    @TableField("PROJECTWAY")
    private String projectWay;

    @ApiModelProperty("评分")
    @TableField("SCORE")
    private Double score;

    @ApiModelProperty("建议")
    @TableField("SUGGEST")
    private String suggest;

    @ApiModelProperty("领导评分")
    @TableField("scoreList")
    private String scoreList;

    @ApiModelProperty("创建者名称")
    @TableField("CREATEDBYNAME")
    private String createdByName;

    @ApiModelProperty("组织全编码")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty("相关联系人编码")
    @TableField("RELATEDLINKMANNO")
    private String relatedLinkmanNo;

    @ApiModelProperty("分部牵头人编码")
    @TableField("DIVISIONLEADERNO")
    private String divisionLeaderNo;

    @ApiModelProperty("分部参与人编码")
    @TableField("PARTICIPANTNO")
    private String participantNo;

    @ApiModelProperty("责任板块公司编码")
    @TableField("RESPONSIBLECOMPANYNO")
    private String responsibleCompanyNo;

}
