package com.gaog.orderingapplets.restaurant.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.restaurant.vo
 * @Date：2024/12/1 20:00
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@Data
public class CartVO {
    private Long id;
    private List<CartItemVO> items;
    private BigDecimal totalAmount;
    private Integer totalQuantity;
}