package com.uwdp.workflow.controller.preview;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.IForeignAddressService;
import com.uwdp.module.api.service.ILinkmanService;
import com.uwdp.module.api.service.IVisitPlanService;
import com.uwdp.module.api.vo.cmd.ProjectEnableAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectEnableUpdateCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanAddCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanUpdateCmd;
import com.uwdp.module.api.vo.dto.ForeignAddressDto;
import com.uwdp.module.api.vo.dto.LinkmanDto;
import com.uwdp.module.api.vo.dto.ProjectEnableDto;
import com.uwdp.module.api.vo.dto.VisitPlanDto;
import com.uwdp.module.api.vo.query.ForeignAddressQuery;
import com.uwdp.module.api.vo.query.LinkmanQuery;
import com.uwdp.sso.user.UserUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *  在流程中更新业务表单数据 , 本类中的接口通过路由规则实现无需鉴权放行(实际要查看网关配置)
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hpcc-customer-manage/preview/workflow")
public class UpdateBizDataInWorkflowController {

    private final IVisitPlanService iVisitPlanService;

    private final com.uwdp.module.api.service.IProjectEnableService projectEnableService;

    private final UserUtil userUtil;

    private final IVisitPlanService visitPlanService;

    private final IForeignAddressService foreignAddressService;

    private final ILinkmanService linkmanService;
    /**
     * 仅供前端调用
     * 改为任务回调中 处理逻辑
     * @param updateCmd
     * @param request
     * @return
     */
    @Deprecated
    @PutMapping("/visitPlan/updateIfConference")
    @ApiOperation(value = "流程审批页面修改ifConference字段", notes="updateIfConference")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response updateIfConference(@RequestBody @Validated VisitPlanUpdateCmd updateCmd , HttpServletRequest request) {
        try {
            String personName = userUtil.personName(request);
            log.info(">>>>>>>>>>修改ifConference字段接收到参数:{}",updateCmd);
            updateCmd.setCurrentConferenceLeaderName(personName);
            iVisitPlanService.updateConferenceLeadersInWorkflow(updateCmd);
            return Response.buildSuccess();
        }catch (JSONException j){
            throw new BizRuntimeException("500","登录状态已过期,请重新登陆",j);
        }catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    /**
     * 在流程中更新领导评分建议
     * 改为任务回调中 处理逻辑
     * @param updateCmd
     * @param request
     * @return
     */
    @Deprecated
    @PutMapping("/projectEnable/updateScoreList")
    @ApiOperation(value = "流程审批页面修改领导评分建议", notes="updateScoreList")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response updateScoreList(@RequestBody @Validated ProjectEnableUpdateCmd updateCmd , HttpServletRequest request) {
        try {
            String personName = userUtil.personName(request);
            log.info(">>>>>>>>>>修改ScoreList字段接收到参数:{}",updateCmd);
            // 保存触发动作的当前人员姓名 , 后面service中先查再改 , 这里不影响最终修改数据
            updateCmd.setCreatedByName(personName);
            projectEnableService.updateScoreList(updateCmd);
            return Response.buildSuccess();
        }catch (JSONException j){
            throw new BizRuntimeException("500","登录状态已过期,请重新登陆",j);
        }catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


    @GetMapping("/visit-plan/detail")
    @ApiOperation(value = "详情接口", notes="visitPlan")
    public SingleResponse<VisitPlanDto> visitPlanGet(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(visitPlanService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/foreign-address/page")
    @ApiOperation(value = "分页查询接口", notes="foreignAddress")
    public PageResponse<ForeignAddressDto> foreignAddressPage(ForeignAddressQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("foreignAddressName-op","ct");
            SearchResult<ForeignAddressDto> search = foreignAddressService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/linkman/page")
    @ApiOperation(value = "分页查询接口", notes="linkman")
    public PageResponse<LinkmanDto> linkmanPage(LinkmanQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            SearchResult<LinkmanDto> search = linkmanService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/projectEnable/detail")
    @ApiOperation(value = "详情接口", notes="projectEnable")
    public SingleResponse<ProjectEnableDto> projectEnableGet(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(projectEnableService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

}
