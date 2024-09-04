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
import com.uwdp.module.api.service.IMarketDmpTagService;
import com.uwdp.module.api.vo.cmd.MarketDmpTagAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagUpdateCmd;
import com.uwdp.module.api.vo.dto.MarketDmpTagDto;
import com.uwdp.module.api.vo.dto.searcher.MarketDmpTagWorkflowDto;
import com.uwdp.module.api.vo.excel.MarketDmpTagExcelExport;
import com.uwdp.module.api.vo.excel.MarketDmpTagExcelImport;
import com.uwdp.module.api.vo.query.MarketDmpTagQuery;
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
 * 市场部分公司年度指标 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/market-dmp-tag-v1-0-0")
@Api(tags = "市场部分公司年度指标")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "市场部分公司年度指标")
@Validated
public class MarketDmpTagControllerV2 {

    private final IMarketDmpTagService marketDmpTagService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="market_dmp_tag")
    public PageResponse<MarketDmpTagDto> page(MarketDmpTagQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(MarketDmpTagDto::getDmptName).op(Contain.class)
                    .field(MarketDmpTagDto::getUserName).op(Contain.class)
                    .field(MarketDmpTagDto::getTitle).op(Contain.class)
                    .field(MarketDmpTagDto::getYear).op(Contain.class)
                    .build();
            map.remove("createdBy");
            map.put("orderBy","createdTime:desc");
            SearchResult<MarketDmpTagDto> search = marketDmpTagService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/pageThatPassed")
    @ApiOperation(value = "分页查询接口", notes="market_dmp_tag")
    public PageResponse<MarketDmpTagWorkflowDto> pageThatPassed(MarketDmpTagQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(MarketDmpTagWorkflowDto::getDmptName).op(Contain.class)
                    .field(MarketDmpTagWorkflowDto::getUserName).op(Contain.class)
                    .field(MarketDmpTagWorkflowDto::getTitle).op(Contain.class)
                    .field(MarketDmpTagWorkflowDto::getYear).op(Contain.class)
                    .build();
            map.put("orderBy","createdTime:desc");
            SearchResult<MarketDmpTagWorkflowDto> search = marketDmpTagService.pageThatPassed(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="market_dmp_tag")
    public SingleResponse<MarketDmpTagDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(marketDmpTagService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
