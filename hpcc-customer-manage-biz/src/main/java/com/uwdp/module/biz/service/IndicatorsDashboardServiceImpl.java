package com.uwdp.module.biz.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.uwdp.module.api.service.IndicatorsDashboardService;
import com.uwdp.module.api.vo.dashboard.IndicatorsDashboardVO;
import com.uwdp.module.api.vo.dashboard.MarketAmtVO;
import com.uwdp.module.api.vo.dashboard.SignCondition;
import com.uwdp.module.api.vo.dashboard.WinThebidConditionVO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * @author lx
 * @data 2023/7/27 14:59
 */
@Service
public class IndicatorsDashboardServiceImpl implements IndicatorsDashboardService {

    final BigDecimal tenThousand = new BigDecimal(10000);
    final BigDecimal ratio = new BigDecimal(100);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public IndicatorsDashboardVO info(String queryStr, int type, Integer year) {
        IndicatorsDashboardVO indicatorsDashboardVO = new IndicatorsDashboardVO();

        //计算项目数量
        indicatorsDashboardVO.setCounts(getCounts(type, year));
        //计算整体指标
        marketAndSign(indicatorsDashboardVO, year);
        //统计每月指标完成进度，各省签约数
        statistics(indicatorsDashboardVO, type, year);
        //各领域统计
        indicatorsDashboardVO.setStatisticsIndustryMaps(statisticsIndustry(queryStr, type, year));
        //各公司签约19900
        indicatorsDashboardVO.setSignConditionList(signCondition(queryStr, year));
        //公司中标数量
        indicatorsDashboardVO.setWinThebidCondition(winThebidCondition(queryStr, year));
        return indicatorsDashboardVO;
    }

    @Override
    public List<Integer> getCounts(int type, Integer year) {
        String sql;
        if (type == 0) {
            sql = "SELECT count(t.id) as count from  T_PROJECTRECORDS t" +
                    " LEFT JOIN t_lmcworkflow f ON t.id = f.BIZID WHERE t.ISFORIEN='0' AND f.WORKFLOWSTATUS = 'COMPLETED' AND t.projectPhase = '2' UNION ALL " +
                    " SELECT count(t.id) as count from  T_PROJECTBIDDING t LEFT JOIN T_PROJECTRECORDS t2  ON  " +
                    " t.PROJECTNUMBER = t2.id LEFT JOIN t_lmcworkflow f ON t.id = f.BIZID WHERE  YEAR(t.CREATED_TIME)=" + year + " and t2.ISFORIEN='0' AND f.WORKFLOWSTATUS = 'COMPLETED' UNION ALL " +
                    "SELECT count(t.id) as count from  T_WINTHEBID t  LEFT JOIN T_PROJECTRECORDS t2 on  " +
                    " t.ITEMNUMBER = t2.id LEFT JOIN t_lmcworkflow f ON t.id = f.BIZID WHERE YEAR(t.DATA)=" + year + " and t2.ISFORIEN='0' AND f.WORKFLOWSTATUS = 'COMPLETED' UNION ALL " +
                    "SELECT count(t.id) as count from  T_PROJECTSIGNING t LEFT JOIN T_PROJECTRECORDS t2 on  " +
                    " t.PROJECTNUMBER = t2.id LEFT JOIN t_lmcworkflow f ON t.id = f.BIZID WHERE  YEAR(t.TIMEOFSIGNING)=" + year + " and t2.ISFORIEN='0' AND f.WORKFLOWSTATUS = 'COMPLETED' UNION ALL " +
                    "SELECT count(t.id) as count from  t_projectimplement t LEFT JOIN T_PROJECTRECORDS t2 on  " +
                    " t.PROJECTNUMBER = t2.id LEFT JOIN t_lmcworkflow f ON t.id = f.BIZID WHERE  YEAR(t.REGISTERDATE)=" + year + " and t2.ISFORIEN='0' AND f.WORKFLOWSTATUS = 'COMPLETED' UNION ALL " +
                    "SELECT count(t.FDID) from T_CLIENTINFO t LEFT JOIN t_lmcworkflow f ON t.FDID = f.BIZID WHERE  YEAR(t.CREATED_TIME)=" + year + " AND f.WORKFLOWSTATUS = 'COMPLETED' AND " +
                    " t.GROUPFULLCODE != '012013/01201301/01201301-07/01201301-07000003'";

        } else {
            sql = "SELECT count(t.id) as count from  T_PROJECTTRACKING t LEFT JOIN T_PROJECTRECORDS t2 on  " +
                    " t.PROJECTNUMBER = t2.id LEFT JOIN t_lmcworkflow f ON t.id = f.BIZID WHERE t2.ISFORIEN='1' AND f.WORKFLOWSTATUS = 'COMPLETED' UNION ALL   " +
                    " SELECT count(t.id) as count from  T_PROJECTBIDDING t LEFT JOIN T_PROJECTRECORDS t2  ON  " +
                    " t.PROJECTNUMBER = t2.id LEFT JOIN t_lmcworkflow f ON t.id = f.BIZID WHERE  YEAR(t.CREATED_TIME)=" + year + " and t2.ISFORIEN='1' AND f.WORKFLOWSTATUS = 'COMPLETED' UNION ALL  " +
                    "SELECT count(t.id) as count from  T_WINTHEBID t  LEFT JOIN T_PROJECTRECORDS t2 on  " +
                    " t.ITEMNUMBER = t2.id LEFT JOIN t_lmcworkflow f ON t.id = f.BIZID WHERE YEAR(t.DATA)=" + year + " and t2.ISFORIEN='1' AND f.WORKFLOWSTATUS = 'COMPLETED' UNION ALL  " +
                    "SELECT count(t.id) as count from  T_PROJECTSIGNING t LEFT JOIN T_PROJECTRECORDS t2 on  " +
                    " t.PROJECTNUMBER = t2.id LEFT JOIN t_lmcworkflow f ON t.id = f.BIZID WHERE  YEAR(t.TIMEOFSIGNING)=" + year + " and t2.ISFORIEN='1' AND f.WORKFLOWSTATUS = 'COMPLETED' UNION ALL  " +
                    "SELECT count(t.id) as count from  t_projectimplement t LEFT JOIN T_PROJECTRECORDS t2 on  " +
                    " t.PROJECTNUMBER = t2.id LEFT JOIN t_lmcworkflow f ON t.id = f.BIZID WHERE  YEAR(t.REGISTERDATE)=" + year + " and t2.ISFORIEN='1' AND f.WORKFLOWSTATUS = 'COMPLETED' UNION ALL " +
                    "SELECT count(t.FDID) from T_CLIENTINFO t LEFT JOIN t_lmcworkflow f ON t.FDID = f.BIZID WHERE  YEAR(t.CREATED_TIME)=" + year + " AND f.WORKFLOWSTATUS = 'COMPLETED' and  " +
                    " t.GROUPFULLCODE = '012013/01201301/01201301-07/01201301-07000003'";
        }
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    @Override
    public IndicatorsDashboardVO internationalHandover(String queryStr, int type, Integer year) {
        if (StringUtils.isEmpty(queryStr)) {
            queryStr = "YEAR";
        }

        IndicatorsDashboardVO indicatorsDashboardVO = new IndicatorsDashboardVO();
        // 计算项目数量
        indicatorsDashboardVO.setCounts(getCounts(type, year));
        //统计每月指标完成进度，各省签约数
        statistics(indicatorsDashboardVO, type, year);
        //各领域统计
        indicatorsDashboardVO.setStatisticsIndustryMaps(statisticsIndustry(queryStr, type, year));

        return indicatorsDashboardVO;
    }

    /**
     * 计算年度指标
     */
    @Override
    public MarketAmtVO marketAndSign(IndicatorsDashboardVO indicatorsDashboardVO, Integer year) {
        // 年度指标金额
        String sql = "select IFNULL(sum(t.formAmtTotal),0) from t_market_dmp_tag t where YEAR(CREATED_TIME)=" + year;
        BigDecimal toAmt = jdbcTemplate.queryForObject(sql, BigDecimal.class).divide(tenThousand);

        // 年度签约金额
        String sql2 = "SELECT IFNULL(sum(t.CONTRACTAMOUNT),0) FROM T_PROJECTSIGNING T LEFT JOIN t_lmcworkflow L on T.ID =  L.BIZID  " +
                "WHERE L.WORKFLOWSTATUS='COMPLETED' and YEAR(L.UPDATED_TIME) = " + year;
        BigDecimal yearAmt = jdbcTemplate.queryForObject(sql2, BigDecimal.class).divide(tenThousand);

        // 国际国内年度签约金额
        sql2 = "SELECT IFNULL(sum(t.CONTRACTAMOUNT),0) amtY, ISFORIEN FROM T_PROJECTSIGNING T LEFT JOIN t_lmcworkflow L on T.ID =  L.BIZID  " +
                "LEFT JOIN T_PROJECTRECORDS recods ON t.PROJECTNUMBER = recods.id " +
                "WHERE L.WORKFLOWSTATUS='COMPLETED' and YEAR(L.UPDATED_TIME) = " + year + " GROUP BY ISFORIEN";
        List<Map<String, Object>> yearAmtList = jdbcTemplate.queryForList(sql2);
        // 国际年度签约金额
        BigDecimal yearAmtI = BigDecimal.ZERO;
        // 国内年度签约金额
        BigDecimal yearAmtC = BigDecimal.ZERO;

        for (Map<String, Object> map : yearAmtList) {
            String isforien = map.get("ISFORIEN") == null? "" : String.valueOf(map.get("ISFORIEN"));
            if ("1".equals(isforien)) {
                yearAmtI = ((BigDecimal) map.get("amtY")).divide(tenThousand);
            } else if("0".equals(isforien)){
                yearAmtC = ((BigDecimal) map.get("amtY")).divide(tenThousand);
            }
        }

        // 判断当前时间是上半年还是下半年
        boolean isUp = true;
        Integer month = DateUtil.month(new Date());
        Integer y = DateUtil.year(new Date());
        Integer nextYear = y + 1;
        if (month > 6) {
            isUp = false;
        }
        // 半年度签约金额
        sql2 = "SELECT IFNULL(sum(t.CONTRACTAMOUNT), 0) FROM T_PROJECTSIGNING T LEFT JOIN t_lmcworkflow L on T.ID =  L.BIZID  " +
                "LEFT JOIN T_PROJECTRECORDS recods ON t.PROJECTNUMBER = recods.id " +
                "WHERE L.WORKFLOWSTATUS='COMPLETED' ";
        if (isUp)
            sql2 += " and l.UPDATED_TIME BETWEEN '" + y +"-01-01 00:00:00' AND '" + y +"-07-01 00:00:00'";
        else
            sql2 += " and l.UPDATED_TIME BETWEEN '" + y +"-07-01 00:00:00' AND '" + nextYear +"-01-01 00:00:00'";
        BigDecimal haIfAmt = jdbcTemplate.queryForObject(sql2, BigDecimal.class).divide(tenThousand);

        // 国内半年度签约金额
        BigDecimal haIfAmtC = jdbcTemplate.queryForObject(sql2 + " AND ISFORIEN = 0", BigDecimal.class).divide(tenThousand);
        // 国际半年度签约金额
        BigDecimal haIfAmtI = jdbcTemplate.queryForObject(sql2 + " AND ISFORIEN = 1", BigDecimal.class).divide(tenThousand);

        // 季度签约金额
        sql2 = "SELECT IFNULL(sum(t.CONTRACTAMOUNT),0) FROM T_PROJECTSIGNING T LEFT JOIN t_lmcworkflow L on T.ID =  L.BIZID  " +
                "WHERE L.WORKFLOWSTATUS='COMPLETED' and YEAR(L.UPDATED_TIME) = " + year + " AND QUARTER(L.UPDATED_TIME)=QUARTER(now())";
        BigDecimal quarterAmt = jdbcTemplate.queryForObject(sql2, BigDecimal.class).divide(tenThousand);

        // 国际国内季度签约金额
        sql2 = "SELECT IFNULL(sum(t.CONTRACTAMOUNT),0) amtY, ISFORIEN FROM T_PROJECTSIGNING T LEFT JOIN t_lmcworkflow L on T.ID =  L.BIZID  " +
                "LEFT JOIN T_PROJECTRECORDS recods ON t.PROJECTNUMBER = recods.id " +
                "WHERE L.WORKFLOWSTATUS='COMPLETED' and YEAR(L.UPDATED_TIME) = " + year + " AND QUARTER(L.UPDATED_TIME)=QUARTER(now()) GROUP BY ISFORIEN";
        List<Map<String, Object>> quarterAmtList = jdbcTemplate.queryForList(sql2);

        // 国际季度签约金额
        BigDecimal quarterAmtI = BigDecimal.ZERO;
        // 国内季度签约金额
        BigDecimal quarterAmtC = BigDecimal.ZERO;

        for (Map<String, Object> map : quarterAmtList) {
            String isforien = map.get("ISFORIEN") == null? "" : String.valueOf(map.get("ISFORIEN"));
            if ("1".equals(isforien)) {
                quarterAmtI = ((BigDecimal) map.get("amtY")).divide(tenThousand);
            } else if("0".equals(isforien)) {
                quarterAmtC = ((BigDecimal) map.get("amtY")).divide(tenThousand);
            }
        }

        // 月度签约金额
        sql2 = "SELECT IFNULL(sum(t.CONTRACTAMOUNT), 0) FROM T_PROJECTSIGNING T LEFT JOIN t_lmcworkflow L on T.ID =  L.BIZID  " +
                "WHERE L.WORKFLOWSTATUS='COMPLETED' AND DATE_FORMAT(L.UPDATED_TIME,'%Y-%m') = DATE_FORMAT(NOW(),'%Y-%m')";
        BigDecimal monthAmt = jdbcTemplate.queryForObject(sql2, BigDecimal.class).divide(tenThousand);

        // 国际国内月度签约金额
        sql2 = "SELECT IFNULL(sum(t.CONTRACTAMOUNT), 0) amtY, ISFORIEN FROM T_PROJECTSIGNING T LEFT JOIN t_lmcworkflow L on T.ID =  L.BIZID  " +
                "LEFT JOIN T_PROJECTRECORDS recods ON t.PROJECTNUMBER = recods.id " +
                "WHERE L.WORKFLOWSTATUS='COMPLETED' AND DATE_FORMAT(L.UPDATED_TIME,'%Y-%m') = DATE_FORMAT(NOW(),'%Y-%m') GROUP BY ISFORIEN";
        List<Map<String, Object>> monthAmtList = jdbcTemplate.queryForList(sql2);

        // 国际月度签约金额
        BigDecimal monthAmtI = BigDecimal.ZERO;
        // 国内月度签约金额
        BigDecimal monthAmtC = BigDecimal.ZERO;

        for (Map<String, Object> map : monthAmtList) {
            String isforien = map.get("ISFORIEN") == null? "" : String.valueOf(map.get("ISFORIEN"));
            if ("1".equals(isforien)) {
                monthAmtI = ((BigDecimal) map.get("amtY")).divide(tenThousand);
            } else if("0".equals(isforien)){
                monthAmtC = ((BigDecimal) map.get("amtY")).divide(tenThousand);
            }
        }

        indicatorsDashboardVO.getMarketAmtVO().setToAmt(toAmt);
        indicatorsDashboardVO.getMarketAmtVO().setYearAmt(yearAmt);
        indicatorsDashboardVO.getMarketAmtVO().setHaIfAmt(toAmt.divide(new BigDecimal(2), BigDecimal.ROUND_CEILING));
        indicatorsDashboardVO.getMarketAmtVO().setQuarterAmt(toAmt.divide(new BigDecimal(4), BigDecimal.ROUND_CEILING));
        indicatorsDashboardVO.getMarketAmtVO().setMonthAmt(toAmt.divide(new BigDecimal(12), BigDecimal.ROUND_CEILING));

        indicatorsDashboardVO.getMarketAmtVO().setYearSignAmtC(yearAmtC.setScale(4, BigDecimal.ROUND_HALF_DOWN));
        indicatorsDashboardVO.getMarketAmtVO().setYearSignAmtI(yearAmtI.setScale(4, BigDecimal.ROUND_HALF_DOWN));
        indicatorsDashboardVO.getMarketAmtVO().setHaIfSignAmtC(haIfAmtC.setScale(4, BigDecimal.ROUND_HALF_DOWN));
        indicatorsDashboardVO.getMarketAmtVO().setHaIfSignAmtI(haIfAmtI.setScale(4, BigDecimal.ROUND_HALF_DOWN));
        indicatorsDashboardVO.getMarketAmtVO().setQuarterSignAmtC(quarterAmtC.setScale(4, BigDecimal.ROUND_HALF_DOWN));
        indicatorsDashboardVO.getMarketAmtVO().setQuarterSignAmtI(quarterAmtI.setScale(4, BigDecimal.ROUND_HALF_DOWN));
        indicatorsDashboardVO.getMarketAmtVO().setMonthSignAmtC(monthAmtC.setScale(4, BigDecimal.ROUND_HALF_DOWN));
        indicatorsDashboardVO.getMarketAmtVO().setMonthSignAmtI(monthAmtI.setScale(4, BigDecimal.ROUND_HALF_DOWN));

        if(toAmt.compareTo(BigDecimal.ZERO) > 0) {
            indicatorsDashboardVO.getMarketAmtVO().setYearRatio(yearAmt.divide(toAmt, 4, BigDecimal.ROUND_CEILING).multiply(ratio));
            indicatorsDashboardVO.getMarketAmtVO().setHaIfRatio(haIfAmt.divide(toAmt.divide(new BigDecimal(2)), 4, BigDecimal.ROUND_CEILING).multiply(ratio));
            indicatorsDashboardVO.getMarketAmtVO().setQuarterRatio(quarterAmt.divide(toAmt.divide(new BigDecimal(4)), 4, BigDecimal.ROUND_CEILING).multiply(ratio));
            indicatorsDashboardVO.getMarketAmtVO().setMonthRatio(monthAmt.divide(toAmt.divide(new BigDecimal(12), BigDecimal.ROUND_CEILING), 4, BigDecimal.ROUND_CEILING).multiply(ratio));

            indicatorsDashboardVO.getMarketAmtVO().setYearRatioI(yearAmtI.divide(toAmt, 4, BigDecimal.ROUND_CEILING).multiply(ratio));
            indicatorsDashboardVO.getMarketAmtVO().setYearRatioC(yearAmtC.divide(toAmt, 4, BigDecimal.ROUND_CEILING).multiply(ratio));

            indicatorsDashboardVO.getMarketAmtVO().setHaIfRatioI(haIfAmtI.divide(toAmt.divide(new BigDecimal(2)), 4, BigDecimal.ROUND_CEILING).multiply(ratio));
            indicatorsDashboardVO.getMarketAmtVO().setHaIfRatioC(haIfAmtC.divide(toAmt.divide(new BigDecimal(2)), 4, BigDecimal.ROUND_CEILING).multiply(ratio));

            indicatorsDashboardVO.getMarketAmtVO().setQuarterRatioI(quarterAmtI.divide(toAmt.divide(new BigDecimal(4)), 4, BigDecimal.ROUND_CEILING).multiply(ratio));
            indicatorsDashboardVO.getMarketAmtVO().setQuarterRatioC(quarterAmtC.divide(toAmt.divide(new BigDecimal(4)), 4, BigDecimal.ROUND_CEILING).multiply(ratio));

            indicatorsDashboardVO.getMarketAmtVO().setMonthRatioI(monthAmtI.divide(toAmt.divide(new BigDecimal(12), BigDecimal.ROUND_CEILING), 4, BigDecimal.ROUND_CEILING).multiply(ratio));
            indicatorsDashboardVO.getMarketAmtVO().setMonthRatioC(monthAmtC.divide(toAmt.divide(new BigDecimal(12), BigDecimal.ROUND_CEILING), 4, BigDecimal.ROUND_CEILING).multiply(ratio));
        }
        return indicatorsDashboardVO.getMarketAmtVO();
    }

    private void statistics(IndicatorsDashboardVO indicatorsDashboardVO, int type, Integer year) {
        //每月业绩完成情况List
        List<Map<String, Object>> monthList = new ArrayList<>();
        // 年度指标
        List<Map<String, Object>> markerMaps = new ArrayList<>();
        Map<Integer, List<Map<String, Object>>> markerMap;
        String sql = "select IFNULL(sum(t.formAmtTotal),0) from t_market_dmp_tag t where YEAR(CREATED_TIME)=" + year + "";
        BigDecimal toAmt = jdbcTemplate.queryForObject(sql, BigDecimal.class);
        BigDecimal monthAmt = toAmt.divide(new BigDecimal(12), BigDecimal.ROUND_CEILING);

        //每月完成
        List<Map<String, Object>> signMaps = new ArrayList<>();
        Map<Integer, List<Map<String, Object>>> signMap;
        String sql2 = "SELECT IFNULL(sum(t.CONTRACTAMOUNT), 0) amt,MONTH(L.UPDATED_TIME) souDate FROM T_PROJECTSIGNING T LEFT JOIN t_lmcworkflow L on T.ID =  L.BIZID  " +
                "WHERE  L.WORKFLOWSTATUS='COMPLETED' and YEAR(T.TIMEOFSIGNING)=" + year + " GROUP BY MONTH(UPDATED_TIME)";
        signMaps = jdbcTemplate.queryForList(sql2);
        signMap = signMaps.stream().collect(Collectors.groupingBy(e -> Integer.parseInt(e.get("souDate").toString())));

        // 组装 每月业绩完成情况List
        Map<String, Object> monthMap;
        for (int i = 1; i < 13; i++) {
            monthMap = new HashMap<>();
            monthMap.put("month", i);
            monthMap.put("tagAmt", monthAmt);
            monthMap.put("amt", signMap.get(i) == null ? 0 : signMap.get(i).get(0).get("amt"));
            monthList.add(monthMap);
        }
        indicatorsDashboardVO.setMonthList(monthList);

        if (type == 0) {
            //根据各省分组统计
            String sql3 = " SELECT  left(t.location,2) as area,count(t.id) as sum FROM T_PROJECTSIGNING t LEFT JOIN t_lmcworkflow L on t.ID =  L.BIZID  LEFT JOIN T_PROJECTRECORDS t2 on t.PROJECTNUMBER = t2.ID " +
                    "WHERE   L.WORKFLOWSTATUS='COMPLETED' and YEAR(T.TIMEOFSIGNING)=" + year + " and t2.ISFORIEN='0'  GROUP BY left(t.location,2) ";
            indicatorsDashboardVO.setStatisticsSignMaps(jdbcTemplate.queryForList(sql3));
        } else {
            //国际签约数量
            String sql4 = " SELECT  t.location as area,count(t.id) as sum FROM T_PROJECTSIGNING t LEFT JOIN t_lmcworkflow L on t.ID =  L.BIZID  LEFT JOIN T_PROJECTRECORDS t2 on t.PROJECTNUMBER = t2.ID " +
                    "WHERE   L.WORKFLOWSTATUS='COMPLETED' and YEAR(T.TIMEOFSIGNING)=" + year + " and t2.ISFORIEN='1'  GROUP BY t.location";
            indicatorsDashboardVO.setStatisticsSignMaps(jdbcTemplate.queryForList(sql4));

            String sql3 = " SELECT count(t.id) as sum FROM T_PROJECTSIGNING t LEFT JOIN t_lmcworkflow L on t.ID =  L.BIZID  LEFT JOIN T_PROJECTRECORDS t2 on t.PROJECTNUMBER = t2.ID " +
                    "WHERE   L.WORKFLOWSTATUS='COMPLETED' and YEAR(T.TIMEOFSIGNING)=" + year + " and t2.ISFORIEN='0' ";
            Map<String, Object> map = new HashMap<>();
            map.put("area", "China");
            map.put("sum", jdbcTemplate.queryForObject(sql3, Integer.class));
            indicatorsDashboardVO.getStatisticsSignMaps().add(map);
        }

    }

    /**
     * 根据各领域统计
     *
     * @param queryStr
     * @return
     */
    public List<Map<String, Object>> statisticsIndustry(String queryStr, int type, Integer year) {
        String sql = "SELECT t.INDUSTRY industry,IFNULL(sum(t.CONTRACTAMOUNT), 0) sum,COUNT(t.ID) count from  T_PROJECTSIGNING t " +
                " LEFT JOIN t_lmcworkflow L on t.ID =  L.BIZID  LEFT JOIN T_PROJECTRECORDS t2 on " +
                " t.PROJECTNUMBER = t2.ID  " +
                " WHERE   L.WORKFLOWSTATUS='COMPLETED' and YEAR(T.TIMEOFSIGNING)=" + year + " ";

        if (StringUtils.equals(queryStr, "MONTH")) {
            sql += " and MONTH(T.TIMEOFSIGNING) = MONTH(CURDATE())";

        } else if (StringUtils.equals(queryStr, "QUARTER")) {
            sql += " and QUARTER(T.TIMEOFSIGNING) = QUARTER(CURDATE()) ";

        } else if (StringUtils.equals(queryStr, "HAIF")) {
            // 判断当前时间是上半年还是下半年
            boolean isUp = true;
            Integer month = DateUtil.month(new Date());
            if (month > 6) {
                isUp = false;
            }
            if(isUp) {
                sql += " and MONTH(T.TIMEOFSIGNING) <= 6" ;
            }else{
                sql += " and MONTH(T.TIMEOFSIGNING) > 6" ;
            }
        }
        if (type == 0) {
            sql += " and t2.ISFORIEN='0'";
        } else {
            sql += " and t2.ISFORIEN='1'";
        }
        sql += " GROUP BY t.INDUSTRY";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        mapList.stream().flatMapToInt(stringObjectMap -> IntStream.of(Integer.parseInt(stringObjectMap.get("count").toString()))).sum();
        double totalSum = mapList.stream().mapToDouble(stringObjectMap -> Double.parseDouble(stringObjectMap.get("sum").toString())).sum();
        // 最后总数
        BigDecimal sumRatio  = new BigDecimal(0);
        // 总条数
        Integer sumCount = 0;
        for(Map<String, Object> stringObjectMap : mapList){
            BigDecimal industryRatio = new BigDecimal(stringObjectMap.get("sum").toString()).divide(BigDecimal.valueOf(totalSum), 4, BigDecimal.ROUND_DOWN).multiply(ratio);
            stringObjectMap.put("ratio", industryRatio);
            sumCount += Integer.parseInt(stringObjectMap.get("count").toString());
            sumRatio = sumRatio.add(industryRatio);
        }

        if(!ObjectUtils.isEmpty(mapList)) {
            // 距离100的差值
            BigDecimal diffDecimal = BigDecimal.ZERO;
            // 返回的最后一条数据（补差）
            Map<String, Object> lastMap = mapList.get(mapList.size() - 1);
            // 总数<100
            if (sumRatio.compareTo(ratio) < 0) {
                diffDecimal = ratio.subtract(sumRatio);
                BigDecimal lastRatio = (BigDecimal) lastMap.get("ratio");
                lastMap.put("ratio", lastRatio.add(diffDecimal));
            } else if (sumRatio.compareTo(ratio) > 0) {
                diffDecimal = sumRatio.subtract(ratio);
                BigDecimal lastRatio = (BigDecimal) lastMap.get("ratio");
                lastMap.put("ratio", lastRatio.subtract(diffDecimal));
            }
        }

        if(!mapList.isEmpty()) {
            Map<String, Object> countMap = new HashMap<>();
            countMap.put("industry", "00000000");
            countMap.put("sum", totalSum);
            countMap.put("count", sumCount);
            countMap.put("ratio", 100);
            mapList.add(countMap);
        }

        return mapList;
    }


    /**
     * 获取各公司签约情况
     *
     * @param queryStr
     */
    public List<SignCondition> signCondition(String queryStr, Integer year) {
        String sql = "SELECT COMPANY_ID groupFullCode,org.GROUPBELONGUNITName groupName,IFNULL(dt.FORM_AMT, 0) amt  from t_market_dmp_tag_detail dt " +
                "LEFT JOIN t_market_dmp_tag t on dt.PARENT_ID=t.ID " +
                "LEFT JOIN t_mdmorganization org ON dt.COMPANY_ID = org.GROUPBELONGUNITCODE " +
                "WHERE YEAR(dt.UPDATED_TIME)=" + year + " ";
        sql += " GROUP BY dt.COMPANY_ID";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        Map<String, BigDecimal> queryMap = MapUtil.newHashMap();

        String sql2 = "SELECT org.GROUPBELONGUNITCODE groupFullCode, org.GROUPBELONGUNITName groupName,IFNULL(sum(t.CONTRACTAMOUNT),0) contractAmount FROM T_PROJECTSIGNING T LEFT JOIN t_lmcworkflow L " +
                " on T.ID =  L.BIZID LEFT JOIN t_mdmorganization org on t.GROUPFULLCODE = org.GROUPFULLCODE " +
                "WHERE   L.WORKFLOWSTATUS='COMPLETED' and YEAR(T.TIMEOFSIGNING)=" + year + " ";
        if (StringUtils.equals(queryStr, "MONTH")) {
            sql2 += " and MONTH(T.TIMEOFSIGNING) = MONTH(CURDATE())";
        } else if (StringUtils.equals(queryStr, "QUARTER")) {
            sql2 += " and QUARTER(T.TIMEOFSIGNING) = QUARTER(CURDATE()) ";
        } else if (StringUtils.equals(queryStr, "HAIF")) {
            // 判断当前时间是上半年还是下半年
            boolean isUp = true;
            Integer month = DateUtil.month(new Date());
            if (month > 6) {
                isUp = false;
            }
            if(isUp) {
                sql2 += " and MONTH(T.TIMEOFSIGNING) <= 6" ;
            }else{
                sql2 += " and MONTH(T.TIMEOFSIGNING) > 6" ;
            }
        }
        sql2 += " GROUP BY org.GROUPBELONGUNITCODE";

        List<Map<String, Object>> mapList2 = jdbcTemplate.queryForList(sql2);
        List<SignCondition> list = new ArrayList<>();
        for (Map<String, Object> map : mapList2) {
            queryMap.put(map.get("groupFullCode").toString(), (BigDecimal) map.get("contractAmount"));
        }

        for (Map<String, Object> map : mapList) {
            SignCondition signCondition = new SignCondition();
            signCondition.setCode(map.get("groupFullCode").toString());
            signCondition.setName(map.get("groupName").toString());
            signCondition.setSignAmt(queryMap.get(map.get("groupFullCode").toString()) == null ? BigDecimal.ZERO : queryMap.get(map.get("groupFullCode").toString()));

            if (StringUtils.equals(queryStr, "MONTH")) {
                signCondition.setMarketAmt(BigDecimal.valueOf((Double) map.get("amt")).divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
            } else if (StringUtils.equals(queryStr, "QUARTER")) {
                signCondition.setMarketAmt(BigDecimal.valueOf((Double) map.get("amt")).divide(new BigDecimal("4"), 2, RoundingMode.HALF_UP));
            } else if (StringUtils.equals(queryStr, "HAIF")) {
                signCondition.setMarketAmt(BigDecimal.valueOf((Double) map.get("amt")).divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP));
            }else{
                signCondition.setMarketAmt(BigDecimal.valueOf((Double) map.get("amt")));
            }
            if(signCondition.getSignAmt().compareTo(BigDecimal.ZERO) == 0){
                signCondition.setRatio(BigDecimal.ZERO);
            }else if (signCondition.getMarketAmt().compareTo(BigDecimal.ZERO) == 0 && signCondition.getSignAmt().compareTo(signCondition.getMarketAmt()) > 0) {
                signCondition.setRatio(new BigDecimal(1));
            }else if(signCondition.getSignAmt().compareTo(BigDecimal.ZERO) > 0 && signCondition.getMarketAmt().compareTo(BigDecimal.ZERO) > 0) {
                signCondition.setRatio(signCondition.getSignAmt().divide(signCondition.getMarketAmt(), 4, BigDecimal.ROUND_CEILING));
            }else{
                signCondition.setRatio(BigDecimal.ZERO);
            }
            list.add(signCondition);
        }
        return list;

    }

    /**
     * 查询各公司中标情况
     *
     * @param queryStr
     */
    public List<WinThebidConditionVO> winThebidCondition(String queryStr, Integer year) {

        String sql = "SELECT org.GROUPBELONGUNITCODE groupFullCode, org.GROUPBELONGUNITNAME groupName, count(t.ID) count, SUM(MONEY) sumMoney from t_winthebid t  LEFT JOIN t_lmcworkflow L " +
                " on T.ID =  L.BIZID LEFT JOIN t_mdmorganization org on t.GROUPFULLCODE = org.GROUPFULLCODE " +
                "WHERE  L.WORKFLOWSTATUS='COMPLETED'  and  YEAR(L.UPDATED_TIME)=" + year + " " +
                "AND org.GROUPBELONGUNITCODE in ('01201301-01','01201301-02','01201301-03','01201301-04','01201301-05','01201301-06','01201301-07','01201302-01','01201302-02')";

        String sql2 = "SELECT count(t.ID) count, org.GROUPBELONGUNITCODE groupFullCode from T_PROJECTBIDDING t LEFT JOIN t_lmcworkflow L " +
                " on T.ID =  L.BIZID LEFT JOIN t_mdmorganization org ON t.GROUPFULLCODE = org.GROUPFULLCODE WHERE  L.WORKFLOWSTATUS='COMPLETED'  and  YEAR(L.UPDATED_TIME)=" + year + " " +
                "AND org.GROUPBELONGUNITCODE in ('01201301-01','01201301-02','01201301-03','01201301-04','01201301-05','01201301-06','01201301-07','01201302-01','01201302-02')";
        if (StringUtils.equals(queryStr, "MONTH")) {
            sql += " and MONTH(L.UPDATED_TIME) = MONTH(CURDATE())";
            sql2 += " and MONTH(L.UPDATED_TIME) = MONTH(CURDATE())";
        } else if (StringUtils.equals(queryStr, "QUARTER")) {
            sql += " and QUARTER(L.UPDATED_TIME) = QUARTER(CURDATE()) ";
            sql2 += " and QUARTER(L.UPDATED_TIME) = QUARTER(CURDATE()) ";
        } else if (StringUtils.equals(queryStr, "HAIF")) {
            // 判断当前时间是上半年还是下半年
            boolean isUp = true;
            Integer month = DateUtil.month(new Date());
            if (month > 6) {
                isUp = false;
            }
            if(isUp) {
                sql += " and MONTH(L.UPDATED_TIME) <= 6" ;
                sql2 += " and MONTH(L.UPDATED_TIME) <= 6" ;
            }else{
                sql += " and MONTH(L.UPDATED_TIME) > 6" ;
                sql2  += " and MONTH(L.UPDATED_TIME) > 6" ;
            }
        }
        sql += " GROUP BY org.GROUPBELONGUNITCODE";
        sql2 += " GROUP BY org.GROUPBELONGUNITCODE";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql2);
        Map<String, Integer> queryMap = MapUtil.newHashMap();
        for (Map<String, Object> map : mapList) {
            queryMap.put(map.get("groupFullCode").toString(), Integer.parseInt(map.get("count").toString()));
        }

        List<Map<String, Object>> mapList2 = jdbcTemplate.queryForList(sql);

        List<WinThebidConditionVO> list = new ArrayList<>();
        Integer sumWinCount = 0;
        Integer sumBidCount = 0;
        BigDecimal sumWinAmount = BigDecimal.ZERO;
        for (Map<String, Object> map : mapList2) {
            WinThebidConditionVO winThebidCondition = new WinThebidConditionVO();
            winThebidCondition.setCode(String.valueOf(map.get("groupFullCode")));
            winThebidCondition.setName(String.valueOf(map.get("groupName")));
            winThebidCondition.setWinCount(Integer.parseInt(String.valueOf(map.get("count"))));
            Integer bidCount = queryMap.get(map.get("groupFullCode"));
            winThebidCondition.setWinAmount(new BigDecimal(String.valueOf(map.get("sumMoney"))));

            if (bidCount != null && bidCount > 0) {
                winThebidCondition.setRatio(BigDecimal.valueOf(winThebidCondition.getWinCount()).divide(BigDecimal.valueOf(bidCount), 2, BigDecimal.ROUND_CEILING));
                winThebidCondition.setBidCount(bidCount);
                sumBidCount += winThebidCondition.getBidCount();
            } else {
                winThebidCondition.setRatio(BigDecimal.ONE);
            }
            list.add(winThebidCondition);

            sumWinCount += winThebidCondition.getWinCount();
            sumWinAmount = sumWinAmount.add(new BigDecimal(String.valueOf(map.get("sumMoney"))));
        }
        if(sumBidCount != 0 || sumWinCount != 0) {
            WinThebidConditionVO winThebidCondition = new WinThebidConditionVO();
            winThebidCondition.setCode("00000000");
            winThebidCondition.setName("汇总");
            winThebidCondition.setWinCount(sumWinCount);
            winThebidCondition.setBidCount(sumBidCount);
            winThebidCondition.setWinAmount(sumWinAmount);
            if(sumBidCount != 0)
                winThebidCondition.setRatio(BigDecimal.valueOf(sumWinCount).divide(BigDecimal.valueOf(sumBidCount), 2, BigDecimal.ROUND_CEILING));
            else
                winThebidCondition.setRatio(new BigDecimal(1));
            list.add(winThebidCondition);
        }
        return list;
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("860247");
        BigDecimal b = new BigDecimal("209591");
//        System.out.println(b.divide(a));
        System.out.println(b.divide(a, 2, RoundingMode.HALF_UP));
    }

}
