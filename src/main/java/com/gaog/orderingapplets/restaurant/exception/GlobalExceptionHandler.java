package com.gaog.orderingapplets.restaurant.exception;

import com.gaog.orderingapplets.restaurant.common.Result;
import com.gaog.orderingapplets.restaurant.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 全局异常处理程序
 *
 * @CLASSNAME： GlobalExceptionHandler
 * @VERSION: 1.0.0
 * @Date：2024/12/06
 * @Author： ZSJ
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理支付异常
     */
    @ExceptionHandler(PaymentException.class)
    public Result<Void> handlePaymentException(PaymentException e) {
        log.error("===========================支付异常=========================");
        log.error("支付异常：{}", e.getMessage());
        log.error("===========================支付异常=========================");
        return Result.error(e.getErrorCode(), e.getErrorMsg());
    }


    /**
     * 处理其他未知异常
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("===========================系统异常=========================");
        log.error("系统异常：{}", e.getMessage());
        log.error("===========================系统异常=========================");
        return Result.error(ResponseCode.SYSTEM_ERROR.getCode(), ResponseCode.SYSTEM_ERROR.getMessage());
    }


    /**
     * 功能描述： 处理业务异常
     *
     * @param e e
     * @return {@code Result<Void> }
     * @Author： ZSJ
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        log.error("===========================业务异常=========================");
        log.error("业务异常：{}", e.getMessage());
        log.error("===========================业务异常=========================");
        return Result.error(e.getErrorCode(), e.getMessage());
    }


    /**
     * 功能描述： 处理验证异常
     *
     * @param ex 前任
     * @return {@code Result<Map<String, String>> }
     * @Author： ZSJ
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("===========================校验参数异常=========================");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        log.error("参数错误：{}", errors);
        log.error("===========================校验参数异常=========================");
        return Result.error(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getMessage(), errors);
    }


} 