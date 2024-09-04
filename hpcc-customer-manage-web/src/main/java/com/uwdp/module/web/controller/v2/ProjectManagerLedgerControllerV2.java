package com.uwdp.module.web.controller.v2;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.Contain;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.IProjectManagerLedgerService;
import com.uwdp.module.api.vo.dto.ProjectManagerLedgerDto;
import com.uwdp.module.api.vo.query.ProjectManagerLedgerQuery;
import com.uwdp.utils.NoToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 项目经理台账 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/project-manager-ledger-v1-0-0")
@Api(tags = "项目经理台账")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目经理台账")
@Validated
public class ProjectManagerLedgerControllerV2 {

    private final IProjectManagerLedgerService projectManagerLedgerService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="projectManagerLedger")
    public PageResponse<ProjectManagerLedgerDto> page(ProjectManagerLedgerQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flatBuilder(request.getParameterMap())
                    .field(ProjectManagerLedgerDto::getFdPMName).op(Contain.class)
                    .field(ProjectManagerLedgerDto::getFdSafetyCertificate).op(Contain.class)
                    .field(ProjectManagerLedgerDto::getFdCertificateNum).op(Contain.class)
                    .build();
            map.remove("createdBy");//去掉创建人筛选条件
            SearchResult<ProjectManagerLedgerDto> search = projectManagerLedgerService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @NoToken
    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="projectManagerLedger")
    public SingleResponse<ProjectManagerLedgerDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "fdId") Long fdId) {
        try {
            return SingleResponse.of(projectManagerLedgerService.get(fdId));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detailByid")
    @ApiOperation(value = "详情接口", notes="projectManagerLedger")
    public SingleResponse<ProjectManagerLedgerDto> detailByid(@ApiParam(value = "主键", required = true) @RequestParam(name = "hrId") String hrId) {
        try {
            return SingleResponse.of(projectManagerLedgerService.detailByid(hrId));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
