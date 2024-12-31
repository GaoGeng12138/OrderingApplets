package com.gaog.orderingapplets.restaurant.security;

import com.gaog.orderingapplets.restaurant.enums.PermissionConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 功能描述: 自定义授权过滤器
 *
 * @CLASSNAME： CustomAuthorizationFilter
 * @VERSION: 1.0.0
 * @Date：2024/12/18
 * @Author： ZSJ
 */
@Component
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    private CustomPermissionEvaluator customPermissionEvaluator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 获取当前用户的身份信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 进行权限校验
        if (authentication != null && authentication.isAuthenticated()) {
            String requestURI = request.getRequestURI();

            // 示例：检查用户是否有权限访问特定路径
            if (requestURI.contains("/api/admin") && !customPermissionEvaluator.hasPermission(authentication, null, PermissionConstant.ROLE_ADMIN.getMessage())) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: You do not have permission to operate.");
                return;
            }

        }

        // 继续过滤链
        filterChain.doFilter(request, response);
    }
} 