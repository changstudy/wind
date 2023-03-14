package com.chang.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContext.applicationContext == null){
            SpringContext.applicationContext  = applicationContext;

        }
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name,Object.class);
    }

    public static <T> T getBean(String name,Class<T> classOfT){
        checkApplicationContext();
        return applicationContext.getBean(name,classOfT);
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("SpringContext未注入");
        }
    }
}

