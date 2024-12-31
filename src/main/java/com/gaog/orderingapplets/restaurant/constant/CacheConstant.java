package com.gaog.orderingapplets.restaurant.constant;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.restaurant.constant
 * @Date：2024/12/5 16:45
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
public class CacheConstant {


    public static final String CACHE_NAME_PREFIX = "orderingapplets.cache";

    /**
     * 用户缓存key
     */
    public static final String USER_CACHE_KEY = CACHE_NAME_PREFIX + "user:";

    /**
     * 商品缓存key
     */
    public static final String PRODUCT_CACHE_KEY = CACHE_NAME_PREFIX + "product:";

    /**
     * 订单缓存key
     */
    public static final String ORDER_CACHE_KEY = CACHE_NAME_PREFIX + "order:";

    public static final String ORDER_DETAIL_CACHE_KEY = CACHE_NAME_PREFIX + "order_detail:";

    public static final String ORDER_LIST_CACHE_KEY = CACHE_NAME_PREFIX + "order_list:";


}
