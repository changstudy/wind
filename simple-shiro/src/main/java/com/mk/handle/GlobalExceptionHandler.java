package com.mk.handle;

import com.mk.exception.ApiException;
import com.mk.exception.ErrorCode;
import com.mk.utils.ResultVo;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:ChenChang
 * @Description: 全局异常处理器
 * @Data: Create in 15:16 2022/12/09
 * @Modified By:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResultVo response(ApiException e){
        return new ResultVo(e.getErrorCode(), false,  e.getErrorMessage());
    }

    /**
     * 认证异常
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public ResultVo response(AuthorizationException e){
        /**
         * not has role
         * not has permission
         * not have any identifying
         */
        return new ResultVo(ErrorCode.NOT_HAVE_ANY_PERMISSION);
    }
}
