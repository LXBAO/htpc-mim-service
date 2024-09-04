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
import com.uwdp.module.api.service.ILmcWorkflowTimelineService;
import com.uwdp.module.api.vo.cmd.LmcWorkflowTimelineAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowTimelineUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowTimelineDto;
import com.uwdp.module.api.vo.excel.LmcWorkflowTimelineExcelExport;
import com.uwdp.module.api.vo.excel.LmcWorkflowTimelineExcelImport;
import com.uwdp.module.api.vo.query.LmcWorkflowTimelineQuery;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 流程时间线 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/lmc-workflow-timeline-v1-0-0")
@Api(tags = "流程时间线")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "流程时间线")
@Validated
public class LmcWorkflowTimelineController {

    private final ILmcWorkflowTimelineService lmcWorkflowTimelineService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="LmcWorkflowTimeline")
    public PageResponse<LmcWorkflowTimelineDto> page(LmcWorkflowTimelineQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            SearchResult<LmcWorkflowTimelineDto> search = lmcWorkflowTimelineService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="LmcWorkflowTimeline")
    public SingleResponse<LmcWorkflowTimelineDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(lmcWorkflowTimelineService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="LmcWorkflowTimeline")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated LmcWorkflowTimelineAddCmd addCmd) {
        try {
            lmcWorkflowTimelineService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="LmcWorkflowTimeline")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated LmcWorkflowTimelineUpdateCmd updateCmd) {
        try {
            lmcWorkflowTimelineService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="LmcWorkflowTimeline")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            lmcWorkflowTimelineService.delete(id);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="LmcWorkflowTimeline")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids) {
        try {
            lmcWorkflowTimelineService.batchDelete(ids);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="LmcWorkflowTimeline")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            lmcWorkflowTimelineService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="LmcWorkflowTimeline")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            lmcWorkflowTimelineService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="LmcWorkflowTimeline")
    public SingleResponse<ExcelParseDTO<LmcWorkflowTimelineExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(lmcWorkflowTimelineService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="LmcWorkflowTimeline")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<LmcWorkflowTimelineExcelImport> list) {
        try {
            lmcWorkflowTimelineService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="LmcWorkflowTimeline")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<LmcWorkflowTimelineExcelExport> list, HttpServletResponse response) {
        try {
            lmcWorkflowTimelineService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
