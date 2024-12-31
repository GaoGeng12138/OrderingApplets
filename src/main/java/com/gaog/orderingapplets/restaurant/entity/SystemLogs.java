package com.gaog.orderingapplets.restaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.gaog.orderingapplets.restaurant.entity
 * @Date：2024/12/31 16:58
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@Data
public class SystemLogs {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String operationDescription;
    private String methodName;
    private String requestParams;
    private Long executionTime;
    @TableField("operator_name")
    private String operatorName;
    @TableField("operator_email")
    private String operatorEmail;
    @TableField("created_at")
    private Date createdAt;
    @TableField("updated_at")
    private Date updatedAt;
}