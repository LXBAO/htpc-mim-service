package com.uwdp.config;

import com.ejlchina.searcher.BeanMeta;
import com.ejlchina.searcher.ParamFilter;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class BeanSearcherConfig {
    @Bean
    public ParamFilter myParamFilter() {
        return new ParamFilter() {
            @Override
            public <T> Map<String, Object> doFilter(BeanMeta<T> beanMeta, Map<String, Object> paraMap) {
                // beanMeta 是正在检索的实体类的元信息, paraMap 是当前的检索参数
                // 这里可以写一些自定义的参数过滤规则
                // 主数据机构查询时不做倒序
                if (beanMeta.getBeanClass() == MdmOrganizationDto.class){
                    return paraMap;
                }
                paraMap.put("sort","createdTime");
                paraMap.put("order","desc");
                return paraMap;      // 返回过滤后的检索参数
            }
        };
    }
}
