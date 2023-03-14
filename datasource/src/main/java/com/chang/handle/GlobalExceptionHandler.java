package com.chang.handle;

import com.chang.exception.ApiException;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:ChenChang
 * @Description: 全局异常处理器
 * @Data: Create in 15:16 2022/11/15
 * @Modified By:
 */
@ControllerAdvice
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public Map<String, Object> response(ApiException e){
        HashMap<String, Object> res = new HashMap<>();
        res.put("errorId", e.getErrorId());
        res.put("errorMessage", e.getErrorMessage());
        res.put("message", e.getMessage());
        return res;
    }
}
