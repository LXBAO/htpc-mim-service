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
 * 流程表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LmcWorkflowExcelImport对象", description = "流程表", discriminator = "LmcWorkflow")
@SearchBean(tables = "T_LMCWORKFLOW")
@EqualsAndHashCode(callSuper = false)
public class LmcWorkflowExcelImport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @DbField("ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主键ID"}, index = 0)
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

    @ApiModelProperty(value = "业务类型")
    @DbField("BIZCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业务类型"}, index = 5)
    // @Length(max = 50, message = "bizCode长度不在有效范围内")
    private String bizCode;

    @ApiModelProperty(value = "业务唯一标识")
    @DbField("BIZID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业务唯一标识"}, index = 6)
    // @Length(max = 36, message = "bizId长度不在有效范围内")
    private String bizId;

    @ApiModelProperty(value = "流程编码")
    @DbField("PROCESSCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程编码"}, index = 7)
    // @Length(max = 50, message = "processCode长度不在有效范围内")
    private String processCode;

    @ApiModelProperty(value = "流程实例id", required = true)
    @DbField("PROCESSINSTANCEID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程实例id"}, index = 8)
    // @Length(max = 36, message = "processInstanceId长度不在有效范围内")
    private String processInstanceId;

    @ApiModelProperty(value = "流程标题")
    @DbField("TITLE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程标题"}, index = 9)
    // @Length(max = 255, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "AppId")
    @DbField("APPID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"AppId"}, index = 10)
    // @Length(max = 50, message = "appId长度不在有效范围内")
    private String appId;

    @ApiModelProperty(value = "发起人编码")
    @DbField("SUBMITTER_CODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"发起人编码"}, index = 11)
    // @Length(max = 50, message = "submitterCode长度不在有效范围内")
    private String submitterCode;

    @ApiModelProperty(value = "发起人名称")
    @DbField("SUBMITTERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"发起人名称"}, index = 12)
    // @Length(max = 100, message = "submitterName长度不在有效范围内")
    private String submitterName;

    @ApiModelProperty(value = "发起时间")
    @DbField("SUBMITTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"发起时间"}, index = 13)
    private LocalDateTime submitTime;

    @ApiModelProperty(value = "流程结束时间")
    @DbField("ENDTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程结束时间"}, index = 14)
    private LocalDateTime endTime;

    @ApiModelProperty(value = "流程状态(数据字典)")
    @DbField("WORKFLOWSTATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程状态(数据字典)"}, index = 15)
    // @Length(max = 20, message = "workflowStatus长度不在有效范围内")
    private String workflowStatus;

    @ApiModelProperty(value = "操作原因")
    @DbField("REASON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"操作原因"}, index = 16)
    // @Length(max = 1024, message = "reason长度不在有效范围内")
    private String reason;

    @ApiModelProperty(value = "发起序号")
    @DbField("SORTNUMBER")
    @ColumnWidth(16)
    @ExcelProperty(value = {"发起序号"}, index = 17)
    // @Range(max = Long.MAX_VALUE, message = "sortNumber长度不在有效范围内")
    private Integer sortNumber;

    @ApiModelProperty(value = "数据版本号 , 默认为1 , 变更一次 , 加1")
    @DbField("VERSION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"数据版本号 , 默认为1 , 变更一次 , 加1"}, index = 18)
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private Long version;

    @ApiModelProperty(value = "是否删除(0: 否; 1: 是)")
    @DbField("ISDELETED")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否删除(0: 否; 1: 是)"}, index = 19)
    // @Range(max = 10L, message = "isDeleted长度不在有效范围内")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建人编码")
    @DbField("CREATEUSERCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人编码"}, index = 20)
    // @Length(max = 50, message = "createUserCode长度不在有效范围内")
    private String createUserCode;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEUSERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人名称"}, index = 21)
    // @Length(max = 100, message = "createUserName长度不在有效范围内")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATETIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 22)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人编码")
    @DbField("UPDATEUSERCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新人编码"}, index = 23)
    // @Length(max = 50, message = "updateUserCode长度不在有效范围内")
    private String updateUserCode;

    @ApiModelProperty(value = "更新人名称")
    @DbField("UPDATEUSERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新人名称"}, index = 24)
    // @Length(max = 100, message = "updateUserName长度不在有效范围内")
    private String updateUserName;

    @ApiModelProperty(value = "更新时间", required = true)
    @DbField("UPDATETIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 25)
    private LocalDateTime updateTime;

    @ExcelIgnore
    @DbIgnore
    @JsonIgnore
    private String rowIndex;
}
