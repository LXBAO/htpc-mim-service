package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 荣誉证书
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CertificatesDo Query对象", description = "荣誉证书", discriminator = "certificates")
public class CertificatesQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "获奖单位或项目名称")
    private String projectName;

    @ApiModelProperty(value = "荣誉称号或奖项名称")
    private String honoraryTitle;

    @ApiModelProperty(value = "授奖单位")
    private String awardingUnit;

    @ApiModelProperty(value = "授奖时间")
    private String awardTime;

    @ApiModelProperty(value = "附件")
    private String file;

    @ApiModelProperty(value = "文号")
    private String referenceNumber;

    @ApiModelProperty(value = "电子文档序号")
    private String documentNumber;

    @ApiModelProperty(value = "是否报党建部")
    private String takebe;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "权限id")
    private String groupFullCode;

    @ApiModelProperty(value = "创建人名称")
    private String createdName;
}
