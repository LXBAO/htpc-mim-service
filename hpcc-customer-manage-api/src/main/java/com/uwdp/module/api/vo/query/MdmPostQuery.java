package com.uwdp.module.api.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 主数据-岗位
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MdmPostDo Query对象", description = "主数据-岗位", discriminator = "mdmPost")
public class MdmPostQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "所属部门编码（集团）")
    private String groupBelongDepartmentCode;

    @ApiModelProperty(value = "所属部门名称（集团）")
    private String groupBelongDepartmentName;

    @ApiModelProperty(value = "所属单位编码（集团）")
    private String groupBelongUnitCode;

    @ApiModelProperty(value = "所属单位名称（集团）")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "岗位类别（集团）")
    private String groupPostCategory;

    @ApiModelProperty(value = "岗位编码（集团唯一）")
    private String groupPostCode;

    @ApiModelProperty(value = "岗位名称（集团）")
    private String groupPostName;

    @ApiModelProperty(value = "标准岗位名称（集团）")
    private String groupStandardPostName;

    @ApiModelProperty(value = "唯一标识")
    private Long mdmId;

    @ApiModelProperty(value = "是否删除（0：否；1：是）")
    private Integer isDeleted;

    @ApiModelProperty(value = "劳保用品类型（公司）")
    private String laborType;

    @ApiModelProperty(value = "主数据编码")
    private String mdmCode;

    @ApiModelProperty(value = "岗位编码（公司）")
    private String postCode;

    @ApiModelProperty(value = "岗位名称（公司）")
    private String postName;

    @ApiModelProperty(value = "岗位性质（4：生产；5：服务；6：其他；7：管理；8：技术；9：技能）（公司）")
    private String postProperty;

    @ApiModelProperty(value = "岗位类型（标准岗位/业务岗位）（公司）")
    private String postType;

    @ApiModelProperty(value = "关联的标岗编码（公司）")
    private String relevancePostCode;

    @ApiModelProperty(value = "状态编码（0：停用；1：启用）（集团）")
    private String status;

    @ApiModelProperty(value = "状态描述（集团）")
    private String statusDesc;

    @ApiModelProperty(value = "数据版本号,默认为1,变更一次,加1")
    private String version;

    @ApiModelProperty(value = "本地数据版本号,默认为1,变更一次,加1")
    private Integer localVersion;
}
