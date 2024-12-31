package com.gaog.orderingapplets.restaurant.exception;

import lombok.Getter;

import java.io.Serial;

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

    @Serial
    private static final long serialVersionUID = -1052600973441555455L;

    private int errorCode;
    private String errorMsg;
    
    public PaymentException(String message) {
        super(message);
        this.errorMsg = message;
    }
    
    public PaymentException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    
    public PaymentException(String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
    }
} 