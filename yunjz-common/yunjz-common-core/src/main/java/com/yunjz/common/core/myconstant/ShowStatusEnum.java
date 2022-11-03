package com.yunjz.common.core.myconstant;

/**
 * @author yunsheng
 * @create 2022-09-27 10:27
 */
public enum ShowStatusEnum {
    Enable(0,"可用"),Disable(1,"禁用");
    private int code;
    private String msg;

    ShowStatusEnum(int code,String msg){
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
