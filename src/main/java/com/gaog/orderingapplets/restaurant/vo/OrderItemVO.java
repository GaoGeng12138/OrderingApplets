package com.gaog.orderingapplets.restaurant.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 功能描述: 订购商品 vo
 *
 * @CLASSNAME： OrderItemVO
 * @VERSION: 1.0.0
 * @Date：2024/12/05
 * @Author： ZSJ
 */
@Data
public class OrderItemVO {
    private Long id;
    private String productName;
    private Long productId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subtotal;
    private String productImage;
} 