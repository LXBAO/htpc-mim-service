package com.uwdp.module.biz.infrastructure.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.ejlchina.searcher.bean.DbField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 公关实施
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PUBLICRELATIONS")
@ApiModel(value = "PublicRelationsDo entity对象", description = "公关实施")
public class PublicRelationsDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一标识")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建者名称")
    @TableField("CREATED_BY_NAME")
    private String createdByName;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("关联计划")
    @TableField("VISITPLANID")
    private Long visitPlanId;

    @ApiModelProperty("责任单位")
    @TableField("DUTYUNIT")
    private String dutyUnit;

    @ApiModelProperty("公关时间")
    @TableField("`DATE`")
    private LocalDateTime date;

    @ApiModelProperty("活动方式")
    @TableField("ACTIVITYWAY")
    private String activityWay;

    @ApiModelProperty("活动省市")
    @TableField("ACTIVITYPROVINCEANDCITY")
    private String activityProvinceAndCity;

    @ApiModelProperty(value = "活动省市名称")
    @TableField("ACTIVITYPROVINCEANDCITYNAME")
    private String activityProvinceAndCityName;

    @ApiModelProperty("活动地点")
    @TableField("ACTIVITYADDRESS")
    private String activityAddress;

    @ApiModelProperty("主要参会人")
    @TableField("MAINPERSON")
    private String mainPerson;

    @ApiModelProperty("主要参会人id")
    @TableField("MAINPERSONIDS")
    private String mainPersonIds;

    @ApiModelProperty("备注")
    @TableField("MEMO")
    private String memo;

    @ApiModelProperty("公关成果")
    @TableField("RESULTS")
    private String results;

    @ApiModelProperty("后续事项跟踪人")
    @TableField("FOLLOWERS")
    private String followers;

    @ApiModelProperty("文件路径")
    @TableField("FILE")
    private String file;

    @ApiModelProperty("组织全编码（group_code，分隔符：/）（集团）")
    @TableField("GROUPFULLCODE")
    private String groupFullCode;

}
