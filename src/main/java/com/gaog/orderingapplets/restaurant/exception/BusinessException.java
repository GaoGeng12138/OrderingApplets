package com.gaog.orderingapplets.restaurant.exception;

import com.gaog.orderingapplets.restaurant.enums.ResponseCode;
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

    private static final long serialVersionUID = 3488143909118951900L;

    private int errorCode;

    public BusinessException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.errorCode = responseCode.getCode();
    }

    public BusinessException(ResponseCode responseCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = responseCode.getCode();
    }
}
