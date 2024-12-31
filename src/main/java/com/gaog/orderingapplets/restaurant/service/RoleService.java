package com.gaog.orderingapplets.restaurant.service;

import com.gaog.orderingapplets.restaurant.common.Result;
import com.gaog.orderingapplets.restaurant.dto.role.RoleDTO;
import com.gaog.orderingapplets.restaurant.vo.RoleVO;

import java.util.List;

/**
 * 功能描述: 角色服务
 *
 * @CLASSNAME： RoleService
 * @VERSION: 1.0.0
 * @Date：2024/12/18
 * @Author： ZSJ
 */
public interface RoleService {
    /**
     * 功能描述： 创建角色
     *
     * @param roleDTO 角色 DTO
     * @Author： ZSJ
     */
    Result<Boolean> createRole(RoleDTO roleDTO);

    /**
     * 功能描述： 将角色分配给用户
     *
     * @param userId 用户 ID
     * @param roleId 角色 ID
     * @Author： ZSJ
     */
    Result<Boolean> assignRoleToUser(Long userId, Long roleId);

    /**
     * 获取所有角色列表
     * @return
     */
    Result<List<RoleVO>> getAllRoles();
}