package com.uwdp.config;

import cn.net.ceec.hpcc.mdm.sdk.util.MdmUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 湖南火电公司主数据SDK集成
 * </p>
 *
 * @author liqingdian
 * @since 2023/4/20
 */
@Configuration
public class MdmSdkConfiguration {

    @Value("${mdm-data.sdk.app-key:MIM}")
    private String appKey;

    @Value("${mdm-data.sdk.app-secret:f44bb49cad7143808c2d4e7ae1e5709e}")
    private String appSecret;

    @Value("${mdm-data.sdk.token:5ad2bf82fe2aa1ff3a62ec36d8524104}")
    private String token;

    @Value("${mdm-data.sdk.saltValue:6da2bd017c574150bd9ee5039e19d8bf}")
    private String saltValue;

    /**
     * <p>
     * 注入mdmUtil
     * </p>
     *
     * @return cn.net.ceec.hpcc.mdm.sdk.util.MdmUtil
     * @author liqingdian
     * @since 2023-04-20
     */
    @Bean
    public MdmUtil mdmUtil() {
        return MdmUtil.getInstance(this.appKey, this.appSecret, this.token);
    }

}
