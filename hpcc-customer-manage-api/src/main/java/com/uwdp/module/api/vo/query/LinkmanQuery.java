package com.uwdp.module.api.vo.query;

import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 联系人
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LinkmanDo Query对象", description = "联系人", discriminator = "linkman")
public class LinkmanQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "岗位")
    private String job;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "投标id")
    private String biddingId;

    @ApiModelProperty(value = "项目经理id")
    private String nameId;

    @ApiModelProperty(value = "关联人资id")
    private String hrId;
}
