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
import com.uwdp.module.api.service.IForeignAddressService;
import com.uwdp.module.api.vo.cmd.ForeignAddressAddCmd;
import com.uwdp.module.api.vo.cmd.ForeignAddressUpdateCmd;
import com.uwdp.module.api.vo.dto.ForeignAddressDto;
import com.uwdp.module.api.vo.excel.ForeignAddressExcelExport;
import com.uwdp.module.api.vo.excel.ForeignAddressExcelImport;
import com.uwdp.module.api.vo.query.ForeignAddressQuery;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 国外地址 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/foreign-address-v1-0-0")
@Api(tags = "国外地址")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "国外地址")
@Validated
public class ForeignAddressControllerV2 {

    private final IForeignAddressService foreignAddressService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="foreignAddress")
    public PageResponse<ForeignAddressDto> page(ForeignAddressQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("foreignAddressName-op","ct");
            SearchResult<ForeignAddressDto> search = foreignAddressService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="foreignAddress")
    public SingleResponse<ForeignAddressDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(foreignAddressService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detailByForeignAddressId")
    @ApiOperation(value = "详情接口", notes="foreignAddress")
    public SingleResponse<List<ForeignAddressDto>> getByForeignAddressId(@ApiParam(value = "主键", required = true) @RequestParam(name = "foreignAddressId") String foreignAddressId) {
        try {
            Map map = new HashMap<>();
            map.put("foreignAddressId",foreignAddressId);
            return SingleResponse.of(foreignAddressService.searchByParam(map));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/listForeignAddressId")
    @ApiOperation(value = "全量接口", notes="foreignAddress")
    public SingleResponse<List<ForeignAddressDto>> listForeignAddress(@ApiParam(value = "主键", required = true)  String foreignAddressId) {
        try {
            Map map = new HashMap<>();
            map.put("foreignAddressId",foreignAddressId);
            return SingleResponse.of(foreignAddressService.searchByParam(map));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
