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
import com.uwdp.module.api.service.IPublicRelationsService;
import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;
import com.uwdp.module.api.vo.cmd.PublicRelationsUpdateCmd;
import com.uwdp.module.api.vo.dto.PublicRelationsDto;
import com.uwdp.module.api.vo.dto.searcher.PublicRelationsHeadlineDto;
import com.uwdp.module.api.vo.excel.PublicRelationsExcelExport;
import com.uwdp.module.api.vo.excel.PublicRelationsExcelImport;
import com.uwdp.module.api.vo.query.PublicRelationsQuery;
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
 * 公关实施 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/public-relations-v1-0-0")
@Api(tags = "公关实施")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "公关实施")
@Validated
public class PublicRelationsControllerV2 {

    private final IPublicRelationsService publicRelationsService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="publicRelations")
    public PageResponse<PublicRelationsDto> page(PublicRelationsQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(PublicRelationsDto::getDutyUnit).op(Contain.class)
                    .field(PublicRelationsDto::getActivityAddress).op(Contain.class)
                    .field(PublicRelationsDto::getResults).op(Contain.class)
                    .build();
            SearchResult<PublicRelationsDto> search = publicRelationsService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/headlinePage")
    @ApiOperation(value = "标题分页查询接口", notes="publicRelations")
    public PageResponse<PublicRelationsHeadlineDto> headlinePage(PublicRelationsQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(PublicRelationsHeadlineDto::getDutyUnit).op(Contain.class)
                    .field(PublicRelationsHeadlineDto::getActivityAddress).op(Contain.class)
                    .field(PublicRelationsHeadlineDto::getResults).op(Contain.class)
                    .build();
            SearchResult<PublicRelationsHeadlineDto> search = publicRelationsService.pageHeadlineByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="publicRelations")
    public SingleResponse<PublicRelationsDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(publicRelationsService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
