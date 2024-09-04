package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uwdp.module.api.vo.enums.*;
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
@ApiModel(value = "MdmPersonDTO对象", description = "主数据-人员", discriminator = "mdmPerson")
@SearchBean(tables = "T_MDMPERSON")
public class MdmPersonDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;


    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "姓名（集团）")
    @DbField("PERSONNAME")
    // @Length(max = 50, message = "personName长度不在有效范围内")
    private String personName;

    @ApiModelProperty("手机号")
    @DbField("TELEPHONE")
    private String telephone;

    @ApiModelProperty(value = "主数据id")
    @DbField("MDMID")
    // @Range(max = Long.MAX_VALUE, message = "mdmId长度不在有效范围内")
    private Long mdmId;

    @ApiModelProperty(value = "所属岗位编码（公司）")
    @DbField("BELONGPOSTCODE")
    // @Length(max = 200, message = "belongPostCode长度不在有效范围内")
    private String belongPostCode;

    @ApiModelProperty(value = "所属部门编码（公司）")
    @DbField("BELONGDEPARTMENTCODE")
    // @Length(max = 200, message = "belongDepartmentCode长度不在有效范围内")
    private String belongDepartmentCode;

    @ApiModelProperty(value = "所属单位编码（公司）")
    @DbField("BELONGUNITCODE")
    // @Length(max = 200, message = "belongUnitCode长度不在有效范围内")
    private String belongUnitCode;

    @ApiModelProperty(value = "证件号码（集团）")
    @DbField("CERTIFICATENO")
    // @Length(max = 100, message = "certificateNo长度不在有效范围内")
    private String certificateNo;

    @ApiModelProperty(value = "证件类型名称（集团）")
    @DbField("CERTIFICATETYPE")
    // @Length(max = 50, message = "certificateType长度不在有效范围内")
    private String certificateType;

    @ApiModelProperty(value = "出生日期（公司）")
    @DbField("BIRTHDAY")
    // @Length(max = 30, message = "birthday长度不在有效范围内")
    private String birthday;

    @ApiModelProperty(value = "所属部门编码（集团）")
    @DbField("GROUPBELONGDEPARTMENTCODE")
    // @Length(max = 100, message = "groupBelongDepartmentCode长度不在有效范围内")
    private String groupBelongDepartmentCode;

    @ApiModelProperty(value = "所属部门名称（集团）")
    @DbField("GROUPBELONGDEPARTMENTNAME")
    // @Length(max = 200, message = "groupBelongDepartmentName长度不在有效范围内")
    private String groupBelongDepartmentName;

    @ApiModelProperty(value = "所属部门内码（集团）")
    @DbField("GROUPBELONGDEPARTMENTUUID")
    // @Length(max = 100, message = "groupBelongDepartmentUuid长度不在有效范围内")
    private String groupBelongDepartmentUuid;

    @ApiModelProperty(value = "所属岗位编码（集团）")
    @DbField("GROUPBELONGPOSTCODE")
    // @Length(max = 100, message = "groupBelongPostCode长度不在有效范围内")
    private String groupBelongPostCode;

    @ApiModelProperty(value = "所属岗位名称（集团）")
    @DbField("GROUPBELONGPOSTNAME")
    // @Length(max = 200, message = "groupBelongPostName长度不在有效范围内")
    private String groupBelongPostName;

    @ApiModelProperty(value = "所属单位编码（集团）")
    @DbField("GROUPBELONGUNITCODE")
    // @Length(max = 100, message = "groupBelongUnitCode长度不在有效范围内")
    private String groupBelongUnitCode;

    @ApiModelProperty(value = "所属单位名称（集团）")
    @DbField("GROUPBELONGUNITNAME")
    // @Length(max = 200, message = "groupBelongUnitName长度不在有效范围内")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "更新时间（集团）")
    @DbField("GROUPMDMUPDATETIME")
    // @Length(max = 30, message = "groupMdmUpdateTime长度不在有效范围内")
    private String groupMdmUpdateTime;

    @ApiModelProperty(value = "人员编号（集团唯一）")
    @DbField("GROUPPERSONCODE")
    // @Length(max = 100, message = "groupPersonCode长度不在有效范围内")
    private String groupPersonCode;

    @ApiModelProperty(value = "人员编号（公司）")
    @DbField("PERSONCODE")
    // @Length(max = 100, message = "PersonCode长度不在有效范围内")
    private String personCode;

    @ApiModelProperty(value = "状态编码（ 0：停用；1：启用）（集团）")
    @DbField("STATUS")
    // @Range(max = 10L, message = "status长度不在有效范围内")
    private Integer status;

    @ApiModelProperty(value = "状态名称（集团）")
    @DbField("STATUSDESC")
    // @Length(max = 50, message = "statusDesc长度不在有效范围内")
    private String statusDesc;

    @ApiModelProperty(value = "数据版本号,默认为1,变更一次,加1")
    @DbField("VERSION")
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private Integer version;

    @ApiModelProperty(value = "mim系统中的版本号记录")
    @DbField("LOCALVERSION")
    // @Range(max = Long.MAX_VALUE, message = "localVersion长度不在有效范围内")
    private Integer localVersion;

}
