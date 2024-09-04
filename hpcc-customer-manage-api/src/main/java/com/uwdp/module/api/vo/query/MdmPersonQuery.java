package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 主数据-人员
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MdmPersonDo Query对象", description = "主数据-人员", discriminator = "mdmPerson")
public class MdmPersonQuery extends BasePageQuery {

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

    @ApiModelProperty(value = "姓名（集团）")
    private String personName;

    @ApiModelProperty(value = "主数据id")
    private Long mdmId;

    @ApiModelProperty(value = "所属岗位编码（公司）")
    private String belongPostCode;

    @ApiModelProperty(value = "所属部门编码（公司）")
    private String belongDepartmentCode;

    @ApiModelProperty(value = "所属单位编码（公司）")
    private String belongUnitCode;

    @ApiModelProperty(value = "证件号码（集团）")
    private String certificateNo;

    @ApiModelProperty(value = "证件类型名称（集团）")
    private String certificateType;

    @ApiModelProperty(value = "出生日期（公司）")
    private String birthday;

    @ApiModelProperty(value = "所属部门编码（集团）")
    private String groupBelongDepartmentCode;

    @ApiModelProperty(value = "所属部门名称（集团）")
    private String groupBelongDepartmentName;

    @ApiModelProperty(value = "所属部门内码（集团）")
    private String groupBelongDepartmentUuid;

    @ApiModelProperty(value = "所属岗位编码（集团）")
    private String groupBelongPostCode;

    @ApiModelProperty(value = "所属岗位名称（集团）")
    private String groupBelongPostName;

    @ApiModelProperty(value = "所属单位编码（集团）")
    private String groupBelongUnitCode;

    @ApiModelProperty(value = "所属单位名称（集团）")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "更新时间（集团）")
    private String groupMdmUpdateTime;

    @ApiModelProperty(value = "人员编号（集团唯一）")
    private String groupPersonCode;

    @ApiModelProperty(value = "人员编号（公司）")
    private String personCode;

    @ApiModelProperty(value = "状态编码（ 0：停用；1：启用）（集团）")
    private Integer status;

    @ApiModelProperty(value = "状态名称（集团）")
    private String statusDesc;

    @ApiModelProperty(value = "数据版本号,默认为1,变更一次,加1")
    private Integer version;

    @ApiModelProperty(value = "mim系统中的版本号记录")
    private Integer localVersion;
}
