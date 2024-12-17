package com.gaog.orderingapplets.restaurant.dto.product;

import com.gaog.orderingapplets.restaurant.dto.PageRequest;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductQueryDTO extends PageRequest {
    private String keyword;
    private Long categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Boolean inStock;
} 