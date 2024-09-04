package com.uwdp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lx
 * @data 2023/6/27 17:00
 */

@Component
@ConfigurationProperties(prefix = "qichacha")
@Data
public class QiChaChaConfiguration {

    private String appKey;


    private String secretKey;


    private String nameSearchUrl;


    private String fuzzySearchUrl;
}
