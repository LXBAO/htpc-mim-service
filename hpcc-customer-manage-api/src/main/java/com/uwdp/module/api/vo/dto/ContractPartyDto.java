package com.uwdp.module.api.vo.dto;

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
import java.time.LocalDateTime;

/**
 * <p>
 * 合同甲方
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ContractPartyDTO对象", description = "合同甲方", discriminator = "contractParty")
@SearchBean(tables = "T_CONTRACTPARTY")
public class ContractPartyDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @DbField("ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

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

    @ApiModelProperty(value = "关联业绩id")
    @DbField("PERFORMANCEID")
    // @Range(max = Long.MAX_VALUE, message = "performanceId长度不在有效范围内")
    private Long performanceId;

    @ApiModelProperty(value = "中文名称")
    @DbField("DOMESTICNAME")
    // @Length(max = 32, message = "domesticName长度不在有效范围内")
    private String domesticName;

    @ApiModelProperty(value = "外文名称")
    @DbField("FOREIGNNAME")
    // @Length(max = 32, message = "foreignName长度不在有效范围内")
    private String foreignName;

}
