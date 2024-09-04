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
import com.uwdp.module.api.service.IProjectEnableDetailService;
import com.uwdp.module.api.vo.cmd.ProjectEnableDetailAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectEnableDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectEnableDetailDto;
import com.uwdp.module.api.vo.excel.ProjectEnableDetailExcelExport;
import com.uwdp.module.api.vo.excel.ProjectEnableDetailExcelImport;
import com.uwdp.module.api.vo.query.ProjectEnableDetailQuery;
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
 * 项目赋能明细 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/project-enable-detail-v1-0-0")
@Api(tags = "项目赋能明细")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目赋能明细")
@Validated
public class ProjectEnableDetailControllerV2 {

    private final IProjectEnableDetailService projectEnableDetailService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="projectEnableDetail")
    public PageResponse<ProjectEnableDetailDto> page(ProjectEnableDetailQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.remove("createdBy");
            SearchResult<ProjectEnableDetailDto> search = projectEnableDetailService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="projectEnableDetail")
    public SingleResponse<ProjectEnableDetailDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(projectEnableDetailService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
