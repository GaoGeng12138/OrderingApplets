package com.gaog.orderingapplets.restaurant.service;

import com.gaog.orderingapplets.restaurant.dto.cartItem.CartItemDTO;
import com.gaog.orderingapplets.restaurant.vo.CartVO;

public interface CartService {
    // 添加商品到购物车
    void add(CartItemDTO itemDTO);
    
    // 更新购物车商品数量
    void updateQuantity(Long itemId, Integer quantity);
    
    // 删除购物车商品
    void delete(Long itemId);
    
    // 清空购物车
    void clear(Long userId);
    
    // 获取购物车详情
    CartVO getDetail(Long userId);
} 