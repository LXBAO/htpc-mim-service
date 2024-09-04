package com.uwdp.module.api.vo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * <p>
 * 权限表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ClientRoleEntityExcelExport对象", description = "权限表", discriminator = "ClientRoleEntity")
@SearchBean(tables = "T_CLIENTROLEENTITY")
@EqualsAndHashCode(callSuper = false)
public class ClientRoleEntityExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @DbField("ID")
    @ExcelIgnore
    private String id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者"}, index = 0)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 1)
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新者"}, index = 2)
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 3)
    private String updatedTime;

    @ApiModelProperty(value = "部门id（多个,分隔）")
    @DbField("DEPTID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门id（多个,分隔）"}, index = 4)
    // @Length(max = 5000, message = "deptId长度不在有效范围内")
    private String deptId;

    @ApiModelProperty(value = "部门名称（多个，分隔）")
    @DbField("DEPTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门名称（多个，分隔）"}, index = 5)
    // @Length(max = 5000, message = "deptName长度不在有效范围内")
    private String deptName;

    @ApiModelProperty(value = "用户编码")
    @DbField("USERID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"用户编码"}, index = 6)
    // @Length(max = 5000, message = "userId长度不在有效范围内")
    private String userId;

    @ApiModelProperty(value = "姓名")
    @DbField("USERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"姓名"}, index = 7)
    // @Length(max = 5000, message = "userName长度不在有效范围内")
    private String userName;

    @ApiModelProperty(value = "角色")
    @DbField("ROLE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"角色"}, index = 8)
    // @Range(max = Long.MAX_VALUE, message = "role长度不在有效范围内")
    private String role;

    @ApiModelProperty(value = "状态")
    @DbField("STATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态"}, index = 9)
    // @Range(max = Long.MAX_VALUE, message = "status长度不在有效范围内")
    private String status;

    @ApiModelProperty(value = "ctime")
    @DbField("CTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"ctime"}, index = 10)
    private String ctime;


}
