package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 企查查记录
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "EnterpriseCheckRecordDo Query对象", description = "企查查记录", discriminator = "enterpriseCheckRecord")
public class EnterpriseCheckRecordQuery extends BasePageQuery {

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

    @ApiModelProperty(value = "主键")
    private String keyNo;

    @ApiModelProperty(value = "企业名称")
    private String name;

    @ApiModelProperty(value = "统一社会信用代码")
    private String creditCode;

    @ApiModelProperty(value = "成立日期")
    private String startDate;

    @ApiModelProperty(value = "法定代表人姓名")
    private String operName;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "注册号")
    private String no;

    @ApiModelProperty(value = "注册地址")
    private String address;
}
