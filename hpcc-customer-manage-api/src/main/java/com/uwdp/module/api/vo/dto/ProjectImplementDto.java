package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
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
 * 项目实施
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectImplementDTO对象", description = "项目实施", discriminator = "projectImplement")
@SearchBean(
        tables = "T_PROJECTIMPLEMENT p left join T_PROJECTRECORDS r on p.PROJECTNUMBER = r.ID left join T_LMCWORKFLOW l on p.ID =  l.BIZID"
)
public class ProjectImplementDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否国际项目")
    @DbField("r.ISFORIEN")
    // @Length(max = 100, message = "isForien长度不在有效范围内")
    private String isForien;

    @ApiModelProperty("流程实例id")
    @DbField("l.PROCESSINSTANCEID")
    private String processInstanceId;

    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;

    @ApiModelProperty(value = "主键")
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

    @ApiModelProperty(value = "项目名称")
    @DbField("p.PROJECTNAME")
    // @Length(max = 100, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "项目状态")
    @DbField("p.PROJECTSTATE")
    // @Length(max = 32, message = "projectState长度不在有效范围内")
    private String projectState;

    @ApiModelProperty(value = "开工时间")
    @DbField("p.WORKDATE")
    private LocalDateTime workDate;

    @ApiModelProperty(value = "投产时间")
    @DbField("p.COMMISSIONINGDATE")
    private LocalDateTime commissioningDate;

    @ApiModelProperty(value = "未开工原因")
    @DbField("p.NONWORKINGCAUSE")
    // @Length(max = 200, message = "nonWorkingCause长度不在有效范围内")
    private String nonWorkingCause;

    @ApiModelProperty(value = "项目阶段")
    @DbField("p.PROJECTSTAGE")
    // @Length(max = 100, message = "projectStage长度不在有效范围内")
    private String projectStage;

    @ApiModelProperty(value = "项目编号")
    @DbField("p.PROJECTNUMBER")
    // @Length(max = 200, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "登记单位")
    @DbField("p.REGISTRATIONUNIT")
    // @Length(max = 100, message = "registrationUnit长度不在有效范围内")
    private String registrationUnit;

    @ApiModelProperty(value = "产品领域类别")
    @DbField("p.INDUSTRYCATEGORY")
    // @Length(max = 200, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "登记时间")
    @DbField("p.REGISTERDATE")
    private String registerDate;

    @ApiModelProperty(value = "附件")
    @DbField("p.FILE")
    // @Length(max = 200, message = "file长度不在有效范围内")
    private String file;

    @ApiModelProperty(value = "创建者名称")
    @DbField("p.CREATEDBYNAME")
    // @Length(max = 100, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "权限id")
    @DbField("p.GROUPFULLCODE")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "入档状态")
    // @Length(max = 200, message = "inGear长度不在有效范围内")
    @DbField("p.INGEAR")
    private String inGear;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("r.INTASSISTANCEUNIT")
    // @Length(max = 255)
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("r.INTASSISTANCEUNITNAME")
    // @Length(max = 255)
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "所属区域", required = true)
    @DbField("r.OWNINGREGION")
    // @Length(max = 255, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    //附件表
    @DbIgnore
    List<AttachmentDto> attachmentDtos;

}
