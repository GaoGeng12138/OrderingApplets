package com.gaog.orderingapplets.restaurant.controller.user;

import com.gaog.orderingapplets.restaurant.common.Result;
import com.gaog.orderingapplets.restaurant.dto.user.PasswordChangeDTO;
import com.gaog.orderingapplets.restaurant.dto.user.UserUpdateDTO;
import com.gaog.orderingapplets.restaurant.entity.User;
import com.gaog.orderingapplets.restaurant.service.UserService;
import com.gaog.orderingapplets.restaurant.vo.UserVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.gaog.orderingapplets.restaurant.controller.user
 * @Date：2024/12/31 10:28
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@RestController
@RequestMapping("/api/admin/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 功能描述： 获取用户信息
     *
     * @param userId
     * @return
     */
    @PostMapping("/getUserInfo")
    public Result<UserVO> getUserInfo(Long userId) {
        return userService.getUserInfo(userId);
    }

    /**
     * 修改用户信息
     *
     * @param updateDTO
     * @return
     */
    @PostMapping("/updateUserInfo")
    public Result<Boolean> updateUserInfo(@RequestBody @Valid UserUpdateDTO updateDTO) {
        return userService.updateUserInfo(updateDTO);
    }

    /**
     * 修改密码
     *
     * @param passwordDTO
     * @return
     */
    @PostMapping("/changePassword")
    Result<Boolean> changePassword(PasswordChangeDTO passwordDTO) {
        return userService.changePassword(passwordDTO);
    }

    /**
     * 获取所有的用户
     * @return
     */
    @GetMapping("/getAllUsers")
    Result<List<UserVO>> getAllUsers() {
        return userService.getAllUsers();
    }
}
