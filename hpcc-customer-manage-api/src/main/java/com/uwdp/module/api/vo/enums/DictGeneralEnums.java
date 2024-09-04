package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lx
 * @data 2023/7/19 15:08
 */
@Getter
@AllArgsConstructor
public enum DictGeneralEnums {
    DICT_GENERAL_ENUMS_0("1", "是"),
    DICT_GENERAL_ENUMS_1("0", "否"),
;

    private final String value;
    private final String name;

    private static final Map<String, DictGeneralEnums> DICT_GENERAL_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(DictGeneralEnums.values()).collect(Collectors.toMap(DictGeneralEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            DictGeneralEnums enums = DICT_GENERAL_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}
