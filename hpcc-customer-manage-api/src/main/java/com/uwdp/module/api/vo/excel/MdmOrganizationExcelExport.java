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
 * 主数据-组织
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "MdmOrganizationExcelExport对象", description = "主数据-组织", discriminator = "mdmOrganization")
@SearchBean(tables = "T_MDMORGANIZATION")
@EqualsAndHashCode(callSuper = false)
public class MdmOrganizationExcelExport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "组织简称（集团）")
    @DbField("GROUPALIAS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织简称（集团）"}, index = 5)
    // @Length(max = 100, message = "groupAlias长度不在有效范围内")
    private String groupAlias;

    @ApiModelProperty(value = "所属单位编码（公司）")
    @DbField("BELONGUNITCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属单位编码（公司）"}, index = 6)
    // @Length(max = 100, message = "belongUnitCode长度不在有效范围内")
    private String belongUnitCode;

    @ApiModelProperty(value = "业务类型（1：公司；2：部门；3：项目部；4：虚拟项目）（公司）")
    @DbField("BUSINESSTYPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业务类型（1：公司；2：部门；3：项目部；4：虚拟项目）（公司）"}, index = 7)
    // @Range(max = Long.MAX_VALUE, message = "businessType长度不在有效范围内")
    private String businessType;

    @ApiModelProperty(value = "组织全编码（org_code，分隔符：/）（公司）")
    @DbField("FULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织全编码（org_code，分隔符：/）（公司）"}, index = 8)
    // @Length(max = 200, message = "fullCode长度不在有效范围内")
    private String fullCode;

    @ApiModelProperty(value = "所属单位对应组织机构编码（集团）")
    @DbField("GROUPBELONGUNITCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属单位对应组织机构编码（集团）"}, index = 9)
    // @Length(max = 200, message = "groupBelongUnitCode长度不在有效范围内")
    private String groupBelongUnitCode;

    @ApiModelProperty(value = "所属单位对应组织机构名称（集团）")
    @DbField("GROUPBELONGUNITNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属单位对应组织机构名称（集团）"}, index = 10)
    // @Length(max = 255, message = "groupBelongUnitName长度不在有效范围内")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "所属单位对应组织机构内码（集团）")
    @DbField("GROUPBELONGUNITUUID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属单位对应组织机构内码（集团）"}, index = 11)
    // @Length(max = 100, message = "groupBelongUnitUuid长度不在有效范围内")
    private String groupBelongUnitUuid;

    @ApiModelProperty(value = "组织编码（集团唯一）")
    @DbField("GROUPCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织编码（集团唯一）"}, index = 12)
    // @Length(max = 50, message = "groupCode长度不在有效范围内")
    private String groupCode;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织全编码（group_code，分隔符：/）（集团）"}, index = 13)
    // @Length(max = 200, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "组织全名称（group_name，分隔符：/）（集团）")
    @DbField("GROUPFULLNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织全名称（group_name，分隔符：/）（集团）"}, index = 14)
    // @Length(max = 500, message = "groupFullName长度不在有效范围内")
    private String groupFullName;

    @ApiModelProperty(value = "组织级别（集团）")
    @DbField("GROUPGRADE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织级别（集团）"}, index = 15)
    // @Range(max = Long.MAX_VALUE, message = "groupGrade长度不在有效范围内")
    private String groupGrade;

    @ApiModelProperty(value = "组织层级（集团）")
    @DbField("GROUPLEVEL")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织层级（集团）"}, index = 16)
    // @Range(max = Long.MAX_VALUE, message = "groupLevel长度不在有效范围内")
    private String groupLevel;

    @ApiModelProperty(value = "组织名称（集团）")
    @DbField("GROUPNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织名称（集团）"}, index = 17)
    // @Length(max = 100, message = "groupName长度不在有效范围内")
    private String groupName;

    @ApiModelProperty(value = "上级编码（集团）")
    @DbField("GROUPPARENTCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"上级编码（集团）"}, index = 18)
    // @Length(max = 100, message = "groupParentCode长度不在有效范围内")
    private String groupParentCode;

    @ApiModelProperty(value = "上级名称（集团）")
    @DbField("GROUPPARENTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"上级名称（集团）"}, index = 19)
    // @Length(max = 100, message = "groupParentName长度不在有效范围内")
    private String groupParentName;

    @ApiModelProperty(value = "组织排序码（集团）")
    @DbField("GROUPSORT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织排序码（集团）"}, index = 20)
    // @Length(max = 100, message = "groupSort长度不在有效范围内")
    private String groupSort;

    @ApiModelProperty(value = "唯一标识")
    @DbField("MDMID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"唯一标识"}, index = 21)
    // @Range(max = Long.MAX_VALUE, message = "mdmId长度不在有效范围内")
    private String mdmId;

    @ApiModelProperty(value = "主数据编码（公司）")
    @DbField("MDMCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主数据编码（公司）"}, index = 22)
    // @Length(max = 100, message = "mdmCode长度不在有效范围内")
    private String mdmCode;

    @ApiModelProperty(value = "部门负责人工号（集团）")
    @DbField("RESPONSIBLEPERSONCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门负责人工号（集团）"}, index = 23)
    // @Length(max = 50, message = "responsiblePersonCode长度不在有效范围内")
    private String responsiblePersonCode;

    @ApiModelProperty(value = "部门负责人名称（集团）")
    @DbField("RESPONSIBLEPERSONNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"部门负责人名称（集团）"}, index = 24)
    // @Length(max = 30, message = "responsiblePersonName长度不在有效范围内")
    private String responsiblePersonName;

    @ApiModelProperty(value = "状态编码（0：停用；1：启用）（公司）")
    @DbField("STATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态编码（0：停用；1：启用）（公司）"}, index = 25)
    // @Range(max = Long.MAX_VALUE, message = "status长度不在有效范围内")
    private String status;

    @ApiModelProperty(value = "状态描述（公司）")
    @DbField("STATUSDESC")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态描述（公司）"}, index = 26)
    // @Length(max = 100, message = "statusDesc长度不在有效范围内")
    private String statusDesc;

    @ApiModelProperty(value = "组织类型编码（0：单位；1：部门）（集团）")
    @DbField("TYPECODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织类型编码（0：单位；1：部门）（集团）"}, index = 27)
    // @Range(max = Long.MAX_VALUE, message = "typeCode长度不在有效范围内")
    private String typeCode;

    @ApiModelProperty(value = "组织类型名称（集团）")
    @DbField("TYPENAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织类型名称（集团）"}, index = 28)
    // @Length(max = 100, message = "typeName长度不在有效范围内")
    private String typeName;

    @ApiModelProperty(value = "数据版本号,默认为1,变更一次,加1")
    @DbField("VERSION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"数据版本号,默认为1,变更一次,加1"}, index = 29)
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private String version;

    @ApiModelProperty(value = "本地数据版本号,默认为1,变更一次,加1")
    @DbField("LOCALVERSION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"本地数据版本号,默认为1,变更一次,加1"}, index = 30)
    // @Range(max = Long.MAX_VALUE, message = "localVersion长度不在有效范围内")
    private String localVersion;


}
