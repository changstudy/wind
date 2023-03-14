package com.chang.configuration;

import com.chang.handle.PreInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 11:10 2022/11/7
 * @Modified By:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private PreInterceptor preInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(preInterceptor).addPathPatterns("/**");
    }
}
