package com.gaog.orderingapplets.restaurant.service.impl;

import com.gaog.orderingapplets.restaurant.common.Result;
import com.gaog.orderingapplets.restaurant.enums.ResponseCode;
import com.gaog.orderingapplets.restaurant.dto.role.RoleDTO;
import com.gaog.orderingapplets.restaurant.entity.Role;
import com.gaog.orderingapplets.restaurant.entity.UserRole;
import com.gaog.orderingapplets.restaurant.exception.BusinessException;
import com.gaog.orderingapplets.restaurant.mapper.RoleMapper;
import com.gaog.orderingapplets.restaurant.mapper.UserMapper;
import com.gaog.orderingapplets.restaurant.mapper.UserRoleMapper;
import com.gaog.orderingapplets.restaurant.service.RoleService;
import com.gaog.orderingapplets.restaurant.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述: 角色服务实现
 *
 * @CLASSNAME： RoleServiceImpl
 * @VERSION: 1.0.0
 * @Date：2024/12/18
 * @Author： ZSJ
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 功能描述： 创建角色
     *
     * @param roleDTO 角色 DTO
     * @Author： ZSJ
     */
    @Override
    public Result<Boolean> createRole(RoleDTO roleDTO) {

        Role roleByRoleName = roleMapper.selectRoleByRoleName(roleDTO.getName());
        if (roleByRoleName != null) {
            throw new BusinessException(ResponseCode.ERROR_EXIST_ROLE);
        }

        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        role.setIsActive(true);
        role.setIsDeleted(0);
        role.setCreatedAt(new Date());
        role.setUpdatedAt(new Date());
        int insert = roleMapper.insert(role);
        if (insert > 0) {
            return Result.success(true, "创建角色成功");
        } else {
            return Result.error(ResponseCode.ERROR_CREATE_ROLE.getCode(), ResponseCode.ERROR_CREATE_ROLE.getMessage(), null);
        }
    }

    /**
     * 功能描述： 将角色分配给用户
     *
     * @param userId 用户 ID
     * @param roleId 角色 ID
     * @Author： ZSJ
     */
    @Override
    public Result<Boolean> assignRoleToUser(Long userId, Long roleId) {
        //查询用户是否存在
        if (userMapper.selectAllByUserId(userId) == null) {
            throw new BusinessException(ResponseCode.USER_NOT_EXIST);
        }
        //查询角色是否存在
        if (roleMapper.selectAllByRoleId(roleId) == null) {
            throw new BusinessException(ResponseCode.ERROR_NOT_EXIST_ROLE);
        }
        //查询用户是否已经拥有该角色
        if (userRoleMapper.selectByUserIdAndRoleId(userId, roleId) != null) {
            throw new BusinessException(ResponseCode.ERROR_ASSIGN_ROLE,"该用户已经拥有此角色！");
        }
        int insert = userRoleMapper.insert(new UserRole(userId, roleId));
        if (insert > 0) {
            return Result.success(true, "分配角色成功");
        } else {
            return Result.error(ResponseCode.ERROR_ASSIGN_ROLE.getCode(), ResponseCode.ERROR_ASSIGN_ROLE.getMessage(), null);
        }
    }

    /**
     * 获取所有角色
     *
     * @return
     */
    @Override
    public Result<List<RoleVO>> getAllRoles() {
        // 获取所有角色
        List<Role> roles = roleMapper.selectList(null);
        List<RoleVO> roleVOList = roles.stream()
                .map(role -> new RoleVO(role.getId(), role.getName(), role.getDescription(), role.getIsActive(), role.getIsDeleted(), role.getCreatedAt(), role.getUpdatedAt()))
                .collect(Collectors.toList());
        return Result.success(roleVOList);
    }
} 