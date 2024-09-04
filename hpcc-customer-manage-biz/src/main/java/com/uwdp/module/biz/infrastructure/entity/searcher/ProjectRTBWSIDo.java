package com.uwdp.module.biz.infrastructure.entity.searcher;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
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
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_ATTACHMENT")
@ApiModel(value = "ProjectRTBWSIDo entity对象", description = "项目登记、跟踪、投标、中标、签约、实施连表查询")
public class ProjectRTBWSIDo implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    @DbField("r.PROJECTNUMBER")
    private String projectNumber;

    @ApiModelProperty(value = "项目名称")
    @DbField("s.PROJECTNAME")
    private String projectName;

    @ApiModelProperty(value = "建设单位")
    @DbField("s.CONSTRUCTIONUNIT")
    private String constructionUnit;

    @ApiModelProperty(value = "参与资质或其他")
    @DbField("t.QUALIFICATIONOROTHERWISE")
    private String qualificationOrOtherwise;

    @ApiModelProperty(value = "合同金额(万元)")
    @DbField("s.CONTRACTAMOUNT")
    private BigDecimal contractAmount;

    @ApiModelProperty(value = "产业领域类别", required = true)
    @DbField("r.INDUSTRYCATEGORY")
    private String industryCategory;

    @ApiModelProperty(value = "重要程度")
    @DbField("r.IMPORTANTTYPE")
    private String importantType;

    @ApiModelProperty(value = "产业链类别")
    @DbField("t.INDUSTRYCHAINCATEGORY")
    private String industryChainCategory;

    @ApiModelProperty(value = "新产业类别")
    @DbField("s.NEWINDUSTRY")
    private String newIndustry;


    @ApiModelProperty(value = "商业模式")
    @DbField("w.BUSINESSMODEL")
    private String businessModel;

    @ApiModelProperty(value = "建设地点")
    @DbField("r.LOCATION")
    private String location;

    @ApiModelProperty(value = "合同签订时间")
    @DbField("s.TIMEOFSIGNING")
    private String timeOfSigning;

    @ApiModelProperty(value = "合同开始时间")
    @DbField("s.COMMENCEMENTOFCONTRACT")
    private String commencementOfContract;

    @ApiModelProperty(value = "合同结束时间")
    @DbField("s.CONTRACTENDTIME")
    private String contractEndTime;

    @ApiModelProperty(value = "主要实物量指标1")
    @DbField("s.MAINPHYSICALQUANTITYINDEXONE")
    private String mainPhysicalQuantityIndexOne;

    @ApiModelProperty(value = "主要实物量指标2")
    @DbField("s.MAINPHYSICALQUANTITYINDEXTWO")
    private String mainPhysicalQuantityIndexTwo;

    @ApiModelProperty(value = "开工时间")
    @DbField("i.WORKDATE")
    private LocalDateTime workDate;

    @ApiModelProperty(value = "主要实物量1")
    @DbField("s.PRINCIPALPHYSICALQUANTITYONE")
    private String principalPhysicalQuantityOne;

    @ApiModelProperty(value = "主要实物量2")
    @DbField("s.PRINCIPALPHYSICALQUANTITYTWO")
    private String principalPhysicalQuantityTwo;

    @ApiModelProperty(value = "投产时间")
    @DbField("i.COMMISSIONINGDATE")
    private LocalDateTime commissioningDate;

    @ApiModelProperty(value = "项目基本情况")
    @DbField("t.PROJECTSITUATION")
    private String projectSituation;

    @ApiModelProperty(value = "备注")
    @DbField("b.REMARK")
    private String remark;


}
