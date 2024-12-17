package com.gaog.orderingapplets.restaurant.controller;

import com.gaog.orderingapplets.restaurant.annotation.SystemLog;
import com.gaog.orderingapplets.restaurant.common.Result;
import com.gaog.orderingapplets.restaurant.dto.order.OrderCreateDTO;
import com.gaog.orderingapplets.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 订单控制器
 *
 * @CLASSNAME： OrderController
 * @VERSION: 1.0.0
 * @Date：2024/12/06
 * @Author： ZSJ
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @SystemLog("创建订单")
    @PostMapping("createOrder")
    public Result<Long> createOrder(@RequestBody OrderCreateDTO orderDTO) {
        // 创建订单逻辑
        return Result.success(orderService.create(orderDTO));
    }
} 