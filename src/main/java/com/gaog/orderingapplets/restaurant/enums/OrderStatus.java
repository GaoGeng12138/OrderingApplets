package com.gaog.orderingapplets.restaurant.enums;

public enum OrderStatus {
    PENDING("待支付"),
    PAID("已支付"),
    PREPARING("制作中"),
    DELIVERING("配送中"),
    COMPLETED("已完成"),
    CANCELLED("已取消");
    
    private String description;
    
    OrderStatus(String description) {
        this.description = description;
    }
} 