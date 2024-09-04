package com.uwdp.module.api.vo.dashboard;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lx
 * @data 2023/7/27 17:31
 */
@Data
public class IndicatorsDashboardVO {
    //国内[项目跟踪，项目签约，项目中标，项目实施,项目投标,客户信息 ]
    private List<Integer> counts;

    //总年度整体指标
    private MarketAmtVO marketAmtVO = new MarketAmtVO();

    //每月指标
//    private List<Map<String,Object>> monthMarkerMaps;
    //每月签约
//    private List<Map<String,Object>> monthSignMaps;
    // 每月业绩完成情况
    List<Map<String, Object>> monthList;
    //根据各省分组统计
    private List<Map<String,Object>> statisticsSignMaps = new ArrayList<>();

    //根据各领域统计
    private List<Map<String,Object>> statisticsIndustryMaps;
    //各公司签约
    private List<SignCondition> signConditionList;
    //公司中标数量
    private List<WinThebidConditionVO> winThebidCondition;


}
