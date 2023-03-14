package com.chang.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//@Component
public class ExceptionHandle implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception exception) {
        try {
            if (exception instanceof ApiException){
                ApiException apiEx = (ApiException) exception;
                String printString;
                response.setContentType("application/json;charset=UTF-8");
                if (apiEx.isPrintAll()){
                    printString = apiEx.getErrorMessage();
                } else {
                    ErrorMessage errorMessage = new ErrorMessage(apiEx.getErrorId(), apiEx.getMessage(), apiEx.getErrorMessage());
                    printString = errorMessage.toString();
                }
                response.getOutputStream().write(printString.getBytes(StandardCharsets.UTF_8));
                response.getOutputStream().close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
