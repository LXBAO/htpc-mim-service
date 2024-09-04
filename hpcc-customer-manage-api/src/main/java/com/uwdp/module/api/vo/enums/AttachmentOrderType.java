package com.uwdp.module.api.vo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lx
 * @data 2023/7/10 9:36
 */
@Getter
@AllArgsConstructor
public enum AttachmentOrderType{
    PUBLIC_RELATION(1L,"公关计划"),
    PROJECT_BIDDING(2L,"项目投标"),
    PROJECT_WIN_THE(2L,"项目中标"),
    CER_INFO(3L,"证书信息"),
    PROJECT_IMPLEMENT(4L,"项目实施"),
    CERTIFICATES(4L,"项目实施"),
    CA_LEDGER(5L,"CA认证平台"),
    PLATFORM_LEDGER(6L,"招标平台"),
    ;
    private final long id;
    private final String name;

}
