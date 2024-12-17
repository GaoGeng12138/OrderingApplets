package com.gaog.orderingapplets.restaurant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaog.orderingapplets.restaurant.common.PageResult;
import com.gaog.orderingapplets.restaurant.dto.order.OrderCreateDTO;
import com.gaog.orderingapplets.restaurant.dto.order.OrderItemDTO;
import com.gaog.orderingapplets.restaurant.dto.order.OrderQueryDTO;
import com.gaog.orderingapplets.restaurant.entity.Order;
import com.gaog.orderingapplets.restaurant.entity.OrderItem;
import com.gaog.orderingapplets.restaurant.enums.OrderStatus;
import com.gaog.orderingapplets.restaurant.exception.BusinessException;
import com.gaog.orderingapplets.restaurant.mapper.OrderItemMapper;
import com.gaog.orderingapplets.restaurant.mapper.OrderMapper;
import com.gaog.orderingapplets.restaurant.service.CartService;
import com.gaog.orderingapplets.restaurant.service.OrderService;
import com.gaog.orderingapplets.restaurant.service.ProductService;
import com.gaog.orderingapplets.restaurant.vo.OrderItemVO;
import com.gaog.orderingapplets.restaurant.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述: 订单服务实现
 *
 * @CLASSNAME： OrderServiceImpl
 * @VERSION: 1.0.0
 * @Date：2024/12/06
 * @Author： ZSJ
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(OrderCreateDTO createDTO) {
        // 1. 创建订单
        Order order = new Order();
        order.setUserId(createDTO.getUserId());
        order.setStatus(OrderStatus.PENDING.name());
        order.setTotalAmount(calculateTotal(createDTO.getItems()));
        orderMapper.insert(order);

        // 2. 创建订单项
        List<OrderItem> orderItems = createDTO.getItems().stream()
                .map(item -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(order.getId());
                    orderItem.setProductId(item.getProductId());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(item.getPrice());
                    return orderItem;
                })
                .collect(Collectors.toList());

        orderItemMapper.batchInsert(orderItems);

        // 3. 扣减库存
        for (OrderItemDTO item : createDTO.getItems()) {
            productService.updateStock(item.getProductId(), item.getQuantity());
        }

        // 4. 清空购物车（如果是从购物车下单）
        if (createDTO.isFromCart()) {
            cartService.clear(createDTO.getUserId());
        }

        return order.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancel(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }

        if (!OrderStatus.PENDING.name().equals(order.getStatus())) {
            throw new BusinessException("订单状态不允许取消");
        }

        order.setStatus(OrderStatus.CANCELLED.name());
        orderMapper.updateById(order);

        // 恢复库存
        List<OrderItemVO> items = orderItemMapper.selectByOrderId(orderId);
        for (OrderItemVO item : items) {
            productService.updateStock(item.getProductId(), -item.getQuantity());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pay(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }

        if (!OrderStatus.PENDING.name().equals(order.getStatus())) {
            throw new BusinessException("订单状态不正确");
        }

        order.setStatus(OrderStatus.PAID.name());
        orderMapper.updateById(order);
    }

    @Override
    public OrderVO getDetail(Long orderId) {
        OrderVO orderVO = orderMapper.selectOrderDetail(orderId);
        if (orderVO == null) {
            throw new BusinessException("订单不存在");
        }

        List<OrderItemVO> items = orderItemMapper.selectByOrderId(orderId);
        orderVO.setItems(items);

        return orderVO;
    }

    @Override
    public PageResult<OrderVO> page(OrderQueryDTO queryDTO) {
        try {
            Page<Order> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
            IPage<Order> resultPage = orderMapper.selectUserOrders(
                page, queryDTO.getUserId(), queryDTO.getStatus());
                
            List<OrderVO> voList = resultPage.getRecords().stream()
                    .map(this::convertToVO)
                    .collect(Collectors.toList());
                    
            return PageResult.success(new Page<OrderVO>()
                    .setRecords(voList)
                    .setTotal(resultPage.getTotal())
                    .setSize(resultPage.getSize())
                    .setCurrent(resultPage.getCurrent()));
        } catch (Exception e) {
            log.error("查询订单列表异常", e);
            return PageResult.fail("查询订单列表失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(Long orderId, String status) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        
        // 检查状态转换是否合法
        checkStatusTransition(order.getStatus(), status);
        
        order.setStatus(status);
        orderMapper.updateById(order);
    }

    private BigDecimal calculateTotal(List<OrderItemDTO> items) {
        return items.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private OrderVO convertToVO(Order order) {
        OrderVO vo = new OrderVO();
        BeanUtils.copyProperties(order, vo);
        vo.setItems(orderItemMapper.selectByOrderId(order.getId()));
        return vo;
    }

    private void checkStatusTransition(String currentStatus, String newStatus) {
        // 实现订单状态转换的业务规则
        if (OrderStatus.CANCELLED.name().equals(currentStatus) ||
            OrderStatus.COMPLETED.name().equals(currentStatus)) {
            throw new BusinessException("当前订单状态不允许修改");
        }

    }
} 