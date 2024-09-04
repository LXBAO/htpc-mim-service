package com.uwdp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lx
 * @data 2023/7/3 17:50
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 访问路径以 “/staFile” 开头时，会去 磁盘 fileDir  路径下找静态资源
        registry.addResourceHandler("/hpcc-customer-manage/preview/data/**")
                .addResourceLocations("file:" + fileDir);
    }

}
