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
public enum PoliticsStatusEnums {
    POLITICS_STATUS_ENUMS_1("1", "中共党员"),
    POLITICS_STATUS_ENUMS_2("2", "预备党员"),
    POLITICS_STATUS_ENUMS_3("3", "共青团员"),
    POLITICS_STATUS_ENUMS_4("4", "群众"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, PoliticsStatusEnums> POLITICS_STATUS_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(PoliticsStatusEnums.values()).collect(Collectors.toMap(PoliticsStatusEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            PoliticsStatusEnums enums = POLITICS_STATUS_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}
