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
 * 功能描述: 角色
 *
 * @CLASSNAME： Role
 * @VERSION: 1.0.0
 * @Date：2024/12/06
 * @Author： ZSJ
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("roles")
public class Role {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

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