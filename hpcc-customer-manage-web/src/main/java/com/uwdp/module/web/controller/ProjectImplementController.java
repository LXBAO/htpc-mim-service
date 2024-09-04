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
import com.uwdp.module.api.service.IProjectImplementService;
import com.uwdp.module.api.service.IProjectRecordsService;
import com.uwdp.module.api.vo.cmd.ProjectImplementAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectImplementUpdateCmd;
import com.uwdp.module.api.vo.cmd.ProjectRecordsUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectImplementDto;
import com.uwdp.module.api.vo.excel.ProjectImplementExcelExport;
import com.uwdp.module.api.vo.excel.ProjectImplementExcelImport;
import com.uwdp.module.api.vo.query.ProjectImplementQuery;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
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
 * 项目实施 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/project-implement-v1-0-0")
@Api(tags = "项目实施")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目实施")
@Validated
public class ProjectImplementController {

    private final IProjectImplementService projectImplementService;

    private final IProjectRecordsService projectRecordsService;



    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="projectImplement")
    public PageResponse<ProjectImplementDto> page(ProjectImplementQuery paramQuery, HttpServletRequest request) {
        String workflowStatusParam = request.getParameter("workflowStatus");
        System.out.println(request.getParameter("groupFullCode"));
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
//            Map<String, Object> aMap = new HashMap<>();
//            for (String key : map.keySet()){
//                aMap.put("A." + key, map.get(key));
//            }
            map.put("C.projectName-op","ct");
            map.put("C.registrationUnit-op","ct");
            map.put("C.createdByName-op","ct");
            map.put("C.groupFullCode-op","ct");
            if("1".equals(workflowStatusParam)){
                map.put("C.workflowStatus-op", "nl");
                map.remove("workflowStatus");
            }
            SearchResult<ProjectImplementDto> search = projectImplementService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="projectImplement")
    public SingleResponse<ProjectImplementDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(projectImplementService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="projectImplement")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated ProjectImplementAddCmd addCmd) {
        try {
            projectImplementService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="projectImplement")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated ProjectImplementUpdateCmd updateCmd) {
        try {
            projectImplementService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="projectImplement")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id,@RequestParam(name = "projectNumber") String projectNumber) {
        try {
            projectImplementService.delete(id,projectNumber);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="projectImplement")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids,
                                @RequestParam(name = "numbers") String numbers) {
        try {
            projectImplementService.batchDelete(ids,numbers);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


    @DeleteMapping("/entryFile")
    @ApiOperation(value = "根据id联查项目管理数据,ids以逗号分隔多个id", notes="projectImplement")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response entryFile(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids) {
        try {
            projectImplementService.entryFile(ids);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="projectImplement")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            projectImplementService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="projectImplement")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
        String workflowStatusParam = request.getParameter("workflowStatus");
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            if("1".equals(workflowStatusParam)){
                map.put("workflowStatus-op", "nl");
                map.remove("workflowStatus");
            }
            projectImplementService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="projectImplement")
    public SingleResponse<ExcelParseDTO<ProjectImplementExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(projectImplementService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="projectImplement")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<ProjectImplementExcelImport> list) {
        try {
            projectImplementService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="projectImplement")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<ProjectImplementExcelExport> list, HttpServletResponse response) {
        try {
            projectImplementService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
