package com.uwdp.module.biz.infrastructure.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_MDMPOST")
@ApiModel(value = "MdmPostDo entity对象", description = "主数据-岗位")
public class MdmPostDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(deserialize = false) /* 设置反序列化时忽略该字段  */
    @ApiModelProperty("唯一标识")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建者")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新者")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty("所属部门编码（集团）")
    @TableField("GROUPBELONGDEPARTMENTCODE")
    private String groupBelongDepartmentCode;

    @ApiModelProperty("所属部门名称（集团）")
    @TableField("GROUPBELONGDEPARTMENTNAME")
    private String groupBelongDepartmentName;

    @ApiModelProperty("所属单位编码（集团）")
    @TableField("GROUPBELONGUNITCODE")
    private String groupBelongUnitCode;

    @ApiModelProperty("所属单位名称（集团）")
    @TableField("GROUPBELONGUNITNAME")
    private String groupBelongUnitName;

    @ApiModelProperty("岗位类别（集团）")
    @TableField("GROUPPOSTCATEGORY")
    private String groupPostCategory;

    @ApiModelProperty("岗位编码（集团唯一）")
    @TableField("GROUPPOSTCODE")
    private String groupPostCode;

    @ApiModelProperty("岗位名称（集团）")
    @TableField("GROUPPOSTNAME")
    private String groupPostName;

    @ApiModelProperty("标准岗位名称（集团）")
    @TableField("GROUPSTANDARDPOSTNAME")
    private String groupStandardPostName;

    @ApiModelProperty("唯一标识")
    @TableField("MDMID")
    private Long mdmId;

    @ApiModelProperty("是否删除（0：否；1：是）")
    @TableField("ISDELETED")
    private Integer isDeleted;

    @ApiModelProperty("劳保用品类型（公司）")
    @TableField("LABORTYPE")
    private String laborType;

    @ApiModelProperty("主数据编码")
    @TableField("MDMCODE")
    private String mdmCode;

    @ApiModelProperty("岗位编码（公司）")
    @TableField("POSTCODE")
    private String postCode;

    @ApiModelProperty("岗位名称（公司）")
    @TableField("POSTNAME")
    private String postName;

    @ApiModelProperty("岗位性质（4：生产；5：服务；6：其他；7：管理；8：技术；9：技能）（公司）")
    @TableField("POSTPROPERTY")
    private String postProperty;

    @ApiModelProperty("岗位类型（标准岗位/业务岗位）（公司）")
    @TableField("POSTTYPE")
    private String postType;

    @ApiModelProperty("关联的标岗编码（公司）")
    @TableField("RELEVANCEPOSTCODE")
    private String relevancePostCode;

    @ApiModelProperty("状态编码（0：停用；1：启用）（集团）")
    @TableField("`STATUS`")
    private String status;

    @ApiModelProperty("状态描述（集团）")
    @TableField("STATUSDESC")
    private String statusDesc;

    @ApiModelProperty("数据版本号,默认为1,变更一次,加1")
    @TableField("VERSION")
    private String version;

    @ApiModelProperty("本地数据版本号,默认为1,变更一次,加1")
    @TableField("LOCALVERSION")
    private Integer localVersion;

}
