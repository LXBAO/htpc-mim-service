package com.uwdp.module.api.vo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

/**
 * <p>
 * 数据库连接配置
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "DBConfigExcelImport对象", description = "数据库连接配置", discriminator = "DBConfig")
@SearchBean(tables = "T_DBCONFIG")
@EqualsAndHashCode(callSuper = false)
public class DBConfigExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @DbField("FDID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"ID"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
    private Long fdId;

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

    @ApiModelProperty(value = "名称")
    @DbField("DBNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"名称"}, index = 5)
    // @Length(max = 100, message = "dbName长度不在有效范围内")
    private String dbName;

    @ApiModelProperty(value = "数据库类型")
    @DbField("DBTYPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"数据库类型"}, index = 6)
    // @Length(max = 36, message = "dBType长度不在有效范围内")
    private String dBType;

    @ApiModelProperty(value = "JDBC驱动")
    @DbField("JDBCDRIVER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"JDBC驱动"}, index = 7)
    // @Length(max = 200, message = "jDBCDriver长度不在有效范围内")
    private String jDBCDriver;

    @ApiModelProperty(value = "连接URL")
    @DbField("CONNECTURL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"连接URL"}, index = 8)
    // @Length(max = 500, message = "connectUrl长度不在有效范围内")
    private String connectUrl;

    @ApiModelProperty(value = "用户名")
    @DbField("USERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"用户名"}, index = 9)
    // @Length(max = 255, message = "userName长度不在有效范围内")
    private String userName;

    @ApiModelProperty(value = "密码")
    @DbField("PASSWORD")
    @ColumnWidth(16)
    @ExcelProperty(value = {"密码"}, index = 10)
    // @Length(max = 500, message = "password长度不在有效范围内")
    private String password;

    @ApiModelProperty(value = "描述")
    @DbField("DESCRIPTION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"描述"}, index = 11)
    // @Length(max = 500, message = "description长度不在有效范围内")
    private String description;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
