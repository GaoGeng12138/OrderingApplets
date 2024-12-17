package com.gaog.orderingapplets.restaurant.service.impl;

import com.alipay.service.schema.util.StringUtil;
import com.gaog.orderingapplets.restaurant.common.Result;
import com.gaog.orderingapplets.restaurant.dto.user.LoginDTO;
import com.gaog.orderingapplets.restaurant.dto.user.PasswordChangeDTO;
import com.gaog.orderingapplets.restaurant.dto.user.UserRegisterDTO;
import com.gaog.orderingapplets.restaurant.dto.user.UserUpdateDTO;
import com.gaog.orderingapplets.restaurant.entity.User;
import com.gaog.orderingapplets.restaurant.exception.BusinessException;
import com.gaog.orderingapplets.restaurant.mapper.UserMapper;
import com.gaog.orderingapplets.restaurant.service.UserService;
import com.gaog.orderingapplets.restaurant.util.JwtUtil;
import com.gaog.orderingapplets.restaurant.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 用户服务实现
 *
 * @CLASSNAME： UserServiceImpl
 * @VERSION: 1.0.0
 * @Date：2024/12/05
 * @Author： ZSJ
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 功能描述： 注册
     *
     * @param registerDTO 注册 DTO
     * @Author： ZSJ
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterDTO registerDTO) {


        // 检查用户名是否存在
        if (userMapper.selectByUsername(registerDTO.getUsername()) != null) {
            throw new BusinessException("用户名已存在");
        }

        // 检查邮箱是否存在
        if (userMapper.selectByEmail(registerDTO.getEmail()) != null) {
            throw new BusinessException("邮箱已被注册");
        }

        // 创建用户
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        userMapper.insert(user);
    }

    /**
     * 功能描述： 登录
     *
     * @param loginDTO 登录 DTO
     * @return {@code String }
     * @Author： ZSJ
     */
    @Override
    public Result<Map<String, String>> login(LoginDTO loginDTO) {
        Map<String, String> map = new HashMap<>();
        User user = userMapper.selectByUsername(loginDTO.getUsername());
        if (user == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }
        String token = jwtUtil.generateToken(user.getId().toString());
        if (StringUtil.isEmpty(token)) {
            map.put("token", "");
            map.put("msg", "登录失败");
        } else {
            map.put("token", token);
            map.put("msg", "登录成功");
        }
        return Result.success(map);
    }


/**
 * 功能描述： 获取用户信息
 *
 * @param userId 用户 ID
 * @return {@code UserVO }
 * @Author： ZSJ
 */
@Override
public UserVO getUserInfo(Long userId) {
    User user = userMapper.selectById(userId);
    if (user == null) {
        throw new BusinessException("用户不存在");
    }

    return convertToVO(user);
}

/**
 * 功能描述： 更新用户信息
 *
 * @param updateDTO 更新 DTO
 * @Author： ZSJ
 */
@Override
@Transactional(rollbackFor = Exception.class)
public void updateUserInfo(UserUpdateDTO updateDTO) {
    User user = userMapper.selectById(updateDTO.getId());
    if (user == null) {
        throw new BusinessException("用户不存在");
    }

    // 检查邮箱是否被其他用户使用
    User existUser = userMapper.selectByEmail(updateDTO.getEmail());
    if (existUser != null && !existUser.getId().equals(updateDTO.getId())) {
        throw new BusinessException("邮箱已被使用");
    }

    user.setUsername(updateDTO.getUsername());
    user.setEmail(updateDTO.getEmail());
    user.setUpdatedAt(new Date());
    userMapper.updateById(user);
}

/**
 * 功能描述： 更改密码
 *
 * @param passwordDTO 密码 DTO
 * @Author： ZSJ
 */
@Override
@Transactional(rollbackFor = Exception.class)
public void changePassword(PasswordChangeDTO passwordDTO) {
    User user = userMapper.selectById(passwordDTO.getUserId());
    if (user == null) {
        throw new BusinessException("用户不存在");
    }

    if (!passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
        throw new BusinessException("原密码错误");
    }

    user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
    userMapper.updateById(user);
}

/**
 * 功能描述： 转换为 VO
 *
 * @param user 用户
 * @return {@code UserVO }
 * @Author： ZSJ
 */
private UserVO convertToVO(User user) {
    UserVO vo = new UserVO();
    BeanUtils.copyProperties(user, vo);
    // 不返回密码
    vo.setPassword(null);
    return vo;
}
} 