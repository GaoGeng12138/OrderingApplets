package com.gaog.orderingapplets.restaurant.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述: 角色 DTO
 *
 * @CLASSNAME： RoleDTO
 * @VERSION: 1.0.0
 * @Date：2024/12/18
 * @Author： ZSJ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    @NotBlank(message = "角色名称不能为空")
    private String name;

    private String description;
} 