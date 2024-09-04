package com.uwdp.module.api.service;

import com.uwdp.module.api.vo.dashboard.IndicatorsDashboardVO;
import com.uwdp.module.api.vo.dashboard.MarketAmtVO;
import com.uwdp.module.api.vo.dashboard.SignCondition;
import com.uwdp.module.api.vo.dashboard.WinThebidConditionVO;

import java.util.List;
import java.util.Map;

/**
 * @author lx
 * @data 2023/7/27 14:59
 */
public interface IndicatorsDashboardService {
    /**
     * 大屏总体信息
     *
     * @param queryStr
     * @param type
     * @param year
     * @return
     */
    IndicatorsDashboardVO info(String queryStr, int type, Integer year);

    /**
     * 根据type计算项目数量
     *
     * @param type 0国内 1国际
     * @param year
     * @return
     */
    List<Integer> getCounts(int type, Integer year);

    /**
     * 国内外切换
     *
     * @param queryStr MONTH （月）/ QUARTER（季度）/ HAIF（半年）
     * @param type     0国内 1国际
     * @param year
     * @return
     */
    IndicatorsDashboardVO internationalHandover(String queryStr, int type, Integer year);

    /**
     *
      * @param indicatorsDashboardVO
     * @param year
     */
    MarketAmtVO marketAndSign(IndicatorsDashboardVO indicatorsDashboardVO, Integer year);

    /**
     * 获取各公司签约情况
     *
     * @param queryStr
     * @return
     */
    List<SignCondition> signCondition(String queryStr, Integer year);

    /**
     * 各产业领域签约情况
     *
     * @param queryStr
     * @param type
     * @return
     */
    List<Map<String, Object>> statisticsIndustry(String queryStr, int type, Integer year);

    /**
     * 各公司中标数量
     *
     * @param queryStr
     * @return
     */
    List<WinThebidConditionVO> winThebidCondition(String queryStr, Integer year);
}
