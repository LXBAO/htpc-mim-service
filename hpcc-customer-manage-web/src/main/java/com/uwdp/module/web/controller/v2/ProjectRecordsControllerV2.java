package com.uwdp.module.web.controller.v2;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目登记 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/project-records-v1-0-0")
@Api(tags = "项目登记")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目登记")
@Validated
public class ProjectRecordsControllerV2 {

    private final IProjectRecordsService projectRecordsService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="projectRecords")
    public PageResponse<ProjectRecordsWorkflowDto> page(ProjectRecordsQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("projectName-op","ct");
            map.put("createdName-op","ct");
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

    @GetMapping("/headlinePage")
    @ApiOperation(value = "分页查询接口", notes="projectRecords")
    public PageResponse<ProjectRecordsWorkflowDto> headlinePage(ProjectRecordsQuery paramQuery, HttpServletRequest request) {
        String workflowStatusParam = request.getParameter("workflowStatus");
        String projectPhaseParam = request.getParameter("projectPhase");
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            Map<String, Object> aMap = new HashMap<>();
            for (String key : map.keySet()){
                aMap.put("A." + key, map.get(key));
            }
            aMap.put("A.projectName-op","ct");
            aMap.put("A.createdName-op","ct");
            aMap.put("A.groupFullCode-op","ct");
            if("1".equals(workflowStatusParam)){
                aMap.put("A.workflowStatus-op", "nl");
                aMap.remove("A.workflowStatus");
            }
            if (projectPhaseParam != null && !projectPhaseParam.isEmpty()) {
                String[] projectPhaseValues = projectPhaseParam.split(",");
                List<String> projectPhaseList = Arrays.asList(projectPhaseValues);
                for (int i = 0; i < projectPhaseList.size(); i++) {
                    aMap.put("A.projectPhase-"+i,projectPhaseList.get(i));
                }
                System.out.println(projectPhaseList);
                aMap.put("A.projectPhase-op", "il");
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
}
