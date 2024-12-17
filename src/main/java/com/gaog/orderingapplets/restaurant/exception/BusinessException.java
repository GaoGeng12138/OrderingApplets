package com.gaog.orderingapplets.restaurant.exception;

import lombok.Data;

/**
 * 功能描述: 业务异常
 *
 * @CLASSNAME： BusinessException
 * @VERSION: 1.0.0
 * @Date：2024/12/06
 * @Author： ZSJ
 */
@Data
public class BusinessException extends RuntimeException {
    private String errorCode;
    private String errorMsg;

    public BusinessException(String message) {
        super(message);
        this.errorMsg = message;
    }

    public BusinessException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
    }
}
