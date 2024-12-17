package com.gaog.orderingapplets.restaurant.dto.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.restaurant.dto.product
 * @Date：2024/12/5 15:21
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@Data
public class ProductUpdateDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Date createdAt;
    private Date updatedAt;


}
