package com.daryl.customtemplate;

import com.daryl.domain.enums.HttpCodeEnum;

/**
 * @author wl
 * @create 2022-01-21
 */
public class MyException extends RuntimeException {
    private Integer code;
    private Object data;

    public MyException() {
    }

    public MyException(String message) {
        super(message);
        this.code = 999000;
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public MyException(Integer code, String message, Object data) {
        this(code, message);
        this.data = data;
    }

    public MyException(HttpCodeEnum msg, String... messageParams) {
        super(replaceParams(msg.getMsg(), messageParams));
        this.code = msg.getCode();
    }

    public MyException(HttpCodeEnum msg, Object data, String... messageParams) {
        this(msg, messageParams);
        this.data = data;
    }

    public static MyException error(String msg){
        return new MyException(msg);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static String replaceParams(String message, String... params) {
        String[] var2 = params;
        int var3 = params.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String param = var2[var4];
            message = message.replaceFirst("\\{}", param);
        }

        message = message.replace("{}", "");
        return message;
    }
}
