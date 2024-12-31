package com.gaog.orderingapplets.restaurant.converter;

import com.gaog.orderingapplets.restaurant.entity.User;
import com.gaog.orderingapplets.restaurant.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.gaog.orderingapplets.restaurant.converter
 * @Date：2024/12/30 14:51
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@Component
public class UserConverter {


    /**
     * 功能描述： 转换为 VO
     *
     * @param user 用户
     * @return {@code UserVO }
     * @Author： ZSJ
     */
    public static UserVO convertToVO(User user) {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        // 不返回密码
        vo.setPassword(null);
        return vo;
    }
}
