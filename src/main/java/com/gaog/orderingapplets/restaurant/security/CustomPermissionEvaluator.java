package com.gaog.orderingapplets.restaurant.security;

import com.gaog.orderingapplets.restaurant.enums.PermissionConstant;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.gaog.orderingapplets.restaurant.security
 * @Date：2024/12/30 16:32
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        // 自定义权限校验逻辑
        if (authentication == null || !authentication.isAuthenticated()) {
            // 用户未认证
            return false;
        }

        // 假设我们有一个角色 "ROLE_ADMIN" 和一个权限 "EDIT"
        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(PermissionConstant.ROLE_ADMIN.getMessage()));

        if (permission.equals(PermissionConstant.ROLE_ADMIN.getMessage())) {
            // 只有管理员可以编辑
            return hasAdminRole;
        }

        // 默认不允许
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // 这里可以实现基于 ID 和类型的权限校验
        // 默认不允许
        return false;
    }
}
