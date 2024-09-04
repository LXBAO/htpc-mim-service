package com.uwdp.module.api.vo.dto;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
@ToString
@ApiModel(value = "WinTheBidDTO对象", description = "项目中标", discriminator = "WinTheBid")
@SearchBean(tables = "T_WINTHEBID")
public class WinTheBidDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    @DbField("CREATEDNAME")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    @DbField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目名称")
    @DbField("PROJECTNAME")
    // @Length(max = 50, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "所属区域")
    @DbField("OWNINGREGION")
    // @Length(max = 50, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    @ApiModelProperty(value = "合同额(万元)")
    @DbField("MONEY")
    // @Range(max = Long.MAX_VALUE, message = "money长度不在有效范围内")
    private Double money;

    @ApiModelProperty(value = "登记单位")
    @DbField("REGISTRATIONUNIT")
    // @Length(max = 50, message = "registrationUnit长度不在有效范围内")
    private String registrationUnit;

    @ApiModelProperty(value = "商业模式")
    @DbField("BUSINESSMODEL")
    // @Length(max = 50, message = "businessModel长度不在有效范围内")
    private String businessModel;

    @ApiModelProperty(value = "产业领域类别")
    @DbField("INDUSTRYCATEGORY")
    // @Length(max = 50, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "中标日期")
    @DbField("DATA")
    private LocalDate data;

    @ApiModelProperty(value = "项目阶段")
    @DbField("PROJECTPHASE")
    // @Length(max = 50, message = "projectPhase长度不在有效范围内")
    private String projectPhase;

    @ApiModelProperty(value = "项目编号")
    @DbField("ITEMNUMBER")
    // @Length(max = 50, message = "itemNumber长度不在有效范围内")
    private String itemNumber;

    @ApiModelProperty(value = "所在省份")
    @DbField("HOMEPROVINCE")
    // @Length(max = 50, message = "homeProvince长度不在有效范围内")
    private String homeProvince;

    @ApiModelProperty(value = "预计签约时间")
    @DbField("SIGNINGTIME")
    private LocalDate signingTime;

    @ApiModelProperty(value = "登记时间")
    @DbField("REGISTRATIONTIME")
    private LocalDate registrationTime;

    @ApiModelProperty(value = "是否重大项目")
    @DbField("MAJORPROJECT")
    // @Length(max = 50, message = "majorProject长度不在有效范围内")
    private String majorProject;

    @ApiModelProperty(value = "是否需要专业评估")
    @DbField("PROFESSIONALEVALUATION")
    // @Length(max = 50, message = "professionalEvaluation长度不在有效范围内")
    private String professionalEvaluation;

    @ApiModelProperty(value = "是否获取新能源指标")
    @DbField("NEWENERGY")
    // @Length(max = 50, message = "newEnergy长度不在有效范围内")
    private String newEnergy;

    @ApiModelProperty(value = "是否三交九直")
    @DbField("WHETHERTHREE")
    // @Length(max = 50, message = "whetherThree长度不在有效范围内")
    private String whetherThree;

    @ApiModelProperty(value = "审核状态")
    @DbField("AUDITSTATUS")
    // @Length(max = 50, message = "auditStatus长度不在有效范围内")
    private String auditStatus;

    @ApiModelProperty(value = "权限id")
    @DbField("GROUPFULLCODE")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "所属公司名称")
    @DbField("GROUPBELONGUNITNAME")
    // @Length(max = 200, message = "GROUPBELONGUNITNAME长度不在有效范围内")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "附件")
    @DbField("file")
    // @Length(max = 255, message = "file长度不在有效范围内")
    private String file;

    @ApiModelProperty(value = "建设地点名称")
    @DbField("HOMEPROVINCENAME")
    // @Length(max = 50, message = "homeProvinceName长度不在有效范围内")
    private String homeProvinceName;

    //附件表
    @DbIgnore
    List<AttachmentDto> attachmentDtos;
}
