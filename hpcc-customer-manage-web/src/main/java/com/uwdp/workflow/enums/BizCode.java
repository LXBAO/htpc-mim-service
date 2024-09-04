package com.uwdp.workflow.enums;

public enum BizCode {

    // 新增客户
    ADD_CLIENT("addClient"),
    // 公关反馈
    ADD_FEEDBACK("feedback"),
    // 公关计划
    ADD_VISIT_PLAN("ggjhap"),
    // 项目登记
    ADD_PROJECT_RECORDS("xmdj"),
    // 项目投标
    ADD_PROJECT_BIDDING("projectBidding"),
    // 项目跟踪
    ADD_PROJECT_TRACKING("projectTracking"),
    // 项目签约
    ADD_PROJECT_SIGNING("projectSigning"),
    // 项目中标
    ADD_WIN_THE_BID("winTheBid"),
    // 项目实施
    ADD_PROJECT_IMPLEMENT("projectImplement"),
    // 年度指标
    ADD_MARKET_DMP_TAG("marketDmpTag"),
    // 项目终止
    ADD_PROJECT_SUSPENSION("projectSuspension"),
    // 项目赋能
    ADD_PROJECT_ENABLE("projectEnable"),
    ;

    private final String value;

    BizCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
