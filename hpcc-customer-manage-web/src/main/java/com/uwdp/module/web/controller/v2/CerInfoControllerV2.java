package com.uwdp.module.web.controller.v2;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.ICerInfoService;
import com.uwdp.module.api.vo.dto.CerInfoDto;
import com.uwdp.module.api.vo.query.CerInfoQuery;
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
 * 证书信息 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/cer-info-v1-0-0")
@Api(tags = "证书信息")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "证书信息")
@Validated
public class CerInfoControllerV2 {

    private final ICerInfoService cerInfoService;

    @NoToken
    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="cerInfo")
    public PageResponse<CerInfoDto> page(CerInfoQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            SearchResult<CerInfoDto> search = cerInfoService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="cerInfo")
    public SingleResponse<CerInfoDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(cerInfoService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


}
