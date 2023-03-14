package com.mk.filter;

import com.alibaba.fastjson.JSON;
import com.mk.utils.AuthToken;
import com.mk.utils.ResultVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 16:50 2023/3/1
 * @Modified By:
 */
@Slf4j
public class AuthFilter extends BasicHttpAuthenticationFilter {



    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            return executeLogin(request, response);
        } catch (Exception e) {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(new ResultVo(e.getMessage())));
        }
        return false;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null){
            AuthToken authToken = new AuthToken(token);
            getSubject(request, response).login(authToken);
            //if ("mkChang".equals(token)){
            //    UsernamePasswordToken passwordToken = new UsernamePasswordToken();
            //    passwordToken.setUsername("mkChang");
            //    passwordToken.setPassword("123456".toCharArray());
            //    getSubject(request, response).login(passwordToken);
            //}else{
            //   response.setContentType("application/json;charset=UTF-8");
            //   response.setCharacterEncoding("utf-8");
            //   response.getWriter().write(JSON.toJSONString(new ResultVo("验签失败")));
            //}
        }
        return true;
    }
}
