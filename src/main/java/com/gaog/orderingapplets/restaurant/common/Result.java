package com.gaog.orderingapplets.restaurant.common;

import com.gaog.orderingapplets.restaurant.enums.ResponseCode;
import lombok.Data;

/**
 * 功能描述: 结果
 *
 * @CLASSNAME： Result
 * @VERSION: 1.0.0
 * @Date：2024/12/17
 * @Author： ZSJ
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;
    private String timestamp;
    private boolean success;

    public static <T> Result<T>  success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResponseCode.SUCCESS.getCode());
        result.setMessage(ResponseCode.SUCCESS.getMessage());
        result.setData(data);
        result.setSuccess(Boolean.TRUE);
        result.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return result;
    }

    public static <T> Result<T> success(T data,String message) {
        Result<T> result = new Result<>();
        result.setCode(ResponseCode.SUCCESS.getCode());
        result.setMessage(message);
        result.setData(data);
        result.setSuccess(Boolean.TRUE);
        result.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return result;
    }

    public static <T> Result<T> success(int code,String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        result.setSuccess(Boolean.TRUE);
        result.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return result;
    }

    public static <T> Result<T> error(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResponseCode.FAILURE.getCode());
        result.setMessage(ResponseCode.FAILURE.getMessage());
        result.setSuccess(Boolean.FALSE);
        result.setData(data);
        result.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return result;
    }

    public static <T> Result<T> error(T data,String message) {
        Result<T> result = new Result<>();
        result.setCode(ResponseCode.FAILURE.getCode());
        result.setMessage(message);
        result.setSuccess(Boolean.FALSE);
        result.setData(data);
        result.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return result;
    }

    public static <T> Result<T> error(int code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        result.setSuccess(Boolean.FALSE);
        result.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return result;
    }

    /**
     * 自定义返回结果
     * @param code
     * @param message
     * @param success
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Result<T> result(int code, String message, boolean success, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        result.setSuccess(success);
        result.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return result;
    }
} 