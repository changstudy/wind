package com.chang.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TowLimiter {
    /* 默认开启 */
    boolean value() default true;
}
