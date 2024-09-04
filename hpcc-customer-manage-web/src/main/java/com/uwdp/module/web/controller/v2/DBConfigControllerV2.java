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
import com.uwdp.module.api.service.IDBConfigService;
import com.uwdp.module.api.vo.cmd.DBConfigAddCmd;
import com.uwdp.module.api.vo.cmd.DBConfigUpdateCmd;
import com.uwdp.module.api.vo.dto.DBConfigDto;
import com.uwdp.module.api.vo.excel.DBConfigExcelExport;
import com.uwdp.module.api.vo.excel.DBConfigExcelImport;
import com.uwdp.module.api.vo.query.DBConfigQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * 数据库连接配置 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/d-bconfig-v1-0-0")
@Api(tags = "数据库连接配置")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "数据库连接配置")
@Validated
@Slf4j
public class DBConfigControllerV2 {

    private final IDBConfigService dBConfigService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="DBConfig")
    public PageResponse<DBConfigDto> page(DBConfigQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            SearchResult<DBConfigDto> search = dBConfigService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="DBConfig")
    public SingleResponse<DBConfigDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "fdId") Long fdId) {
        try {
            return SingleResponse.of(dBConfigService.get(fdId));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
