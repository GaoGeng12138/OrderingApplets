package com.gaog.orderingapplets.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaog.orderingapplets.restaurant.entity.Cart;
import com.gaog.orderingapplets.restaurant.vo.CartItemVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    /**
     * 查询用户购物车
     */
    @Select("SELECT * FROM carts WHERE user_id = #{userId}")
    Cart selectByUserId(Long userId);
    
    /**
     * 查询购物车详情
     */
    @Select("SELECT ci.*, p.name as product_name, p.price as product_price " +
            "FROM cart_items ci " +
            "LEFT JOIN products p ON ci.product_id = p.id " +
            "WHERE ci.cart_id = #{cartId}")
    List<CartItemVO> selectCartItems(Long cartId);
    
    /**
     * 清空购物车
     */
    @Delete("DELETE FROM cart_items WHERE cart_id = #{cartId}")
    int clearCart(Long cartId);
} 