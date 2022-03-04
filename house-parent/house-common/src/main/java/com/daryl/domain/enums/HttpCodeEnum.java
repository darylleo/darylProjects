package com.daryl.domain.enums;

/**
 * @author wl
 * @create 2022-02-08
 */
public enum HttpCodeEnum {
    SUCCESS(666666, "操作成功"),
    NEED_LOGIN(1, "需要登录后操作"),
    LOGIN_PASSWORD_ERROR(2, "密码错误"),
    LOGIN_FAILED(3,"登录失败"),
    LOGOUT(4,"用户已登出"),
    TOKEN_INVALID(50, "无效的TOKEN"),
    TOKEN_EXPIRE(51, "TOKEN已过期"),
    TOKEN_REQUIRE(52, "TOKEN是必须的"),
    SIGN_INVALID(100, "无效的SIGN"),
    SIG_TIMEOUT(101, "SIGN已过期"),
    FORBIDDEN(403,"无权访问"),
    PARAM_REQUIRE(500, "缺少参数"),
    PARAM_INVALID(501, "无效参数"),
    PARAM_IMAGE_FORMAT_ERROR(502, "图片格式有误"),
    SERVER_ERROR(503, "服务器内部错误"),
    PARAM_ERROR(504,"参数错误"),
    DATA_EXIST(1000, "数据已经存在"),
    AP_USER_DATA_NOT_EXIST(1001, "ApUser数据不存在"),
    DATA_NOT_EXIST(1002, "数据不存在"),
    NO_OPERATOR_AUTH(3000, "无权限操作"),
    FAILED(999999,"操作失败");

    int code;
    String msg;

    private HttpCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
