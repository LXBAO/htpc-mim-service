package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lx
 * @data 2023/7/14 15:15
 *
 * 学历
 */
@Getter
@AllArgsConstructor
public enum EducationEnums {

    EDUCATION_ENUMS_ENUMS_1("1", "初中"),
    EDUCATION_ENUMS_ENUMS_2("2", "高中"),
    EDUCATION_ENUMS_ENUMS_3("3", "大专"),
    EDUCATION_ENUMS_ENUMS_4("4", "大学"),
    EDUCATION_ENUMS_ENUMS_5("5", "研究生"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, EducationEnums> EDUCATION_ENUMS_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(EducationEnums.values()).collect(Collectors.toMap(EducationEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            EducationEnums enums = EDUCATION_ENUMS_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}
