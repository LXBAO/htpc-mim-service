package com.uwdp.module.web.controller.v2;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.Contain;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.IMdmOrganizationService;
import com.uwdp.module.api.service.IMdmPersonService;
import com.uwdp.module.api.vo.cmd.MdmPersonAddCmd;
import com.uwdp.module.api.vo.cmd.MdmPersonUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import com.uwdp.module.api.vo.dto.MdmPersonDto;
import com.uwdp.module.api.vo.excel.MdmPersonExcelExport;
import com.uwdp.module.api.vo.excel.MdmPersonExcelImport;
import com.uwdp.module.api.vo.query.MdmPersonQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 主数据-人员 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/mdm-person-v1-0-0")
@Api(tags = "主数据-人员")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "主数据-人员")
@Validated
public class MdmPersonControllerV2 {

    private final IMdmPersonService mdmPersonService;
    private final IMdmOrganizationService dmOrganizationService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="mdmPerson")
    public PageResponse<MdmPersonDto> page(MdmPersonQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(MdmPersonDto::getPersonName).op(Contain.class)
                    .field(MdmPersonDto::getStatus,"1") // 默认查询启用状态的人员
                    .orderBy(MdmPersonDto::getPersonCode).desc()
                    .build();

            SearchResult<MdmPersonDto> search = mdmPersonService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


    @GetMapping("/personInfoByGroupBelongUnitCode/page")
    @ApiOperation(value = "分页查询接口", notes="mdmPerson")
    public PageResponse<MdmPersonDto> personInfoByGroupBelongUnitCode(MdmPersonQuery paramQuery, HttpServletRequest request) {
        try {

            SearchResult<MdmPersonDto> search = mdmPersonService.personInfoByGroupBelongUnitCode(paramQuery,request.getParameterMap());
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="mdmPerson")
    public SingleResponse<MdmPersonDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(mdmPersonService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/getPersonCodeDetail")
    @ApiOperation(value = "详情接口", notes="mdmPerson")
    public SingleResponse<MdmPersonDto> getPersonCodeDetail(@ApiParam(value = "主键", required = true) @RequestParam(name = "personCode") String personCode) {
        try {
            return SingleResponse.of(mdmPersonService.getPersonCodeDetail(personCode));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detailByPersonCode")
    @ApiOperation(value = "详情接口", notes="mdmPerson")
    public SingleResponse<MdmPersonDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "personCode") String personCode) {
        try {
            return SingleResponse.of(mdmPersonService.get(personCode));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
    @GetMapping("/groupFullCode")
    @ApiOperation(value = "查询子公司", notes="mdmPerson")
    public SingleResponse<MdmOrganizationDto> groupFullCode(@ApiParam(value = "主键", required = true) @RequestParam(name = "personCode") String personCode) {

        try {
            //通过t_mdmperson查询出GROUPBELONGDEPARTMENTCODE
            MdmPersonDto of = mdmPersonService.getPersonCodeDetail(personCode);
            String groupBelongDepartmentCode = of.getGroupBelongDepartmentCode();
            //通过t_mdmorganization查询出groupFullCode
            MdmOrganizationDto organizationDto = dmOrganizationService.getGroupCode(groupBelongDepartmentCode);
            String fullCode = organizationDto.getGroupFullCode();
            //截取字符串/后的字符串
            String[] parts = fullCode.split("/");
            String groupFullCode = parts[1];

            MdmOrganizationDto mdmOrganizationDto = dmOrganizationService.getGroupCode(groupFullCode);

            return SingleResponse.of(mdmOrganizationDto);
        }catch (NullPointerException n){
            throw new BizRuntimeException(String.format("根据当前登陆人信息带出填报单位时发生错误:"), n);
        }
        catch (Exception e) {
            throw new BizRuntimeException(e);
        }

    }
}
