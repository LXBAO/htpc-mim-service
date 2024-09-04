package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 主数据-组织
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MdmOrganizationDo Query对象", description = "主数据-组织", discriminator = "mdmOrganization")
public class MdmOrganizationQuery extends BasePageQuery {

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

    @ApiModelProperty(value = "组织简称（集团）")
    private String groupAlias;

    @ApiModelProperty(value = "所属单位编码（公司）")
    private String belongUnitCode;

    @ApiModelProperty(value = "业务类型（1：公司；2：部门；3：项目部；4：虚拟项目）（公司）")
    private Integer businessType;

    @ApiModelProperty(value = "组织全编码（org_code，分隔符：/）（公司）")
    private String fullCode;

    @ApiModelProperty(value = "所属单位对应组织机构编码（集团）")
    private String groupBelongUnitCode;

    @ApiModelProperty(value = "所属单位对应组织机构名称（集团）")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "所属单位对应组织机构内码（集团）")
    private String groupBelongUnitUuid;

    @ApiModelProperty(value = "组织编码（集团唯一）")
    private String groupCode;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    private String groupFullCode;

    @ApiModelProperty(value = "组织全名称（group_name，分隔符：/）（集团）")
    private String groupFullName;

    @ApiModelProperty(value = "组织级别（集团）")
    private Integer groupGrade;

    @ApiModelProperty(value = "组织层级（集团）")
    private Integer groupLevel;

    @ApiModelProperty(value = "组织名称（集团）")
    private String groupName;

    @ApiModelProperty(value = "上级编码（集团）")
    private String groupParentCode;

    @ApiModelProperty(value = "上级名称（集团）")
    private String groupParentName;

    @ApiModelProperty(value = "组织排序码（集团）")
    private String groupSort;

    @ApiModelProperty(value = "唯一标识")
    private Long mdmId;

    @ApiModelProperty(value = "主数据编码（公司）")
    private String mdmCode;

    @ApiModelProperty(value = "部门负责人工号（集团）")
    private String responsiblePersonCode;

    @ApiModelProperty(value = "部门负责人名称（集团）")
    private String responsiblePersonName;

    @ApiModelProperty(value = "状态编码（0：停用；1：启用）（公司）")
    private Integer status;

    @ApiModelProperty(value = "状态描述（公司）")
    private String statusDesc;

    @ApiModelProperty(value = "组织类型编码（0：单位；1：部门）（集团）")
    private Integer typeCode;

    @ApiModelProperty(value = "组织类型名称（集团）")
    private String typeName;

    @ApiModelProperty(value = "数据版本号,默认为1,变更一次,加1")
    private Integer version;

    @ApiModelProperty(value = "本地数据版本号,默认为1,变更一次,加1")
    private Integer localVersion;
}
