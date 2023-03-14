package com.chang.pojo;

import lombok.Data;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 17:14 2022/11/3
 * @Modified By:
 */
@Data
public class ResultVo {

    private Integer code;

    private String message;

    private Object data;

    public ResultVo defineResult(Integer code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
        return this;
    }
}
