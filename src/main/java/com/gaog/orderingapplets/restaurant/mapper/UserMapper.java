package com.gaog.orderingapplets.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaog.orderingapplets.restaurant.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     */
    @Select("SELECT id, username, email, password,created_at, updated_at FROM users WHERE username = #{username}")
    User selectByUsername(String username);

    /**
     * 根据邮箱查询用户
     */
    @Select("SELECT * FROM users WHERE email = #{email}")
    User selectByEmail(String email);

    /**
     * 更新用户信息
     */
    @Update("UPDATE users SET username = #{username}, email = #{email} WHERE id = #{id}")
    int updateUserInfo(User user);

    /**
     * 更新密码
     */
    @Update("UPDATE users SET password = #{password} WHERE id = #{id}")
    int updatePassword(@Param("id") Long id, @Param("password") String password);


    /**
     * 功能描述： 按 User ID 选择全部
     *
     * @param userId 用户 ID
     * @return {@code User }
     * @Author： ZSJ
     */
    @Select("SELECT id, username, email, password, created_at, updated_at FROM users WHERE id = #{id}")
    User selectAllByUserId(Long userId);
} 