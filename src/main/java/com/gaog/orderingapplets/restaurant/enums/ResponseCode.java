package com.gaog.orderingapplets.restaurant.enums;

import lombok.Getter;

/**
 * 功能描述: 响应代码
 *
 * @CLASSNAME： ResponseCode
 * @VERSION: 1.0.0
 * @Date：2024/12/17
 * @Author： ZSJ
 */
@Getter
public enum ResponseCode {

    /**
     * 系统级别枚举值返回
     */
    SUCCESS(20000, "Operation successful"),
    BAD_REQUEST(400, "错误请求"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "未找到"),
    INTERNAL_SERVER_ERROR(500, "内部服务器错误"),
    FAILURE(5000, "Operation failed"),
    SYSTEM_ERROR(5001, "系统异常，请稍后再试"),
    PARAM_ERROR(5002, "参数错误"),
    PARAM_NOT_NULL(5003, "参数不能为空"),


    /**
     * 用户相关枚举值返回
     */
    USERNAME_EXISTS(4001, "用户名已存在"),
    EMAIL_EXISTS(4002, "邮箱已被注册"),
    INVALID_CREDENTIALS(4003, "用户名或密码错误"),
    USER_NOT_EXIST(4004,"用户不存在！"),
    USER_IS_EXIST(4005,"用户已登录！请勿重复登录！"),
    TOKEN_EXPIRED(4011, "Token已过期"),
    TOKEN_INVALID(4012, "Token无效"),
    ORIGINAL_PASSWORD_ERROR(4013,"原始密码错误！"),


    /**
     * 商品类枚举值返回
     */
    ERROR_ADD_PRODUCT(4100, "新增商品失败！"),
    ERROR_EXIST_PRODUCT(4101, "商品已存在！"),
    ERROR_NOT_EXIST_PRODUCT(4102, "商品不存在！"),
    ERROR_INSUFFICIENT_PRODUCT(4103, "商品库存不足！"),


    /**
     * 购物车枚举值返回
     */
    ERROR_NOT_EXIST_CARTITEM(4200, "购物车项不存在！"),

    /**
     * 订单类枚举值返回
     */
    ERROR_NOT_EXIST_ORDER(4300,"订单不存在!"),
    ERROR_CANCEL_ORDER(4301,"当前订单不允许取消!"),




    /**
     * 角色类枚举值返回
     */
    ERROR_CREATE_ROLE(5003, "创建角色失败"),
    ERROR_ASSIGN_ROLE(5004, "分配角色失败"),
    ERROR_EXIST_ROLE(5005, "该角色已存在！"),
    ERROR_NOT_EXIST_ROLE(5005, "该角色不存在！");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}