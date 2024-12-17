package com.gaog.orderingapplets.restaurant.dto.cartItem;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long productId;
    private Integer quantity;
    private Long userId;
} 