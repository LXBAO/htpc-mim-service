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
import com.uwdp.mdm.service.QueryMdmService;
import com.uwdp.module.api.service.IMdmOrganizationService;
import com.uwdp.module.api.vo.cmd.MdmOrganizationAddCmd;
import com.uwdp.module.api.vo.cmd.MdmOrganizationUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import com.uwdp.module.api.vo.excel.MdmOrganizationExcelExport;
import com.uwdp.module.api.vo.excel.MdmOrganizationExcelImport;
import com.uwdp.module.api.vo.query.MdmOrganizationQuery;
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
 * 主数据-组织 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/mdm-organization-v1-0-0")
@Api(tags = "主数据-组织")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "主数据-组织")
@Validated
public class MdmOrganizationControllerV2 {

    private final IMdmOrganizationService mdmOrganizationService;

    private final QueryMdmService queryMdmService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="mdmOrganization")
    public PageResponse<MdmOrganizationDto> page(MdmOrganizationQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            SearchResult<MdmOrganizationDto> search = mdmOrganizationService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="mdmOrganization")
    public SingleResponse<MdmOrganizationDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(mdmOrganizationService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @ResponseBody
    @GetMapping({"/getOrganizationTree"})
    @ApiOperation(value = "获取单位部门树状json", notes = "getOrganizationTree")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public String getOrganizationTree() {
        return queryMdmService.get().toJSONString();
    }
}
