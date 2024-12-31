package com.gaog.orderingapplets.restaurant.converter;

import com.gaog.orderingapplets.restaurant.dto.order.OrderCreateDTO;
import com.gaog.orderingapplets.restaurant.entity.Order;
import com.gaog.orderingapplets.restaurant.entity.OrderItem;
import com.gaog.orderingapplets.restaurant.enums.OrderStatus;
import com.gaog.orderingapplets.restaurant.vo.OrderVO;
import com.gaog.orderingapplets.restaurant.vo.OrderItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述: 阶次转换器
 *
 * @CLASSNAME： OrderConverter
 * @VERSION: 1.0.0
 * @Date：2024/12/17
 * @Author： ZSJ
 */
@Component
public class OrderConverter {

    public OrderVO toVO(Order order, List<OrderItem> items) {
        OrderVO vo = new OrderVO();
        BeanUtils.copyProperties(order, vo);

        List<OrderItemVO> itemVOs = items.stream()
                .map(this::toItemVO)
                .collect(Collectors.toList());
        vo.setItems(itemVOs);
        return vo;
    }

    public OrderItemVO toItemVO(OrderItem item) {
        OrderItemVO vo = new OrderItemVO();
        BeanUtils.copyProperties(item, vo);
        vo.setSubtotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        return vo;
    }

    public static Order toEntity(OrderCreateDTO dto) {
        Order order = new Order();
        order.setUserId(dto.getUserId());
        order.setStatus(OrderStatus.PENDING.name());
        return order;
    }
} 