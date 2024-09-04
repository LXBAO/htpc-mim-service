package com.uwdp.module.web.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.Contain;
import com.ejlchina.searcher.operator.Equal;
import com.ejlchina.searcher.operator.InList;
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
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 主数据-人员 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/mdm-person-v1-0-0")
@Api(tags = "主数据-人员")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "主数据-人员")
@Validated
public class MdmPersonController {

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

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="mdmPerson")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated MdmPersonAddCmd addCmd) {
        try {
            mdmPersonService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="mdmPerson")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated MdmPersonUpdateCmd updateCmd) {
        try {
            mdmPersonService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="mdmPerson")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            mdmPersonService.delete(id);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="mdmPerson")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids) {
        try {
            mdmPersonService.batchDelete(ids);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="mdmPerson")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            mdmPersonService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="mdmPerson")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            mdmPersonService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="mdmPerson")
    public SingleResponse<ExcelParseDTO<MdmPersonExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(mdmPersonService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="mdmPerson")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<MdmPersonExcelImport> list) {
        try {
            mdmPersonService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="mdmPerson")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<MdmPersonExcelExport> list, HttpServletResponse response) {
        try {
            mdmPersonService.excelErrorDownload(list, response);
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
            String groupFullCode = parts[2];

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
