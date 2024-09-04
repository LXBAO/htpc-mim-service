package com.uwdp.module.api.vo.query;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目中标
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WinTheBidDo Query对象", description = "项目中标", discriminator = "WinTheBid")
public class WinTheBidQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private String createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "所属区域")
    private String owningRegion;

    @ApiModelProperty(value = "合同额(万元)")
    private Double money;

    @ApiModelProperty(value = "登记单位")
    private String registrationUnit;

    @ApiModelProperty(value = "商业模式")
    private String businessModel;

    @ApiModelProperty(value = "产业领域类别")
    private String industryCategory;

    @ApiModelProperty(value = "中标日期")
    private String data;

    @ApiModelProperty(value = "项目阶段")
    private String projectPhase;

    @ApiModelProperty(value = "项目编号")
    private String itemNumber;

    @ApiModelProperty(value = "所在省份")
    private String homeProvince;

    @ApiModelProperty(value = "预计签约时间")
    private LocalDate signingTime;

    @ApiModelProperty(value = "登记时间")
    private LocalDate registrationTime;

    @ApiModelProperty(value = "是否重大项目")
    private String majorProject;

    @ApiModelProperty(value = "是否需要专业评估")
    private String professionalEvaluation;

    @ApiModelProperty(value = "是否获取新能源指标")
    private String newEnergy;

    @ApiModelProperty(value = "是否三交九直")
    private String whetherThree;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "权限id")
    private String groupFullCode;

    @ApiModelProperty(value = "创建人名称")
    private String createdName;

    @ApiModelProperty(value = "所属公司名称")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "附件")
    private String file;

    @ApiModelProperty(value = "建设地点名称")
    private String homeProvinceName;
}
