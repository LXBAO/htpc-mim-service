package com.uwdp.module.api.vo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
@ApiModel(value = "ProjectEnableExcelExport对象", description = "项目赋能", discriminator = "projectEnable")
@SearchBean(tables = "T_PROJECTENABLE")
@EqualsAndHashCode(callSuper = false)
public class ProjectEnableExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"ID"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private String id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 1)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 2)
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新者"}, index = 3)
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 4)
    private String updatedTime;

    @ApiModelProperty(value = "关联项目名称")
    @DbField("PROJECTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联项目名称"}, index = 5)
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "关联项目id")
    @DbField("PROJECTID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联项目id"}, index = 6)
    // @Range(max = Long.MAX_VALUE, message = "projectId长度不在有效范围内")
    private String projectId;

    @ApiModelProperty(value = "信息来源时间")
    @DbField("INFODATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"信息来源时间"}, index = 7)
    private String infoDate;

    @ApiModelProperty(value = "相关方联系人")
    @DbField("RELATEDLINKMAN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"相关方联系人"}, index = 8)
    // @Length(max = 255, message = "relatedLinkman长度不在有效范围内")
    private String relatedLinkman;

    @ApiModelProperty(value = "分部牵头人")
    @DbField("DIVISIONLEADER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"分部牵头人"}, index = 9)
    // @Length(max = 255, message = "divisionLeader长度不在有效范围内")
    private String divisionLeader;

    @ApiModelProperty(value = "分部参与人")
    @DbField("PARTICIPANT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"分部参与人"}, index = 10)
    // @Length(max = 2000, message = "participant长度不在有效范围内")
    private String participant;

    @ApiModelProperty(value = "责任板块公司")
    @DbField("RESPONSIBLECOMPANY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"责任板块公司"}, index = 11)
    // @Length(max = 2000, message = "responsibleCompany长度不在有效范围内")
    private String responsibleCompany;

    @ApiModelProperty(value = "项目信息简介")
    @DbField("PROJECTINFO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目信息简介"}, index = 12)
    // @Length(max = 2000, message = "projectInfo长度不在有效范围内")
    private String projectInfo;

    @ApiModelProperty(value = "主要合作关系说明")
    @DbField("COOPERATIONEXPLAIN")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要合作关系说明"}, index = 13)
    // @Length(max = 2000, message = "cooperationExplain长度不在有效范围内")
    private String cooperationExplain;

    @ApiModelProperty(value = "项目实现路径")
    @DbField("PROJECTWAY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"项目实现路径"}, index = 14)
    // @Length(max = 1000, message = "projectWay长度不在有效范围内")
    private String projectWay;

    @ApiModelProperty(value = "评分")
    @DbField("SCORE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"评分"}, index = 15)
    // @Range(max = Long.MAX_VALUE, message = "score长度不在有效范围内")
    private String score;

    @ApiModelProperty(value = "建议")
    @DbField("SUGGEST")
    @ColumnWidth(16)
    @ExcelProperty(value = {"建议"}, index = 16)
    // @Length(max = 2000, message = "suggest长度不在有效范围内")
    private String suggest;

    @ApiModelProperty(value = "创建者名称")
    @DbField("CREATEDBYNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 17)
    // @Length(max = 100, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "组织全编码")
    @DbField("GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织全编码"}, index = 18)
    // @Length(max = 255, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "相关联系人编码")
    @DbField("RELATEDLINKMANNO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"相关联系人编码"}, index = 19)
    // @Length(max = 255, message = "relatedLinkmanNo长度不在有效范围内")
    private String relatedLinkmanNo;

    @ApiModelProperty(value = "分部牵头人编码")
    @DbField("DIVISIONLEADERNO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"分部牵头人编码"}, index = 20)
    // @Length(max = 255, message = "divisionLeaderNo长度不在有效范围内")
    private String divisionLeaderNo;

    @ApiModelProperty(value = "分部参与人编码")
    @DbField("PARTICIPANTNO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"分部参与人编码"}, index = 21)
    // @Length(max = 2000, message = "participantNo长度不在有效范围内")
    private String participantNo;

    @ApiModelProperty(value = "责任板块公司编码")
    @DbField("RESPONSIBLECOMPANYNO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"责任板块公司编码"}, index = 22)
    // @Length(max = 2000, message = "responsibleCompanyNo长度不在有效范围内")
    private String responsibleCompanyNo;


}
