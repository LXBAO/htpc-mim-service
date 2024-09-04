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
 * 流程时间线
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LmcWorkflowTimelineExcelExport对象", description = "流程时间线", discriminator = "LmcWorkflowTimeline")
@SearchBean(tables = "T_LMCWORKFLOWTIMELINE")
@EqualsAndHashCode(callSuper = false)
public class LmcWorkflowTimelineExcelExport extends BaseExcelDTO {

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

    @ApiModelProperty(value = "流程id")
    @DbField("WORKFLOWID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程id"}, index = 5)
    // @Length(max = 32, message = "workflowId长度不在有效范围内")
    private String workflowId;

    @ApiModelProperty(value = "流程状态(数据字典)")
    @DbField("WORKFLOWSTATUS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程状态(数据字典)"}, index = 6)
    // @Length(max = 20, message = "workflowStatus长度不在有效范围内")
    private String workflowStatus;

    @ApiModelProperty(value = "回调/触发时间")
    @DbField("TRIGGERTIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"回调/触发时间"}, index = 7)
    private String triggerTime;

    @ApiModelProperty(value = "流程编码")
    @DbField("PROCESSCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程编码"}, index = 8)
    // @Length(max = 50, message = "processCode长度不在有效范围内")
    private String processCode;

    @ApiModelProperty(value = "流程名称")
    @DbField("PROCESSNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程名称"}, index = 9)
    // @Length(max = 255, message = "processName长度不在有效范围内")
    private String processName;

    @ApiModelProperty(value = "流程实例id")
    @DbField("PROCESSINSTANCEID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程实例id"}, index = 10)
    // @Length(max = 36, message = "processInstanceId长度不在有效范围内")
    private String processInstanceId;

    @ApiModelProperty(value = "流程回调id")
    @DbField("PROCESSCALLEVENTID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"流程回调id"}, index = 11)
    // @Length(max = 36, message = "processCallEventId长度不在有效范围内")
    private String processCallEventId;

    @ApiModelProperty(value = "AppId")
    @DbField("APPID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"AppId"}, index = 12)
    // @Length(max = 50, message = "appId长度不在有效范围内")
    private String appId;

    @ApiModelProperty(value = "操作人编码")
    @DbField("OPERATORCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"操作人编码"}, index = 13)
    // @Length(max = 50, message = "operatorCode长度不在有效范围内")
    private String operatorCode;

    @ApiModelProperty(value = "操作人名称")
    @DbField("OPERATORNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"操作人名称"}, index = 14)
    // @Length(max = 100, message = "operatorName长度不在有效范围内")
    private String operatorName;

    @ApiModelProperty(value = "事件在哪个任务发生的任务id")
    @DbField("CURRENTTASKID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"事件在哪个任务发生的任务id"}, index = 15)
    // @Length(max = 36, message = "currentTaskId长度不在有效范围内")
    private String currentTaskId;

    @ApiModelProperty(value = "事件在哪个任务发生的任务编码")
    @DbField("CURRENTTASKKEY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"事件在哪个任务发生的任务编码"}, index = 16)
    // @Length(max = 50, message = "currentTaskKey长度不在有效范围内")
    private String currentTaskKey;

    @ApiModelProperty(value = "事件在哪个任务发生的任务名称")
    @DbField("CURRENTTASKNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"事件在哪个任务发生的任务名称"}, index = 17)
    // @Length(max = 255, message = "currentTaskName长度不在有效范围内")
    private String currentTaskName;

    @ApiModelProperty(value = "业务类型")
    @DbField("BIZ_CODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业务类型"}, index = 18)
    // @Length(max = 50, message = "bizCode长度不在有效范围内")
    private String bizCode;

    @ApiModelProperty(value = "业务唯一标识")
    @DbField("BIZ_ID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"业务唯一标识"}, index = 19)
    // @Length(max = 36, message = "bizId长度不在有效范围内")
    private String bizId;

    @ApiModelProperty(value = "操作原因")
    @DbField("REASON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"操作原因"}, index = 20)
    // @Length(max = 1024, message = "reason长度不在有效范围内")
    private String reason;

    @ApiModelProperty(value = "是否成功(0:否;1:是)")
    @DbField("SUCCEED")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否成功(0:否;1:是)"}, index = 21)
    // @Range(max = 10L, message = "succeed长度不在有效范围内")
    private String succeed;

    @ApiModelProperty(value = "错误信息")
    @DbField("ERRORMSG")
    @ColumnWidth(16)
    @ExcelProperty(value = {"错误信息"}, index = 22)
    // @Length(max = 1024, message = "errorMsg长度不在有效范围内")
    private String errorMsg;

    @ApiModelProperty(value = "错误详细信息")
    @DbField("ERRORTEXT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"错误详细信息"}, index = 23)
    // @Length(max = 1024, message = "errorText长度不在有效范围内")
    private String errorText;

    @ApiModelProperty(value = "数据版本号, 默认为1 ,变更一次,加1")
    @DbField("VERSION")
    @ColumnWidth(16)
    @ExcelProperty(value = {"数据版本号, 默认为1 ,变更一次,加1"}, index = 24)
    // @Range(max = Long.MAX_VALUE, message = "version长度不在有效范围内")
    private String version;

    @ApiModelProperty(value = "是否删除(0否1是)")
    @DbField("DELETED")
    @ColumnWidth(16)
    @ExcelProperty(value = {"是否删除(0否1是)"}, index = 25)
    // @Range(max = 10L, message = "deleted长度不在有效范围内")
    private String deleted;

    @ApiModelProperty(value = "创建人编码")
    @DbField("CREATEUSERCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人编码"}, index = 26)
    // @Length(max = 50, message = "createUserCode长度不在有效范围内")
    private String createUserCode;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEUSERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建人名称"}, index = 27)
    // @Length(max = 100, message = "createUserName长度不在有效范围内")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATETIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 28)
    private String createTime;

    @ApiModelProperty(value = "更新人编码")
    @DbField("UPDATEUSERCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新人编码"}, index = 29)
    // @Length(max = 50, message = "updateUserCode长度不在有效范围内")
    private String updateUserCode;

    @ApiModelProperty(value = "更新人名称")
    @DbField("UPDATEUSERNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新人名称"}, index = 30)
    // @Length(max = 100, message = "updateUserName长度不在有效范围内")
    private String updateUserName;

    @ApiModelProperty(value = "更新时间", required = true)
    @DbField("UPDATETIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"更新时间"}, index = 31)
    private String updateTime;


}
