package com.gaog.orderingapplets.restaurant.exception;

import lombok.Getter;

/**
 * 功能描述: 付款异常
 *
 * @CLASSNAME： PaymentException
 * @VERSION: 1.0.0
 * @Date：2024/12/06
 * @Author： ZSJ
 */
@Getter
public class PaymentException extends RuntimeException {
    
    private String errorCode;
    private String errorMsg;
    
    public PaymentException(String message) {
        super(message);
        this.errorMsg = message;
    }
    
    public PaymentException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    
    public PaymentException(String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
    }
} 