package com.chang.aspect;

import com.chang.annotation.TowLimiter;
import com.chang.exception.ApiException;
import com.chang.exception.ErrorCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.concurrent.TimeUnit;

/**
 * @Author:ChenChang
 * @Description: 去重拦截
 * @Data: Create in 15:06 2022/11/10
 * @Modified By:
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class TowLimiterAspect {

    private static Logger logger = LoggerFactory.getLogger(TowLimiterAspect.class);

    @Autowired
    private RedisTemplate redisService;

    @Before("@annotation(towLimiter)")
    public void doBefore(JoinPoint joinPoint, TowLimiter towLimiter) throws Exception {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String path = request.getRequestURL().toString() + "-" + request.getMethod();
        String uid = request.getParameter("uid");
        String key = path + "-" + uid;
        System.out.println(key);
        Boolean lock = redisService.opsForValue().setIfAbsent(key, true, 3, TimeUnit.SECONDS);
        if (!lock){
            System.out.println("lock");
            throw new ApiException(ErrorCode.TOW_LIMITER);
        }
    }


}
