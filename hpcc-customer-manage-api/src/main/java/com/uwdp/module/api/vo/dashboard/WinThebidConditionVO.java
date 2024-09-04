package com.uwdp.module.api.vo.dashboard;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lx
 * @data 2023/8/4 16:12
 */
@Data
public class WinThebidConditionVO {
    private String code;
    private String name;
    //中标数
    private Integer winCount;
    //投标数
    private Integer bidCount;
    //中标金额
    private BigDecimal winAmount;
    private BigDecimal ratio;
}
