package com.gaog.orderingapplets.restaurant.service;

import com.gaog.orderingapplets.restaurant.common.PageResult;
import com.gaog.orderingapplets.restaurant.dto.product.ProductCreateDTO;
import com.gaog.orderingapplets.restaurant.dto.product.ProductQueryDTO;
import com.gaog.orderingapplets.restaurant.dto.product.ProductUpdateDTO;
import com.gaog.orderingapplets.restaurant.vo.ProductVO;

/**
 * 功能描述: 产品服务
 *
 * @CLASSNAME： ProductService
 * @VERSION: 1.0.0
 * @Date：2024/12/05
 * @Author： ZSJ
 */
public interface ProductService {
    /**
     * 功能描述： 分页查询商品
     *
     * @param queryDTO 查询 DTO
     * @return {@code PageResult<ProductVO> }
     * @Author： ZSJ
     */
    PageResult<ProductVO> page(ProductQueryDTO queryDTO);

    /**
     * 功能描述： 获取商品详情
     *
     * @param id 身份证
     * @return {@code ProductVO }
     * @Author： ZSJ
     */
    ProductVO getDetail(Long id);

    /**
     * 功能描述： 新增商品
     *
     * @param createDTO 创建 DTO
     * @Author： ZSJ
     */
    Integer add(ProductCreateDTO createDTO);

    /**
     * 功能描述： 更新商品
     *
     * @param updateDTO 更新 DTO
     * @Author： ZSJ
     */
    void update(ProductUpdateDTO updateDTO);

    /**
     * 功能描述： 删除商品
     *
     * @param id 身份证
     * @Author： ZSJ
     */
    void delete(Long id);

    /**
     * 功能描述： 更新商品库存
     *
     * @param id    身份证
     * @param stock 股票
     * @Author： ZSJ
     */
    void updateStock(Long id, Integer stock);
} 