package com.uwdp.module.api.vo.dto.searcher;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.uwdp.module.api.vo.dto.AttachmentDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 类的描述：...
 *
 * @version 1.0
 * @auther wll
 * @DateTime 19/7/2023 14:47
 */


@SearchBean(
        tables = "T_WINTHEBID w left join T_PROJECTRECORDS r on w.itemNumber = r.ID left join T_LMCWORKFLOW l on w.ID =  l.BIZID"
)
@ToString
@Data
public class WinTheBidWorkflowDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否国际项目")
    @DbField("r.ISFORIEN")
    // @Length(max = 100, message = "isForien长度不在有效范围内")
    private String isForien;

    @ApiModelProperty("流程实例id")
    @DbField("l.PROCESSINSTANCEID")
    private String processInstanceId;

    @ApiModelProperty("审批状态")
    @DbField("l.WORKFLOWSTATUS")
    private String workflowStatus;


    @ApiModelProperty(value = "唯一标识")
    @DbField("w.ID")
    // @Range(max = Long.MAX_VALUE, message = "id长度不在有效范围内")
    private Long id;

    @ApiModelProperty(value = "创建者")
    @DbField("w.CREATED_BY")
    // @Length(max = 64, message = "createdBy长度不在有效范围内")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @DbField("w.CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新者")
    @DbField("w.UPDATED_BY")
    // @Length(max = 64, message = "updatedBy长度不在有效范围内")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @DbField("w.UPDATED_TIME")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "项目名称")
    @DbField("w.PROJECTNAME")
    // @Length(max = 50, message = "projectName长度不在有效范围内")
    private String projectName;

    @ApiModelProperty(value = "所属区域")
    @DbField("w.OWNINGREGION")
    // @Length(max = 50, message = "owningRegion长度不在有效范围内")
    private String owningRegion;

    @ApiModelProperty(value = "合同额(万元)")
    @DbField("w.MONEY")
    // @Range(max = Long.MAX_VALUE, message = "money长度不在有效范围内")
    private Double money;

    @ApiModelProperty(value = "登记单位")
    @DbField("w.REGISTRATIONUNIT")
    // @Length(max = 50, message = "registrationUnit长度不在有效范围内")
    private String registrationUnit;

    @ApiModelProperty(value = "商业模式")
    @DbField("w.BUSINESSMODEL")
    // @Length(max = 50, message = "businessModel长度不在有效范围内")
    private String businessModel;

    @ApiModelProperty(value = "产业领域类别")
    @DbField("w.INDUSTRYCATEGORY")
    // @Length(max = 50, message = "industryCategory长度不在有效范围内")
    private String industryCategory;

    @ApiModelProperty(value = "中标日期")
    @DbField("w.DATA")
    private String data;

    @ApiModelProperty(value = "项目阶段")
    @DbField("w.PROJECTPHASE")
    // @Length(max = 50, message = "projectPhase长度不在有效范围内")
    private String projectPhase;

    @ApiModelProperty(value = "项目编号")
    @DbField("w.ITEMNUMBER")
    // @Length(max = 50, message = "itemNumber长度不在有效范围内")
    private String itemNumber;

    @ApiModelProperty(value = "所在省份")
    @DbField("w.HOMEPROVINCE")
    // @Length(max = 50, message = "homeProvince长度不在有效范围内")
    private String homeProvince;

    @ApiModelProperty(value = "预计签约时间")
    @DbField("w.SIGNINGTIME")
    private LocalDate signingTime;

    @ApiModelProperty(value = "登记时间")
    @DbField("w.REGISTRATIONTIME")
    private LocalDate registrationTime;

    @ApiModelProperty(value = "是否重大项目")
    @DbField("w.MAJORPROJECT")
    // @Length(max = 50, message = "majorProject长度不在有效范围内")
    private String majorProject;

    @ApiModelProperty(value = "是否需要专业评估")
    @DbField("w.PROFESSIONALEVALUATION")
    // @Length(max = 50, message = "professionalEvaluation长度不在有效范围内")
    private String professionalEvaluation;

    @ApiModelProperty(value = "是否获取新能源指标")
    @DbField("w.NEWENERGY")
    // @Length(max = 50, message = "newEnergy长度不在有效范围内")
    private String newEnergy;

    @ApiModelProperty(value = "是否三交九直")
    @DbField("w.WHETHERTHREE")
    // @Length(max = 50, message = "whetherThree长度不在有效范围内")
    private String whetherThree;

    @ApiModelProperty(value = "审核状态")
    @DbField("w.AUDITSTATUS")
    // @Length(max = 50, message = "auditStatus长度不在有效范围内")
    private String auditStatus;

    @ApiModelProperty(value = "权限id")
    @DbField("w.GROUPFULLCODE")
    // @Length(max = 200, message = "GROUPFULLCODE长度不在有效范围内")
    private String groupFullCode;

    @ApiModelProperty(value = "所属公司名称")
    @DbField("w.GROUPBELONGUNITNAME")
    // @Length(max = 200, message = "GROUPBELONGUNITNAME长度不在有效范围内")
    private String groupBelongUnitName;

    @ApiModelProperty(value = "创建人名称")
    @DbField("w.CREATEDNAME")
    // @Length(max = 255, message = "createdName长度不在有效范围内")
    private String createdName;

    @ApiModelProperty(value = "附件")
    @DbField("w.file")
    // @Length(max = 255, message = "file长度不在有效范围内")
    private String file;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("r.INTASSISTANCEUNIT")
    // @Length(max = 255)
    private String IntAssistanceUnit;

    @ApiModelProperty(value = "根据内部协助单位")
    @DbField("r.INTASSISTANCEUNITNAME")
    // @Length(max = 255)
    private String IntAssistanceUnitName;

    @ApiModelProperty(value = "建设地点名称")
    @DbField("w.HOMEPROVINCENAME")
    // @Length(max = 50, message = "homeProvinceName长度不在有效范围内")
    private String homeProvinceName;

    //附件表
    @DbIgnore
    List<AttachmentDto> attachmentDtos;

}
