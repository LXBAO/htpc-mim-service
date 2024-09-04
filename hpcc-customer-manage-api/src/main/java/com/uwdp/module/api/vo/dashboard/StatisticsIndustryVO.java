package com.uwdp.module.api.vo.dashboard;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lx
 * @data 2023/8/4 14:51
 */
@Data
public class StatisticsIndustryVO {
    private String industry;

    private BigDecimal totalAmt;


    private List<Double> sum;

    private List<Integer> count;

}
