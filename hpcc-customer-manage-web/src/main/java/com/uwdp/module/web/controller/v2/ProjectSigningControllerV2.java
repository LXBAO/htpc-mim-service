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
import com.uwdp.module.api.service.IProjectSigningService;
import com.uwdp.module.api.vo.cmd.ProjectSigningAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectSigningUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectSigningDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectSigningRecordsWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectSigningExcelExport;
import com.uwdp.module.api.vo.excel.ProjectSigningExcelImport;
import com.uwdp.module.api.vo.query.ProjectSigningQuery;
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
 * 项目签约 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/project-signing-v1")
@Api(tags = "项目签约")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目签约")
@Validated
public class ProjectSigningControllerV2 {

    private final IProjectSigningService projectSigningService;

    private final IProjectRecordsService projectRecordsService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="ProjectSigning")
    public PageResponse<ProjectSigningDto> page(ProjectSigningQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("projectName-op","ct");
            map.put("contracName-op","ct");
            map.put("groupBelongUnitName-op","ct");
            SearchResult<ProjectSigningDto> search = projectSigningService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/pageTwo")
    @ApiOperation(value = "分页查询接口", notes="ProjectSigning")
    public PageResponse<ProjectSigningRecordsWorkflowDto> pageTwo(ProjectSigningQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("contractName-op","ct");
            map.put("projectName-op","ct");
            map.put("groupBelongUnitName-op","ct");
            SearchResult<ProjectSigningRecordsWorkflowDto> search = projectSigningService.pageByParamTwo(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="ProjectSigning")
    public SingleResponse<ProjectSigningDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(projectSigningService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
