package com.gaog.orderingapplets.restaurant.util;


import com.gaog.orderingapplets.restaurant.constant.CacheConstant;
import com.gaog.orderingapplets.restaurant.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: jwt util
 *
 * @CLASSNAME： JwtUtil
 * @VERSION: 1.0.0
 * @Date：2024/12/06
 * @Author： ZSJ
 */
@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private Key key;

    private final RedisUtil redisUtil;

    public JwtUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 功能描述： 生成令牌
     *
     * @param user 用户
     * @return {@code String }
     * @Author： ZSJ
     */
    public String generateToken(UserVO user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration * 1000);


        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();

        // 将 token 存储到 Redis 中，设置过期时间
        redisUtil.set(CacheConstant.USER_TOKEN_CACHE_KEY + user.getUsername(), token, expiration, TimeUnit.SECONDS);

        // 将用户信息存储到 Redis 中，设置过期时间
        storeUserInfoInCache(token, user, expiration);

        return token;
    }

    /**
     * 功能描述： 从 Token 获取用户名
     *
     * @param token 令 牌
     * @return {@code String }
     * @Author： ZSJ
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * 功能描述： 验证令牌
     *
     * @param token 令 牌
     * @return boolean
     * @Author： ZSJ
     */
    public boolean validateToken(String token) {
        try {
            // 检查 用户缓存 是否在 Redis 中存在
            if (redisUtil.get(CacheConstant.USER_CACHE_KEY + token) == null) {
                // Token 不存在
                return false;
            }
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 功能描述： 使令牌失效
     *
     * @param token 令 牌
     * @Author： ZSJ
     */
    public void invalidateToken(String token) {
        // 从 Redis 中删除 token
        redisUtil.delete(CacheConstant.USER_CACHE_KEY + token);
    }

    /**
     * 功能描述： 存储用户信息
     *
     * @param token
     * @param userInfo
     */
    public void storeUserInfoInCache(String token, UserVO userInfo, long timeout) {
        // 假设 redisUtil 有 set 方法
        redisUtil.set(CacheConstant.USER_CACHE_KEY + token, userInfo, timeout, TimeUnit.SECONDS);
    }
} 