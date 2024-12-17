package com.gaog.orderingapplets.restaurant.constant;

/**
 * 功能描述: 响应代码
 *
 * @CLASSNAME： ResponseCode
 * @VERSION: 1.0.0
 * @Date：2024/12/17
 * @Author： ZSJ
 */
public enum ResponseCode {

    SUCCESS("200", "成功"),
    USERNAME_EXISTS("4001", "用户名已存在"),
    EMAIL_EXISTS("4002", "邮箱已被注册"),
    INVALID_CREDENTIALS("4003", "用户名或密码错误"),
    UNAUTHORIZED("401", "未授权"),
    FORBIDDEN("403", "禁止访问"),
    NOT_FOUND("404", "未找到"),
    INTERNAL_SERVER_ERROR("500", "内部服务器错误"),
    BAD_REQUEST("400", "错误请求"),
    TOKEN_EXPIRED("4011", "Token已过期"),
    TOKEN_INVALID("4012", "Token无效"),
    SYSTEM_ERROR("5001", "系统异常，请稍后再试"),
    PARAM_ERROR("5002", "参数错误");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
} 