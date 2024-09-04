package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 投标单位
 *
 */
@Getter
@AllArgsConstructor
public enum DictTendererEnums {

    DICT_TENDERER_ENUMS_0("2", "中国能建成员企业"),
    DICT_TENDERER_ENUMS_1("1", "中国电建成员企业"),
    DICT_TENDERER_ENUMS_2("0", "非两大集团成员企业"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, DictTendererEnums> DICT_TENDERER_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(DictTendererEnums.values()).collect(Collectors.toMap(DictTendererEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            DictTendererEnums enums = DICT_TENDERER_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}