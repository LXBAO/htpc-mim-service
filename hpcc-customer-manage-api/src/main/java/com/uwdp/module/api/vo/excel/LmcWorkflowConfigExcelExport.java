package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
 * 流程配置表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LmcWorkflowConfigExcelExport对象", description = "流程配置表", discriminator = "LmcWorkflowConfig")
@SearchBean(tables = "T_LMCWORKFLOWCONFIG")
@EqualsAndHashCode(callSuper = false)
public class LmcWorkflowConfigExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主键ID"}, index = 0)
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

    @ApiModelProperty(value = "业务类型", required = true)
    @DbField("BIZCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业务类型"}, index = 5)
    // @Length(max = 50, message = "bizCode长度不在有效范围内")
    private String bizCode;

    @ApiModelProperty(value = "流程编码", required = true)
    @DbField("PROCESSCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程编码"}, index = 6)
    // @Length(max = 50, message = "processCode长度不在有效范围内")
    private String processCode;

    @ApiModelProperty(value = "流程名称")
    @DbField("PROCESSNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程名称"}, index = 7)
    // @Length(max = 255, message = "processName长度不在有效范围内")
    private String processName;

    @ApiModelProperty(value = "流程回调编码 , 根据该编码实现具体的回调逻辑 , 多个逗号分隔", required = true)
    @DbField("PROCESSCALLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程回调编码 , 根据该编码实现具体的回调逻辑 , 多个逗号分隔"}, index = 8)
    // @Length(max = 255, message = "processCallCode长度不在有效范围内")
    private String processCallCode;

    @ApiModelProperty(value = "数据版本号 , 默认为1 , 变更一次 ,加 1", required = true)
    @DbField("VERSION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"数据版本号 , 默认为1 , 变更一次 ,加 1"}, index = 9)
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private String version;

    @ApiModelProperty(value = "是否删除(0: 否 ,1: 是)")
    @DbField("ISDELETED")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否删除(0: 否 ,1: 是)"}, index = 10)
    // @Range(max = 10L, message = "isDeleted长度不在有效范围内")
    private String isDeleted;

    @ApiModelProperty(value = "创建人编码")
    @DbField("CREATEUSERCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人编码"}, index = 11)
    // @Length(max = 50, message = "createUserCode长度不在有效范围内")
    private String createUserCode;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEUSERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人名称"}, index = 12)
    // @Length(max = 100, message = "createUserName长度不在有效范围内")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATETIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 13)
    private String createTime;

    @ApiModelProperty(value = "更新人编码")
    @DbField("UPDATEUSERCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新人编码"}, index = 14)
    // @Length(max = 50, message = "updateUserCode长度不在有效范围内")
    private String updateUserCode;

    @ApiModelProperty(value = "更新人名称")
    @DbField("UPDATEUSERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新人名称"}, index = 15)
    // @Length(max = 100, message = "updateUserName长度不在有效范围内")
    private String updateUserName;

    @ApiModelProperty(value = "更新时间", required = true)
    @DbField("UPDATETIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 16)
    private String updateTime;


}
