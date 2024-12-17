package com.gaog.orderingapplets.restaurant.dto.order;

import lombok.Data;

import java.util.List;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.restaurant.dto
 * @Date：2024/12/1 19:53
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@Data
public class OrderCreateDTO {
    private Long userId;
    private List<OrderItemDTO> items;
    private String address;
    private String phone;
    private String remark;
    private boolean isFromCart;
}