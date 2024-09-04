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
 * 联系人
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "LinkmanDTO对象", description = "联系人", discriminator = "linkman")
@SearchBean(tables = "T_LINKMAN")
public class LinkmanDto implements Serializable {

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

    @ApiModelProperty(value = "岗位")
    @DbField("JOB")
    // @Length(max = 100, message = "job长度不在有效范围内")
    private String job;

    @ApiModelProperty(value = "姓名")
    @DbField("NAME")
    // @Length(max = 100, message = "name长度不在有效范围内")
    private String name;

    @ApiModelProperty(value = "状态")
    @DbField("STATE")
    // @Length(max = 100, message = "state长度不在有效范围内")
    private String state;

    @ApiModelProperty(value = "投标id")
    @DbField("BIDDINGID")
    // @Length(max = 100, message = "biddingId长度不在有效范围内")
    private String biddingId;

    @ApiModelProperty(value = "项目经理id")
    @DbField("nameId")
    // @Length(max = 100, message = "nameId长度不在有效范围内")
    private String nameId;

    @ApiModelProperty(value = "关联人资id")
    @DbField("hrId")
    // @Length(max = 100, message = "hrId长度不在有效范围内")
    private String hrId;
}
