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
@ConfigurationProperties(prefix = "attachment")
@Data
public class AttachmentConfiguration {
    private String uploadUrl;
    private Integer maxSize;
    private Integer minSize;
}
