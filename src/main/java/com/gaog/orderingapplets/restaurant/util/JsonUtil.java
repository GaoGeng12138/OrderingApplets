package com.gaog.orderingapplets.restaurant.util;

import ch.qos.logback.core.rolling.helper.RenameUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.gaog.orderingapplets.restaurant.util
 * @Date：2024/12/31 17:35
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@Slf4j
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换为 JSON 字符串
     *
     * @param obj 要转换的对象
     * @return JSON 字符串
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("对象转换为 JSON 字符串失败:{}", e.getMessage());
            return Collections.emptyList().toString();
        }
    }

    /**
     * 将方法参数转换为 JSON 字符串
     *
     * @param args 方法参数
     * @return JSON 字符串
     */
    public static String argsToJson(Object[] args) {
        return toJson(args);
    }

    /**
     * 根据给定的键从对象中提取值并转换为指定类型
     *
     * @param obj  要提取值的对象
     * @param key  要提取的键
     * @param type 目标类型
     * @param <T>  类型参数
     * @return 转换后的值
     */
    public static <T> T getValueByKey(Object obj, String key, Class<T> type) {
        try {
            JsonNode jsonNode = objectMapper.valueToTree(obj);

            // 检查是否为数组
            if (jsonNode.isArray()) {
                for (JsonNode arrayElement : jsonNode) {
                    JsonNode valueNode = arrayElement.get(key);
                    if (valueNode != null) {
                        return objectMapper.treeToValue(valueNode, type);
                    }
                }
            } else {
                // 处理非数组情况
                JsonNode valueNode = jsonNode.get(key);
                if (valueNode != null) {
                    return objectMapper.treeToValue(valueNode, type);
                }
            }

            log.warn("未找到键: {}", key);
        } catch (JsonProcessingException e) {
            log.error("根据给定的键从对象中提取值并转换为指定类型失败:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 根据给定的键从对象中提取值并转换为字符串类型
     *
     * @param obj
     * @param key
     * @return
     */
    public static String getString(Object obj, String key) {
        return getValueByKey(obj, key, String.class);
    }

    public static void main(String[] args) {
        RenameUtil renameUtil = new RenameUtil();

    }
}
