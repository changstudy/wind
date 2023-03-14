package com.mk.exception;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 5678744573352979565L;

    private Integer errorCode;
    private String errorMessage;


    public ApiException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public ApiException(String errorMessage){
        super(errorMessage);
        this.errorCode = 10500;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
