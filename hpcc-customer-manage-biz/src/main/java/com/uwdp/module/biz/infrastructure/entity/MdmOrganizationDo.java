package com.uwdp.module.biz.infrastructure.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_MDMORGANIZATION")
@ApiModel(value = "MdmOrganizationDo entity对象", description = "主数据-组织")
public class MdmOrganizationDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(deserialize = false) /* 设置反序列化时忽略该字段  */
    @ApiModelProperty("唯一标识")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("组织简称（集团）")
    @TableField("GROUPALIAS")
    private String groupAlias;

    @ApiModelProperty("所属单位编码（公司）")
    @TableField("BELONGUNITCODE")
    private String belongUnitCode;

    @ApiModelProperty("业务类型（1：公司；2：部门；3：项目部；4：虚拟项目）（公司）")
    @TableField("BUSINESSTYPE")
    private Integer businessType;

    @ApiModelProperty("组织全编码（org_code，分隔符：/）（公司）")
    @TableField("FULLCODE")
    private String fullCode;

    @ApiModelProperty("所属单位对应组织机构编码（集团）")
    @TableField("GROUPBELONGUNITCODE")
    private String groupBelongUnitCode;

    @ApiModelProperty("所属单位对应组织机构名称（集团）")
    @TableField("GROUPBELONGUNITNAME")
    private String groupBelongUnitName;

    @ApiModelProperty("所属单位对应组织机构内码（集团）")
    @TableField("GROUPBELONGUNITUUID")
    private String groupBelongUnitUuid;

    @ApiModelProperty("组织编码（集团唯一）")
    @TableField("GROUPCODE")
    private String groupCode;

    @ApiModelProperty("组织全编码（group_code，分隔符：/）（集团）")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty("组织全名称（group_name，分隔符：/）（集团）")
    @TableField("GROUPFULLNAME")
    private String groupFullName;

    @ApiModelProperty("组织级别（集团）")
    @TableField("GROUPGRADE")
    private Integer groupGrade;

    @ApiModelProperty("组织层级（集团）")
    @TableField("GROUPLEVEL")
    private Integer groupLevel;

    @ApiModelProperty("组织名称（集团）")
    @TableField("GROUPNAME")
    private String groupName;

    @ApiModelProperty("上级编码（集团）")
    @TableField("GROUPPARENTCODE")
    private String groupParentCode;

    @ApiModelProperty("上级名称（集团）")
    @TableField("GROUPPARENTNAME")
    private String groupParentName;

    @ApiModelProperty("组织排序码（集团）")
    @TableField("GROUPSORT")
    private String groupSort;

    @ApiModelProperty("唯一标识")
    @TableField("MDMID")
    private Long mdmId;

    @ApiModelProperty("主数据编码（公司）")
    @TableField("MDMCODE")
    private String mdmCode;

    @ApiModelProperty("部门负责人工号（集团）")
    @TableField("RESPONSIBLEPERSONCODE")
    private String responsiblePersonCode;

    @ApiModelProperty("部门负责人名称（集团）")
    @TableField("RESPONSIBLEPERSONNAME")
    private String responsiblePersonName;

    @ApiModelProperty("状态编码（0：停用；1：启用）（公司）")
    @TableField("`STATUS`")
    private Integer status;

    @ApiModelProperty("状态描述（公司）")
    @TableField("STATUSDESC")
    private String statusDesc;

    @ApiModelProperty("组织类型编码（0：单位；1：部门）（集团）")
    @TableField("TYPECODE")
    private Integer typeCode;

    @ApiModelProperty("组织类型名称（集团）")
    @TableField("TYPENAME")
    private String typeName;

    @ApiModelProperty("数据版本号,默认为1,变更一次,加1")
    @TableField("VERSION")
    private Integer version;

    @ApiModelProperty("本地数据版本号,默认为1,变更一次,加1")
    @TableField("LOCALVERSION")
    private Integer localVersion;

}
