package com.uwdp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.uwdp.module","com.gientech.lcds.workflow.sdk"})
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = {"com.uwdp"})
@MapperScan({"com.uwdp.**.infrastructure.mapper","com.gientech.lcds.**.infrastructure.mapper"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
