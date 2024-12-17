package com.gaog.orderingapplets.restaurant.enums;

public enum OrderStateChangeEvent {
    // 支付成功事件
    PAYMENT_SUCCESS,
    
    // 开始准备事件
    START_PREPARING,
    
    // 准备完成事件
    FINISH_PREPARING,
    
    // 开始配送事件
    START_DELIVERING,
    
    // 配送完成事件
    FINISH_DELIVERING,
    
    // 取消订单事件
    CANCEL_ORDER;
} 