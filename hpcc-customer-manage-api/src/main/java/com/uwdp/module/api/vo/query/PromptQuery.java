package com.uwdp.module.api.vo.query;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 信息提示
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PromptDo Query对象", description = "信息提示", discriminator = "prompt")
public class PromptQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建者名称")
    private String createdName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "提示信息标题  ")
    private String promptTitle;

    @ApiModelProperty(value = "提示信息详情")
    private String promptDetails;

    @ApiModelProperty(value = "过期时间")
    private LocalDate expireDate;

    @ApiModelProperty(value = "需要提示的工号")
    private String promptId;

    @ApiModelProperty(value = "已读/未读")
    private String promptStatus;

    @ApiModelProperty(value = "删除/未删")
    private String deleteStatus;

    @ApiModelProperty(value = "QID")
    private Long qid;

    @ApiModelProperty(value = "跳转路径")
    private String promptPath;


}
