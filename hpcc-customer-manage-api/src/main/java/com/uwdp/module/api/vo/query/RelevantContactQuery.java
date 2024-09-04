package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 客户相关联系人
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RelevantContactDo Query对象", description = "客户相关联系人", discriminator = "relevantContact")
public class RelevantContactQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "关联客户ID", required = true)
    private Long clientId;

    @ApiModelProperty(value = "联系人姓名", required = true)
    private String name;

    @ApiModelProperty(value = "联系人职位")
    private String position ;

    @ApiModelProperty(value = "联系方式", required = true)
    private String contact;

    @ApiModelProperty(value = "联系地址")
    private String contactAddress;
}
