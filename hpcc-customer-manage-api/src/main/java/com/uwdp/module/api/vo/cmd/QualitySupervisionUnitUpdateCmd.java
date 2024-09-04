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

/**
 * <p>
 * 质量监督单位
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "QualitySupervisionUnit UpdateCmd对象", description = "质量监督单位", discriminator = "qualitySupervisionUnit")
public class QualitySupervisionUnitUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识", required = true)
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

    @ApiModelProperty(value = "关联业绩id")
    // @Range(max = Long.MAX_VALUE, message = "performanceId长度不在有效范围内")
    private Long performanceId;

    @ApiModelProperty(value = "名称")
    // @Length(max = 32, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "地址")
    // @Length(max = 32, message = "address长度不在有效范围内")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    // @Length(max = 32, message = "postalCode长度不在有效范围内")
    private String postalCode;

    @ApiModelProperty(value = "联系人")
    // @Length(max = 32, message = "contactPerson长度不在有效范围内")
    private String contactPerson;

    @ApiModelProperty(value = "电话")
    // @Length(max = 32, message = "phone长度不在有效范围内")
    private String phone;


}
