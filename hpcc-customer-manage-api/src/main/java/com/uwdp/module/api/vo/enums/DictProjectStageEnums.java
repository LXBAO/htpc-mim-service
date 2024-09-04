package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 项目阶段
 *
 */
@Getter
@AllArgsConstructor
public enum DictProjectStageEnums {

    DICT_PROJECT_STAGE_ENUMS_0("1", "仅登记"),
    DICT_PROJECT_STAGE_ENUMS_1("2", "已跟踪"),
    DICT_PROJECT_STAGE_ENUMS_2("3", "已投标"),
    DICT_PROJECT_STAGE_ENUMS_3("4", "已中标"),
    DICT_PROJECT_STAGE_ENUMS_4("5", "已签约"),
    DICT_PROJECT_STAGE_ENUMS_5("6", "已实施"),
    DICT_PROJECT_STAGE_ENUMS_6("7", "已中止"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, DictProjectStageEnums> DICT_PROJECT_STAGE_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(DictProjectStageEnums.values()).collect(Collectors.toMap(DictProjectStageEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            DictProjectStageEnums enums = DICT_PROJECT_STAGE_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}