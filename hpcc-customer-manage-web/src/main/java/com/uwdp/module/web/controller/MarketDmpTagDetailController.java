package com.uwdp.module.web.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.IMarketDmpTagDetailService;
import com.uwdp.module.api.vo.cmd.MarketDmpTagDetailAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.MarketDmpTagDetailDto;
import com.uwdp.module.api.vo.excel.MarketDmpTagDetailExcelExport;
import com.uwdp.module.api.vo.excel.MarketDmpTagDetailExcelImport;
import com.uwdp.module.api.vo.query.MarketDmpTagDetailQuery;
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
 * 指标明细 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/market-dmp-tag-detail-v1-0-0")
@Api(tags = "指标明细")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "指标明细")
@Validated
public class MarketDmpTagDetailController {

    private final IMarketDmpTagDetailService marketDmpTagDetailService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="market_dmp_tag_detail")
    public PageResponse<MarketDmpTagDetailDto> page(MarketDmpTagDetailQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.remove("createdBy");
            map.put("createdName-op","ct");
            SearchResult<MarketDmpTagDetailDto> search = marketDmpTagDetailService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="market_dmp_tag_detail")
    public SingleResponse<MarketDmpTagDetailDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(marketDmpTagDetailService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="market_dmp_tag_detail")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated MarketDmpTagDetailAddCmd addCmd) {
        try {
            marketDmpTagDetailService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="market_dmp_tag_detail")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated MarketDmpTagDetailUpdateCmd updateCmd) {
        try {
            marketDmpTagDetailService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="market_dmp_tag_detail")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            marketDmpTagDetailService.delete(id);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="market_dmp_tag_detail")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids) {
        try {
            marketDmpTagDetailService.batchDelete(ids);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="market_dmp_tag_detail")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            marketDmpTagDetailService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="market_dmp_tag_detail")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            marketDmpTagDetailService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="market_dmp_tag_detail")
    public SingleResponse<ExcelParseDTO<MarketDmpTagDetailExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(marketDmpTagDetailService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="market_dmp_tag_detail")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<MarketDmpTagDetailExcelImport> list) {
        try {
            marketDmpTagDetailService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="market_dmp_tag_detail")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<MarketDmpTagDetailExcelExport> list, HttpServletResponse response) {
        try {
            marketDmpTagDetailService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
