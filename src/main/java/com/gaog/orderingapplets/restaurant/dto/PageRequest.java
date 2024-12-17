package com.gaog.orderingapplets.restaurant.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * 功能描述: 页面请求
 *
 * @CLASSNAME： PageRequest
 * @VERSION: 1.0.0
 * @Date：2024/12/05
 * @Author： ZSJ
 */
@Data
public class PageRequest {
    @Min(1)
    private Integer page = 1;
    
    @Min(1)
    @Max(100)
    private Integer size = 10;
    
    private String sortField;
    private String sortOrder;
} 