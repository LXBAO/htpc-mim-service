package com.uwdp.module.web.controller.v2;

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
import com.uwdp.module.api.service.IProjectSuspensionService;
import com.uwdp.module.api.vo.cmd.ProjectSuspensionAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectSuspensionUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectSuspensionDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectSuspensionWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectSuspensionExcelExport;
import com.uwdp.module.api.vo.excel.ProjectSuspensionExcelImport;
import com.uwdp.module.api.vo.query.ProjectSuspensionQuery;
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
 * 项目中止 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/project-suspension-v1-0-0")
@Api(tags = "项目中止")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目中止")
@Validated
public class ProjectSuspensionControllerV2 {

    private final IProjectSuspensionService projectSuspensionService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="projectSuspension")
    public PageResponse<ProjectSuspensionDto> page(ProjectSuspensionQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(ProjectSuspensionQuery::getProjectName).op(Contain.class)
                    .field(ProjectSuspensionQuery::getProjectNo).op(Contain.class)
                    .field(ProjectSuspensionQuery::getSuspensionTime).op(Contain.class)
                    .field(ProjectSuspensionQuery::getDiscontinuer).op(Contain.class)
                    .build();
            SearchResult<ProjectSuspensionDto> search = projectSuspensionService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/pageThatPassed")
    @ApiOperation(value = "分页查询接口", notes="projectSuspension")
    public PageResponse<ProjectSuspensionWorkflowDto> pageThatPassed(ProjectSuspensionQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(ProjectSuspensionQuery::getProjectName).op(Contain.class)
                    .field(ProjectSuspensionQuery::getProjectNo).op(Contain.class)
                    .field(ProjectSuspensionQuery::getSuspensionTime).op(Contain.class)
                    .field(ProjectSuspensionQuery::getDiscontinuer).op(Contain.class)
                    .build();
            SearchResult<ProjectSuspensionWorkflowDto> search = projectSuspensionService.pageThatPassed(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="projectSuspension")
    public SingleResponse<ProjectSuspensionDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(projectSuspensionService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
