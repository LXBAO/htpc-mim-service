package com.uwdp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lx
 * @data 2023/6/27 17:00
 */

@Component
@ConfigurationProperties(prefix = "qiyeweixin")
@Data
public class QiYeWeiXinConfiguration {

    private String appid;

    private String agentId;

    private String secret;

    private String accessTokenUrl;

    private String userInfoUrl;

    private String getuserdetailUrl;

}
