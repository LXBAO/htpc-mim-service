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
import com.uwdp.module.api.service.IProjectRecordsService;
import com.uwdp.module.api.vo.cmd.ProjectRecordsAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectRecordsUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectRecordsDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectRTBWSIDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectRecordsWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectRecordsExcelExport;
import com.uwdp.module.api.vo.excel.ProjectRecordsExcelImport;
import com.uwdp.module.api.vo.query.ProjectRecordsQuery;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/**
 * <p>
 * 项目登记 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/project-records-v1-0-0")
@Api(tags = "项目登记")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目登记")
@Validated
public class ProjectRecordsController {

    private final IProjectRecordsService projectRecordsService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="projectRecords")
    public PageResponse<ProjectRecordsWorkflowDto> page(ProjectRecordsQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("projectName-op","ct");
            map.put("createdName-op","ct");
            map.put("GROUPFULLCODE-op","ct");
            SearchResult<ProjectRecordsWorkflowDto> search = projectRecordsService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/getByProjectNumber")
    @ApiOperation(value = "分页查询接口", notes="projectRecords")
    public List<ProjectRTBWSIDto> getByProjectNumber(ProjectRecordsQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            return projectRecordsService.getProject(map);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/getProjectCount")
    @ApiOperation(value = "查询条数接口", notes="getProjectCount")
    public Map getProjectCount(String projectName, String location,String ownerUnit, String industryCategor) {
        try {
            System.out.println(projectName+" "+location+" "+ownerUnit+" "+industryCategor);
            Integer projectCount = projectRecordsService.getProjectCount(projectName, location, ownerUnit, industryCategor);
            System.out.println(projectCount);
            Map map = new HashMap();
            map.put("data",projectCount);
            return map;
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/headlinePage")
    @ApiOperation(value = "分页查询接口", notes="projectRecords")
    public PageResponse<ProjectRecordsWorkflowDto> headlinePage(ProjectRecordsQuery paramQuery, HttpServletRequest request) {
        String workflowStatusParam = request.getParameter("workflowStatus");
        String projectPhaseParam = request.getParameter("projectPhase");
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            Map<String, Object> aMap = new HashMap<>();
//            for (String key : map.keySet()){
//                aMap.put("A." + key, map.get(key));
//            }
            aMap.put("C.projectName-op","ct");
            aMap.put("C.createdName-op","ct");
            aMap.put("C.groupFullCode-op","ct");
            if("1".equals(workflowStatusParam)){
                aMap.put("C.workflowStatus-op", "nl");
                aMap.remove("workflowStatus");
            }
            if (projectPhaseParam != null && !projectPhaseParam.isEmpty()) {
                String[] projectPhaseValues = projectPhaseParam.split(",");
                List<String> projectPhaseList = Arrays.asList(projectPhaseValues);
                for (int i = 0; i < projectPhaseList.size(); i++) {
                    aMap.put("C.projectPhase-"+i,projectPhaseList.get(i));
                }
                System.out.println(projectPhaseList);
                aMap.put("C.projectPhase-op", "il");
            }
            SearchResult<ProjectRecordsWorkflowDto> search = projectRecordsService.pageHeadlineByParam(aMap);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/projectTrackingHeadlinePage")
    @ApiOperation(value = "分页查询接口", notes="projectRecords")
    public PageResponse<ProjectRecordsWorkflowDto> projectTrackingHeadlinePage(ProjectRecordsQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("projectName-op","ct");
            map.put("createdName-op","ct");
            map.put("projectPhase-0",1);
            map.put("projectPhase-1",2);
            map.put("projectPhase-op","bt");
            map.put("GROUPFULLCODE-op","ct");
            SearchResult<ProjectRecordsWorkflowDto> search = projectRecordsService.pageHeadlineByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    /**
     * 项目赋能，可选所有审批结束项目
     * @param paramQuery
     * @param request
     * @return
     */
    @GetMapping("/projectEnablePage")
    @ApiOperation(value = "分页查询接口", notes="projectRecords")
    public PageResponse<ProjectRecordsWorkflowDto> projectEnablePage(ProjectRecordsQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("projectName-op","ct");
            map.put("createdName-op","ct");
//            map.put("projectPhase-0",1);
//            map.put("projectPhase-1",2);
            map.put("projectPhase-op","bt");
            map.remove("createdBy");
            map.put("GROUPFULLCODE-op","ct");
            SearchResult<ProjectRecordsWorkflowDto> search = projectRecordsService.pageHeadlineByParam0(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="projectRecords")
    public SingleResponse<ProjectRecordsDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(projectRecordsService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="projectRecords")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated ProjectRecordsAddCmd addCmd) {
        try {
            projectRecordsService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="projectRecords")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated ProjectRecordsUpdateCmd updateCmd) {
        try {
            projectRecordsService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="projectRecords")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            projectRecordsService.delete(id);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="projectRecords")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids) {
        try {
            projectRecordsService.batchDelete(ids);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="projectRecords")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            projectRecordsService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="projectRecords")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        String workflowStatusParam = request.getParameter("workflowStatus");
        String projectPhaseParam = request.getParameter("projectPhase");
        try {
            if("1".equals(workflowStatusParam)){
                map.put("workflowStatus-op", "nl");
                map.remove("workflowStatus");
            }
            if (projectPhaseParam != null && !projectPhaseParam.isEmpty()) {
                String[] projectPhaseValues = projectPhaseParam.split(",");
                List<String> projectPhaseList = Arrays.asList(projectPhaseValues);
                for (int i = 0; i < projectPhaseList.size(); i++) {
                    map.put("projectPhase-"+i,projectPhaseList.get(i));
                }
                System.out.println(projectPhaseList);
                map.put("projectPhase-op", "il");
            }
            map.put("projectName-op","ct");
            map.put("createdName-op","ct");
            map.put("GROUPFULLCODE-op","ct");
            projectRecordsService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="projectRecords")
    public SingleResponse<ExcelParseDTO<ProjectRecordsExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(projectRecordsService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="projectRecords")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<ProjectRecordsExcelImport> list) {
        try {
            projectRecordsService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="projectRecords")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<ProjectRecordsExcelExport> list, HttpServletResponse response) {
        try {
            projectRecordsService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
