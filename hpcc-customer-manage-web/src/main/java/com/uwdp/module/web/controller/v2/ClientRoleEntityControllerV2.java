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
import com.uwdp.module.api.service.IClientRoleService;
import com.uwdp.module.api.vo.cmd.ClientRoleEntityAddCmd;
import com.uwdp.module.api.vo.cmd.ClientRoleEntityUpdateCmd;
import com.uwdp.module.api.vo.dto.ClientRoleEntityDto;
import com.uwdp.module.api.vo.excel.ClientRoleEntityExcelExport;
import com.uwdp.module.api.vo.excel.ClientRoleEntityExcelImport;
import com.uwdp.module.api.vo.query.ClientRoleEntityQuery;
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
 * 权限表 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/client-role-entity-v1-0-0")
@Api(tags = "权限表")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "权限表")
@Validated
public class ClientRoleEntityControllerV2 {

    private final IClientRoleService clientRoleEntityService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="ClientRoleEntity")
    public PageResponse<ClientRoleEntityDto> page(ClientRoleEntityQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("deptName-op", "ct");
            map.put("userName-op", "ct");
            map.put("roleName-op", "ct");
            map.remove("createdBy");//去掉创建人筛选条件
            SearchResult<ClientRoleEntityDto> search = clientRoleEntityService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="ClientRoleEntity")
    public SingleResponse<ClientRoleEntityDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Integer id) {
        try {
            return SingleResponse.of(clientRoleEntityService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
