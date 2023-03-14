package com.mk.utils;

import com.mk.exception.ErrorCode;
import lombok.Data;

/**
 * @Author:ChenChang
 * @Description: 数据返回
 * @Data: Create in 16:30 2022/12/9
 * @Modified By:
 */
@Data
public class ResultVo<T> {

    private int code;

    private boolean success;

    private Object data;

    public ResultVo() {
        this.code = 200;
        this.success = true;
    }

    public ResultVo(int code, boolean success, Object data){
        this.code = code;
        this.success = success;
        this.data = data;
    }

    public ResultVo(T t){
        this.code = 200;
        this.success = true;
        this.data = t;
    }

    public ResultVo(ErrorCode errorCode){
        this.code = errorCode.getErrorCode();
        this.success = false;
        this.data = errorCode.getErrorMessage();
    }
}
