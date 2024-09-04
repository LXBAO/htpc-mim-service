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
import com.uwdp.module.api.service.IConstructionUnitService;
import com.uwdp.module.api.vo.cmd.ConstructionUnitAddCmd;
import com.uwdp.module.api.vo.cmd.ConstructionUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.ConstructionUnitDto;
import com.uwdp.module.api.vo.excel.ConstructionUnitExcelExport;
import com.uwdp.module.api.vo.excel.ConstructionUnitExcelImport;
import com.uwdp.module.api.vo.query.ConstructionUnitQuery;
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
 * 建设单位 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/construction-unit-v1")
@Api(tags = "建设单位")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "建设单位")
@Validated
public class ConstructionUnitControllerV2 {

    private final IConstructionUnitService constructionUnitService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="constructionUnit")
    public PageResponse<ConstructionUnitDto> page(ConstructionUnitQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            SearchResult<ConstructionUnitDto> search = constructionUnitService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="constructionUnit")
    public SingleResponse<ConstructionUnitDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(constructionUnitService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
