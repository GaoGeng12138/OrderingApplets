package com.gaog.orderingapplets.restaurant.aspect;

import com.gaog.orderingapplets.restaurant.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {
    
    @Pointcut("@annotation(com.gaog.orderingapplets.restaurant.annotation.SystemLog)")
    public void logPointcut() {}
    
    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        
        recordLog(point, time);
        return result;
    }
    
    private void recordLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SystemLog systemLog = method.getAnnotation(SystemLog.class);
        
        log.info("===================== 操作日志开始 =====================");
        log.info("操作描述: {}", systemLog.value());
        log.info("请求方法: {}", method.getName());
        log.info("请求参数: {}", Arrays.toString(point.getArgs()));
        log.info("执行时长: {} ms", time);
        log.info("===================== 操作日志结束 =====================");
    }
} 