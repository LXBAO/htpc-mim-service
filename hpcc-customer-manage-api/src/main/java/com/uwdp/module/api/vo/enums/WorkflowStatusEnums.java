package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lx
 * @data 2023/7/19 17:48
 */
@Getter
@AllArgsConstructor
public enum WorkflowStatusEnums {
    WORKFLOW_STATUS_ENUMS_ACTIVE("ACTIVE", "审批中"),
    WORKFLOW_STATUS_ENUMS_REVOKE("REVOKE", "撤回"),
    WORKFLOW_STATUS_ENUMS_BACK("BACK", "驳回"),
    WORKFLOW_STATUS_ENUMS_SUSPENDED("SUSPENDED", "挂起"),
    WORKFLOW_STATUS_ENUMS_COMPLETED("COMPLETED", "审批通过"),
    WORKFLOW_STATUS_ENUMS_VETO("VETO", "否决"),
    WORKFLOW_STATUS_ENUMS_CANCEL("CANCEL", "取消"),
    WORKFLOW_STATUS_ENUMS_STOP("STOP", "终止"),
    WORKFLOW_STATUS_ENUMS_1("1", "草稿"),

    ;

    private final String value;
    private final String name;

    private static final Map<String, WorkflowStatusEnums> WORKFLOW_STATUS_ENUMS_MAP = Collections
            .unmodifiableMap(Arrays.stream(WorkflowStatusEnums.values()).collect(Collectors.toMap(WorkflowStatusEnums::getValue, e -> e)));

    public static String getName(String value) {
        if (value != null) {
            WorkflowStatusEnums enums = WORKFLOW_STATUS_ENUMS_MAP.get(value);
            if (enums == null) {
                return "";
            } else {
                return enums.getName();
            }
        }
        return "";
    }
}
