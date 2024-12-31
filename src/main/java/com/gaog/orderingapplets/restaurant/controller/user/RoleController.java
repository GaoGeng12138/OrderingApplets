package com.gaog.orderingapplets.restaurant.controller.user;

import com.gaog.orderingapplets.restaurant.common.Result;
import com.gaog.orderingapplets.restaurant.dto.role.RoleDTO;
import com.gaog.orderingapplets.restaurant.service.RoleService;
import com.gaog.orderingapplets.restaurant.vo.RoleVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述: 角色控制器
 *
 * @CLASSNAME： RoleController
 * @VERSION: 1.0.0
 * @Date：2024/12/18
 * @Author： ZSJ
 */
@RestController
@RequestMapping("/api/admin/roles")
@Validated
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 功能描述： 创建角色
     *
     * @param roleDTO 角色 DTO
     * @return {@code Result<Boolean> }
     * @Author： ZSJ
     */
    @PostMapping("/createRole")
    public Result<Boolean> createRole(@RequestBody @Valid RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }

    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("/getAllRoles")
    public Result<List<RoleVO>> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * 功能描述： 将角色分配给用户
     *
     * @param userId 用户 ID
     * @param roleId 角色 ID
     * @return {@code Result<Boolean> }
     * @Author： ZSJ
     */
    @PostMapping("/{userId}/assign/{roleId}")
    public Result<Boolean> assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        return roleService.assignRoleToUser(userId, roleId);
    }
} 