package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.alibaba.fastjson2.annotation.JSONField;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 主数据-人员
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "MdmPersonAddCmd对象", description = "主数据-人员", discriminator = "mdmPerson")
public class MdmPersonAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(deserialize = false)
    @ApiModelProperty(value = "唯一标识")
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

    @ApiModelProperty(value = "姓名（集团）")
    // @Length(max = 50, message = "personName长度不在有效范围内")
    private String personName;

    @JSONField(serialize = false)
    @ApiModelProperty(value = "主数据id")
    // @Range(max = Long.MAX_VALUE, message = "mdmId长度不在有效范围内")
    private Long mdmId;

    @ApiModelProperty(value = "所属岗位编码（公司）")
    // @Length(max = 200, message = "belongPostCode长度不在有效范围内")
    private String belongPostCode;

    @ApiModelProperty(value = "所属部门编码（公司）")
    // @Length(max = 200, message = "belongDepartmentCode长度不在有效范围内")
    private String belongDepartmentCode;

    @ApiModelProperty(value = "所属单位编码（公司）")
    // @Length(max = 200, message = "belongUnitCode长度不在有效范围内")
    private String belongUnitCode;

    @ApiModelProperty(value = "证件号码（集团）")
    // @Length(max = 100, message = "certificateNo长度不在有效范围内")
    private String certificateNo;

    @ApiModelProperty(value = "证件类型名称（集团）")
    // @Length(max = 50, message = "certificateType长度不在有效范围内")
    private String certificateType;

    @ApiModelProperty(value = "出生日期（公司）")
    // @Length(max = 30, message = "birthday长度不在有效范围内")
    private String birthday;

    @ApiModelProperty(value = "所属部门编码（集团）")
    // @Length(max = 100, message = "groupBelongDepartmentCode长度不在有效范围内")
    private String groupBelongDepartmentCode;

    @ApiModelProperty(value = "所属部门名称（集团）")
    // @Length(max = 200, message = "groupBelongDepartmentName长度不在有效范围内")
    private String groupBelongDepartmentName;

    @ApiModelProperty(value = "所属部门内码（集团）")
    // @Length(max = 100, message = "groupBelongDepartmentUuid长度不在有效范围内")
    private String groupBelongDepartmentUuid;

    @ApiModelProperty(value = "所属岗位编码（集团）")
    // @Length(max = 100, message = "groupBelongPostCode长度不在有效范围内")
    private String groupBelongPostCode;

    @ApiModelProperty(value = "所属岗位名称（集团）")
    // @Length(max = 200, message = "groupBelongPostName长度不在有效范围内")
    private String groupBelongPostName;

    @ApiModelProperty(value = "所属单位编码（集团）")
    // @Length(max = 100, message = "groupBelongUnitCode长度不在有效范围内")
    private String groupBelongUnitCode;

    @ApiModelProperty(value = "所属单位名称（集团）")
    // @Length(max = 200, message = "groupBelongUnitName长度不在有效范围内")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "更新时间（集团）")
    // @Length(max = 30, message = "groupMdmUpdateTime长度不在有效范围内")
    private String groupMdmUpdateTime;

    @ApiModelProperty(value = "人员编号（集团唯一）")
    // @Length(max = 100, message = "groupPersonCode长度不在有效范围内")
    private String groupPersonCode;

    @ApiModelProperty(value = "状态编码（ 0：停用；1：启用）（集团）")
    // @Range(max = 10L, message = "status长度不在有效范围内")
    private Integer status;

    @ApiModelProperty(value = "状态名称（集团）")
    // @Length(max = 50, message = "statusDesc长度不在有效范围内")
    private String statusDesc;

    @ApiModelProperty(value = "数据版本号,默认为1,变更一次,加1")
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private Integer version;

    @ApiModelProperty(value = "mim系统中的版本号记录")
    // @Range(max = Long.MAX_VALUE, message = "localVersion长度不在有效范围内")
    private Integer localVersion;

    @ApiModelProperty(value = "接口-人员code")
    // @Length(max = 64)
    private String personCode;


}
