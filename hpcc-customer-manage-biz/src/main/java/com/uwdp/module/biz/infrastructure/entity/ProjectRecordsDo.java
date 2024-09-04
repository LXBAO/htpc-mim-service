package com.uwdp.module.biz.infrastructure.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ejlchina.searcher.bean.DbField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 项目登记
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PROJECTRECORDS")
@ApiModel(value = "ProjectRecordsDo entity对象", description = "项目登记")
public class ProjectRecordsDo implements Serializable {

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

    @ApiModelProperty(value = "权限id")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("项目工程名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty("所在地(省市)")
    @TableField("LOCATION")
    private String location;

    @ApiModelProperty("产业领域类别")
    @TableField("INDUSTRYCATEGORY")
    private String industryCategory;

    @ApiModelProperty("装机规模(MW)")
    @TableField("INSTALLEDCAPACITY")
    private String installedCapacity;

    @ApiModelProperty("承包模式")
    @TableField("CONTRACTINGMODE")
    private String contractingMode;

    @ApiModelProperty("预计合同金额(亿)")
    @TableField("ESTIMATEDCONTRACTAMOUNT")
    private Double estimatedContractAmount;

    @ApiModelProperty("预计投标时间")
    @TableField("ESTIMATEDBIDDINGTIME")
    private LocalDateTime estimatedBiddingTime;

    @ApiModelProperty("项目编号")
    @TableField("PROJECTNUMBER")
    private String projectNumber;

    @ApiModelProperty("登记单位")
    @TableField("REGISTERUNIT")
    private String registerUnit;

    @ApiModelProperty("项目阶段")
    @TableField("PROJECTPHASE")
    private String projectPhase;

    @ApiModelProperty("项目阶段old")
    @TableField("PROJECTPHASEOLD")
    private String projectPhaseOld;

    @ApiModelProperty("业主单位")
    @TableField("OWNERUNIT")
    private String ownerUnit;

    @ApiModelProperty("业主单位/人")
    @TableField("`OWNER`")
    private String owner;

    @ApiModelProperty("重要程度")
    @TableField("IMPORTANTTYPE")
    private String importantType;

    @ApiModelProperty("所属区域")
    @TableField("OWNINGREGION")
    private String owningRegion;

    @ApiModelProperty("项目来源")
    @TableField("PROJECTSOURCE")
    private String projectSource;

    @ApiModelProperty("投资规模(万元)")
    @TableField("INVESTMENTSCALE")
    private Double investmentScale;

    @ApiModelProperty("项目金额")
    @TableField("ITEMAMOUNT")
    private Double itemAmount;

    @ApiModelProperty("请求")
    @TableField("DEMAND")
    private String demand;

    @ApiModelProperty("建设内容")
    @TableField("CONSTRUCTIONCONTENT")
    private String constructionContent;

    @ApiModelProperty("项目背景")
    @TableField("PROJECTCONTEXT")
    private String projectContext;

    @ApiModelProperty("项目状态")
    @TableField("PROJECTSTATUS")
    private String projectStatus;

    @ApiModelProperty("项目类别")
    @TableField("PROJECTCATEGORY")
    private String projectCategory;

    @ApiModelProperty("客户ID")
    @TableField("FDID")
    private String  fdId;

    @ApiModelProperty("客户名称")
    @TableField("FDNAME")
    private String  fdName;

    @ApiModelProperty("原因")
    @TableField("CAUSE")
    private String cause;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEDNAME")
    private String createdName;

    @ApiModelProperty("隐藏状态")
    @TableField("hideState")
    private String hideState;

    @ApiModelProperty("是否国际项目")
    @TableField("ISFORIEN")
    private String isForien;

    @ApiModelProperty(value = "产业领域")
    @TableField("INDUSTRY")
    private String industry;

    @ApiModelProperty(value = "根据内部协助单位")
    @TableField("INTASSISTANCEUNIT")
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    @TableField("INTASSISTANCEUNITNAME")
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "项目规模单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty(value = "所在地名称")
    @TableField("LOCATIONNAME")
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String locationName;

    @ApiModelProperty(value = "承包模式名称")
    @TableField("CONTRACTINGMODENAME")
    // @Length(max = 100, message = "contractingMode长度不在有效范围内")
    private String contractingModeName;

    @ApiModelProperty(value = "登记通过时间")
    @TableField("SUCCESSTIME")
    private LocalDate successTime;
}
