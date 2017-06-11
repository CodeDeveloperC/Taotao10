package com.taotao.rest.component.impl;

import com.taotao.rest.component.JetisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <p>Title:com.taotao.rest.component.impl</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/10.
 */
public class JetisClientSingle implements JetisClient {
    @Autowired
    private JedisPool jedisPool;


    @Override
    public String set(String key, String value) {
        Jedis resource = jedisPool.getResource();
        String result = resource.set(key, value);
        resource.close();

        return result;
    }

    @Override
    public String get(String key) {
        Jedis resource = jedisPool.getResource();
        String result = resource.get(key);
        resource.close();
        return result;
    }

    @Override
    public Long hset(String key, String item, String value) {
        Jedis resource = jedisPool.getResource();
        Long result = resource.hset(key, item, value);
        resource.close();
        return result;
    }

    @Override
    public String hget(String key, String item) {
        Jedis resource = jedisPool.getResource();
        String result = resource.hget(key, item);
        resource.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis resource = jedisPool.getResource();
        Long result = resource.incr(key);
        resource.close();
        return result;
    }

    @Override
    public Long decr(String key) {
        Jedis resource = jedisPool.getResource();
        Long result = resource.decr(key);
        resource.close();
        return result;
    }

    @Override
    public Long expire(String key, int second) {
        Jedis resource = jedisPool.getResource();
        Long result = resource.expire(key,second);
        resource.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis resource = jedisPool.getResource();
        Long result = resource.ttl(key);
        resource.close();
        return result;
    }

    @Override
    public Long hdel(String key, String item) {
        Jedis resource = jedisPool.getResource();
        Long result = resource.hdel(key,item);
        resource.close();
        return result;
    }
}
