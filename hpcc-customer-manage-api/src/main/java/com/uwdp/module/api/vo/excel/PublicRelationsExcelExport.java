package com.uwdp.module.api.vo.excel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.*;

import com.gientech.lcds.generator.commons.api.entity.BaseExcelDTO;
import com.uwdp.module.api.vo.enums.DictVisitPlanActivityWayEnums;
import com.uwdp.module.api.vo.enums.WorkflowStatusEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 公关实施
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "PublicRelationsExcelExport对象", description = "公关实施", discriminator = "publicRelations")
@SearchBean(tables = "T_PUBLICRELATIONS p left join T_LMCWORKFLOW l on p.ID =  l.BIZID")
@EqualsAndHashCode(callSuper = false)
public class PublicRelationsExcelExport extends BaseExcelDTO {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "唯一标识")
//    @DbField("p.ID")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"唯一标识"}, index = 0)
//    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
//    private String id;

    @ExcelIgnore
    @DbField("p.CREATED_BY")
    @ColumnWidth(16)
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

//    @ApiModelProperty(value = "更新者")
//    @DbField("p.UPDATED_BY")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新者"}, index = 3)
//    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
//    private String updatedBy;
//
//    @ApiModelProperty(value = "更新时间")
//    @DbField("p.UPDATED_TIME")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"更新时间"}, index = 4)
//    private String updatedTime;

    @ApiModelProperty(value = "关联计划", required = true)
    @DbField("p.VISITPLANID")
    @ColumnWidth(16)
    @ExcelProperty(value = {"关联计划"}, index = 0)
    // @Range(max = Long.MAX_VALUE, message = "visitPlanId长度不在有效范围内")
    private String visitPlanId;

    @ApiModelProperty(value = "责任单位", required = true)
    @DbField("p.DUTYUNIT")
    @ColumnWidth(16)
    @ExcelProperty(value = {"责任单位"}, index = 1)
    // @Length(max = 100, message = "dutyUnit长度不在有效范围内")
    private String dutyUnit;

    @ApiModelProperty(value = "公关时间")
    @DbField("p.DATE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"公关时间"}, index = 2)
    private String date;

    @ApiModelProperty(value = "活动方式")
    @DbField("p.ACTIVITYWAY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动方式"}, index = 3)
    // @Length(max = 100, message = "activityWay长度不在有效范围内")
    private String activityWay;
    public String getActivityWay(){
        return DictVisitPlanActivityWayEnums.getName(activityWay);
    }


    @ApiModelProperty(value = "活动省市")
    @DbField("p.ACTIVITYPROVINCEANDCITY")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动省市"}, index = 4)
    // @Length(max = 100, message = "activityProvinceAndCity长度不在有效范围内")
    private String activityProvinceAndCity;

    @ApiModelProperty(value = "活动省市名称")
    @DbField("p.ACTIVITYPROVINCEANDCITYNAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动省市名称"}, index = 5)
    // @Length(max = 100, message = "activityProvinceAndCityName长度不在有效范围内")
    private String activityProvinceAndCityName;

    @ApiModelProperty(value = "活动地点")
    @DbField("p.ACTIVITYADDRESS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动地点"}, index = 6)
    // @Length(max = 250, message = "activityAddress长度不在有效范围内")
    private String activityAddress;

    @ApiModelProperty(value = "主要参会人")
    @DbField("p.MAINPERSON")
    @ColumnWidth(16)
    @ExcelProperty(value = {"主要参会人"}, index = 7)
    // @Length(max = 250, message = "mainPerson长度不在有效范围内")
    private String mainPerson;

//    @ApiModelProperty(value = "主要参会人id")
//    @DbField("p.MAINPERSONIDS")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"主要参会人id"}, index = 11)
//    // @Length(max = 500, message = "mainPersonIds长度不在有效范围内")
//    private String mainPersonIds;

    @ApiModelProperty(value = "备注")
    @DbField("p.MEMO")
    @ColumnWidth(16)
    @ExcelProperty(value = {"备注"}, index = 8)
    // @Length(max = 500, message = "memo长度不在有效范围内")
    private String memo;

    @ApiModelProperty(value = "活动成果")
    @DbField("p.RESULTS")
    @ColumnWidth(16)
    @ExcelProperty(value = {"活动成果"}, index = 9)
    // @Length(max = 500, message = "results长度不在有效范围内")
    private String results;

//    @ApiModelProperty(value = "后续事项跟踪人")
//    @DbField("p.FOLLOWERS")
//    @ColumnWidth(16)
//    @ExcelProperty(value = {"后续事项跟踪人"}, index = 14)
//    // @Length(max = 200, message = "followers长度不在有效范围内")
//    private String followers;

    @ApiModelProperty(value = "创建者名称")
    @DbField("p.CREATED_BY_NAME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建者名称"}, index = 10)
    // @Length(max = 64, message = "createdByName长度不在有效范围内")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    @DbField("p.CREATED_TIME")
    @ColumnWidth(16)
    @ExcelProperty(value = {"创建时间"}, index = 11)
    private String createdTime;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    @DbField("p.GROUPFULLCODE")
    @ColumnWidth(16)
    @ExcelProperty(value = {"组织全编码"}, index = 12)
    // @Length(max = 64, message = "groupFullCode长度不在有效范围内")
    private String groupFullCode;

    @ColumnWidth(16)
    @ExcelProperty(value = {"审批状态"}, index = 13)
    // @Length(max = 15, message = "createdName长度不在有效范围内")
    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;
    public String getWorkflowStatus(){
        return WorkflowStatusEnums.getName(workflowStatus);
    }


}
