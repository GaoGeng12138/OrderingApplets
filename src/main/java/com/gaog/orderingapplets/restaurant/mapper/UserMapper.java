package com.gaog.orderingapplets.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaog.orderingapplets.restaurant.entity.User;
import com.gaog.orderingapplets.restaurant.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 功能描述: 用户映射器
 *
 * @CLASSNAME： UserMapper
 * @VERSION: 1.0.0
 * @Date：2025/01/03
 * @Author： ZSJ
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     */
    @Select("SELECT \r\n" + //
            "    u.id AS user_id,\r\n" +
            "    u.username,\r\n" +
            "    u.password,\r\n" +
            "    u.email,\r\n" +
            "    u.is_active,\r\n" +
            "    u.is_deleted,\r\n" +
            "    u.created_at,\r\n" +
            "    u.updated_at,\r\n" +
            "    r.id AS role_id,\r\n" +
            "    r.name AS role_name,\r\n" +
            "    r.description AS role_description\r\n" +
            "FROM \r\n" +
            "    users u\r\n" +
            "LEFT JOIN \r\n" +
            "    user_roles ur ON u.id = ur.user_id\r\n" +
            "LEFT JOIN \r\n" +
            "    roles r ON ur.role_id = r.id WHERE u.username = #{username}")
    UserVO selectByUsername(String username);

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