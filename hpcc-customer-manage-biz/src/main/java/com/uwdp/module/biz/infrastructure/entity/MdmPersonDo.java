package com.uwdp.module.biz.infrastructure.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.ejlchina.searcher.bean.DbField;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_MDMPERSON")
@ApiModel(value = "MdmPersonDo entity对象", description = "主数据-人员")
public class MdmPersonDo implements Serializable {

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

    @ApiModelProperty("姓名（集团）")
    @TableField("PERSONNAME")
    private String personName;

    @ApiModelProperty("手机号")
    @TableField("TELEPHONE")
    private String telephone;

    @ApiModelProperty("主数据id")
    @TableField("MDMID")
    private Long mdmId;

    @ApiModelProperty("所属岗位编码（公司）")
    @TableField("BELONGPOSTCODE")
    private String belongPostCode;

    @ApiModelProperty("所属部门编码（公司）")
    @TableField("BELONGDEPARTMENTCODE")
    private String belongDepartmentCode;

    @ApiModelProperty("所属单位编码（公司）")
    @TableField("BELONGUNITCODE")
    private String belongUnitCode;

    @ApiModelProperty("证件号码（集团）")
    @TableField("CERTIFICATENO")
    private String certificateNo;

    @ApiModelProperty("证件类型名称（集团）")
    @TableField("CERTIFICATETYPE")
    private String certificateType;

    @ApiModelProperty("出生日期（公司）")
    @TableField("BIRTHDAY")
    private String birthday;

    @ApiModelProperty("所属部门编码（集团）")
    @TableField("GROUPBELONGDEPARTMENTCODE")
    private String groupBelongDepartmentCode;

    @ApiModelProperty("所属部门名称（集团）")
    @TableField("GROUPBELONGDEPARTMENTNAME")
    private String groupBelongDepartmentName;

    @ApiModelProperty("所属部门内码（集团）")
    @TableField("GROUPBELONGDEPARTMENTUUID")
    private String groupBelongDepartmentUuid;

    @ApiModelProperty("所属岗位编码（集团）")
    @TableField("GROUPBELONGPOSTCODE")
    private String groupBelongPostCode;

    @ApiModelProperty("所属岗位名称（集团）")
    @TableField("GROUPBELONGPOSTNAME")
    private String groupBelongPostName;

    @ApiModelProperty("所属单位编码（集团）")
    @TableField("GROUPBELONGUNITCODE")
    private String groupBelongUnitCode;

    @ApiModelProperty("所属单位名称（集团）")
    @TableField("GROUPBELONGUNITNAME")
    private String groupBelongUnitName;

    @ApiModelProperty("更新时间（集团）")
    @TableField("GROUPMDMUPDATETIME")
    private String groupMdmUpdateTime;

    @ApiModelProperty("人员编号（集团唯一）")
    @TableField("GROUPPERSONCODE")
    private String groupPersonCode;

    @ApiModelProperty(value = "人员编号（公司）")
    @TableField("PERSONCODE")
    private String personCode;

    @ApiModelProperty("状态编码（ 0：停用；1：启用）（集团）")
    @TableField("`STATUS`")
    private Integer status;

    @ApiModelProperty("状态名称（集团）")
    @TableField("STATUSDESC")
    private String statusDesc;

    @ApiModelProperty("数据版本号,默认为1,变更一次,加1")
    @TableField("VERSION")
    private Integer version;

    @ApiModelProperty("mim系统中的版本号记录")
    @TableField("LOCALVERSION")
    private Integer localVersion;

}
