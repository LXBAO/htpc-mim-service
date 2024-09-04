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
 * 荣誉证书
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "CertificatesDTO对象", description = "荣誉证书", discriminator = "certificates")
@SearchBean(tables = "T_CERTIFICATES")
public class CertificatesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEDNAME")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

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

    @ApiModelProperty(value = "获奖单位或项目名称")
    @DbField("PROJECTNAME")
    // @Length(max = 100, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "荣誉称号或奖项名称")
    @DbField("HONORARYTITLE")
    // @Length(max = 200, message = "honoraryTitle长度不在有效范围内")
    private String honoraryTitle;

    @ApiModelProperty(value = "授奖单位")
    @DbField("AWARDINGUNIT")
    // @Length(max = 200, message = "awardingUnit长度不在有效范围内")
    private String awardingUnit;

    @ApiModelProperty(value = "授奖时间")
    @DbField("AWARDTIME")
    private String awardTime;

    @ApiModelProperty(value = "附件")
    @DbField("FILE")
    // @Length(max = 200, message = "file长度不在有效范围内")
    private String file;

    @ApiModelProperty(value = "文号")
    @DbField("REFERENCENUMBER")
    // @Length(max = 100, message = "referenceNumber长度不在有效范围内")
    private String referenceNumber;

    @ApiModelProperty(value = "电子文档序号")
    @DbField("DOCUMENTNUMBER")
    // @Length(max = 100, message = "documentNumber长度不在有效范围内")
    private String documentNumber;

    @ApiModelProperty(value = "是否报党建部")
    @DbField("TAKEBE")
    // @Length(max = 200, message = "takebe长度不在有效范围内")
    private String takebe;

    @ApiModelProperty(value = "备注")
    @DbField("REMARK")
    // @Length(max = 200, message = "remark长度不在有效范围内")
    private String remark;

    @ApiModelProperty(value = "权限id")
    @DbField("GROUPFULLCODE")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    //附件表
    @DbIgnore
    List<AttachmentDto> attachmentDtos;
}
