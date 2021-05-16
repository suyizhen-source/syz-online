package com.syz.commonutils;


public enum ResultCode implements IResultCode{
    SUCCESS(20000,"成功"),
    ERROR (20001,"失败");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code=code;
        this.message=message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
