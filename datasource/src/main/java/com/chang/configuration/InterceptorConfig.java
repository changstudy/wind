package com.chang.configuration;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 10:58 2022/11/10
 * @Modified By:
 */
@Configuration
public class InterceptorConfig {

    @Bean
    public PageInterceptor pageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.put("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

}
