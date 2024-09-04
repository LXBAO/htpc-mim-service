package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 公关计划-活动方式
 *
 */
@Getter
@AllArgsConstructor
public enum DictVisitPlanActivityWayEnums {

    DICT_VISIT_PLAN_ACTIVITY_WAY_ENUMS_0("1", "双方会晤"),
    DICT_VISIT_PLAN_ACTIVITY_WAY_ENUMS_1("2", "出席会议"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, DictVisitPlanActivityWayEnums> DICT_VISIT_PLAN_ACTIVITY_WAY_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(DictVisitPlanActivityWayEnums.values()).collect(Collectors.toMap(DictVisitPlanActivityWayEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            DictVisitPlanActivityWayEnums enums = DICT_VISIT_PLAN_ACTIVITY_WAY_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}