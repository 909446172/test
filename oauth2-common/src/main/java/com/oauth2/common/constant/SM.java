package com.oauth2.common.constant;

public enum SM {
    SUCCESS(true, 20000,"成功"),
    UNKNOWN_REASON(false, 20001, "未知错误"),
    PARAM_ERROR(false, 21002, "参数不正确");


    private Boolean isSuccess;
    private String  message ;
    private Integer code;


    SM(Boolean isSuccess, Integer code,String message) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Status{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
