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
 * 历史记录表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LogSheetDTO对象", description = "历史记录表", discriminator = "logSheet")
@SearchBean(tables = "T_LOGSHEET")
public class LogSheetDto implements Serializable {

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

    @ApiModelProperty(value = "客户id")
    @DbField("CLIENTID")
    // @Length(max = 100, message = "clientId长度不在有效范围内")
    private String clientId;

    @ApiModelProperty(value = "版本编号")
    @DbField("VERSION")
    // @Length(max = 100, message = "version长度不在有效范围内")
    private String version;

    @ApiModelProperty(value = "更新人")
    @DbField("UPDATEPERSON")
    // @Length(max = 100, message = "updatePerson长度不在有效范围内")
    private String updatePerson;

    @ApiModelProperty(value = "客户信息")
    @DbField("CLIENTINFORMATION")
    private String clientInformation;

}
