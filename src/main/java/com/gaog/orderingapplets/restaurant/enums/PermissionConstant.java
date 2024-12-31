package com.gaog.orderingapplets.restaurant.enums;

import lombok.Getter;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.gaog.orderingapplets.restaurant.constant
 * @Date：2024/12/30 15:22
 * @Version： 1.0.0
 * @Description： 角色 /权限 枚举值
 * @Author： ZSJ
 */
@Getter
public enum PermissionConstant {


    /**
     * 角色 /权限 枚举值
     */
    ROLE_ADMIN("ROLE_ADMIN", "ADD,EDIT,DELETE,QUERY"),
    ROLE_EDITOR("ROLE_EDITOR", "EDIT,QUERY"),
    ROLE_USER("ROLE_USER", "QUERY"),
    ROLE_VISITOR("ROLE_VISITOR", "QUERY"),


    /**
     * 权限描述
     */
    ADD("ADD", "新增"),
    EDIT("EDIT", "编辑"),
    DELETE("DELETE", "删除"),
    QUERY("QUERY", "查询");




    private final String message;
    private final String description;

    PermissionConstant(String message, String description) {
        this.message = message;
        this.description = description;
    }
}
