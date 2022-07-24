package com.tenneling.entity.wechat;

import com.tenneling.constant.ResultDataEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JsonResult<T> {
    private int status;

    private String message;

    private T data;

    public JsonResult() {
    }

    public static JsonResult getInstance() {
        return new JsonResult<>();
    }

    public JsonResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public JsonResult ok(T data) {
        return new JsonResult(200, "成功", data);
    }

    public static JsonResult success() {
        return new JsonResult(200, "成功", null);
    }

    public static JsonResult success(String msg) {
        return new JsonResult(200, msg, null);
    }

    public static JsonResult fail(int status, String message) {
        return new JsonResult(status, message, null);
    }

    public static JsonResult fail(ResultDataEnum resultDataEnum) {
        return new JsonResult(resultDataEnum.getCode(), resultDataEnum.getMsg(), null);
    }

    public static JsonResult fail() {
        return new JsonResult(ResultDataEnum.FAIL.getCode(), ResultDataEnum.FAIL.getMsg(), null);
    }

    public static JsonResult fail(String msg) {
        return new JsonResult(ResultDataEnum.FAIL.getCode(), msg + "~", null);
    }
}
