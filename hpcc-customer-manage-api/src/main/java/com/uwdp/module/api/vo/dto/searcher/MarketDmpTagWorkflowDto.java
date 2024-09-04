package com.uwdp.module.api.vo.dto.searcher;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.uwdp.module.api.vo.dto.MarketDmpTagDetailDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@SearchBean(
        tables = "T_MARKET_DMP_TAG m left join T_LMCWORKFLOW l on m.ID =  l.BIZID"
)
@ToString
@Data
public class MarketDmpTagWorkflowDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("流程实例id")
    @DbField("l.PROCESSINSTANCEID")
    private String processInstanceId;

    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;

    @ApiModelProperty(value = "主键")
    @DbField("m.ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("m.CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("m.CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("m.UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("m.UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "填报人名字")
    @DbField("m.USER_NAME")
    // @Length(max = 20, message = "userName长度不在有效范围内")
    private String userName;

    @ApiModelProperty(value = "部门id")
    @DbField("m.DMPT_ID")
    // @Range(max = Long.MAX_VALUE, message = "dmptId长度不在有效范围内")
    private Long dmptId;

    @ApiModelProperty(value = "部门名称")
    @DbField("m.DMPT_NAME")
    // @Length(max = 30, message = "dmptName长度不在有效范围内")
    private String dmptName;

    @ApiModelProperty(value = "标题")
    @DbField("m.TITLE")
    // @Length(max = 50, message = "title长度不在有效范围内")
    private String title;

    @ApiModelProperty(value = "年度")
    @DbField("m.YEAR")
    // @Length(max = 12, message = "year长度不在有效范围内")
    private String year;

    @ApiModelProperty(value = "力争指标金额总计")
    @DbField("m.TOAMTTOTAL")
    // @Range(max = Long.MAX_VALUE, message = "toAmt长度不在有效范围内")
    private Double toAmtTotal;

    @ApiModelProperty(value = "确保指标金额总计")
    @DbField("m.FORMAMTTOTAL")
    // @Range(max = Long.MAX_VALUE, message = "formAmt长度不在有效范围内")
    private Double formAmtTotal;

    @DbIgnore
    private List<MarketDmpTagDetailDto> marketDmpTagDetailDto;

    @ApiModelProperty(value = "创建人名称")
    @DbField("m.CREATEDNAME")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;
}
