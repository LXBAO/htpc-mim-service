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
 * 更新查看
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "UpdateViewDTO对象", description = "更新查看", discriminator = "updateView")
@SearchBean(tables = "T_UPDATEVIEW")
public class UpdateViewDto implements Serializable {

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

    @ApiModelProperty(value = "更新人")
    @DbField("UPDATEPERSON")
    // @Length(max = 50, message = "updatePerson长度不在有效范围内")
    private String updatePerson;

    @ApiModelProperty(value = "更新时间")
    @DbField("UPDATEDATE")
    // @Length(max = 50, message = "updateDate长度不在有效范围内")
    private String updateDate;

    @ApiModelProperty(value = "行信息id")
    @DbField("ROWID")
    // @Length(max = 100, message = "rowId长度不在有效范围内")
    private String rowId;

    @ApiModelProperty(value = "公司名称")
    @DbField("companyName")
    // @Length(max = 60, message = "companyName长度不在有效范围内")
    private String companyName;

    @ApiModelProperty(value = "力争金额")
    @DbField("toAmt")
    // @Range(max = Long.MAX_VALUE, message = "toAmt长度不在有效范围内")
    private Double toAmt;

    @ApiModelProperty(value = "确保金额")
    @DbField("formAmt")
    // @Range(max = Long.MAX_VALUE, message = "formAmt长度不在有效范围内")
    private Double formAmt;

    @ApiModelProperty(value = "更新表详情id")
    @DbField("mdtDetailId")
    // @Range(max = 60, message = "mdtDetailId长度不在有效范围内")
    private Double mdtDetailId;
}
