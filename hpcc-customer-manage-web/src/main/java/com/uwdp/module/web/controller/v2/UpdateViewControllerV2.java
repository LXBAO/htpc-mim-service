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
import com.uwdp.module.api.service.IUpdateViewService;
import com.uwdp.module.api.vo.cmd.UpdateViewAddCmd;
import com.uwdp.module.api.vo.cmd.UpdateViewUpdateCmd;
import com.uwdp.module.api.vo.dto.UpdateViewDto;
import com.uwdp.module.api.vo.excel.UpdateViewExcelExport;
import com.uwdp.module.api.vo.excel.UpdateViewExcelImport;
import com.uwdp.module.api.vo.query.UpdateViewQuery;
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
 * 更新查看 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/update-view-v1-0-0")
@Api(tags = "更新查看")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "更新查看")
@Validated
public class UpdateViewControllerV2 {

    private final IUpdateViewService updateViewService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="updateView")
    public PageResponse<UpdateViewDto> page(UpdateViewQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            SearchResult<UpdateViewDto> search = updateViewService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="updateView")
    public SingleResponse<UpdateViewDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(updateViewService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
