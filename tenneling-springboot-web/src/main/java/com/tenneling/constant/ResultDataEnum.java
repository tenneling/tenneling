package com.tenneling.constant;

/**
 * @ Description: 返回值枚举类
 * @ Author:wenyanni
 * @ Date:2022/7/24 14:38
 */
public enum  ResultDataEnum {

    FAIL(500,"失败"),
    APPID_MISSING(41002,"appid missing"),
    APPID_ERROR(40013,"invalid appid"),
    CODE_ERROR(40029,"invalid code");



    int code;
    String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private ResultDataEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
