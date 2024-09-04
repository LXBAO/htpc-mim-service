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
import com.uwdp.module.api.service.IMdmPostService;
import com.uwdp.module.api.vo.cmd.MdmPostAddCmd;
import com.uwdp.module.api.vo.cmd.MdmPostUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmPostDto;
import com.uwdp.module.api.vo.excel.MdmPostExcelExport;
import com.uwdp.module.api.vo.excel.MdmPostExcelImport;
import com.uwdp.module.api.vo.query.MdmPostQuery;
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
 * 主数据-岗位 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/mdm-post-v1-0-0")
@Api(tags = "主数据-岗位")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "主数据-岗位")
@Validated
public class MdmPostControllerV2 {

    private final IMdmPostService mdmPostService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="mdmPost")
    public PageResponse<MdmPostDto> page(MdmPostQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            SearchResult<MdmPostDto> search = mdmPostService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="mdmPost")
    public SingleResponse<MdmPostDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(mdmPostService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
