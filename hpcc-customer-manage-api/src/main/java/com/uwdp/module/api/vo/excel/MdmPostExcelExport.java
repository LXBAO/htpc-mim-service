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
 * 主数据-岗位
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "MdmPostExcelExport对象", description = "主数据-岗位", discriminator = "mdmPost")
@SearchBean(tables = "T_MDMPOST")
@EqualsAndHashCode(callSuper = false)
public class MdmPostExcelExport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "所属部门编码（集团）")
    @DbField("GROUPBELONGDEPARTMENTCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属部门编码（集团）"}, index = 5)
    // @Length(max = 100, message = "groupBelongDepartmentCode长度不在有效范围内")
    private String groupBelongDepartmentCode;

    @ApiModelProperty(value = "所属部门名称（集团）")
    @DbField("GROUPBELONGDEPARTMENTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属部门名称（集团）"}, index = 6)
    // @Length(max = 100, message = "groupBelongDepartmentName长度不在有效范围内")
    private String groupBelongDepartmentName;

    @ApiModelProperty(value = "所属单位编码（集团）")
    @DbField("GROUPBELONGUNITCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属单位编码（集团）"}, index = 7)
    // @Length(max = 100, message = "groupBelongUnitCode长度不在有效范围内")
    private String groupBelongUnitCode;

    @ApiModelProperty(value = "所属单位名称（集团）")
    @DbField("GROUPBELONGUNITNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"所属单位名称（集团）"}, index = 8)
    // @Length(max = 100, message = "groupBelongUnitName长度不在有效范围内")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "岗位类别（集团）")
    @DbField("GROUPPOSTCATEGORY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"岗位类别（集团）"}, index = 9)
    // @Length(max = 50, message = "groupPostCategory长度不在有效范围内")
    private String groupPostCategory;

    @ApiModelProperty(value = "岗位编码（集团唯一）")
    @DbField("GROUPPOSTCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"岗位编码（集团唯一）"}, index = 10)
    // @Length(max = 100, message = "groupPostCode长度不在有效范围内")
    private String groupPostCode;

    @ApiModelProperty(value = "岗位名称（集团）")
    @DbField("GROUPPOSTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"岗位名称（集团）"}, index = 11)
    // @Length(max = 100, message = "groupPostName长度不在有效范围内")
    private String groupPostName;

    @ApiModelProperty(value = "标准岗位名称（集团）")
    @DbField("GROUPSTANDARDPOSTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"标准岗位名称（集团）"}, index = 12)
    // @Length(max = 150, message = "groupStandardPostName长度不在有效范围内")
    private String groupStandardPostName;

    @ApiModelProperty(value = "唯一标识")
    @DbField("MDMID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"唯一标识"}, index = 13)
    // @Range(max = Long.MAX_VALUE, message = "mdmId长度不在有效范围内")
    private String mdmId;

    @ApiModelProperty(value = "是否删除（0：否；1：是）")
    @DbField("ISDELETED")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否删除（0：否；1：是）"}, index = 14)
    // @Range(max = Long.MAX_VALUE, message = "isDeleted长度不在有效范围内")
    private String isDeleted;

    @ApiModelProperty(value = "劳保用品类型（公司）")
    @DbField("LABORTYPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"劳保用品类型（公司）"}, index = 15)
    // @Length(max = 50, message = "laborType长度不在有效范围内")
    private String laborType;

    @ApiModelProperty(value = "主数据编码")
    @DbField("MDMCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主数据编码"}, index = 16)
    // @Length(max = 50, message = "mdmCode长度不在有效范围内")
    private String mdmCode;

    @ApiModelProperty(value = "岗位编码（公司）")
    @DbField("POSTCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"岗位编码（公司）"}, index = 17)
    // @Length(max = 50, message = "postCode长度不在有效范围内")
    private String postCode;

    @ApiModelProperty(value = "岗位名称（公司）")
    @DbField("POSTNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"岗位名称（公司）"}, index = 18)
    // @Length(max = 100, message = "postName长度不在有效范围内")
    private String postName;

    @ApiModelProperty(value = "岗位性质（4：生产；5：服务；6：其他；7：管理；8：技术；9：技能）（公司）")
    @DbField("POSTPROPERTY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"岗位性质（4：生产；5：服务；6：其他；7：管理；8：技术；9：技能）（公司）"}, index = 19)
    // @Length(max = 20, message = "postProperty长度不在有效范围内")
    private String postProperty;

    @ApiModelProperty(value = "岗位类型（标准岗位/业务岗位）（公司）")
    @DbField("POSTTYPE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"岗位类型（标准岗位/业务岗位）（公司）"}, index = 20)
    // @Length(max = 10, message = "postType长度不在有效范围内")
    private String postType;

    @ApiModelProperty(value = "关联的标岗编码（公司）")
    @DbField("RELEVANCEPOSTCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联的标岗编码（公司）"}, index = 21)
    // @Length(max = 50, message = "relevancePostCode长度不在有效范围内")
    private String relevancePostCode;

    @ApiModelProperty(value = "状态编码（0：停用；1：启用）（集团）")
    @DbField("STATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态编码（0：停用；1：启用）（集团）"}, index = 22)
    // @Length(max = 10, message = "status长度不在有效范围内")
    private String status;

    @ApiModelProperty(value = "状态描述（集团）")
    @DbField("STATUSDESC")
    @ColumnWidth(16)
    @ExcelProperty(value = {"状态描述（集团）"}, index = 23)
    // @Length(max = 50, message = "statusDesc长度不在有效范围内")
    private String statusDesc;

    @ApiModelProperty(value = "数据版本号,默认为1,变更一次,加1")
    @DbField("VERSION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"数据版本号,默认为1,变更一次,加1"}, index = 24)
    // @Length(max = 64, message = "version长度不在有效范围内")
    private String version;

    @ApiModelProperty(value = "本地数据版本号,默认为1,变更一次,加1")
    @DbField("LOCALVERSION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"本地数据版本号,默认为1,变更一次,加1"}, index = 25)
    // @Range(max = Long.MAX_VALUE, message = "localVersion长度不在有效范围内")
    private String localVersion;


}
