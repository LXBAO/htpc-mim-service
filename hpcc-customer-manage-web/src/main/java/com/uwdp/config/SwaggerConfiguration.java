package com.uwdp.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.time.LocalTime;

/**
 * SwaggerConfiguration
 *
 */
@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
public class SwaggerConfiguration {

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public SwaggerConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean(value = "ServiceAPi")
    public Docket platformApi2() {
        String groupName = "服务接口";
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .directModelSubstitute(LocalTime.class, String.class)
                // 分组名称
                .groupName(groupName)
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.uwdp.module.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
    }

    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title("统一前端开发平台接口文档")
                .description("# 源启.低代码开发软件")
                .termsOfServiceUrl("http://www.group.com/")
                .version("1.0")
                .build();
    }
}
