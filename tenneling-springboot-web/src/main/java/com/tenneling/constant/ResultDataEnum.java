package com.tenneling.constant;

import lombok.Getter;

/**
 * @ Description: 返回值枚举类
 * @ Author:wenyanni
 * @ Date:2022/7/24 14:38
 */
@Getter
public enum ResultDataEnum {

    FAIL(500, "失败"),
    APPID_MISSING(41002, "appid missing"),
    APPID_ERROR(40013, "invalid appid"),
    CODE_ERROR(40029, "invalid code"),

    SUCCESS(200, "操作成功"),
    DELETE_SUCCESS(200, "删除成功"),
    COLLECTION_SUCCESS(200, "收藏成功"),
    CANCEL_SUCCESS(200, "取消成功"),
    FAILED(202, "操作失败,请稍后重试"),
    CREATED(200, "创建成功"),
    UPDATED(200, "修改成功"),
    UPDATE_ERROR(202, "修改失败"),
    CREATE_ERROR(202, "创建失败"),
    INVALID_VERIFY_CODE(202, "验证码错误！"),
    VERIFICATION_CODE_EXPIRED(202, "验证码过期！"),
    INVALID_USERNAME_PASSWORD(202, "无效的用户名和密码！"),
    USER_NOT_EXIST(202, "用户不存在"),
    PHONE_CODE_ERROR(202, "手机号验证码错误"),
    PHONE_CODE_EXPIRED(202, "验证码过期"),
    INVALID_SERVER_ID_SECRET(202, "无效的服务id和密钥！"),
    INSERT_OPERATION_FAIL(202, "新增操作失败！"),
    UPDATE_OPERATION_FAIL(202, "更新操作失败！"),
    DELETE_OPERATION_FAIL(202, "删除操作失败！"),
    FILE_UPLOAD_ERROR(202, "文件上传失败！"),
    DIRECTORY_WRITER_ERROR(202, "目录写入失败！"),
    FILE_WRITER_ERROR(202, "文件写入失败！"),
    SEND_MESSAGE_ERROR(202, "短信发送失败！"),
    REPEAT_PROCESS(202, "重复处理！"),
    REPEAT_PROCESS_USER(202, "重复邀请！"),
    SECKILL_ALREADY_JOIN_ERROR(203, "当前参与用户过多，请求稍后重试！"),
    TOO_MANY_VISITS(203, "访问太频繁,请稍后重试"),
    DATA_NOT_FOUNT(204, "暂无数据"),
    FILE_NOT_FOUNT(204, "未查询到文件"),
    ALGORITHM_NOT_FOUND(204, "未找到算法信息！"),
    DELETE_ERROR_DATA_NOTFOUND(204, "删除失败!请确认是否有此数据"),

    USER_NOT_LOGIN(300, "用户未登录"),

    PAGE_NOTNULL(400, "当前页和每页展示条数页码不能为空且页码必须大于等于1"),
    PARAM_FORMAT_ERROR(400, "参数格式错误"),
    CONTENT_TYPE_ERROR(400, "请检查Content type类型"),
    JSON_PARAM_FORMAT_ERROR(400, "JSON参数格式错误"),
    JSON_INPUT_ERROR(412, "JSON文件解析失败!"),
    SIGN_CHANNEL_NOTNULL(400, "报名渠道不能为空"),
    INVALID_FILE_TYPE(400, "无效的文件类型！"),
    INVALID_PARAM_ERROR(400, "无效的请求参数！"),
    INVALID_PHONE_NUMBER(400, "无效的手机号码"),
    LONG_SIZE(400, "长度不合法"),
    FILE_TO_LONG(400, "文件大小超出规定"),
    INVALID_NOTIFY_PARAM(401, "回调参数有误！"),
    INVALID_NOTIFY_SIGN(401, "回调签名有误！"),
    APPLICATION_NOT_FOUND(404, "应用不存在！"),

    CANNOT_UPDATE(401, "公开数据集不能改为私有数据集"),
    DATA_SET_NOT_FOUND(404, "暂无此数据集"),

    DATA_TRANSFER_ERROR(500, "数据转换异常！"),
    INVOKING_ERROR(500, "接口调用失败！"),
    SQL_EXCEPTION(500, "SQL异常"),

    UNKNOWN_ERROR(600, "服务器错误"),
    REQUEST_METHOD_ERROR(600, "请求方式错误"),

    USER_CENTER_ERROR(700, "用户中心异常");

    int code;
    String msg;

    ResultDataEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
