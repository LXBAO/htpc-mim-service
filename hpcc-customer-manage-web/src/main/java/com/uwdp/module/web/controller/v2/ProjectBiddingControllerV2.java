package com.uwdp.module.web.controller.v2;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.IProjectBiddingService;
import com.uwdp.module.api.vo.dto.ProjectBiddingDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectBiddingWorkflowDto;
import com.uwdp.module.api.vo.query.ProjectBiddingQuery;
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
 * 项目投标 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/project-bidding-v1-0-0")
@Api(tags = "项目投标")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目投标")
@Validated
public class ProjectBiddingControllerV2 {

    private final IProjectBiddingService projectBiddingService;



    @NoToken
    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="ProjectBidding")
    public PageResponse<ProjectBiddingDto> page(ProjectBiddingQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("projectName-op","ct");
            map.put("createdByName-op","ct");
            map.put("projectDate-op","ct");
            SearchResult<ProjectBiddingDto> search = projectBiddingService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @NoToken
    @GetMapping("/pageById")
    @ApiOperation(value = "分页查询接口", notes="ProjectBidding")
    public PageResponse<ProjectBiddingWorkflowDto> pageById(ProjectBiddingQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("projectName-op","ct");
            map.put("createdByName-op","ct");
            map.put("projectDate-op","ct");
            SearchResult<ProjectBiddingWorkflowDto> search = projectBiddingService.pageById(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @NoToken
    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="ProjectBidding")
    public SingleResponse<ProjectBiddingDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(projectBiddingService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
