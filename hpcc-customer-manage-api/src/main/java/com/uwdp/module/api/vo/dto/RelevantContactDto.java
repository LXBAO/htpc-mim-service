package com.uwdp.module.api.vo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uwdp.module.api.vo.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 客户相关联系人
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "RelevantContactDTO对象", description = "客户相关联系人", discriminator = "relevantContact")
@SearchBean(tables = "T_RELEVANTCONTACT")
public class RelevantContactDto implements Serializable {

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

    @ApiModelProperty(value = "关联客户ID", required = true)
    @DbField("CLIENTID")
    // @Range(max = Long.MAX_VALUE, message = "clientId长度不在有效范围内")
    private Long clientId;

    @ApiModelProperty(value = "联系人姓名", required = true)
    @DbField("NAME")
    // @Length(max = 50, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "联系人职位")
    @DbField("POSITION ")
    // @Length(max = 100, message = "position 长度不在有效范围内")
    private String position ;

    @ApiModelProperty(value = "联系方式", required = true)
    @DbField("CONTACT")
    // @Length(max = 100, message = "contact长度不在有效范围内")
    private String contact;

    @ApiModelProperty(value = "联系地址")
    @DbField("CONTACTADDRESS")
    // @Length(max = 255, message = "contactAddress长度不在有效范围内")
    private String contactAddress;


}
