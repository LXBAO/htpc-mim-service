package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 项目状态
 *
 */
@Getter
@AllArgsConstructor
public enum IndustryCategoryOptions {

    INDUSTRY_CATEGORY_OPTIONS_ENUMS_1("20101", "传统能源/火电"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_2("20102", "传统能源/水电"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_3("20103", "传统能源/核电"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_4("20104", "传统能源/输变电"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_5("20105", "传统能源/其他传统能源"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_6("20106", "传统能源/工程施工和调试与运维业务"),

    INDUSTRY_CATEGORY_OPTIONS_ENUMS_7("20201", "新能源及综合智慧能源/风电"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_8("20202", "新能源及综合智慧能源/太阳能"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_9("20203", "新能源及综合智慧能源/生物质能"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_10("20204", "新能源及综合智慧能源/综合智慧能源"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_11("20205", "新能源及综合智慧能源/储能"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_12("20206", "新能源及综合智慧能源/氢能"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_13("20207", "新能源及综合智慧能源/地热及其他新能源"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_14("20208", "新能源及综合智慧能源/工程施工和调试与运维业务"),

    INDUSTRY_CATEGORY_OPTIONS_ENUMS_15("20301", "水利（水务）/水源及供水"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_16("20302", "水利（水务）/河湖治理及防洪"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_17("20303", "水利（水务）/海洋工程"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_18("20304", "水利（水务）/工程施工和调试与运维业务"),

    INDUSTRY_CATEGORY_OPTIONS_ENUMS_19("20401", "生态环保/生态保护"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_20("20402", "生态环保/环境治理"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_21("20403", "生态环保/节能"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_22("20404", "生态环保/工程施工和调试与运维业务"),

    INDUSTRY_CATEGORY_OPTIONS_ENUMS_23("20501", "综合交通/公路"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_24("20502", "综合交通/铁路"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_25("20503", "综合交通/港口与航道"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_26("20504", "综合交通/城市轨道交通"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_27("20505", "综合交通/机场"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_28("20506", "综合交通/工程施工和调试与运维业务"),

    INDUSTRY_CATEGORY_OPTIONS_ENUMS_29("20601", "市政/城镇道路桥梁"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_30("20602", "市政/城镇管道工程"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_31("20603", "市政/地下综合管廊"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_32("20604", "市政/工程施工和调试与运维业务"),

    INDUSTRY_CATEGORY_OPTIONS_ENUMS_33("20701", "房建/房屋建筑"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_34("20702", "房建/装配式建筑"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_35("20703", "房建/工程施工和调试与运维业务"),

    INDUSTRY_CATEGORY_OPTIONS_ENUMS_36("20801", "其他工程/矿山"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_37("20802", "其他工程/工业建设项目"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_38("20803", "其他工程/制造加工"),
    INDUSTRY_CATEGORY_OPTIONS_ENUMS_39("20804", "其他工程/工程施工和调试与运维业务"),
    ;

    private final String value;
    private final String name;

    private static final Map<String, IndustryCategoryOptions> INDUSTRY_CATEGORY_OPTIONS_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(IndustryCategoryOptions.values()).collect(Collectors.toMap(IndustryCategoryOptions::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            IndustryCategoryOptions enums = INDUSTRY_CATEGORY_OPTIONS_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}