package com.mk.exception;

public enum ErrorCode {

    NOT_LOGIN(10501, "未登录"),
    LOGIN_FAIL(10502, "登入失败"),
    PASSWORD_ERROR(10503, "密码错误"),
    VISA_VERIFICATION_FAIL(10504, "认证失败"),
    NOT_HAVE_ANY_PERMISSION(10505, "无权限"),
    TOKEN_IS_INVALID(10605, "token无效"),
    ;

    private Integer errorCode;
    private String errorMessage;


    ErrorCode(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
