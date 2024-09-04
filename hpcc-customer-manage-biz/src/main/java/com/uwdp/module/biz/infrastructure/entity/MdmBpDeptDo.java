package com.uwdp.module.biz.infrastructure.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
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
import org.hibernate.validator.constraints.Range;

/**
 * <p>
 * 主数据-业务编码所对应部门
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_MDM_BP_DEPT")
@ApiModel(value = "MdmBpDeptDo entity对象", description = "主数据-业务编码所对应部门")
public class MdmBpDeptDo implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty("业务编码")
    @TableField("BP_CODE")
    private String bpCode;

    @ApiModelProperty("业务名称")
    @TableField("BP_NAME")
    private String bpName;

    @ApiModelProperty("部门集合code")
    @TableField("DEPT_CODES")
    private String deptCodes;

    @ApiModelProperty("部门集合名称")
    @TableField("DEPT_NAMES")
    private String deptNames;

    @ApiModelProperty(value = "部门")
    @TableField("SCOPE")
    private Integer scope;

    @ApiModelProperty(value = "区域")
    @TableField("area")
    private String area;

    @ApiModelProperty(value = "领域")
    @TableField("domain")
    private String domain;

}
