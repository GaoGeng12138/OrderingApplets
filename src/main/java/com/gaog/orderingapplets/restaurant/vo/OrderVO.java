package com.gaog.orderingapplets.restaurant.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
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
public class OrderVO {
    private Long id;
    private String orderNo;
    private Long userId;
    private String userName;
    private BigDecimal totalAmount;
    private String status;
    private Date createdAt;
    private List<OrderItemVO> items;
    private String address;
    private String phone;
    private String remark;
}