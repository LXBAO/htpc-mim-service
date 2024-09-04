package com.uwdp.module.api.vo.dto;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
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
@ApiModel(value = "CerInfoDTO对象", description = "证书信息", discriminator = "cerInfo")
@SearchBean(tables = "T_CERINFO")
public class CerInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "关联认证平台id")
    @DbField("FDCAID")
    // @Length(max = 200, message = "fdCaId长度不在有效范围内")
    private String fdCaId;

    @ApiModelProperty(value = "证书名称")
    @DbField("FDCERTIFICATENAME")
    // @Length(max = 255, message = "fdCertificateName长度不在有效范围内")
    private String fdCertificateName;

    @ApiModelProperty(value = "证书编号")
    @DbField("FDCERTIFICATENO")
    // @Length(max = 255, message = "fdCertificateNo长度不在有效范围内")
    private String fdCertificateNo;

    @ApiModelProperty(value = "附件")
    @DbField("FILE")
    // @Length(max = 500, message = "file长度不在有效范围内")
    private String file;

    @ApiModelProperty(value = "认证时间")
    @DbField("CERTIFICATIONDATE")
    private String certificationDate;

    @ApiModelProperty(value = "到期时间")
    @DbField("EXPIREDATE")
    private String expireDate;

    @ApiModelProperty(value = "关联认证平台名称")
    @DbField("FDCANAME")
    // @Length(max = 255, message = "fdCaName长度不在有效范围内")
    private String fdCaName;

    //附件表
    @DbIgnore
    List<AttachmentDto> attachmentDtos;

}
