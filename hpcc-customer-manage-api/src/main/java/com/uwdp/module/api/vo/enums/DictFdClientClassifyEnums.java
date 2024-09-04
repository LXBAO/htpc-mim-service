package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 客户分类
 *
 */
@Getter
@AllArgsConstructor
public enum DictFdClientClassifyEnums {

    DICT_FD_CLIENT_CLASSIFY_ENUMS_0("1", "A类客户"),
    DICT_FD_CLIENT_CLASSIFY_ENUMS_1("2", "B类客户"),
    DICT_FD_CLIENT_CLASSIFY_ENUMS_2("3", "C类客户"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, DictFdClientClassifyEnums> DICT_FD_CLIENT_CLASSIFY_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(DictFdClientClassifyEnums.values()).collect(Collectors.toMap(DictFdClientClassifyEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            DictFdClientClassifyEnums enums = DICT_FD_CLIENT_CLASSIFY_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}