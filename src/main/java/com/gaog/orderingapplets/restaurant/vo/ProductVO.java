package com.gaog.orderingapplets.restaurant.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    /**
     * 额外的分类名称
     */

    private String categoryName;
    /**
     * 是否收藏
     */
    private Boolean isFavorite;
    /**
     * 商品图片
     */
    private String imageUrl;
} 