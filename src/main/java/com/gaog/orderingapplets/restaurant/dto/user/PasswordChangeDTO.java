package com.gaog.orderingapplets.restaurant.dto.user;

import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.restaurant.dto.user
 * @Date：2024/12/5 16:54
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@Data
public class PasswordChangeDTO {

    private Long userId;
    private String username;
    private String email;
    private String newPassword;
    private String oldPassword;
    private Date updatedAt;
}
