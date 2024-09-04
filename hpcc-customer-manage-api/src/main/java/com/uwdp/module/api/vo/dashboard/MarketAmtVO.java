package com.uwdp.module.api.vo.dashboard;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lx
 * @data 2023/8/4 11:24
 */
@Data
public class MarketAmtVO {
    /**
     * 指标数
     */
    private BigDecimal toAmt;
    /**
     * 年度目标金额
     */
    private BigDecimal yearAmt;
    /**
     * 国内年度签约金额
     */
    private BigDecimal yearSignAmtC;
    /**
     * 国际年度签约金额
     */
    private BigDecimal yearSignAmtI;
    /**
     * 年度完成度
     */
    private BigDecimal yearRatio;
    /**
     * 国内年度完成度
     */
    private BigDecimal yearRatioC;
    /**
     * 国际年度完成度
     */
    private BigDecimal yearRatioI;

    /**
     * 半年度目标金额
     */
    private BigDecimal haIfAmt;
    /**
     * 国内半年度签约金额
     */
    private BigDecimal haIfSignAmtC;
    /**
     * 国际半年度签约金额
     */
    private BigDecimal haIfSignAmtI;
    /**
     * 半年度完成度
     */
    private BigDecimal haIfRatio;
    /**
     * 国内半年度完成度
     */
    private BigDecimal haIfRatioC;
    /**
     * 国际半年度完成度
     */
    private BigDecimal haIfRatioI;
    /**
     * 季度目标金额
     */
    private BigDecimal quarterAmt;
    /**
     * 国内季度签约金额
     */
    private BigDecimal quarterSignAmtC;
    /**
     * 国际季度签约金额
     */
    private BigDecimal quarterSignAmtI;
    /**
     * 季度完成度
     */
    private BigDecimal quarterRatio;
    /**
     * 国内季度完成度
     */
    private BigDecimal quarterRatioC;
    /**
     * 国际季度完成度
     */
    private BigDecimal quarterRatioI;
    /**
     * 月度目标金额
     */
    private BigDecimal monthAmt;
    /**
     * 国内月度签约金额
     */
    private BigDecimal monthSignAmtC;
    /**
     * 国际月度签约金额
     */
    private BigDecimal monthSignAmtI;
    /**
     * 月度完成度
     */
    private BigDecimal monthRatio;
    /**
     * 国内月度完成度
     */
    private BigDecimal monthRatioC;
    /**
     * 国际月度完成度
     */
    private BigDecimal monthRatioI;
}
