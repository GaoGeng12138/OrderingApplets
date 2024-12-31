package com.gaog.orderingapplets.restaurant.controller.user;


import com.gaog.orderingapplets.restaurant.common.Result;
import com.gaog.orderingapplets.restaurant.dto.user.LoginDTO;
import com.gaog.orderingapplets.restaurant.dto.user.UserRegisterDTO;
import com.gaog.orderingapplets.restaurant.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 功能描述: 身份验证控制器
 *
 * @CLASSNAME： AuthController
 * @VERSION: 1.0.0
 * @Date：2024/12/17
 * @Author： ZSJ
 */
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * 功能描述： 注册
     *
     * @param registerDTO 注册 DTO
     * @return {@code Result<String> }
     * @Author： ZSJ
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody @Valid UserRegisterDTO registerDTO) {
        userService.register(registerDTO);
        return Result.success("注册成功");
    }


    /**
     * 功能描述： 登录
     *
     * @param loginDTO 登录 DTO
     * @return {@code Result<String> }
     * @Author： ZSJ
     */
    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody @Valid LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    /**
     * 功能描述： 登出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success("登出成功");
    }


}