package com.gaog.orderingapplets.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaog.orderingapplets.restaurant.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {
    
    /**
     * 更新购物车商品数量
     */
    @Update("UPDATE cart_items SET quantity = #{quantity} WHERE id = #{id}")
    int updateQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);
    
    /**
     * 查询购物车中是否存在商品
     */
    @Select("SELECT * FROM cart_items WHERE cart_id = #{cartId} AND product_id = #{productId}")
    CartItem selectByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);
} 