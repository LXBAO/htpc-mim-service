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
import com.uwdp.module.api.service.IClientInfoService;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.ClientInfoUpdateCmd;
import com.uwdp.module.api.vo.dto.ClientInfoDto;
import com.uwdp.module.api.vo.dto.searcher.ClientInfoWorkflowDto;
import com.uwdp.module.api.vo.excel.ClientInfoExcelExport;
import com.uwdp.module.api.vo.excel.ClientInfoExcelImport;
import com.uwdp.module.api.vo.query.ClientInfoQuery;
import com.uwdp.module.api.vo.query.searcher.ClientInfoWorkflowQuery;
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
 * 客户信息总表 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/client-info-v001")
@Api(tags = "客户信息总表")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "客户信息总表")
@Validated
public class ClientInfoControllerV2 {

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

    /**
     * 只返回审批通过的客户
     * @param paramQuery
     * @param request
     * @return
     */

    @GetMapping("/pageThatPassed")
    @ApiOperation(value = "分页查询接口", notes="clientInfo")
    public PageResponse<ClientInfoWorkflowDto> pageThatPassed(ClientInfoWorkflowQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(ClientInfoWorkflowDto::getFdName).op(Contain.class)
                    .field(ClientInfoWorkflowDto::getFdAffiliatedUser).op(Contain.class)
                    .field(ClientInfoWorkflowDto::getFdUnit).op(Contain.class)
                    .field(ClientInfoWorkflowDto::getFdClientClassify).op(Contain.class)
                    .field(ClientInfoWorkflowDto::getFdContactPerson).op(Contain.class)
                    .field(ClientInfoDto::getCreatedByName).op(Contain.class)
                    .build();
            map.put("GROUPFULLCODE-op","ct");

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
}
