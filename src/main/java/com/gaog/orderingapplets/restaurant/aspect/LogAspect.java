package com.gaog.orderingapplets.restaurant.aspect;

import com.alibaba.fastjson.JSON;
import com.gaog.orderingapplets.restaurant.annotation.SystemLog;
import com.gaog.orderingapplets.restaurant.entity.SystemLogs;
import com.gaog.orderingapplets.restaurant.mapper.SystemLogMapper;
import com.gaog.orderingapplets.restaurant.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 功能描述: 对数方面
 *
 * @CLASSNAME： LogAspect
 * @VERSION: 1.0.0
 * @Date：2024/12/17
 * @Author： ZSJ
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Pointcut("@annotation(com.gaog.orderingapplets.restaurant.annotation.SystemLog)")
    public void logPointcut() {
    }

    /**
     * 功能描述： 周围
     *
     * @param point 点
     * @return {@code Object }
     * @throws Throwable 可投掷
     * @Author： ZSJ
     */
    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;

        recordLog(point, time);
        return result;
    }

    /**
     * 功能描述： 记录日志
     *
     * @param point 点
     * @param time  时间
     * @Author： ZSJ
     */
    private void recordLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SystemLog systemLog = method.getAnnotation(SystemLog.class);

        // 获取当前用户的身份信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("当前用户: {}", JSON.toJSONString(authentication));
        String operatorEmail = "";
        String operatorName = "";
        if (authentication != null && authentication.isAuthenticated()) {
            // 这里可以根据你的 UserDetails 实现获取邮箱
            if (method.getName().contains("login") || method.getName().contains("register")) {
                operatorName = JsonUtil.getString(point.getArgs(), "username");
            } else {
                operatorEmail = authentication.getName();
            }

        }

        // 创建日志实体
        SystemLogs logEntry = new SystemLogs();
        logEntry.setOperationDescription(systemLog.value());
        logEntry.setMethodName(method.getName());
        logEntry.setRequestParams(Arrays.toString(point.getArgs()));
        logEntry.setExecutionTime(time);
        logEntry.setOperatorName(operatorName);
        logEntry.setOperatorEmail(operatorEmail);

        // 插入日志到数据库
        systemLogMapper.insert(logEntry);

        log.info("===================== 操作日志开始 =====================");
        log.info("操作描述: {}", systemLog.value());
        log.info("请求方法: {}", method.getName());
        log.info("请求参数: {}", Arrays.toString(point.getArgs()));
        log.info("执行时长: {} ms", time);
        log.info("===================== 操作日志结束 =====================");
    }
} 