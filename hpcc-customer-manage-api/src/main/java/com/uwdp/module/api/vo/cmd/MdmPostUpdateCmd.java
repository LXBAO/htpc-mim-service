package com.uwdp.module.api.vo.cmd;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.annotation.PatternCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import lombok.*;

/**
 * <p>
 * 主数据-岗位
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "MdmPost UpdateCmd对象", description = "主数据-岗位", discriminator = "mdmPost")
public class MdmPostUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识", required = true)
    @NotNull(message = "主键不能为空")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "所属部门编码（集团）")
    // @Length(max = 100, message = "groupBelongDepartmentCode长度不在有效范围内")
    private String groupBelongDepartmentCode;

    @ApiModelProperty(value = "所属部门名称（集团）")
    // @Length(max = 100, message = "groupBelongDepartmentName长度不在有效范围内")
    private String groupBelongDepartmentName;

    @ApiModelProperty(value = "所属单位编码（集团）")
    // @Length(max = 100, message = "groupBelongUnitCode长度不在有效范围内")
    private String groupBelongUnitCode;

    @ApiModelProperty(value = "所属单位名称（集团）")
    // @Length(max = 100, message = "groupBelongUnitName长度不在有效范围内")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "岗位类别（集团）")
    // @Length(max = 50, message = "groupPostCategory长度不在有效范围内")
    private String groupPostCategory;

    @ApiModelProperty(value = "岗位编码（集团唯一）")
    // @Length(max = 100, message = "groupPostCode长度不在有效范围内")
    private String groupPostCode;

    @ApiModelProperty(value = "岗位名称（集团）")
    // @Length(max = 100, message = "groupPostName长度不在有效范围内")
    private String groupPostName;

    @ApiModelProperty(value = "标准岗位名称（集团）")
    // @Length(max = 150, message = "groupStandardPostName长度不在有效范围内")
    private String groupStandardPostName;

    @ApiModelProperty(value = "唯一标识")
    // @Range(max = Long.MAX_VALUE, message = "mdmId长度不在有效范围内")
    private Long mdmId;

    @ApiModelProperty(value = "是否删除（0：否；1：是）")
    // @Range(max = Long.MAX_VALUE, message = "isDeleted长度不在有效范围内")
    private Integer isDeleted;

    @ApiModelProperty(value = "劳保用品类型（公司）")
    // @Length(max = 50, message = "laborType长度不在有效范围内")
    private String laborType;

    @ApiModelProperty(value = "主数据编码")
    // @Length(max = 50, message = "mdmCode长度不在有效范围内")
    private String mdmCode;

    @ApiModelProperty(value = "岗位编码（公司）")
    // @Length(max = 50, message = "postCode长度不在有效范围内")
    private String postCode;

    @ApiModelProperty(value = "岗位名称（公司）")
    // @Length(max = 100, message = "postName长度不在有效范围内")
    private String postName;

    @ApiModelProperty(value = "岗位性质（4：生产；5：服务；6：其他；7：管理；8：技术；9：技能）（公司）")
    // @Length(max = 20, message = "postProperty长度不在有效范围内")
    private String postProperty;

    @ApiModelProperty(value = "岗位类型（标准岗位/业务岗位）（公司）")
    // @Length(max = 10, message = "postType长度不在有效范围内")
    private String postType;

    @ApiModelProperty(value = "关联的标岗编码（公司）")
    // @Length(max = 50, message = "relevancePostCode长度不在有效范围内")
    private String relevancePostCode;

    @ApiModelProperty(value = "状态编码（0：停用；1：启用）（集团）")
    // @Length(max = 10, message = "status长度不在有效范围内")
    private String status;

    @ApiModelProperty(value = "状态描述（集团）")
    // @Length(max = 50, message = "statusDesc长度不在有效范围内")
    private String statusDesc;

    @ApiModelProperty(value = "数据版本号,默认为1,变更一次,加1")
    // @Length(max = 64, message = "version长度不在有效范围内")
    private String version;

    @ApiModelProperty(value = "本地数据版本号,默认为1,变更一次,加1")
    // @Range(max = Long.MAX_VALUE, message = "localVersion长度不在有效范围内")
    private Integer localVersion;


}
