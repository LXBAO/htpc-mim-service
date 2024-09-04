package com.uwdp.module.api.vo.dashboard;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lx
 * @data 2023/8/4 15:13
 */
@Data
public class SignCondition {
    private String code;
    private String name;
    private BigDecimal marketAmt;//指标金额
    private BigDecimal signAmt;//签约金额
    private BigDecimal ratio;
}
