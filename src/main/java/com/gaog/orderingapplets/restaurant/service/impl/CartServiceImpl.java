package com.gaog.orderingapplets.restaurant.service.impl;

import com.gaog.orderingapplets.restaurant.dto.cartItem.CartItemDTO;
import com.gaog.orderingapplets.restaurant.entity.Cart;
import com.gaog.orderingapplets.restaurant.entity.CartItem;
import com.gaog.orderingapplets.restaurant.entity.Product;
import com.gaog.orderingapplets.restaurant.exception.BusinessException;
import com.gaog.orderingapplets.restaurant.mapper.CartItemMapper;
import com.gaog.orderingapplets.restaurant.mapper.CartMapper;
import com.gaog.orderingapplets.restaurant.mapper.ProductMapper;
import com.gaog.orderingapplets.restaurant.service.CartService;
import com.gaog.orderingapplets.restaurant.vo.CartItemVO;
import com.gaog.orderingapplets.restaurant.vo.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CartItemDTO itemDTO) {
        // 获取或创建购物车
        Cart cart = getOrCreateCart(itemDTO.getUserId());

        // 检查商品是否存在
        Product product = productMapper.selectById(itemDTO.getProductId());
        if (product == null) {
            throw new BusinessException("商品不存在");
        }

        // 检查库存
        if (product.getStock() < itemDTO.getQuantity()) {
            throw new BusinessException("商品库存不足");
        }

        // 检查购物车是否已有该商品
        CartItem existItem = cartItemMapper.selectByCartIdAndProductId(
                cart.getId(), itemDTO.getProductId());

        if (existItem != null) {
            // 更新数量
            existItem.setQuantity(existItem.getQuantity() + itemDTO.getQuantity());
            cartItemMapper.updateById(existItem);
        } else {
            // 新增购物车项
            CartItem cartItem = new CartItem();
            cartItem.setCartId(cart.getId());
            cartItem.setProductId(itemDTO.getProductId());
            cartItem.setQuantity(itemDTO.getQuantity());
            cartItemMapper.insert(cartItem);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateQuantity(Long itemId, Integer quantity) {
        CartItem cartItem = cartItemMapper.selectById(itemId);
        if (cartItem == null) {
            throw new BusinessException("购物车项不存在");
        }

        // 检查库存
        Product product = productMapper.selectById(cartItem.getProductId());
        if (product.getStock() < quantity) {
            throw new BusinessException("商品库存不足");
        }

        cartItem.setQuantity(quantity);
        cartItemMapper.updateById(cartItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long itemId) {
        cartItemMapper.deleteById(itemId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void clear(Long userId) {
        Cart cart = cartMapper.selectByUserId(userId);
        if (cart != null) {
            cartMapper.clearCart(cart.getId());
        }
    }

    @Override
    public CartVO getDetail(Long userId) {
        Cart cart = cartMapper.selectByUserId(userId);
        if (cart == null) {
            return new CartVO();
        }

        List<CartItemVO> items = cartMapper.selectCartItems(cart.getId());

        CartVO vo = new CartVO();
        vo.setId(cart.getId());
        vo.setItems(items);
        vo.setTotalAmount(calculateTotal(items));
        vo.setTotalQuantity(calculateTotalQuantity(items));

        return vo;
    }

    private Cart getOrCreateCart(Long userId) {
        Cart cart = cartMapper.selectByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setCreatedAt(new Date());
            cartMapper.insert(cart);
        }
        return cart;
    }

    private BigDecimal calculateTotal(List<CartItemVO> items) {
        return items.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Integer calculateTotalQuantity(List<CartItemVO> items) {
        return items.stream()
                .mapToInt(CartItemVO::getQuantity)
                .sum();
    }
} 