package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lx
 * @data 2023/7/14 15:04
 * 客户属性
 */
@Getter
@AllArgsConstructor
public enum DictClientEnterEnums {

    DICT_FD_CLIENT_ENTER_ENUMS_0("1", "政府客户"),
    DICT_FD_CLIENT_ENTER_ENUMS_1("2", "咨询客户"),
    DICT_FD_CLIENT_ENTER_ENUMS_2("3", "企业客户"),
    DICT_FD_CLIENT_ENTER_ENUMS_3("4", "其他"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, DictClientEnterEnums> DICT_FD_CLIENT_ENTER_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(DictClientEnterEnums.values()).collect(Collectors.toMap(DictClientEnterEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            DictClientEnterEnums enums = DICT_FD_CLIENT_ENTER_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}
