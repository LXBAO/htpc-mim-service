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
import com.uwdp.module.api.service.IProjectImplementService;
import com.uwdp.module.api.service.IProjectRecordsService;
import com.uwdp.module.api.vo.cmd.ProjectImplementAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectImplementUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectImplementDto;
import com.uwdp.module.api.vo.excel.ProjectImplementExcelExport;
import com.uwdp.module.api.vo.excel.ProjectImplementExcelImport;
import com.uwdp.module.api.vo.query.ProjectImplementQuery;
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
 * 项目实施 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/project-implement-v1-0-0")
@Api(tags = "项目实施")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目实施")
@Validated
public class ProjectImplementControllerV2 {

    private final IProjectImplementService projectImplementService;

    private final IProjectRecordsService projectRecordsService;



    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="projectImplement")
    public PageResponse<ProjectImplementDto> page(ProjectImplementQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("projectName-op","ct");
            map.put("registrationUnit-op","ct");
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
}
