package com.gaog.orderingapplets.restaurant.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class UserUpdateDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Date updatedAt;

}
