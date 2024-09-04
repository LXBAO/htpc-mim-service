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
import com.uwdp.module.api.service.IProjectEnableService;
import com.uwdp.module.api.vo.cmd.ProjectEnableAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectEnableUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectEnableDto;
import com.uwdp.module.api.vo.excel.ProjectEnableExcelExport;
import com.uwdp.module.api.vo.excel.ProjectEnableExcelImport;
import com.uwdp.module.api.vo.query.ProjectEnableQuery;
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
 * 项目赋能 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/project-enable-v1-0-0")
@Api(tags = "项目赋能")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目赋能")
@Validated
public class ProjectEnableControllerV2 {

    private final IProjectEnableService projectEnableService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="projectEnable")
    public PageResponse<ProjectEnableDto> page(ProjectEnableQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("projectName-op","ct");
            map.put("divisionLeader-op","ct");
            map.put("participant-op","ct");
            map.put("responsibleCompany-op","ct");
            map.put("createdByName-op","ct");
            SearchResult<ProjectEnableDto> search = projectEnableService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="projectEnable")
    public SingleResponse<ProjectEnableDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(projectEnableService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
