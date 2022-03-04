package com.daryl.domain.dtos;

import com.daryl.domain.enums.HttpCodeEnum;

import java.io.Serializable;

/**
 * @author wl
 * @create 2022-02-08
 */
public class ResponseResult<T> implements Serializable {
    private String host;
    private Integer code;
    private String rspMsg;
    private T data;

    public ResponseResult() {
        this.code = 200;
    }

    public ResponseResult(String errorMsg) {
        this.code = 999999;
        this.rspMsg = errorMsg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.rspMsg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.rspMsg = msg;
    }

    public static ResponseResult okResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.ok(code, (Object) null, msg);
    }

    public static ResponseResult okResult(Object data) {
        ResponseResult result = setHttpCodeEnum(HttpCodeEnum.SUCCESS, HttpCodeEnum.SUCCESS.getMsg());
        if (data != null) {
            result.setData(data);
        }

        return result;
    }
    public static ResponseResult errorResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.error(code, msg);
    }

    public static ResponseResult<?> errorResult(String msg) {
        ResponseResult result = new ResponseResult<>();
        result.errors(msg);
        return result;
    }


    public static ResponseResult errorResult(HttpCodeEnum enums) {
        return setHttpCodeEnum(enums, enums.getMsg());
    }

    public static ResponseResult errorResult(HttpCodeEnum enums, String errorMessage) {
        return setHttpCodeEnum(enums, errorMessage);
    }

    public static ResponseResult setHttpCodeEnum(HttpCodeEnum enums) {
        return okResult(enums.getCode(), enums.getMsg());
    }

    private static ResponseResult setHttpCodeEnum(HttpCodeEnum enums, String errorMessage) {
        return okResult(enums.getCode(), errorMessage);
    }

    public ResponseResult<?> error(Integer code, String msg) {
        this.code = code;
        this.rspMsg = msg;
        return this;
    }

    public ResponseResult<?> errors(String msg) {
        this.code = HttpCodeEnum.FAILED.getCode();
        this.rspMsg = msg;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.rspMsg = msg;
        return this;
    }

    public ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
