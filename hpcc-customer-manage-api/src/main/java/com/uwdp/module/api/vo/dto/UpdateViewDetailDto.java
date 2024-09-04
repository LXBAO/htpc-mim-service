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
 * 更新查看详情
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "UpdateViewDetailDTO对象", description = "更新查看详情", discriminator = "updateViewDetail")
@SearchBean(tables = "T_UPDATEVIEWDETAIL")
public class UpdateViewDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
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

    @ApiModelProperty(value = "公司名称")
    @DbField("COMPANYNAME")
    // @Length(max = 60, message = "companyName长度不在有效范围内")
    private String companyName;

    @ApiModelProperty(value = "力争金额")
    @DbField("TOAMT")
    // @Length(max = 60, message = "toAmt长度不在有效范围内")
    private String toAmt;

    @ApiModelProperty(value = "确保金额")
    @DbField("FORMAMT")
    // @Length(max = 60, message = "formAmt长度不在有效范围内")
    private String formAmt;

    @ApiModelProperty(value = "更新表详情id")
    @DbField("MDTDETAILID")
    // @Length(max = 60, message = "mdtDetailId长度不在有效范围内")
    private String mdtDetailId;

    @ApiModelProperty(value = "更新人")
    @DbField("UPDATEPERSON")
    // @Length(max = 50, message = "updatePerson长度不在有效范围内")
    private String updatePerson;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATEDATE")
    // @Length(max = 50, message = "updateDate长度不在有效范围内")
    private String updateDate;

    @ApiModelProperty(value = "更新表id")
    @DbField("mdtId")
    // @Length(max = 60, message = "mdtId长度不在有效范围内")
    private String mdtId;
}
