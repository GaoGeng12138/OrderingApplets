package com.gaog.orderingapplets.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaog.orderingapplets.restaurant.entity.OrderItem;
import com.gaog.orderingapplets.restaurant.vo.OrderItemVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    
    /**
     * 批量插入订单项
     */
    @Insert("<script>" +
            "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES " +
            "<foreach collection='items' item='item' separator=','>" +
            "(#{item.orderId}, #{item.productId}, #{item.quantity}, #{item.price})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("items") List<OrderItem> items);
    
    /**
     * 查询订单项列表
     */
    @Select("SELECT oi.*, p.name as product_name " +
            "FROM order_items oi " +
            "LEFT JOIN products p ON oi.product_id = p.id " +
            "WHERE oi.order_id = #{orderId}")
    List<OrderItemVO> selectByOrderId(Long orderId);
} 