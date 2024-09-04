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
 * 项目经理台账
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ProjectManagerLedgerDTO对象", description = "项目经理台账", discriminator = "projectManagerLedger")
@SearchBean(tables = "T_PROJECTMANAGERLEDGER")
public class ProjectManagerLedgerDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @DbField("FDID")
    // @Range(max = Long.MAX_VALUE, message = "fdId长度不在有效范围内")
    private Long fdId;

    @ApiModelProperty(value = "关联人资id")
    @DbField("HRID")
    private String hrId;

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

    @ApiModelProperty(value = "工号")
    @DbField("FDJOBNUMBER")
    // @Length(max = 36, message = "fdJobNumber长度不在有效范围内")
    private String fdJobNumber;

    @ApiModelProperty(value = "姓名")
    @DbField("FDPMNAME")
    // @Length(max = 200, message = "fdPMName长度不在有效范围内")
    private String fdPMName;

    @ApiModelProperty(value = "身份证号码")
    @DbField("FDIDCARDNO")
    // @Length(max = 200, message = "fdIDCardNo长度不在有效范围内")
    private String fdIDCardNo;

    @ApiModelProperty(value = "手机号码")
    @DbField("FDPHONENUMBER")
    // @Length(max = 200, message = "fdPhoneNumber长度不在有效范围内")
    private String fdPhoneNumber;

    @ApiModelProperty(value = "单位")
    @DbField("FDUNIT")
    // @Length(max = 200, message = "fdUnit长度不在有效范围内")
    private String fdUnit;

    @ApiModelProperty(value = "部门")
    @DbField("FDDEPARTMENT")
    // @Length(max = 200, message = "fdDepartment长度不在有效范围内")
    private String fdDepartment;

    @ApiModelProperty(value = "岗位")
    @DbField("FDPOSITION")
    // @Length(max = 200, message = "fdPosition长度不在有效范围内")
    private String fdPosition;

    @ApiModelProperty(value = "岗位性质")
    @DbField("FDNATUREOFPOST")
    // @Length(max = 200, message = "fdNatureOfPost长度不在有效范围内")
    private String fdNatureOfPost;

    @ApiModelProperty(value = "岗位层级")
    @DbField("FDJOBLEVEL")
    // @Length(max = 200, message = "fdJobLevel长度不在有效范围内")
    private String fdJobLevel;

    @ApiModelProperty(value = "职业资格")
    @DbField("FDNVQ")
    // @Length(max = 500, message = "fdNVQ长度不在有效范围内")
    private String fdNVQ;

    @ApiModelProperty(value = "安全证书")
    @DbField("FDSAFETYCERTIFICATE")
    // @Length(max = 500, message = "fdSafetyCertificate长度不在有效范围内")
    private String fdSafetyCertificate;

    @ApiModelProperty(value = "证书编号")
    @DbField("FDCERTIFICATENO")
    // @Length(max = 500, message = "fdCertificateNo长度不在有效范围内")
    private String fdCertificateNo;

    @ApiModelProperty(value = "授予单位")
    @DbField("FDGRANTINGUNIT")
    // @Length(max = 200, message = "fdGrantingUnit长度不在有效范围内")
    private String fdGrantingUnit;

    @ApiModelProperty(value = "授予时间")
    @DbField("FDGRANTTIME")
    private LocalDateTime fdGrantTime;

    @ApiModelProperty(value = "获得单位")
    @DbField("FDGAINUNITS")
    // @Length(max = 200, message = "fdGainUnits长度不在有效范围内")
    private String fdGainUnits;

    @ApiModelProperty(value = "最新注册时间")
    @DbField("FDNEWREGISTERTIME")
    private LocalDateTime fdNewRegisterTime;

    @ApiModelProperty(value = "到期时间")
    @DbField("FDEXPIREDATE")
    private LocalDateTime fdExpireDate;

    @ApiModelProperty(value = "备注")
    @DbField("FDMEMO")
    // @Length(max = 2000, message = "fdMemo长度不在有效范围内")
    private String fdMemo;

    @ApiModelProperty(value = "证件名称")
    @DbField("FDCERTIFICATENAME")
    // @Length(max = 200, message = "fdCertificateName长度不在有效范围内")
    private String fdCertificateName;

    @ApiModelProperty(value = "证件编号")
    @DbField("FDCERTIFICATENUM")
    // @Length(max = 200, message = "fdCertificateNum长度不在有效范围内")
    private String fdCertificateNum;

    @ApiModelProperty(value = "证件内容")
    @DbField("FDCERTIFICATECONTENT")
    // @Length(max = 200, message = "fdCertificateContent长度不在有效范围内")
    private String fdCertificateContent;

    @ApiModelProperty(value = "证件类别")
    @DbField("FDCERTIFICATECATEGORY")
    // @Length(max = 200, message = "fdCertificateCategory长度不在有效范围内")
    private String fdCertificateCategory;

    @ApiModelProperty(value = "发证日期")
    @DbField("FDLICENCEISSUED")
    private LocalDateTime fdLicenceIssued;

    @ApiModelProperty(value = "有效期开始日期")
    @DbField("FDEXPIRATIONDATE")
    private LocalDateTime fdExpirationDate;

    @ApiModelProperty(value = "有效期截止日期")
    @DbField("FDEXPIRYDATE")
    private LocalDateTime fdExpiryDate;

    @ApiModelProperty(value = "人员类型")
    @DbField("FDPERSONNELTYPE")
    // @Length(max = 200, message = "fdPersonnelType长度不在有效范围内")
    private String fdPersonnelType;

    @ApiModelProperty(value = "项目投入状态")
    @DbField("FDINPUTTYPE")
    // @Length(max = 200, message = "fdInputType长度不在有效范围内")
    private String fdInputType;
}
