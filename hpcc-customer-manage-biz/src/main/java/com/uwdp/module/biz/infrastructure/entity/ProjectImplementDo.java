package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 项目实施
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PROJECTIMPLEMENT")
@ApiModel(value = "ProjectImplementDo entity对象", description = "项目实施")
public class ProjectImplementDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
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

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("项目名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty("项目状态")
    @TableField("PROJECTSTATE")
    private String projectState;

    @ApiModelProperty("开工时间")
    @TableField("WORKDATE")
    private LocalDateTime workDate;

    @ApiModelProperty("投产时间")
    @TableField("COMMISSIONINGDATE")
    private LocalDateTime commissioningDate;

    @ApiModelProperty("未开工原因")
    @TableField("NONWORKINGCAUSE")
    private String nonWorkingCause;

    @ApiModelProperty("项目阶段")
    @TableField("PROJECTSTAGE")
    private String projectStage;

    @ApiModelProperty("项目编号")
    @TableField("PROJECTNUMBER")
    private String projectNumber;

    @ApiModelProperty("登记单位")
    @TableField("REGISTRATIONUNIT")
    private String registrationUnit;

    @ApiModelProperty("产品领域类别")
    @TableField("INDUSTRYCATEGORY")
    private String industryCategory;

    @ApiModelProperty("登记时间")
    @TableField("REGISTERDATE")
    private String registerDate;

    @ApiModelProperty("附件")
    @TableField("`FILE`")
    private String file;

    @ApiModelProperty("创建者名称")
    @TableField("CREATEDBYNAME")
    private String createdByName;

    @ApiModelProperty(value = "权限id")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty(value = "入档状态")
    @TableField("INGEAR")
    private String inGear;
}
