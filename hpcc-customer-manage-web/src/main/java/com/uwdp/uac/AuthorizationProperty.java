package com.uwdp.uac;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hpcc-mim-server")
@Data
public class AuthorizationProperty {
    private String permissonUrl;
    private Long appId;
}
