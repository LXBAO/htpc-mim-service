package com.uwdp.module.biz.utils;

public enum BizCodeUtil {

    ADD_CLIENT("addClient"),
    ADD_FEEDBACK("feedback"),
    ADD_VISIT_PLAN("ggjhap"),
    ADD_PROJECT_RECORDS("xmdj"),
    ADD_PROJECT_BIDDING("projectBidding"),
    ADD_PROJECT_TRACKING("projectTracking"),
    ADD_PROJECT_SIGNING("projectSigning"),
    ADD_WIN_THE_BID("winTheBid"),
    ADD_PROJECT_IMPLEMENT("projectImplement"),
    ADD_MARKET_DMP_TAG("marketDmpTag"),
    ADD_PROJECT_SUSPENSION("projectSuspension"),
    ADD_PROJECT_ENABLE("projectEnable"),
    ;

    private final String value;

    BizCodeUtil(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
