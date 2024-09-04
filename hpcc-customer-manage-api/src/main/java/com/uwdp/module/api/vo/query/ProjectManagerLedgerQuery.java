package com.uwdp.module.api.vo.query;

import com.ejlchina.searcher.bean.DbField;
import com.gientech.lcds.generator.commons.api.entity.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * <p>
 * 项目经理台账
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProjectManagerLedgerDo Query对象", description = "项目经理台账", discriminator = "projectManagerLedger")
public class ProjectManagerLedgerQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long fdId;

    @ApiModelProperty(value = "关联人资id")
    private String hrId;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "工号")
    private String fdJobNumber;

    @ApiModelProperty(value = "姓名")
    private String fdPMName;

    @ApiModelProperty(value = "身份证号码")
    private String fdIDCardNo;

    @ApiModelProperty(value = "手机号码")
    private String fdPhoneNumber;

    @ApiModelProperty(value = "单位")
    private String fdUnit;

    @ApiModelProperty(value = "部门")
    private String fdDepartment;

    @ApiModelProperty(value = "岗位")
    private String fdPosition;

    @ApiModelProperty(value = "岗位性质")
    private String fdNatureOfPost;

    @ApiModelProperty(value = "岗位层级")
    private String fdJobLevel;

    @ApiModelProperty(value = "职业资格")
    private String fdNVQ;

    @ApiModelProperty(value = "安全证书")
    private String fdSafetyCertificate;

    @ApiModelProperty(value = "证书编号")
    private String fdCertificateNo;

    @ApiModelProperty(value = "授予单位")
    private String fdGrantingUnit;

    @ApiModelProperty(value = "授予时间")
    private LocalDateTime fdGrantTime;

    @ApiModelProperty(value = "获得单位")
    private String fdGainUnits;

    @ApiModelProperty(value = "最新注册时间")
    private LocalDateTime fdNewRegisterTime;

    @ApiModelProperty(value = "到期时间")
    private LocalDateTime fdExpireDate;

    @ApiModelProperty(value = "备注")
    private String fdMemo;

    @ApiModelProperty(value = "证件名称")
    private String fdCertificateName;

    @ApiModelProperty(value = "证件编号")
    private String fdCertificateNum;

    @ApiModelProperty(value = "证件内容")
    private String fdCertificateContent;

    @ApiModelProperty(value = "证件类别")
    private String fdCertificateCategory;

    @ApiModelProperty(value = "发证日期")
    private LocalDateTime fdLicenceIssued;

    @ApiModelProperty(value = "有效期开始日期")
    private LocalDateTime fdExpirationDate;

    @ApiModelProperty(value = "有效期截止日期")
    private LocalDateTime fdExpiryDate;

    @ApiModelProperty(value = "人员类型")
    private String fdPersonnelType;

    @ApiModelProperty(value = "项目投入状态")
    private String fdInputType;
}
