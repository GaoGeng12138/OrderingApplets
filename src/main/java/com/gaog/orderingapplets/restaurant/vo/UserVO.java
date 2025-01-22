package com.gaog.orderingapplets.restaurant.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.restaurant.vo
 * @Date：2024/12/5 16:54
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@Data
public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2701372624645934828L;

    private Long id;
    private String username;
    private String email;
    private String password;
    private Integer isActive;
    private Integer isDeleted;
    private Date createdAt;
    private Date updatedAt;
    private String roleName;

}
