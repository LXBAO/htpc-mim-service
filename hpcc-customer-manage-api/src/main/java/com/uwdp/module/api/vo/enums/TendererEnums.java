package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lx
 * @data 2023/7/19 14:39
 * 开标方式
 */
@Getter
@AllArgsConstructor
public enum TendererEnums {
    TENDERER_ENUMS_1("1", "现场开标"),
    TENDERER_ENUMS_2("2", "电子开标"),
    TENDERER_ENUMS_0("0", "保留附件")
    ;

    private final String value;
    private final String name;

    private static final Map<String, TendererEnums> TENDERER_MAP = Collections
            .unmodifiableMap(Arrays.stream(TendererEnums.values()).collect(Collectors.toMap(TendererEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            TendererEnums enums = TENDERER_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}
