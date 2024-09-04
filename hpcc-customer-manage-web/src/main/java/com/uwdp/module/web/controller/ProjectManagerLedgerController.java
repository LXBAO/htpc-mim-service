package com.uwdp.module.web.controller;

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
import com.uwdp.module.api.service.IProjectManagerLedgerService;
import com.uwdp.module.api.vo.cmd.ProjectManagerLedgerAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectManagerLedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectManagerLedgerDto;
import com.uwdp.module.api.vo.excel.ProjectManagerLedgerExcelExport;
import com.uwdp.module.api.vo.excel.ProjectManagerLedgerExcelImport;
import com.uwdp.module.api.vo.query.ProjectManagerLedgerQuery;
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
 * 项目经理台账 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/project-manager-ledger-v1-0-0")
@Api(tags = "项目经理台账")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目经理台账")
@Validated
public class ProjectManagerLedgerController {

    private final IProjectManagerLedgerService projectManagerLedgerService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="projectManagerLedger")
    public PageResponse<ProjectManagerLedgerDto> page(ProjectManagerLedgerQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(ProjectManagerLedgerDto::getFdPMName).op(Contain.class)
                    .field(ProjectManagerLedgerDto::getFdSafetyCertificate).op(Contain.class)
                    .field(ProjectManagerLedgerDto::getFdCertificateNum).op(Contain.class)
                    .build();
            map.remove("createdBy");//去掉创建人筛选条件
            map.put("fdDepartment-op","ct");
            SearchResult<ProjectManagerLedgerDto> search = projectManagerLedgerService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="projectManagerLedger")
    public SingleResponse<ProjectManagerLedgerDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "fdId") Long fdId) {
        try {
            return SingleResponse.of(projectManagerLedgerService.get(fdId));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detailByid")
    @ApiOperation(value = "详情接口", notes="projectManagerLedger")
    public SingleResponse<ProjectManagerLedgerDto> detailByid(@ApiParam(value = "主键", required = true) @RequestParam(name = "hrId") String hrId) {
        try {
            return SingleResponse.of(projectManagerLedgerService.detailByid(hrId));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="projectManagerLedger")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated ProjectManagerLedgerAddCmd addCmd) {
        try {
            projectManagerLedgerService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="projectManagerLedger")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated ProjectManagerLedgerUpdateCmd updateCmd) {
        try {
            projectManagerLedgerService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="projectManagerLedger")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "fdId") Long fdId) {
        try {
            projectManagerLedgerService.delete(fdId);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="projectManagerLedger")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "fdIds") String fdIds) {
        try {
            projectManagerLedgerService.batchDelete(fdIds);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="projectManagerLedger")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            projectManagerLedgerService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="projectManagerLedger")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            projectManagerLedgerService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="projectManagerLedger")
    public SingleResponse<ExcelParseDTO<ProjectManagerLedgerExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(projectManagerLedgerService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="projectManagerLedger")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<ProjectManagerLedgerExcelImport> list) {
        try {
            projectManagerLedgerService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="projectManagerLedger")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<ProjectManagerLedgerExcelExport> list, HttpServletResponse response) {
        try {
            projectManagerLedgerService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
