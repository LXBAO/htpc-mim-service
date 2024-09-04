package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 项目状态
 *
 */
@Getter
@AllArgsConstructor
public enum IndustryOptions {

    INDUSTRY_OPTIONS_ENUMS_1("201", "传统能源"),

    INDUSTRY_OPTIONS_ENUMS_2("202", "新能源及综合智慧能源"),

    INDUSTRY_OPTIONS_ENUMS_3("203", "水利（水务）"),

    INDUSTRY_OPTIONS_ENUMS_4("204", "生态环保"),

    INDUSTRY_OPTIONS_ENUMS_5("205", "生态环保"),

    INDUSTRY_OPTIONS_ENUMS_6("206", "市政"),

    INDUSTRY_OPTIONS_ENUMS_7("207", "房建"),

    INDUSTRY_OPTIONS_ENUMS_8("208", "其他工程"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, IndustryOptions> INDUSTRY_OPTIONS_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(IndustryOptions.values()).collect(Collectors.toMap(IndustryOptions::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            IndustryOptions enums = INDUSTRY_OPTIONS_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}