package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
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
@ApiModel(value = "MdmPersonExcelExport对象", description = "主数据-人员", discriminator = "mdmPerson")
@SearchBean(tables = "T_MDMPERSON")
@EqualsAndHashCode(callSuper = false)
public class MdmPersonExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"唯一标识"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private String id;

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
    private String createdTime;

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
    private String updatedTime;

    @ApiModelProperty(value = "姓名（集团）")
    @DbField("PERSONNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"姓名（集团）"}, index = 5)
    // @Length(max = 50, message = "personName长度不在有效范围内")
    private String personName;

    @ApiModelProperty(value = "主数据id")
    @DbField("MDMID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主数据id"}, index = 6)
    // @Range(max = Long.MAX_VALUE, message = "mdmId长度不在有效范围内")
    private String mdmId;

    @ApiModelProperty(value = "所属岗位编码（公司）")
    @DbField("BELONGPOSTCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属岗位编码（公司）"}, index = 7)
    // @Length(max = 200, message = "belongPostCode长度不在有效范围内")
    private String belongPostCode;

    @ApiModelProperty(value = "所属部门编码（公司）")
    @DbField("BELONGDEPARTMENTCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属部门编码（公司）"}, index = 8)
    // @Length(max = 200, message = "belongDepartmentCode长度不在有效范围内")
    private String belongDepartmentCode;

    @ApiModelProperty(value = "所属单位编码（公司）")
    @DbField("BELONGUNITCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属单位编码（公司）"}, index = 9)
    // @Length(max = 200, message = "belongUnitCode长度不在有效范围内")
    private String belongUnitCode;

    @ApiModelProperty(value = "证件号码（集团）")
    @DbField("CERTIFICATENO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"证件号码（集团）"}, index = 10)
    // @Length(max = 100, message = "certificateNo长度不在有效范围内")
    private String certificateNo;

    @ApiModelProperty(value = "证件类型名称（集团）")
    @DbField("CERTIFICATETYPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"证件类型名称（集团）"}, index = 11)
    // @Length(max = 50, message = "certificateType长度不在有效范围内")
    private String certificateType;

    @ApiModelProperty(value = "出生日期（公司）")
    @DbField("BIRTHDAY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"出生日期（公司）"}, index = 12)
    // @Length(max = 30, message = "birthday长度不在有效范围内")
    private String birthday;

    @ApiModelProperty(value = "所属部门编码（集团）")
    @DbField("GROUPBELONGDEPARTMENTCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属部门编码（集团）"}, index = 13)
    // @Length(max = 100, message = "groupBelongDepartmentCode长度不在有效范围内")
    private String groupBelongDepartmentCode;

    @ApiModelProperty(value = "所属部门名称（集团）")
    @DbField("GROUPBELONGDEPARTMENTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属部门名称（集团）"}, index = 14)
    // @Length(max = 200, message = "groupBelongDepartmentName长度不在有效范围内")
    private String groupBelongDepartmentName;

    @ApiModelProperty(value = "所属部门内码（集团）")
    @DbField("GROUPBELONGDEPARTMENTUUID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属部门内码（集团）"}, index = 15)
    // @Length(max = 100, message = "groupBelongDepartmentUuid长度不在有效范围内")
    private String groupBelongDepartmentUuid;

    @ApiModelProperty(value = "所属岗位编码（集团）")
    @DbField("GROUPBELONGPOSTCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属岗位编码（集团）"}, index = 16)
    // @Length(max = 100, message = "groupBelongPostCode长度不在有效范围内")
    private String groupBelongPostCode;

    @ApiModelProperty(value = "所属岗位名称（集团）")
    @DbField("GROUPBELONGPOSTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属岗位名称（集团）"}, index = 17)
    // @Length(max = 200, message = "groupBelongPostName长度不在有效范围内")
    private String groupBelongPostName;

    @ApiModelProperty(value = "所属单位编码（集团）")
    @DbField("GROUPBELONGUNITCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属单位编码（集团）"}, index = 18)
    // @Length(max = 100, message = "groupBelongUnitCode长度不在有效范围内")
    private String groupBelongUnitCode;

    @ApiModelProperty(value = "所属单位名称（集团）")
    @DbField("GROUPBELONGUNITNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属单位名称（集团）"}, index = 19)
    // @Length(max = 200, message = "groupBelongUnitName长度不在有效范围内")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "更新时间（集团）")
    @DbField("GROUPMDMUPDATETIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间（集团）"}, index = 20)
    // @Length(max = 30, message = "groupMdmUpdateTime长度不在有效范围内")
    private String groupMdmUpdateTime;

    @ApiModelProperty(value = "人员编号（集团唯一）")
    @DbField("GROUPPERSONCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"人员编号（集团唯一）"}, index = 21)
    // @Length(max = 100, message = "groupPersonCode长度不在有效范围内")
    private String groupPersonCode;

    @ApiModelProperty(value = "人员编号（公司）")
    @DbField("PERSONCODE")
    // @Length(max = 100, message = "PersonCode长度不在有效范围内")
    private String personCode;

    @ApiModelProperty(value = "状态编码（ 0：停用；1：启用）（集团）")
    @DbField("STATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态编码（ 0：停用；1：启用）（集团）"}, index = 22)
    // @Range(max = 10L, message = "status长度不在有效范围内")
    private String status;

    @ApiModelProperty(value = "状态名称（集团）")
    @DbField("STATUSDESC")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态名称（集团）"}, index = 23)
    // @Length(max = 50, message = "statusDesc长度不在有效范围内")
    private String statusDesc;

    @ApiModelProperty(value = "数据版本号,默认为1,变更一次,加1")
    @DbField("VERSION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"数据版本号,默认为1,变更一次,加1"}, index = 24)
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private String version;

    @ApiModelProperty(value = "mim系统中的版本号记录")
    @DbField("LOCALVERSION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"mim系统中的版本号记录"}, index = 25)
    // @Range(max = Long.MAX_VALUE, message = "localVersion长度不在有效范围内")
    private String localVersion;


}
