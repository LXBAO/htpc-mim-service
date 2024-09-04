package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import com.gientech.lcds.workflow.sdk.core.runtime.CandidateInfoDto;
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
@ApiModel(value = "ProjectImplementAddCmd对象", description = "项目实施", discriminator = "projectImplement")
public class ProjectImplementAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
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

    @ApiModelProperty(value = "项目名称")
    // @Length(max = 200, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "项目状态")
    // @Length(max = 32, message = "projectState长度不在有效范围内")
    private String projectState;

    @ApiModelProperty(value = "开工时间")
    private LocalDateTime workDate;

    @ApiModelProperty(value = "投产时间")
    private LocalDateTime commissioningDate;

    @ApiModelProperty(value = "未开工原因")
    // @Length(max = 200, message = "nonWorkingCause长度不在有效范围内")
    private String nonWorkingCause;

    @ApiModelProperty(value = "项目阶段")
    // @Length(max = 100, message = "projectStage长度不在有效范围内")
    private String projectStage;

    @ApiModelProperty(value = "项目编号")
    // @Length(max = 200, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "登记单位")
    // @Length(max = 100, message = "registrationUnit长度不在有效范围内")
    private String registrationUnit;

    @ApiModelProperty(value = "产品领域类别")
    // @Length(max = 200, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "登记时间")
    private String registerDate;

    @ApiModelProperty(value = "附件")
    // @Length(max = 200, message = "file长度不在有效范围内")
    private String file;

    @ApiModelProperty(value = "创建者名称")
    // @Length(max = 100, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "权限id")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "工作流指定审批人")
    protected Map<String, List<CandidateInfoDto>> approveUser;

    @ApiModelProperty(value = "入档状态")
    // @Length(max = 200, message = "inGear长度不在有效范围内")
    private String inGear;


    //附件表
    List<AttachmentAddCmd> addCmdList;
}
