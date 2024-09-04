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
 * 建设单位
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ConstructionUnitDTO对象", description = "建设单位", discriminator = "constructionUnit")
@SearchBean(tables = "T_CONSTRUCTIONUNIT")
public class ConstructionUnitDto implements Serializable {

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

    @ApiModelProperty(value = "关联业绩管理id")
    @DbField("PERFORMANCEID")
    // @Range(max = Long.MAX_VALUE, message = "performanceId长度不在有效范围内")
    private Long performanceId;

    @ApiModelProperty(value = "国内名称")
    @DbField("DOMESTICNAME")
    // @Length(max = 32, message = "domesticName长度不在有效范围内")
    private String domesticName;

    @ApiModelProperty(value = "国外名称(外文)")
    @DbField("FOREIGNNAME")
    // @Length(max = 32, message = "foreignName长度不在有效范围内")
    private String foreignName;

    @ApiModelProperty(value = "地址")
    @DbField("ADDRESS")
    // @Length(max = 32, message = "address长度不在有效范围内")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    @DbField("POSTALCODE")
    // @Length(max = 32, message = "postalCode长度不在有效范围内")
    private String postalCode;

    @ApiModelProperty(value = "联系人")
    @DbField("CONTACTPERSON")
    // @Length(max = 32, message = "contactPerson长度不在有效范围内")
    private String contactPerson;

    @ApiModelProperty(value = "电话")
    @DbField("PHONE")
    // @Length(max = 32, message = "phone长度不在有效范围内")
    private String phone;

}
