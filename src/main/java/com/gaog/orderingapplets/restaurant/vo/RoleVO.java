package com.gaog.orderingapplets.restaurant.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.gaog.orderingapplets.restaurant.vo
 * @Date：2024/12/31 10:35
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3192052197877293959L;

    private Long id;

    private String name;

    private String description;

    /**
     * 功能描述: 是否启用 0-禁用 1-启用
     */
    private Boolean isActive;

    /**
     * 功能描述: 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;

    private Date createdAt;

    private Date updatedAt;
}
