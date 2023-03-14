package com.chang.exception;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 5678744573352979565L;
    private Integer errorId;
    private boolean printAll = false;
    private String errorMessage;
    public ApiException(ErrorCode errorCode) {
        super(errorCode.getErrorCode());
        this.errorId = errorCode.getErrorId();
        this.errorMessage = errorCode.getMessage();
    }

    public boolean isPrintAll() {
        return printAll;
    }

    public void setPrintAll(boolean printAll) {
        this.printAll = printAll;
    }

    public ApiException(ErrorCode errorCode, String customMessage) {
        super(errorCode.getErrorCode());
        this.errorId = errorCode.getErrorId();
        this.errorMessage = customMessage;
    }

    public ApiException(String errorMessage) {
        super(errorMessage);
        this.errorId = -1;
        this.errorMessage = errorMessage;
    }

    private ApiException(boolean printAll,String msg){
        super(msg);
        this.printAll = true;
        this.errorMessage = msg;
    }

    public static ApiException flushMsg(String msg){
        return new ApiException(true,msg);
    }

    public ApiException(int errorId, String errorCode) {
        super(errorCode);
        this.errorId = errorId;
    }

    public ApiException(int errorId, String errorCode, String errorMsg) {
        super(errorCode);
        this.errorId = errorId;
        this.errorMessage = errorMsg;
    }

    public Integer getErrorId() {
        return errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
