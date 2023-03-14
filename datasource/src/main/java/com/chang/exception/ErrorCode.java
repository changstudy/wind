package com.chang.exception;

public enum ErrorCode {

    TOW_LIMITER(1, "10520", "请勿重复操作"),
    ;

    private int errorId;
    private String errorCode;
    private String message;


    ErrorCode(int errorId, String errorCode, String message) {
        this.errorId = errorId;
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getErrorId() {
        return errorId;
    }

    public String getMessage() {
        return message;
    }
}
