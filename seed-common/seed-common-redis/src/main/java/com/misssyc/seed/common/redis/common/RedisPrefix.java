package com.misssyc.seed.common.redis.common;

/**
 * @author 33992
 * @since 2024/2/21
 **/
public class RedisPrefix {

    //请求计数，防止一段时间内重复请求数据库
    public static final String ATOMIC_COUNT = "atomic_count:";

    /**
     * 在请求KEY增加计数前缀
     */
    public static String buildAtomicCountKey(String key) {
        return ATOMIC_COUNT + key;
    }
}
