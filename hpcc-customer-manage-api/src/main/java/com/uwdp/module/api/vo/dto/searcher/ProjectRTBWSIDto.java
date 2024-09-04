package com.uwdp.module.api.vo.dto.searcher;

import com.ejlchina.searcher.bean.DbField;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目签约
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectSigningDTO对象", description = "项目签约", discriminator = "ProjectSigning")
@SearchBean(tables = "t_projectrecords r\n" +
        "    join t_projectbidding b on r.id = b.projectNumber\n" +
        "    join t_winthebid w on r.id = w.ITEMNUMBER\n" +
        "    join t_projectsigning s on r.id = s.PROJECTNUMBER\n" +
        "    join t_projectimplement i on r.id = i.PROJECTNUMBER") //"    join t_projecttracking t on r.ID = t.PROJECTNUMBER\n" +
public class ProjectRTBWSIDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("r.ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("r.CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    @DbField("r.CREATEDNAME")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "项目编号")
    @DbField("r.PROJECTNUMBER")
    // @Length(max = 255, message = "projectNumber长度不在有效范围内")
    private String projectNumber;

    @ApiModelProperty(value = "项目名称")
    @DbField("r.PROJECTNAME")
    // @Length(max = 255, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "建设单位")
    @DbField("s.CONSTRUCTIONUNIT")
    // @Length(max = 50, message = "constructionUnit长度不在有效范围内")
    private String constructionUnit;

    /*@ApiModelProperty(value = "参与资质或其他")
    @DbField("t.QUALIFICATIONOROTHERWISE")
    // @Length(max = 50, message = "qualificationOrOtherwise长度不在有效范围内")
    private String qualificationOrOtherwise;*/

    @ApiModelProperty(value = "合同金额(万元)")
    @DbField("s.CONTRACTAMOUNT")
    // @Range(max = Long.MAX_VALUE, message = "contractAmount长度不在有效范围内")
    private BigDecimal contractAmount;

    @ApiModelProperty(value = "产业领域类别", required = true)
    @DbField("r.INDUSTRYCATEGORY")
    // @Length(max = 255, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "重要程度")
    @DbField("r.IMPORTANTTYPE")
    // @Length(max = 255, message = "importantType长度不在有效范围内")
    private String importantType;

    /*@ApiModelProperty(value = "产业链类别")
    @DbField("t.INDUSTRYCHAINCATEGORY")
    // @Length(max = 50, message = "industryChainCategory长度不在有效范围内")
    private String industryChainCategory;*/

    @ApiModelProperty(value = "新产业类别")
    @DbField("s.NEWINDUSTRY")
    // @Length(max = 50, message = "newIndustry长度不在有效范围内")
    private String newIndustry;


    @ApiModelProperty(value = "商业模式")
    @DbField("w.BUSINESSMODEL")
    // @Length(max = 50, message = "businessModel长度不在有效范围内")
    private String businessModel;

    @ApiModelProperty(value = "建设地点")
    @DbField("r.LOCATION")
    // @Length(max = 255, message = "location长度不在有效范围内")
    private String location;

    @ApiModelProperty(value = "合同签订时间")
    @DbField("s.TIMEOFSIGNING")
    // @Length(max = 255, message = "timeOfSigning长度不在有效范围内")
    private String timeOfSigning;

    @ApiModelProperty(value = "合同开始时间")
    @DbField("s.COMMENCEMENTOFCONTRACT")
    // @Length(max = 255, message = "commencementOfContract长度不在有效范围内")
    private String commencementOfContract;

    @ApiModelProperty(value = "合同结束时间")
    @DbField("s.CONTRACTENDTIME")
    // @Length(max = 253, message = "contractEndTime长度不在有效范围内")
    private String contractEndTime;

    @ApiModelProperty(value = "主要实物量指标1")
    @DbField("s.MAINPHYSICALQUANTITYINDEXONE")
    // @Length(max = 255, message = "mainPhysicalQuantityIndexOne长度不在有效范围内")
    private String mainPhysicalQuantityIndexOne;

    @ApiModelProperty(value = "主要实物量指标2")
    @DbField("s.MAINPHYSICALQUANTITYINDEXTWO")
    // @Length(max = 255, message = "mainPhysicalQuantityIndexTwo长度不在有效范围内")
    private String mainPhysicalQuantityIndexTwo;

    @ApiModelProperty(value = "开工时间")
    @DbField("i.WORKDATE")
    private LocalDateTime workDate;

    @ApiModelProperty(value = "主要实物量1")
    @DbField("s.PRINCIPALPHYSICALQUANTITYONE")
    // @Length(max = 255, message = "principalPhysicalQuantityOne长度不在有效范围内")
    private String principalPhysicalQuantityOne;

    @ApiModelProperty(value = "主要实物量2")
    @DbField("s.PRINCIPALPHYSICALQUANTITYTWO")
    // @Length(max = 255, message = "principalPhysicalQuantityTwo长度不在有效范围内")
    private String principalPhysicalQuantityTwo;

    @ApiModelProperty(value = "投产时间")
    @DbField("i.COMMISSIONINGDATE")
    private LocalDateTime commissioningDate;

    /*@ApiModelProperty(value = "项目基本情况")
    @DbField("t.PROJECTSITUATION")
    // @Length(max = 100, message = "projectSituation长度不在有效范围内")
    private String projectSituation;*/

    @ApiModelProperty(value = "备注")
    @DbField("b.REMARK")
    // @Length(max = 50, message = "remark长度不在有效范围内")
    private String remark;


}
