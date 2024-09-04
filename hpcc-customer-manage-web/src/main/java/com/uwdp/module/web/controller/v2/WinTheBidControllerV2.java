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
import com.uwdp.module.api.service.IProjectRecordsService;
import com.uwdp.module.api.service.IWinTheBidService;
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
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目中标 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/win-the-bid-v1")
@Api(tags = "项目中标")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目中标")
@Validated
public class WinTheBidControllerV2 {

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
        try{
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("groupBelongUnitName-op","ct");
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
}
