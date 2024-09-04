package com.uwdp.module.api.vo.dashboard;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lx
 * @data 2023/8/4 11:29
 */
@Data
public class InterMarketAmtVO {

    private BigDecimal yearAmt;

    private BigDecimal HaIfAmt;

    private BigDecimal quarterAmt;

    private BigDecimal monthAmt;
}
