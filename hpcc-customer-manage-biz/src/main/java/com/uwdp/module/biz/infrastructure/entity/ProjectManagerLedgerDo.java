package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ejlchina.searcher.bean.DbField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目经理台账
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("T_PROJECTMANAGERLEDGER")
@ApiModel(value = "ProjectManagerLedgerDo entity对象", description = "项目经理台账")
public class ProjectManagerLedgerDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "FDID", type = IdType.ASSIGN_ID)
    private Long fdId;

    @ApiModelProperty(value = "关联人资id")
    @TableField("HRID")
    private String hrId;

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

    @ApiModelProperty("工号")
    @TableField("FDJOBNUMBER")
    private String fdJobNumber;

    @ApiModelProperty("姓名")
    @TableField("FDPMNAME")
    private String fdPMName;

    @ApiModelProperty("身份证号码")
    @TableField("FDIDCARDNO")
    private String fdIDCardNo;

    @ApiModelProperty("手机号码")
    @TableField("FDPHONENUMBER")
    private String fdPhoneNumber;

    @ApiModelProperty("单位")
    @TableField("FDUNIT")
    private String fdUnit;

    @ApiModelProperty("部门")
    @TableField("FDDEPARTMENT")
    private String fdDepartment;

    @ApiModelProperty("岗位")
    @TableField("FDPOSITION")
    private String fdPosition;

    @ApiModelProperty("岗位性质")
    @TableField("FDNATUREOFPOST")
    private String fdNatureOfPost;

    @ApiModelProperty("岗位层级")
    @TableField("FDJOBLEVEL")
    private String fdJobLevel;

    @ApiModelProperty("职业资格")
    @TableField("FDNVQ")
    private String fdNVQ;

    @ApiModelProperty("安全证书")
    @TableField("FDSAFETYCERTIFICATE")
    private String fdSafetyCertificate;

    @ApiModelProperty("证书编号(如果是九大员：证件编号 项目经理：证书编号")
    @TableField("FDCERTIFICATENO")
    private String fdCertificateNo;

    @ApiModelProperty("授予单位")
    @TableField("FDGRANTINGUNIT")
    private String fdGrantingUnit;

    @ApiModelProperty("授予时间")
    @TableField("FDGRANTTIME")
    private LocalDateTime fdGrantTime;

    @ApiModelProperty("获得单位")
    @TableField("FDGAINUNITS")
    private String fdGainUnits;

    @ApiModelProperty("最新注册时间")
    @TableField("FDNEWREGISTERTIME")
    private LocalDateTime fdNewRegisterTime;

    @ApiModelProperty("到期时间")
    @TableField("FDEXPIREDATE")
    private LocalDateTime fdExpireDate;

    @ApiModelProperty("备注")
    @TableField("FDMEMO")
    private String fdMemo;

    @ApiModelProperty("证件名称")
    @TableField("FDCERTIFICATENAME")
    private String fdCertificateName;

    @ApiModelProperty("证件编号")
    @TableField("FDCERTIFICATENUM")
    private String fdCertificateNum;

    @ApiModelProperty("证件内容")
    @TableField("FDCERTIFICATECONTENT")
    private String fdCertificateContent;

    @ApiModelProperty("证件类别")
    @TableField("FDCERTIFICATECATEGORY")
    private String fdCertificateCategory;

    @ApiModelProperty("发证日期")
    @TableField("FDLICENCEISSUED")
    private LocalDateTime fdLicenceIssued;

    @ApiModelProperty("有效期开始日期")
    @TableField("FDEXPIRATIONDATE")
    private LocalDateTime fdExpirationDate;

    @ApiModelProperty("有效期截止日期")
    @TableField("FDEXPIRYDATE")
    private LocalDateTime fdExpiryDate;

    @ApiModelProperty("人员类型")
    @TableField("FDPERSONNELTYPE")
    private String fdPersonnelType;

    @ApiModelProperty("项目投入状态")
    @TableField("FDINPUTTYPE")
    private String fdInputType;

}
