package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 证书信息
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CerInfoDo Query对象", description = "证书信息", discriminator = "cerInfo")
public class CerInfoQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "关联认证平台id")
    private String fdCaId;

    @ApiModelProperty(value = "证书名称")
    private String fdCertificateName;

    @ApiModelProperty(value = "证书编号")
    private String fdCertificateNo;

    @ApiModelProperty(value = "附件")
    private String file;

    @ApiModelProperty(value = "认证时间")
    private LocalDateTime certificationDate;

    @ApiModelProperty(value = "到期时间")
    private LocalDateTime expireDate;

    @ApiModelProperty(value = "关联认证平台名称")
    private String fdCaName;
}
