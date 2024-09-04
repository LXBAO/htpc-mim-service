package com.uwdp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lx
 * @data 2023/6/30 10:57
 */
@Component
@ConfigurationProperties(prefix = "common")
@Data
public class CommonConfiguration {

    private String userInfoUrl;

}
