package com.uwdp.module.api.vo.cmd;

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

/**
 * <p>
 * 证书信息
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "CerInfo UpdateCmd对象", description = "证书信息", discriminator = "cerInfo")
public class CerInfoUpdateCmd implements Serializable {

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

    @ApiModelProperty(value = "关联认证平台id")
    // @Length(max = 200, message = "fdCaId长度不在有效范围内")
    private String fdCaId;

    @ApiModelProperty(value = "证书名称")
    // @Length(max = 255, message = "fdCertificateName长度不在有效范围内")
    private String fdCertificateName;

    @ApiModelProperty(value = "证书编号")
    // @Length(max = 255, message = "fdCertificateNo长度不在有效范围内")
    private String fdCertificateNo;

    @ApiModelProperty(value = "附件")
    // @Length(max = 500, message = "file长度不在有效范围内")
    private String file;

    @ApiModelProperty(value = "认证时间")
    private String certificationDate;

    @ApiModelProperty(value = "到期时间")
    private String expireDate;

    @ApiModelProperty(value = "关联认证平台名称")
    // @Length(max = 255, message = "fdCaName长度不在有效范围内")
    private String fdCaName;

    //附件表
    List<AttachmentAddCmd> addCmdList;


}
