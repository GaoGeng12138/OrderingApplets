package com.gaog.orderingapplets.restaurant.dto.order;

import com.gaog.orderingapplets.restaurant.dto.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 功能描述: 订单查询 DTO
 *
 * @CLASSNAME： OrderQueryDTO
 * @VERSION: 1.0.0
 * @Date：2024/12/06
 * @Author： ZSJ
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderQueryDTO extends PageRequest {
    private String orderNo;
    private String status;
    private Date startTime;
    private Date endTime;
    private Long userId;
} 