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
public enum ProjectStateEnums {

    PROJECT_STATE_ENUMS_0("1", "已完工"),
    PROJECT_STATE_ENUMS_1("2", "未完工"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, ProjectStateEnums> PROJECT_STATE_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(ProjectStateEnums.values()).collect(Collectors.toMap(ProjectStateEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            ProjectStateEnums enums = PROJECT_STATE_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}