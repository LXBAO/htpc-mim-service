package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ApiModel(value = "MdmBpDeptExcelImport对象", description = "主数据-业务编码所对应部门", discriminator = "mdm_bp_dept")
@SearchBean(tables = "T_MDM_BP_DEPT")
@EqualsAndHashCode(callSuper = false)
public class MdmBpDeptExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"唯一标识"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 1)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 2)
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新者"}, index = 3)
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 4)
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "业务编码")
    @DbField("BP_CODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业务编码"}, index = 5)
    // @Length(max = 255, message = "bpCode长度不在有效范围内")
    private String bpCode;

    @ApiModelProperty(value = "业务名称")
    @DbField("BP_NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业务名称"}, index = 6)
    // @Length(max = 255, message = "bpName长度不在有效范围内")
    private String bpName;

    @ApiModelProperty(value = "部门集合code")
    @DbField("DEPT_CODES")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门集合code"}, index = 7)
    // @Length(max = 255, message = "deptCodes长度不在有效范围内")
    private String deptCodes;

    @ApiModelProperty(value = "部门集合名称")
    @DbField("DEPT_NAMES")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门集合名称"}, index = 8)
    // @Length(max = 255, message = "deptNames长度不在有效范围内")
    private String deptNames;

    @ApiModelProperty(value = "部门")
    @DbField("SCOPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门集合名称"}, index = 9)
    // @Range(max = 10000000000L, message = "scope长度不在有效范围内")
    private Integer scope;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
