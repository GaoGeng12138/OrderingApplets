package com.gaog.orderingapplets.restaurant.service;

import com.gaog.orderingapplets.restaurant.dto.order.OrderCreateDTO;
import com.gaog.orderingapplets.restaurant.dto.order.OrderQueryDTO;
import com.gaog.orderingapplets.restaurant.vo.OrderVO;
import com.gaog.orderingapplets.restaurant.common.PageResult;

/**
 * 功能描述: 订购服务
 *
 * @CLASSNAME： OrderService
 * @VERSION: 1.0.0
 * @Date：2024/12/10
 * @Author： ZSJ
 */
public interface OrderService {
    // 创建订单
    Long create(OrderCreateDTO createDTO);
    
    // 取消订单
    void cancel(Long orderId);
    
    // 支付订单
    void pay(Long orderId);
    
    // 获取订单详情
    OrderVO getDetail(Long orderId);
    
    // 分页查询订单
    PageResult<OrderVO> page(OrderQueryDTO queryDTO);
    
    // 更新订单状态
    void updateStatus(Long orderId, String status);
} 