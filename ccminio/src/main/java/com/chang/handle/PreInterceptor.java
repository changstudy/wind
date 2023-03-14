package com.chang.handle;

import com.chang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 11:08 2022/11/7
 * @Modified By:
 */
@Component
public class PreInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    // 前置
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行前");
        System.out.println(userService);
        return true;
    }

    // 后置
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("执行后");
    }

    // postHandle 执行完后的操作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("post执行完了");
    }

}
