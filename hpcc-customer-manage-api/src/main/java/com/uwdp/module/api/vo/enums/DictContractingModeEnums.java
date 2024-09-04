package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 承包模式
 *
 */
@Getter
@AllArgsConstructor
public enum DictContractingModeEnums {

    DICT_CONTRACTING_MODE_ENUMS_0("mode1", "承包模式1"),
    DICT_CONTRACTING_MODE_ENUMS_1("mode2", "承包模式2"),
    DICT_CONTRACTING_MODE_ENUMS_2("mode3", "承包模式3"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, DictContractingModeEnums> DICT_CONTRACTING_MODE_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(DictContractingModeEnums.values()).collect(Collectors.toMap(DictContractingModeEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            DictContractingModeEnums enums = DICT_CONTRACTING_MODE_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}