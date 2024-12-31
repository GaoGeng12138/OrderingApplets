package com.gaog.orderingapplets.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaog.orderingapplets.restaurant.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 功能描述: 角色映射器
 *
 * @CLASSNAME： RoleMapper
 * @VERSION: 1.0.0
 * @Date：2024/12/18
 * @Author： ZSJ
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 功能描述： 按用户 ID 选择角色
     *
     * @param userId 用户 ID
     * @return {@code List<Role> }
     * @Author： ZSJ
     */
    @Select("SELECT r.* FROM roles r " +
            "INNER JOIN user_roles ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = #{userId} AND r.is_active = 1 AND r.is_deleted = 0")
    List<Role> selectRolesByUserId(Long userId);

    /**
     * 功能描述： 按角色 ID 全选
     *
     * @param roleId 角色 ID
     * @return {@code Role }
     * @Author： ZSJ
     */
    @Select("SELECT id, name, description,is_active, is_deleted,created_at, updated_at FROM roles r WHERE id = #{roleId} AND r.is_active = 1 AND r.is_deleted = 0")
    Role selectAllByRoleId(Long roleId);

    /**
     * 根据角色名称筛选
     *
     * @param roleName
     * @return
     */
    @Select("SELECT id, name, description,is_active, is_deleted,created_at, updated_at FROM roles r WHERE name = #{roleName} AND r.is_active = 1 AND r.is_deleted = 0")
    Role selectRoleByRoleName(String roleName);
}