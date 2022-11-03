package com.yunjz.common.core.myconstant;

/**
 * @author yunsheng
 * @create 2022-09-27 11:30
 */
public enum  DefaultStatusEnum {
    Default(0,"默认状态"),NoDefault(1,"不是默认状态");
    private int code;
    private String msg;

    DefaultStatusEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
