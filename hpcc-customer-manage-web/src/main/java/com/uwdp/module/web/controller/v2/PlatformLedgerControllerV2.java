package com.uwdp.module.web.controller.v2;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.Contain;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.IPlatformLedgerService;
import com.uwdp.module.api.vo.dto.PlatformLedgerDto;
import com.uwdp.module.api.vo.query.PlatformLedgerQuery;
import com.uwdp.utils.NoToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 平台台账 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/platform-ledger-v1-0-0")
@Api(tags = "平台台账")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "平台台账")
@Validated
public class PlatformLedgerControllerV2 {

    private final IPlatformLedgerService platformLedgerService;

    @NoToken
    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="platformLedger")
    public PageResponse<PlatformLedgerDto> page(PlatformLedgerQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(PlatformLedgerDto::getFdUserName).op(Contain.class)
                    .field(PlatformLedgerDto::getFdwebsitename).op(Contain.class)
                    .field(PlatformLedgerDto::getFdWebsiteAddress).op(Contain.class)
                    .field(PlatformLedgerDto::getFdScope).op(Contain.class)
                    .field(PlatformLedgerDto::getFdPlatformValidity).op(Contain.class)
                    .build();
            SearchResult<PlatformLedgerDto> search = platformLedgerService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @NoToken
    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="platformLedger")
    public SingleResponse<PlatformLedgerDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "fdId") Long fdId) {
        try {
            return SingleResponse.of(platformLedgerService.get(fdId));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
