package com.gaog.orderingapplets.restaurant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaog.orderingapplets.restaurant.common.PageResult;
import com.gaog.orderingapplets.restaurant.constant.CacheConstant;
import com.gaog.orderingapplets.restaurant.dto.product.ProductCreateDTO;
import com.gaog.orderingapplets.restaurant.dto.product.ProductQueryDTO;
import com.gaog.orderingapplets.restaurant.dto.product.ProductUpdateDTO;
import com.gaog.orderingapplets.restaurant.entity.Product;
import com.gaog.orderingapplets.restaurant.enums.ResponseCode;
import com.gaog.orderingapplets.restaurant.exception.BusinessException;
import com.gaog.orderingapplets.restaurant.mapper.ProductMapper;
import com.gaog.orderingapplets.restaurant.service.ProductService;
import com.gaog.orderingapplets.restaurant.util.RedisUtil;
import com.gaog.orderingapplets.restaurant.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 功能描述: 功能描述: 产品服务实装
 *
 * @CLASSNAME： ProductServiceImpl
 * @VERSION: 1.0.0
 * @Date：2024/12/05
 * @Author： ZSJ
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 功能描述：
     *
     * @param queryDTO 查询 DTO
     * @return {@code PageResult<ProductVO> }
     * @Author： ZSJ
     */
    @Override
    public PageResult<ProductVO> page(ProductQueryDTO queryDTO) {
        try {
            Page<Product> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
            IPage<Product> resultPage = productMapper.selectPage(page, buildQueryWrapper(queryDTO));

            List<ProductVO> voList = resultPage.getRecords().stream()
                    .map(this::convertToVO)
                    .collect(Collectors.toList());

            return PageResult.success(new Page<ProductVO>()
                    .setRecords(voList)
                    .setTotal(resultPage.getTotal())
                    .setSize(resultPage.getSize())
                    .setCurrent(resultPage.getCurrent()));
        } catch (Exception e) {
            log.error("查询商品列表异常", e);
            return PageResult.fail("查询商品列表失败");
        }
    }

    /**
     * 功能描述： 获取详细信息
     *
     * @param id 身份证
     * @return {@code ProductVO }
     * @Author： ZSJ
     */
    @Override
    public ProductVO getDetail(Long id) {
        // 先从缓存获取
        String key = CacheConstant.PRODUCT_CACHE_KEY + id;
        Product product = (Product) redisUtil.get(key);

        if (product == null) {
            product = productMapper.selectById(id);
            if (product == null) {
                throw new BusinessException(ResponseCode.ERROR_NOT_EXIST_PRODUCT);
            }
            // 放入缓存
            redisUtil.set(key, product, 1, TimeUnit.HOURS);
        }

        return convertToVO(product);
    }

    /**
     * 功能描述： 加
     *
     * @param createDTO
     * @Author： ZSJ
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(ProductCreateDTO createDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(createDTO, product);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        int insert = productMapper.insert(product);
        if (insert == 0) {
            throw new BusinessException(ResponseCode.ERROR_ADD_PRODUCT);
        }
        return insert;
    }

    /**
     * 功能描述： 更新
     *
     * @param updateDTO 更新 DTO
     * @Author： ZSJ
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProductUpdateDTO updateDTO) {
        Product product = productMapper.selectById(updateDTO.getId());
        if (product == null) {
            throw new BusinessException(ResponseCode.ERROR_NOT_EXIST_PRODUCT);
        }

        BeanUtils.copyProperties(updateDTO, product);
        productMapper.updateById(product);

        // 清除缓存
        redisUtil.delete(CacheConstant.PRODUCT_CACHE_KEY + product.getId());
    }

    /**
     * 功能描述： 删除
     *
     * @param id 身份证
     * @Author： ZSJ
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        productMapper.deleteById(id);
        redisUtil.delete(CacheConstant.PRODUCT_CACHE_KEY + id);
    }

    /**
     * 功能描述： 更新库存
     *
     * @param id    身份证
     * @param stock
     * @Author： ZSJ
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStock(Long id, Integer stock) {
        int rows = productMapper.updateStock(id, stock);
        if (rows == 0) {
            throw new BusinessException(ResponseCode.ERROR_INSUFFICIENT_PRODUCT);
        }
        redisUtil.delete(CacheConstant.PRODUCT_CACHE_KEY + id);
    }

    /**
     * 功能描述： 构建查询包装器
     *
     * @param queryDTO 查询 DTO
     * @return {@code LambdaQueryWrapper<Product> }
     * @Author： ZSJ
     */
    private LambdaQueryWrapper<Product> buildQueryWrapper(ProductQueryDTO queryDTO) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(queryDTO.getKeyword())) {
            wrapper.like(Product::getName, queryDTO.getKeyword())
                    .or()
                    .like(Product::getDescription, queryDTO.getKeyword());
        }
        if (queryDTO.getMinPrice() != null) {
            wrapper.ge(Product::getPrice, queryDTO.getMinPrice());
        }
        if (queryDTO.getMaxPrice() != null) {
            wrapper.le(Product::getPrice, queryDTO.getMaxPrice());
        }
        if (queryDTO.getInStock() != null && queryDTO.getInStock()) {
            wrapper.gt(Product::getStock, 0);
        }
        return wrapper;
    }

    /**
     * 功能描述：
     *
     * @param product
     * @return {@code ProductVO }
     * @Author： ZSJ
     */
    private ProductVO convertToVO(Product product) {
        ProductVO vo = new ProductVO();
        BeanUtils.copyProperties(product, vo);
        return vo;
    }
} 