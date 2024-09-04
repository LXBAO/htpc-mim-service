package com.uwdp.config;

import com.uwdp.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置拦截条件
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //参数为我们自定义类，实现了HandlerInterceptor接口重写了三个方法
        
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/hpcc-customer-manage/v2_0/**")   //拦截所有的路径
                .excludePathPatterns("/qiyeweixin/**"); //放行qiyeweixin目录下的,因为生成token拦截就无法生成了
    }
}
