package com.uwdp.module.web.controller.v2;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.Contain;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.ICALedgerService;
import com.uwdp.module.api.vo.dto.CALedgerDto;
import com.uwdp.module.api.vo.query.CALedgerQuery;
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
 * CA台账 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/c-aledger-v1-0-0")
@Api(tags = "CA台账")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "CA台账")
@Validated
public class CALedgerControllerV2 {

    private final ICALedgerService cALedgerService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="CALedger")
    public PageResponse<CALedgerDto> page(CALedgerQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(CALedgerDto::getFdUserName).op(Contain.class)
                    .field(CALedgerDto::getFdwebsitename).op(Contain.class)
                    .field(CALedgerDto::getFdWebsiteAddress).op(Contain.class)
                    .field(CALedgerDto::getFdScope).op(Contain.class)
                    .field(CALedgerDto::getFdPlatformValidity).op(Contain.class)
                    .build();
            SearchResult<CALedgerDto> search = cALedgerService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @NoToken
    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="CALedger")
    public SingleResponse<CALedgerDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "fdId") Long fdId) {
        try {
            return SingleResponse.of(cALedgerService.get(fdId));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
