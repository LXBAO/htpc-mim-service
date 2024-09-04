package com.uwdp.module.web.controller.v2;

import cn.hutool.core.date.DateUtil;
import com.alibaba.cola.dto.SingleResponse;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.IndicatorsDashboardService;
import com.uwdp.module.api.vo.dashboard.IndicatorsDashboardVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author lx
 * @data 2023/7/27 15:13
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/indicators-dashboard")
@Api(tags = "首页仪表盘")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "首页仪表盘")
public class IndicatorsDashboardControllerV2 {

    private final IndicatorsDashboardService indicatorsDashboardService;

    @GetMapping("/info")
    @ApiOperation(value = "获取仪表盘信息", notes = "designUnit")
    public SingleResponse info(HttpServletRequest request,
                               @RequestParam(name = "queryStr") String queryStr,
                               @RequestParam(name = "type") int type,
                               @RequestParam(name = "year", required = false) Integer year) {
        try {
            return SingleResponse.of(indicatorsDashboardService.info(queryStr, type, year));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/getCounts")
    @ApiOperation(value = "大屏上统计项目数量", notes = "designUnit")
    public SingleResponse getCounts(@RequestParam(name = "type") int type,
                                    @RequestParam(name = "year", required = false) Integer year) {
        try {
            if(year == null){
                year = DateUtil.year(new Date());
            }
            return SingleResponse.of(indicatorsDashboardService.getCounts(type, year));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/internationalHandover")
    @ApiOperation(value = "大屏上国内外切换数据刷新接口", notes = "designUnit")
    public SingleResponse internationalHandover(HttpServletRequest request,
                                                @RequestParam(name = "queryStr") String queryStr,
                                                @RequestParam(name = "type") int type,
                                                @RequestParam(name = "year", required = false) Integer year) {
        try {
            return SingleResponse.of(indicatorsDashboardService.internationalHandover(queryStr, type, year));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/marketAndSign")
    @ApiOperation(value = "整体业绩指标完成情况", notes = "designUnit")
    public SingleResponse marketAndSign(HttpServletRequest request,
                                        @RequestParam(name = "year", required = false) Integer year) {
        try {
            if(year == null){
                year = DateUtil.year(new Date());
            }
            return SingleResponse.of(indicatorsDashboardService.marketAndSign(new IndicatorsDashboardVO(), year));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/statisticsIndustry")
    @ApiOperation(value = "各业务领域签约", notes = "designUnit")
    public SingleResponse statisticsIndustry(HttpServletRequest request,
                                             @RequestParam(name = "queryStr") String queryStr,
                                             @RequestParam(name = "type") int type,
                                             @RequestParam(name = "year", required = false) Integer year) {
        try {
            if(year == null){
                year = DateUtil.year(new Date());
            }
            return SingleResponse.of(indicatorsDashboardService.statisticsIndustry(queryStr, type, year));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/signCondition")
    @ApiOperation(value = "查询各公司签约情况", notes = "designUnit")
    public SingleResponse signCondition(HttpServletRequest request,
                                        @RequestParam(name = "queryStr") String queryStr,
                                        @RequestParam(name = "year", required = false) Integer year) {
        try {
            if(year == null){
                year = DateUtil.year(new Date());
            }
            return SingleResponse.of(indicatorsDashboardService.signCondition(queryStr, year));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/winThebidCondition")
    @ApiOperation(value = "查询各公司中标情况", notes = "designUnit")
    public SingleResponse winThebidCondition(HttpServletRequest request,
                                             @RequestParam(name = "queryStr") String queryStr,
                                             @RequestParam(name = "year", required = false) Integer year) {
        try {
            if(year == null){
                year = DateUtil.year(new Date());
            }
            return SingleResponse.of(indicatorsDashboardService.winThebidCondition(queryStr, year));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
