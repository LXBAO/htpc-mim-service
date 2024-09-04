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
import com.uwdp.module.api.service.IMarketDmpTagService;
import com.uwdp.module.api.vo.cmd.MarketDmpTagAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagUpdateCmd;
import com.uwdp.module.api.vo.dto.MarketDmpTagDto;
import com.uwdp.module.api.vo.dto.searcher.MarketDmpTagWorkflowDto;
import com.uwdp.module.api.vo.excel.MarketDmpTagExcelExport;
import com.uwdp.module.api.vo.excel.MarketDmpTagExcelImport;
import com.uwdp.module.api.vo.query.MarketDmpTagQuery;
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
 * 市场部分公司年度指标 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/market-dmp-tag-v1-0-0")
@Api(tags = "市场部分公司年度指标")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "市场部分公司年度指标")
@Validated
public class MarketDmpTagController {

    private final IMarketDmpTagService marketDmpTagService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="market_dmp_tag")
    public PageResponse<MarketDmpTagDto> page(MarketDmpTagQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(MarketDmpTagDto::getDmptName).op(Contain.class)
                    .field(MarketDmpTagDto::getUserName).op(Contain.class)
                    .field(MarketDmpTagDto::getTitle).op(Contain.class)
                    .field(MarketDmpTagDto::getYear).op(Contain.class)
                    .build();
            map.remove("createdBy");
            map.put("orderBy","createdTime:desc");
            map.put("createdName-op","ct");
            SearchResult<MarketDmpTagDto> search = marketDmpTagService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/pageThatPassed")
    @ApiOperation(value = "分页查询接口", notes="market_dmp_tag")
    public PageResponse<MarketDmpTagWorkflowDto> pageThatPassed(MarketDmpTagQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(MarketDmpTagWorkflowDto::getDmptName).op(Contain.class)
                    .field(MarketDmpTagWorkflowDto::getUserName).op(Contain.class)
                    .field(MarketDmpTagWorkflowDto::getTitle).op(Contain.class)
                    .field(MarketDmpTagWorkflowDto::getYear).op(Contain.class)
                    .build();
            map.put("orderBy","createdTime:desc");
            map.put("createdName-op","ct");
            SearchResult<MarketDmpTagWorkflowDto> search = marketDmpTagService.pageThatPassed(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="market_dmp_tag")
    public SingleResponse<MarketDmpTagDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(marketDmpTagService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="market_dmp_tag")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated MarketDmpTagAddCmd addCmd) {
        try {
            marketDmpTagService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "编辑接口", notes="market_dmp_tag")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated MarketDmpTagUpdateCmd updateCmd) {
        try {
            marketDmpTagService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="market_dmp_tag")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            marketDmpTagService.delete(id);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="market_dmp_tag")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids) {
        try {
            marketDmpTagService.batchDelete(ids);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="market_dmp_tag")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            marketDmpTagService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="market_dmp_tag")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            marketDmpTagService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="market_dmp_tag")
    public SingleResponse<ExcelParseDTO<MarketDmpTagExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(marketDmpTagService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="market_dmp_tag")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<MarketDmpTagExcelImport> list) {
        try {
            marketDmpTagService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="market_dmp_tag")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<MarketDmpTagExcelExport> list, HttpServletResponse response) {
        try {
            marketDmpTagService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
