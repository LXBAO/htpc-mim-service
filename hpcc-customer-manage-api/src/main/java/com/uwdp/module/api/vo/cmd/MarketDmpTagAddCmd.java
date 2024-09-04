package com.uwdp.module.api.vo.cmd;

import com.gientech.lcds.workflow.sdk.core.runtime.CandidateInfoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 市场部分公司年度指标
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "MarketDmpTagAddCmd对象", description = "市场部分公司年度指标", discriminator = "market_dmp_tag")
public class MarketDmpTagAddCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


    @ApiModelProperty(value = "填报人名字")
    // @Length(max = 20, message = "userName长度不在有效范围内")
    private String userName;

    @ApiModelProperty(value = "部门id")
    // @Range(max = Long.MAX_VALUE, message = "dmptId长度不在有效范围内")
    private Long dmptId;

    @ApiModelProperty(value = "部门名称")
    // @Length(max = 30, message = "dmptName长度不在有效范围内")
    private String dmptName;

    @ApiModelProperty(value = "标题")
    // @Length(max = 50, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "年度")
    // @Length(max = 12, message = "year长度不在有效范围内")
    private String year;

    @ApiModelProperty(value = "力争指标金额总计")
    // @Range(max = Long.MAX_VALUE, message = "toAmt长度不在有效范围内")
    private Double toAmtTotal;

    @ApiModelProperty(value = "确保指标金额总计")
    // @Range(max = Long.MAX_VALUE, message = "formAmt长度不在有效范围内")
    private Double formAmtTotal;

    @ApiModelProperty(value = "组织全编码（group_code，分隔符：/）（集团）")
    // @Length(max = 500, message = "group_code长度不在有效范围内")
    private String GroupFullCode;

    @ApiModelProperty(value = "指标明细集合")
    private List<MarketDmpTagDetailAddCmd> marketDmpTagDetailList;

    @ApiModelProperty(value = "工作流指定审批人")
    protected Map<String, List<CandidateInfoDto>> approveUser;

}
