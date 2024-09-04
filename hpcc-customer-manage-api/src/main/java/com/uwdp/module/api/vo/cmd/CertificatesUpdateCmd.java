package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 荣誉证书
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "Certificates UpdateCmd对象", description = "荣誉证书", discriminator = "certificates")
public class CertificatesUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = true)
    @NotNull(message = "主键不能为空")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "获奖单位或项目名称")
    // @Length(max = 100, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "荣誉称号或奖项名称")
    // @Length(max = 200, message = "honoraryTitle长度不在有效范围内")
    private String honoraryTitle;

    @ApiModelProperty(value = "授奖单位")
    // @Length(max = 200, message = "awardingUnit长度不在有效范围内")
    private String awardingUnit;

    @ApiModelProperty(value = "授奖时间")
    private String awardTime;

    @ApiModelProperty(value = "附件")
    // @Length(max = 200, message = "file长度不在有效范围内")
    private String file;

    @ApiModelProperty(value = "文号")
    // @Length(max = 100, message = "referenceNumber长度不在有效范围内")
    private String referenceNumber;

    @ApiModelProperty(value = "电子文档序号")
    // @Length(max = 100, message = "documentNumber长度不在有效范围内")
    private String documentNumber;

    @ApiModelProperty(value = "是否报党建部")
    // @Length(max = 200, message = "takebe长度不在有效范围内")
    private String takebe;

    @ApiModelProperty(value = "备注")
    // @Length(max = 200, message = "remark长度不在有效范围内")
    private String remark;

    @ApiModelProperty(value = "权限id")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    //附件表
    List<AttachmentAddCmd> addCmdList;
}
