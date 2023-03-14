package com.chang.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private Integer errorId;
    private String errorCode;
    private String message = "";
    private String detail = "";

    public ErrorMessage(int errorId, String errorCode, String message) {
        this.errorId = errorId;
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorMessage(ErrorCode errorCode){
        this.errorId = errorCode.getErrorId();
        this.errorCode = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }
}
