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
import com.uwdp.module.api.service.ICALedgerService;
import com.uwdp.module.api.vo.cmd.CALedgerAddCmd;
import com.uwdp.module.api.vo.cmd.CALedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.CALedgerDto;
import com.uwdp.module.api.vo.excel.CALedgerExcelExport;
import com.uwdp.module.api.vo.excel.CALedgerExcelImport;
import com.uwdp.module.api.vo.query.CALedgerQuery;
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
 * CA台账 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/c-aledger-v1-0-0")
@Api(tags = "CA台账")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "CA台账")
@Validated
public class CALedgerController {

    private final ICALedgerService cALedgerService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="CALedger")
    public PageResponse<CALedgerDto> page(CALedgerQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(CALedgerDto::getFdUserName).op(Contain.class)
                    .field(CALedgerDto::getFdwebsitename).op(Contain.class)
                    .field(CALedgerDto::getFdWebsiteAddress).op(Contain.class)
                    .field(CALedgerDto::getFdScope).op(Contain.class)
                    .build();
            map.put("createdName-op","ct");
            SearchResult<CALedgerDto> search = cALedgerService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="CALedger")
    public SingleResponse<CALedgerDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "fdId") Long fdId) {
        try {
            return SingleResponse.of(cALedgerService.get(fdId));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detailPassword")
    @ApiOperation(value = "详情接口", notes="CALedger")
    public SingleResponse<CALedgerDto> getPassword(@ApiParam(value = "主键", required = true) @RequestParam(name = "fdId") Long fdId) {
        try {
            return SingleResponse.of(cALedgerService.getPassword(fdId));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="CALedger")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated CALedgerAddCmd addCmd) {
        try {
            cALedgerService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="CALedger")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated CALedgerUpdateCmd updateCmd) {
        try {
            cALedgerService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="CALedger")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "fdId") Long fdId) {
        try {
            cALedgerService.delete(fdId);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="CALedger")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "fdIds") String fdIds) {
        try {
            cALedgerService.batchDelete(fdIds);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="CALedger")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            cALedgerService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="CALedger")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            cALedgerService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="CALedger")
    public SingleResponse<ExcelParseDTO<CALedgerExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(cALedgerService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="CALedger")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<CALedgerExcelImport> list) {
        try {
            cALedgerService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="CALedger")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<CALedgerExcelExport> list, HttpServletResponse response) {
        try {
            cALedgerService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
