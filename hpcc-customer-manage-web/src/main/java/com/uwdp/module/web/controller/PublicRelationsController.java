package com.uwdp.module.web.controller;

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
import com.uwdp.module.api.vo.dto.VisitPlanDto;
import com.uwdp.module.api.vo.dto.searcher.PublicRelationsHeadlineDto;
import com.uwdp.module.api.vo.excel.PublicRelationsExcelExport;
import com.uwdp.module.api.vo.excel.PublicRelationsExcelImport;
import com.uwdp.module.api.vo.query.PublicRelationsQuery;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 公关实施 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/public-relations-v1-0-0")
@Api(tags = "公关实施")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "公关实施")
@Validated
public class PublicRelationsController {

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
        String workflowStatusParam = request.getParameter("workflowStatus");
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(PublicRelationsHeadlineDto::getDutyUnit).op(Contain.class)
                    .field(PublicRelationsHeadlineDto::getCreatedByName).op(Contain.class)
                    .field(PublicRelationsHeadlineDto::getActivityAddress).op(Contain.class)
                    .field(PublicRelationsHeadlineDto::getResults).op(Contain.class)
                    .build();
            map.put("GROUPFULLCODE-op","ct");
            if("1".equals(workflowStatusParam)){
                map.put("workflowStatus-op", "nl");
                map.remove("workflowStatus");
            }
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

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="publicRelations")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated PublicRelationsAddCmd addCmd) {
        try {
            publicRelationsService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="publicRelations")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated PublicRelationsUpdateCmd updateCmd) {
        try {
            publicRelationsService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="publicRelations")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            publicRelationsService.delete(id);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="publicRelations")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids) {
        try {
            publicRelationsService.batchDelete(ids);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="publicRelations")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            publicRelationsService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="publicRelations")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
        String workflowStatusParam = request.getParameter("workflowStatus");
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            if("1".equals(workflowStatusParam)){
                map.put("workflowStatus-op", "nl");
                map.remove("workflowStatus");
            }
            publicRelationsService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="publicRelations")
    public SingleResponse<ExcelParseDTO<PublicRelationsExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(publicRelationsService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="publicRelations")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<PublicRelationsExcelImport> list) {
        try {
            publicRelationsService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="publicRelations")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<PublicRelationsExcelExport> list, HttpServletResponse response) {
        try {
            publicRelationsService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
