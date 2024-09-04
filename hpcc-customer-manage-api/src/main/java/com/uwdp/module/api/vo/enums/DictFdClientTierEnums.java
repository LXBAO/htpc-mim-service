package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 客户层级
 *
 */
@Getter
@AllArgsConstructor
public enum DictFdClientTierEnums {

    DICT_FD_CLIENT_TIER_ENUMS_0("0", "重要"),
    DICT_FD_CLIENT_TIER_ENUMS_1("1", "主要"),
    DICT_FD_CLIENT_TIER_ENUMS_2("2", "普通"),
    DICT_FD_CLIENT_TIER_ENUMS_3("3", "潜在"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, DictFdClientTierEnums> DICT_FD_CLIENT_TIER_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(DictFdClientTierEnums.values()).collect(Collectors.toMap(DictFdClientTierEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            DictFdClientTierEnums enums = DICT_FD_CLIENT_TIER_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}