package com.gaog.orderingapplets.restaurant.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.Collections;
import java.util.List;


/**
 * 功能描述: 页面结果
 *
 * @CLASSNAME： PageResult
 * @VERSION: 1.0.0
 * @Date：2024/12/05
 * @Author： ZSJ
 */
@Data
public class PageResult<T> {

 /**
     * 数据列表
     */
    private List<T> records;
    
    /**
     * 总记录数
     */
    private long total;
    
    /**
     * 每页记录数
     */
    private long size;
    
    /**
     * 当前页码
     */
    private long current;
    
    /**
     * 总页数
     */
    private long pages;
    
    /**
     * 是否成功
     */
    private boolean success;
    
    /**
     * 错误信息
     */
    private String message;
    
    /**
     * 错误码
     */
    private String code;

    /**
     * 构建成功分页结果
     */
    public static <T> PageResult<T> success(IPage<T> page) {
        PageResult<T> result = new PageResult<>();
        result.setRecords(page.getRecords());
        result.setTotal(page.getTotal());
        result.setSize(page.getSize());
        result.setCurrent(page.getCurrent());
        result.setPages(page.getPages());
        result.setSuccess(true);
        return result;
    }

    /**
     * 构建成功分页结果（手动传入参数）
     */
    public static <T> PageResult<T> success(List<T> records, long total, long size, long current) {
        PageResult<T> result = new PageResult<>();
        result.setRecords(records);
        result.setTotal(total);
        result.setSize(size);
        result.setCurrent(current);
        result.setPages(total % size == 0 ? total / size : total / size + 1);
        result.setSuccess(true);
        return result;
    }

    /**
     * 构建空分页结果
     */
    public static <T> PageResult<T> empty() {
        PageResult<T> result = new PageResult<>();
        result.setRecords(Collections.emptyList());
        result.setTotal(0);
        result.setSize(0);
        result.setCurrent(1);
        result.setPages(0);
        result.setSuccess(true);
        return result;
    }

    /**
     * 构建失败结果
     */
    public static <T> PageResult<T> fail(String code, String message) {
        PageResult<T> result = new PageResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        result.setRecords(Collections.emptyList());
        result.setTotal(0);
        result.setSize(0);
        result.setCurrent(1);
        result.setPages(0);
        return result;
    }

    /**
     * 构建失败结果（只传入错误信息）
     */
    public static <T> PageResult<T> fail(String message) {
        return fail("500", message);
    }

} 