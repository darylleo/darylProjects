package com.daryl.domain.constants;

/**
 * 全局字段
 *
 * @author wl
 * @create 2022-01-27
 */
public class GlobalField {
    public static final String WHO_I_AM = "Daryl";

    //响应体类型
    public static final String RESPONSE_CONTENT_TYPE ="application/json";

    //字符集utf8
    public static final String CHARACTER_ENCODING_UTF8 = "UTF-8";

    //请求头Authentication
    public static final String REQUEST_HEAD_AUTHENTICATION = "Authentication";

    //启用状态
    public static final Integer ENTITY_STATUS_UP = 0;

    //禁用状态
    public static final Integer ENTITY_STATUS_DOWN = 1;

    //登录标记
    public static final String LOGIN = "LOGIN_";

    //请求头token
    public static final String TOKEN = "token";

    //逻辑删除
    public static final Integer LOGIC_DELETE_TRUE = 1;

    //逻辑不删除
    public static final Integer LOGIC_DELETE_FALSE = 0;


}
