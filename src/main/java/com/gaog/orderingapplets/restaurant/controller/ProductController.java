package com.gaog.orderingapplets.restaurant.controller;

import com.gaog.orderingapplets.restaurant.common.PageResult;
import com.gaog.orderingapplets.restaurant.common.Result;
import com.gaog.orderingapplets.restaurant.dto.product.ProductCreateDTO;
import com.gaog.orderingapplets.restaurant.dto.product.ProductQueryDTO;
import com.gaog.orderingapplets.restaurant.service.ProductService;
import com.gaog.orderingapplets.restaurant.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 产品控制器
 *
 * @CLASSNAME： ProductController
 * @VERSION: 1.0.0
 * @Date：2024/12/06
 * @Author： ZSJ
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public PageResult<ProductVO> list(@RequestBody ProductQueryDTO queryDTO) {
        return productService.page(queryDTO);
    }

    @GetMapping("/{id}")
    public Result<ProductVO> getById(@PathVariable Long id) {
        return Result.success(productService.getDetail(id));
    }

    @PostMapping
    public Result<Integer> add(@RequestBody ProductCreateDTO product) {
        return Result.success(productService.add(product));
    }
} 