package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lx
 * @data 2023/7/14 15:20
 * 政治面貌
 */
@Getter
@AllArgsConstructor
public enum ProjectModeEnums {
    PROJECT_MODE_ENUMS_1("1", "传统类项目"),
    PROJECT_MODE_ENUMS_2("2", "融资类项目"),
    PROJECT_MODE_ENUMS_3("301", "投资类项目/BOOT"),
    PROJECT_MODE_ENUMS_4("302", "投资类项目/PPP"),
    PROJECT_MODE_ENUMS_5("303", "投资类项目/其他"),
    PROJECT_MODE_ENUMS_6("304", "投资类项目/直接投资类"),
    PROJECT_MODE_ENUMS_7("305", "投资类项目/BOT"),
    PROJECT_MODE_ENUMS_8("4", "待定"),

    ;

    private final String value;
    private final String name;

    private static final Map<String, ProjectModeEnums> PROJECT_MODE_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(ProjectModeEnums.values()).collect(Collectors.toMap(ProjectModeEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            ProjectModeEnums enums = PROJECT_MODE_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}
