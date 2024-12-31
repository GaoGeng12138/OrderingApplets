package com.gaog.orderingapplets.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaog.orderingapplets.restaurant.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 功能描述: 用户角色映射器
 *
 * @CLASSNAME： UserRoleMapper
 * @VERSION: 1.0.0
 * @Date：2024/12/18
 * @Author： ZSJ
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 功能描述： 按 User ID 和 Role ID 选择
     *
     * @param userId 用户 ID
     * @param roleId 角色 ID
     * @return {@code UserRole }
     * @Author： ZSJ
     */
    @Select("SELECT user_id, role_id,created_at,updated_at FROM user_roles WHERE user_id = #{userId} AND role_id = #{roleId}")
    UserRole selectByUserIdAndRoleId(Long userId, Long roleId);
}