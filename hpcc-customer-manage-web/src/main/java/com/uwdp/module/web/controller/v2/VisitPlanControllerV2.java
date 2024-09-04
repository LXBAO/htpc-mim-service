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
import com.uwdp.module.api.service.IVisitPlanService;
import com.uwdp.module.api.vo.cmd.VisitPlanAddCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanUpdateCmd;
import com.uwdp.module.api.vo.dto.VisitPlanDto;
import com.uwdp.module.api.vo.dto.searcher.VisitPlanCliNameDto;
import com.uwdp.module.api.vo.excel.VisitPlanExcelExport;
import com.uwdp.module.api.vo.excel.VisitPlanExcelImport;
import com.uwdp.module.api.vo.query.VisitPlanQuery;
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
 * 公关关系计划 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/visit-plan-v1-0-0")
@Api(tags = "公关关系计划")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "公关关系计划")
@Validated
public class VisitPlanControllerV2 {

    private final IVisitPlanService visitPlanService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="visitPlan")
    public PageResponse<VisitPlanDto> page(VisitPlanQuery paramQuery, HttpServletRequest request) {
        System.out.println(paramQuery.getAdviceDate());
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(VisitPlanDto::getActivityAddress).op(Contain.class)
                    .build();
            map.put("adviceDate-op","ct");
            SearchResult<VisitPlanDto> search = visitPlanService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


    @GetMapping("/newPage")
    @ApiOperation(value = "分页查询接口", notes="visitPlan")
    public PageResponse<VisitPlanCliNameDto> newPage(VisitPlanQuery paramQuery, HttpServletRequest request) {
        System.out.println(paramQuery.getAdviceDate());
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(VisitPlanCliNameDto::getActivityAddress).op(Contain.class)
                    .field(VisitPlanCliNameDto::getFdName).op(Contain.class)
                    .field(VisitPlanCliNameDto::getTitle).op(Contain.class)
                    .field(VisitPlanCliNameDto::getTravelLeader).op(Contain.class)
                    .field(VisitPlanCliNameDto::getTravelLeaderName).op(Contain.class)
                    .field(VisitPlanCliNameDto::getCreatedByName).op(Contain.class)
                    .build();
            map.put("adviceDate-op","ct");
            SearchResult<VisitPlanCliNameDto> search = visitPlanService.pageByNameParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="visitPlan")
    public SingleResponse<VisitPlanDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(visitPlanService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
