package com.uwdp.module.web.controller;

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
import com.uwdp.module.api.service.IProjectRecordsService;
import com.uwdp.module.api.service.IWinTheBidService;
import com.uwdp.module.api.vo.cmd.ProjectRecordsUpdateCmd;
import com.uwdp.module.api.vo.cmd.WinTheBidAddCmd;
import com.uwdp.module.api.vo.cmd.WinTheBidUpdateCmd;
import com.uwdp.module.api.vo.dto.WinTheBidDto;
import com.uwdp.module.api.vo.dto.searcher.WinTheBidWorkflowDto;
import com.uwdp.module.api.vo.excel.WinTheBidExcelExport;
import com.uwdp.module.api.vo.excel.WinTheBidExcelImport;
import com.uwdp.module.api.vo.query.WinTheBidQuery;
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
 * 项目中标 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/win-the-bid-v1")
@Api(tags = "项目中标")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目中标")
@Validated
public class WinTheBidController {

    private final IWinTheBidService winTheBidService;

    private final IProjectRecordsService projectRecordsService;
    /**
     * 分页查询
     * @param paramQuery
     * @param request
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="WinTheBid")
    public PageResponse<WinTheBidDto> page(WinTheBidQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            SearchResult<WinTheBidDto> search = winTheBidService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    /**
     * 分页查询
     * @param paramQuery
     * @param request
     * @return
     */
    @GetMapping("/workflowPage")
    @ApiOperation(value = "分页查询接口", notes="winTheBid")
    public PageResponse<WinTheBidWorkflowDto> workflowPage(WinTheBidQuery paramQuery, HttpServletRequest request){
        String workflowStatusParam = request.getParameter("workflowStatus");
        try{
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
//            Map<String, Object> aMap = new HashMap<>();
//            for (String key : map.keySet()){
//                aMap.put("A." + key, map.get(key));
//            }
            map.put("C.groupFullCode-op","ct");
            map.put("C.projectName-op","ct");
            map.put("C.createdName-op","ct");
            if("1".equals(workflowStatusParam)){
                map.put("C.workflowStatus-op", "nl");
                map.remove("workflowStatus");
            }
            SearchResult<WinTheBidWorkflowDto> search = winTheBidService.workflowPageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        }catch (Exception e){
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="WinTheBid")
    public SingleResponse<WinTheBidDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(winTheBidService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="WinTheBid")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated WinTheBidAddCmd addCmd) {
        try {
            winTheBidService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="WinTheBid")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated WinTheBidUpdateCmd updateCmd) {
        try {
            winTheBidService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="WinTheBid")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id,@RequestParam(name = "projectNumber") String projectNumber) {
        try {
            winTheBidService.delete(id,projectNumber);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="WinTheBid")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids,
                                @RequestParam(name = "numbers") String numbers) {
        try {
            winTheBidService.batchDelete(ids,numbers);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="WinTheBid")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            winTheBidService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="WinTheBid")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
        String workflowStatusParam = request.getParameter("workflowStatus");
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            if("1".equals(workflowStatusParam)){
                map.put("workflowStatus-op", "nl");
                map.remove("workflowStatus");
            }
            winTheBidService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="WinTheBid")
    public SingleResponse<ExcelParseDTO<WinTheBidExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(winTheBidService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="WinTheBid")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<WinTheBidExcelImport> list) {
        try {
            winTheBidService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="WinTheBid")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<WinTheBidExcelExport> list, HttpServletResponse response) {
        try {
            winTheBidService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
