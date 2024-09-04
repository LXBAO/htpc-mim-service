package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uwdp.module.api.vo.cmd.AttachmentAddCmd;
import com.uwdp.module.api.vo.dto.AttachmentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 项目中标
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_WINTHEBID")
@ApiModel(value = "WinTheBidDo entity对象", description = "项目中标")
public class WinTheBidDo implements Serializable {

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

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("项目名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty("所属区域")
    @TableField("OWNINGREGION")
    private String owningRegion;

    @ApiModelProperty("合同额(万元)")
    @TableField("MONEY")
    private Double money;

    @ApiModelProperty("登记单位")
    @TableField("REGISTRATIONUNIT")
    private String registrationUnit;

    @ApiModelProperty("商业模式")
    @TableField("BUSINESSMODEL")
    private String businessModel;

    @ApiModelProperty("产业领域类别")
    @TableField("INDUSTRYCATEGORY")
    private String industryCategory;

    @ApiModelProperty("中标日期")
    @TableField("`DATA`")
    private String data;

    @ApiModelProperty("项目阶段")
    @TableField("PROJECTPHASE")
    private String projectPhase;

    @ApiModelProperty("项目编号")
    @TableField("ITEMNUMBER")
    private String itemNumber;

    @ApiModelProperty("所在省份")
    @TableField("HOMEPROVINCE")
    private String homeProvince;

    @ApiModelProperty("预计签约时间")
    @TableField("SIGNINGTIME")
    private LocalDate signingTime;

    @ApiModelProperty("登记时间")
    @TableField("REGISTRATIONTIME")
    private LocalDate registrationTime;

    @ApiModelProperty("是否重大项目")
    @TableField("MAJORPROJECT")
    private String majorProject;

    @ApiModelProperty("是否需要专业评估")
    @TableField("PROFESSIONALEVALUATION")
    private String professionalEvaluation;

    @ApiModelProperty("是否获取新能源指标")
    @TableField("NEWENERGY")
    private String newEnergy;

    @ApiModelProperty("是否三交九直")
    @TableField("WHETHERTHREE")
    private String whetherThree;

    @ApiModelProperty("审核状态")
    @TableField("AUDITSTATUS")
    private String auditStatus;

    @ApiModelProperty(value = "权限id")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

    @ApiModelProperty("创建人名称")
    @TableField("CREATEDNAME")
    private String createdName;

    @ApiModelProperty(value = "所属公司名称")
    @TableField("GROUPBELONGUNITNAME")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "建设地点名称")
    @TableField("HOMEPROVINCENAME")
    private String homeProvinceName;

    @ApiModelProperty(value = "附件")
    @TableField("file")
    private String file;
}
