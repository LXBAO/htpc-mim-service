package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 重要程度
 *
 */
@Getter
@AllArgsConstructor
public enum DictImportanceDegreeEnums {

    DICT_IMPORTANCE_DEGREE_ENUMS_0("1", "终止"),
    DICT_IMPORTANCE_DEGREE_ENUMS_1("3", "一般"),
    DICT_IMPORTANCE_DEGREE_ENUMS_2("2", "重点"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, DictImportanceDegreeEnums> DICT_IMPORTANCE_DEGREE_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(DictImportanceDegreeEnums.values()).collect(Collectors.toMap(DictImportanceDegreeEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            DictImportanceDegreeEnums enums = DICT_IMPORTANCE_DEGREE_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}