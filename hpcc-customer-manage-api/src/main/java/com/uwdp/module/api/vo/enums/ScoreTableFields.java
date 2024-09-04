package com.uwdp.module.api.vo.enums;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

public enum ScoreTableFields {

    SCORE_LEADER("scoreLeader","评分领导"),
    SCORE("score","评分"),
    SUGGEST("suggest","建议"),
    ;


    private String field;
    private String fieldDescribe;

    public static ScoreTableFields get(String field) {
        ScoreTableFields value = (ScoreTableFields) CollUtil.newArrayList(values()).stream().filter((a) -> {
            return StrUtil.equals(a.getFieldDescribe(), field);
        }).findFirst().orElse(null);
        return value;
    }

    public String getField() {
        return field;
    }

    public String getFieldDescribe() {
        return fieldDescribe;
    }


    private ScoreTableFields(String field, String fieldDescribe) {
        this.field = field;
        this.fieldDescribe = fieldDescribe;
    }
}
