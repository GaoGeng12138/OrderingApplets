package com.gaog.orderingapplets.restaurant.service;

import com.gaog.orderingapplets.restaurant.common.Result;
import com.gaog.orderingapplets.restaurant.dto.user.LoginDTO;
import com.gaog.orderingapplets.restaurant.dto.user.PasswordChangeDTO;
import com.gaog.orderingapplets.restaurant.dto.user.UserRegisterDTO;
import com.gaog.orderingapplets.restaurant.dto.user.UserUpdateDTO;
import com.gaog.orderingapplets.restaurant.vo.UserVO;

import java.util.Map;

/**
 * 功能描述: 用户服务
 *
 * @CLASSNAME： UserService
 * @VERSION: 1.0.0
 * @Date：2024/12/05
 * @Author： ZSJ
 */
public interface UserService {
    /**
     * 功能描述： 注册
     *
     * @param registerDTO 注册 DTO
     * @Author： ZSJ
     */
    void register(UserRegisterDTO registerDTO);

    /**
     * 功能描述： 登录
     *
     * @param loginDTO 登录 DTO
     * @return {@code String }
     * @Author： ZSJ
     */
    Result<Map<String, String>> login(LoginDTO loginDTO);

    /**
     * 功能描述： 获取用户信息
     *
     * @param userId 用户 ID
     * @return {@code UserVO }
     * @Author： ZSJ
     */
    UserVO getUserInfo(Long userId);

    /**
     * 功能描述： 更新用户信息
     *
     * @param updateDTO 更新 DTO
     * @Author： ZSJ
     */
    void updateUserInfo(UserUpdateDTO updateDTO);

    /**
     * 功能描述： 更改密码
     *
     * @param passwordDTO 密码 DTO
     * @Author： ZSJ
     */
    void changePassword(PasswordChangeDTO passwordDTO);
} 