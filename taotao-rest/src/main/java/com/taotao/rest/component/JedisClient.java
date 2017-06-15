package com.taotao.rest.component;

/**
 * <p>Title:com.taotao.rest.component</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/10.
 */
public interface JedisClient {
    String set(String key, String value);

    String get(String key);

    Long hset(String key, String item, String value);

    String hget(String key, String item);

    Long incr(String key);

    Long decr(String key);

    Long expire(String key,int second);

    Long ttl(String key);

    Long hdel(String key, String item);
}
