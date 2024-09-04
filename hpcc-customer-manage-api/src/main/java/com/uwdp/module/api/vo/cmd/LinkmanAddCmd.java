package com.uwdp.module.api.vo.cmd;

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

/**
 * <p>
 * 联系人
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LinkmanAddCmd对象", description = "联系人", discriminator = "linkman")
public class LinkmanAddCmd implements Serializable {

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

    @ApiModelProperty(value = "岗位")
    // @Length(max = 100, message = "job长度不在有效范围内")
    private String job;

    @ApiModelProperty(value = "姓名")
    // @Length(max = 100, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "状态")
    // @Length(max = 100, message = "state长度不在有效范围内")
    private String state;

    @ApiModelProperty(value = "投标id")
    // @Length(max = 100, message = "biddingId长度不在有效范围内")
    private String biddingId;

    @ApiModelProperty(value = "项目经理id")
    // @Length(max = 100, message = "nameId长度不在有效范围内")
    private String nameId;

    @ApiModelProperty(value = "关联人资id")
    // @Length(max = 100, message = "hrId长度不在有效范围内")
    private String hrId;
}
