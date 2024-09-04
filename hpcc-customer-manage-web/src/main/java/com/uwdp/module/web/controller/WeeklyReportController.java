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
import com.uwdp.module.api.service.IWeeklyReportService;
import com.uwdp.module.api.vo.cmd.WeeklyReportAddCmd;
import com.uwdp.module.api.vo.cmd.WeeklyReportUpdateCmd;
import com.uwdp.module.api.vo.dto.WeeklyReportDetailDto;
import com.uwdp.module.api.vo.dto.WeeklyReportDto;
import com.uwdp.module.api.vo.excel.WeeklyReportExcelExport;
import com.uwdp.module.api.vo.excel.WeeklyReportExcelImport;
import com.uwdp.module.api.vo.query.WeeklyReportQuery;
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
 * 周报主表信息 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/weekly-report-v1-0")
@Api(tags = "周报主表信息")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "周报主表信息")
@Validated
public class WeeklyReportController {

    private final IWeeklyReportService weeklyReportService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="weekly_Report")
    public PageResponse<WeeklyReportDto> page(WeeklyReportQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(WeeklyReportDto::getTitle).op(Contain.class)
                    .build();
            SearchResult<WeeklyReportDto> search = weeklyReportService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    /**
     * 获取下周计划
     * @param personCode
     * @return
     */
    @GetMapping("/getWeeklyReportDetailByPersonCode")
    @ApiOperation(value = "详情接口", notes="weeklyReport")
    public SingleResponse<List<WeeklyReportDetailDto>> getWeeklyReportDetailByPersonCode(@ApiParam(value = "主键", required = true) @RequestParam(name = "personCode") Long personCode) {
        try {
            return SingleResponse.of(weeklyReportService.getWeeklyReportDetailByPersonCode(personCode));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="weekly_Report")
    public SingleResponse<WeeklyReportDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(weeklyReportService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="weekly_Report")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated WeeklyReportAddCmd addCmd) {
        try {
            weeklyReportService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="weekly_Report")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated WeeklyReportUpdateCmd updateCmd) {
        try {
            weeklyReportService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="weekly_Report")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            weeklyReportService.delete(id);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="weekly_Report")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids) {
        try {
            weeklyReportService.batchDelete(ids);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="weekly_Report")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            weeklyReportService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="weekly_Report")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            weeklyReportService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="weekly_Report")
    public SingleResponse<ExcelParseDTO<WeeklyReportExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(weeklyReportService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="weekly_Report")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<WeeklyReportExcelImport> list) {
        try {
            weeklyReportService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="weekly_Report")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<WeeklyReportExcelExport> list, HttpServletResponse response) {
        try {
            weeklyReportService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
