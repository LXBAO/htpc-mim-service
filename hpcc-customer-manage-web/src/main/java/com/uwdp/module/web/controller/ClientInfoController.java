package com.uwdp.module.web.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.Contain;
import com.ejlchina.searcher.operator.StartWith;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.IClientInfoService;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.ClientInfoUpdateCmd;
import com.uwdp.module.api.vo.dto.ClientInfoDto;
import com.uwdp.module.api.vo.dto.searcher.ClientInfoWorkflowDto;
import com.uwdp.module.api.vo.excel.ClientInfoExcelExport;
import com.uwdp.module.api.vo.excel.ClientInfoExcelImport;
import com.uwdp.module.api.vo.query.ClientInfoQuery;
import com.uwdp.module.api.vo.query.searcher.ClientInfoWorkflowQuery;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;

/**
 * <p>
 * 客户信息总表 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/client-info-v001")
@Api(tags = "客户信息总表")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "客户信息总表")
@Validated
public class ClientInfoController {

    private final IClientInfoService clientInfoService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="clientInfo")
    public PageResponse<ClientInfoDto> page(ClientInfoQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(ClientInfoDto::getFdName).op(Contain.class)
                    .field(ClientInfoDto::getFdAffiliatedUser).op(Contain.class)
                    .field(ClientInfoDto::getFdUnit).op(Contain.class)
                    .field(ClientInfoDto::getFdClientClassify).op(Contain.class)
                    .field(ClientInfoDto::getFdContactPerson).op(Contain.class)
                    .field(ClientInfoDto::getFdCompanyAddress).op(Contain.class)
                    .field(ClientInfoDto::getCreatedByName).op(Contain.class)
                    .build();
            map.put("GROUPFULLCODE-op","sw");
            SearchResult<ClientInfoDto> search = clientInfoService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/getClientInfoCount")
    @ApiOperation(value = "查询条数接口", notes="getClientInfoCount")
    public Map getClientInfoCount(String fdName,String fdUnit) {
        try {
            System.out.println(fdName+" "+fdUnit);
            Integer clientInfoCount = clientInfoService.getClientInfoCount(fdName,fdUnit);
            System.out.println(clientInfoCount);
            Map map = new HashMap();
            map.put("data",clientInfoCount);
            return map;
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    /**
     * 只返回审批通过的客户
     * @param paramQuery
     * @param request
     * @return
     */

    @GetMapping("/pageThatPassed")
    @ApiOperation(value = "分页查询接口", notes="clientInfo")
    public PageResponse<ClientInfoWorkflowDto> pageThatPassed(ClientInfoWorkflowQuery paramQuery, HttpServletRequest request) {
        String workflowStatusParam = request.getParameter("workflowStatus");
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .put("fdName-op", "ct")
                    .put("fdAffiliatedUser-op", "ct")
                    .put("fdUnit-op", "ct")
                    .put("fdClientClassify-op", "ct")
                    .put("fdContactPerson-op", "ct")
                    .put("createdByName-op", "ct")
                    .build();

//            Map<String, Object> aMap = new HashMap<>();
//            for (String key : map.keySet()){
//                aMap.put("A." + key, map.get(key));
//            }
            if("1".equals(workflowStatusParam)){
                map.put("C.workflowStatus-op", "nl");
                map.remove("workflowStatus");
            }
            //map.put("GROUPFULLCODE-op","ct");
            SearchResult<ClientInfoWorkflowDto> search = clientInfoService.pageThatPassed(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="clientInfo")
    public SingleResponse<ClientInfoDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "fdId") Long fdId) {
        try {
            return SingleResponse.of(clientInfoService.get(fdId));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes="clientInfo")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated ClientInfoAddCmd addCmd) {
        try {
            clientInfoService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes="clientInfo")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated ClientInfoUpdateCmd updateCmd) {
        try {
            clientInfoService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes="clientInfo")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "fdId") Long fdId) {
        try {
            clientInfoService.delete(fdId);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes="clientInfo")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "fdIds") String fdIds) {
        try {
            clientInfoService.batchDelete(fdIds);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes="clientInfo")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            clientInfoService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes="clientInfo")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
        String workflowStatusParam = request.getParameter("workflowStatus");
    Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            if("1".equals(workflowStatusParam)){
                map.put("workflowStatus-op", "nl");
                map.remove("workflowStatus");
            }
            clientInfoService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes="clientInfo")
    public SingleResponse<ExcelParseDTO<ClientInfoExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(clientInfoService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes="clientInfo")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<ClientInfoExcelImport> list) {
        try {
            clientInfoService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes="clientInfo")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<ClientInfoExcelExport> list, HttpServletResponse response) {
        try {
            clientInfoService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
