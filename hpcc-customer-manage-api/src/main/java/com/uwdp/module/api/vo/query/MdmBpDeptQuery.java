package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * <p>
 * 主数据-业务编码所对应部门
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MdmBpDeptDo Query对象", description = "主数据-业务编码所对应部门", discriminator = "mdm_bp_dept")
public class MdmBpDeptQuery extends BasePageQuery {

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

    @ApiModelProperty(value = "业务编码")
    private String bpCode;

    @ApiModelProperty(value = "业务名称")
    private String bpName;

    @ApiModelProperty(value = "部门集合code")
    private String deptCodes;

    @ApiModelProperty(value = "部门集合名称")
    private String deptNames;

    @ApiModelProperty(value = "部门")
    private Integer scope;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "领域")
    private String domain;
}
