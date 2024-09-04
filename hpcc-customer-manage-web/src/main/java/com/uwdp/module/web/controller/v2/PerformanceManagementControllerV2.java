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
import com.uwdp.module.api.service.IPerformanceManagementService;
import com.uwdp.module.api.vo.cmd.PerformanceManagementAddCmd;
import com.uwdp.module.api.vo.cmd.PerformanceManagementUpdateCmd;
import com.uwdp.module.api.vo.dto.PerformanceManagementDto;
import com.uwdp.module.api.vo.excel.PerformanceManagementExcelExport;
import com.uwdp.module.api.vo.excel.PerformanceManagementExcelImport;
import com.uwdp.module.api.vo.query.PerformanceManagementQuery;
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
 * 业绩管理 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/performance-management-v1")
@Api(tags = "业绩管理")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "业绩管理")
@Validated
public class PerformanceManagementControllerV2 {

    private final IPerformanceManagementService performanceManagementService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="performanceManagement")
    public PageResponse<PerformanceManagementDto> page(PerformanceManagementQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(PerformanceManagementDto::getProjectName).op(Contain.class)
                    .field(PerformanceManagementDto::getImplementingUnit).op(Contain.class)
                    .build();
            SearchResult<PerformanceManagementDto> search = performanceManagementService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="performanceManagement")
    public SingleResponse<PerformanceManagementDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(performanceManagementService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
