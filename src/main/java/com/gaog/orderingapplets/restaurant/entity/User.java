package com.gaog.orderingapplets.restaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 功能描述: 用户
 *
 * @CLASSNAME： User
 * @VERSION: 1.0.0
 * @Date：2024/12/17
 * @Author： ZSJ
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("users")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    /**
     * 功能描述: 是否启用 0-禁用 1-启用
     */
    @TableField("is_active")
    private Boolean isActive;

    /**
     * 功能描述: 是否删除 0-未删除 1-已删除
     */

    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("created_at")
    private Date createdAt;
    @TableField("updated_at")
    private Date updatedAt;
} 