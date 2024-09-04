package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 所属区域总部
 *
 */
@Getter
@AllArgsConstructor
public enum DictRegionalHeadquarterEnums {

    DICT_REGIONAL_HEADQUARTER_ENUMS_0("0", "华北区域"),
    DICT_REGIONAL_HEADQUARTER_ENUMS_1("1", "华东区域"),
    DICT_REGIONAL_HEADQUARTER_ENUMS_2("2", "华南区域"),
    DICT_REGIONAL_HEADQUARTER_ENUMS_3("3", "西南区域"),
    DICT_REGIONAL_HEADQUARTER_ENUMS_4("4", "西北区域"),
    DICT_REGIONAL_HEADQUARTER_ENUMS_5("5", "海南区域"),
    DICT_REGIONAL_HEADQUARTER_ENUMS_6("6", "华中区域"),
    DICT_REGIONAL_HEADQUARTER_ENUMS_7("7", "国际区域"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, DictRegionalHeadquarterEnums> DICT_REGIONAL_HEADQUARTER_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(DictRegionalHeadquarterEnums.values()).collect(Collectors.toMap(DictRegionalHeadquarterEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            DictRegionalHeadquarterEnums enums = DICT_REGIONAL_HEADQUARTER_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}