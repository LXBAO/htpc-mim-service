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
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目跟踪 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/project-tracking-v1")
@Api(tags = "项目跟踪")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目跟踪")
@Validated
public class ProjectTrackingControllerV2 {

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
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("trackItemName-op","ct");
            map.put("personName-op","ct");
            map.put("trackDate-op","ct");
            map.put("formModel-op","ct");
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
}
