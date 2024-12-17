package com.gaog.orderingapplets.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaog.orderingapplets.restaurant.entity.Order;
import com.gaog.orderingapplets.restaurant.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 功能描述: Order Mapper （订单映射器）
 *
 * @CLASSNAME： OrderMapper
 * @VERSION: 1.0.0
 * @Date：2024/12/06
 * @Author： ZSJ
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 查询用户订单列表
     */
    @Select("<script>" +
            "SELECT * FROM orders " +
            "WHERE user_id = #{userId} " +
            "<if test='status != null and status != \"\"'>" +
            "AND status = #{status} " +
            "</if>" +
            "ORDER BY created_at DESC" +
            "</script>")
    IPage<Order> selectUserOrders(Page<Order> page,
                                  @Param("userId") Long userId,
                                  @Param("status") String status);

    /**
     * 更新订单状态
     */
    @Update("UPDATE orders SET status = #{status} WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") String status);

    /**
     * 查询订单详情
     */
    @Select("SELECT o.*, u.username as user_name " +
            "FROM orders o " +
            "LEFT JOIN users u ON o.user_id = u.id " +
            "WHERE o.id = #{id}")
    OrderVO selectOrderDetail(Long id);
} 