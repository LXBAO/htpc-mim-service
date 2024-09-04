package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
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
@ApiModel(value = "MdmBpDept UpdateCmd对象", description = "主数据-业务编码所对应部门", discriminator = "mdm_bp_dept")
public class MdmBpDeptUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识", required = true)
    @NotNull(message = "主键不能为空")
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

    @ApiModelProperty(value = "业务编码")
    // @Length(max = 255, message = "bpCode长度不在有效范围内")
    private String bpCode;

    @ApiModelProperty(value = "业务名称")
    // @Length(max = 255, message = "bpName长度不在有效范围内")
    private String bpName;

    @ApiModelProperty(value = "部门集合code")
    // @Length(max = 255, message = "deptCodes长度不在有效范围内")
    private String deptCodes;

    @ApiModelProperty(value = "部门集合名称")
    // @Length(max = 255, message = "deptNames长度不在有效范围内")
    private String deptNames;

    @ApiModelProperty(value = "部门")
    // @Range(max = 10000000000L, message = "scope长度不在有效范围内")
    private Integer scope;

    @ApiModelProperty(value = "区域")
    // @Length(max = 255, message = "area长度不在有效范围内")
    private String area;

    @ApiModelProperty(value = "领域")
    // @Length(max = 255, message = "domain长度不在有效范围内")
    private String domain;

}
