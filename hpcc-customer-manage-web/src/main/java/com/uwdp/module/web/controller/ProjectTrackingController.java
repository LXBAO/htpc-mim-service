package com.uwdp.module.web.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.IProjectTrackingService;
import com.uwdp.module.api.vo.cmd.ProjectTrackingAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectTrackingUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectTrackingDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectTrackingWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectTrackingExcelExport;
import com.uwdp.module.api.vo.excel.ProjectTrackingExcelImport;
import com.uwdp.module.api.vo.query.ProjectTrackingQuery;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目跟踪 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/project-tracking-v1")
@Api(tags = "项目跟踪")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目跟踪")
@Validated
public class ProjectTrackingController {

    private final IProjectTrackingService projectTrackingService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="projectTracking")
    public PageResponse<ProjectTrackingDto> page(ProjectTrackingQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("trackItemName-op","ct");
            map.put("personName-op","ct");
            map.put("trackDate-op","ct");
            map.put("formModel-op","ct");
            SearchResult<ProjectTrackingDto> search = projectTrackingService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/pageTwo")
    @ApiOperation(value = "分页查询接口", notes="projectTracking")
    public PageResponse<ProjectTrackingWorkflowDto> pageTwo(ProjectTrackingQuery paramQuery, HttpServletRequest request) {
        String workflowStatusParam = request.getParameter("workflowStatus");
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
//            Map<String, Object> aMap = new HashMap<>();
//            for (String key : map.keySet()){
//                aMap.put("A." + key, map.get(key));
//            }
            map.put("C.trackItemName-op","ct");
            map.put("C.personName-op","ct");
            map.put("C.creator-op","ct");
            map.put("C.formModel-op","ct");
            map.put("C.groupFullCode-op","ct");
            if("1".equals(workflowStatusParam)){
                map.put("C.workflowStatus-op", "nl");
                map.remove("workflowStatus");
            }
            SearchResult<ProjectTrackingWorkflowDto> search = projectTrackingService.pageByParamTwo(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    /**
     * 项目赋能查询明细(无数据权限)
     * @param paramQuery
     * @param request
     * @return
     */
    @GetMapping("/projectEnablePage")
    @ApiOperation(value = "分页查询接口", notes="projectTracking")
    public PageResponse<ProjectTrackingWorkflowDto> projectEnablePage(ProjectTrackingQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("trackItemName-op","ct");
            map.put("personName-op","ct");
            map.put("trackDate-op","ct");
            map.put("formModel-op","ct");
            map.remove("createdBy");
            map.put("order by", "trackDate DESC");
            map.put("GROUPFULLCODE-op","ct");
            SearchResult<ProjectTrackingWorkflowDto> search = projectTrackingService.projectEnablePage(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="projectTracking")
    public SingleResponse<ProjectTrackingDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(projectTrackingService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="projectTracking")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated ProjectTrackingAddCmd addCmd) {
        try {
            projectTrackingService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="projectTracking")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated ProjectTrackingUpdateCmd updateCmd) {
        try {
            projectTrackingService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="projectTracking")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id,@RequestParam(name = "projectNumber") String projectNumber) {
        try {
            projectTrackingService.delete(id,projectNumber);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="projectTracking")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids,@RequestParam(name = "numbers") String numbers) {
        try {
            projectTrackingService.batchDelete(ids,numbers);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="projectTracking")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            projectTrackingService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="projectTracking")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        String workflowStatusParam = request.getParameter("workflowStatus");
        try {
            if("1".equals(workflowStatusParam)){
                map.put("workflowStatus-op", "nl");
                map.remove("workflowStatus");
            }
            projectTrackingService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="projectTracking")
    public SingleResponse<ExcelParseDTO<ProjectTrackingExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(projectTrackingService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="projectTracking")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<ProjectTrackingExcelImport> list) {
        try {
            projectTrackingService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="projectTracking")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<ProjectTrackingExcelExport> list, HttpServletResponse response) {
        try {
            projectTrackingService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
