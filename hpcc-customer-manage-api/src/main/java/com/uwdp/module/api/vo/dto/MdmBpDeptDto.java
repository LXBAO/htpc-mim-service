package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 * 主数据-业务编码所对应部门
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "MdmBpDeptDTO对象", description = "主数据-业务编码所对应部门", discriminator = "mdm_bp_dept")
@SearchBean(tables = "T_MDM_BP_DEPT")
public class MdmBpDeptDto implements Serializable {

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

    @ApiModelProperty(value = "业务编码")
    @DbField("BP_CODE")
    // @Length(max = 255, message = "bpCode长度不在有效范围内")
    private String bpCode;

    @ApiModelProperty(value = "业务名称")
    @DbField("BP_NAME")
    // @Length(max = 255, message = "bpName长度不在有效范围内")
    private String bpName;

    @ApiModelProperty(value = "部门集合code")
    @DbField("DEPT_CODES")
    // @Length(max = 255, message = "deptCodes长度不在有效范围内")
    private String deptCodes;

    @ApiModelProperty(value = "部门集合名称")
    @DbField("DEPT_NAMES")
    // @Length(max = 255, message = "deptNames长度不在有效范围内")
    private String deptNames;

    @ApiModelProperty(value = "部门")
    @DbField("SCOPE")
    // @Range(max = 10000000000L, message = "scope长度不在有效范围内")
    private Integer scope;

    @ApiModelProperty(value = "区域")
    @DbField("area")
    private String area;

    @ApiModelProperty(value = "领域")
    @DbField("domain")
    private String domain;
}
