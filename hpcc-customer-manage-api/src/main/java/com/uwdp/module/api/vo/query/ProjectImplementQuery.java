package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 项目实施
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProjectImplementDo Query对象", description = "项目实施", discriminator = "projectImplement")
public class ProjectImplementQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
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

    @ApiModelProperty(value = "项目状态")
    private String projectState;

    @ApiModelProperty(value = "开工时间")
    private LocalDateTime workDate;

    @ApiModelProperty(value = "投产时间")
    private LocalDateTime commissioningDate;

    @ApiModelProperty(value = "未开工原因")
    private String nonWorkingCause;

    @ApiModelProperty(value = "项目阶段")
    private String projectStage;

    @ApiModelProperty(value = "项目编号")
    private String projectNumber;

    @ApiModelProperty(value = "登记单位")
    private String registrationUnit;

    @ApiModelProperty(value = "产品领域类别")
    private String industryCategory;

    @ApiModelProperty(value = "登记时间")
    private String registerDate;

    @ApiModelProperty(value = "附件")
    private String file;

    @ApiModelProperty(value = "创建者名称")
    private String createdByName;

    @ApiModelProperty(value = "权限id")
    private String groupFullCode;

    @ApiModelProperty(value = "入档状态")
    private String inGear;
}
