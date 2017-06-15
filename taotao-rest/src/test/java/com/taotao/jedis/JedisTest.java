package com.taotao.jedis;

import com.taotao.rest.component.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

/**
 * <p>Title:com.taotao.jedis</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/10.
 */
public class JedisTest {
    @Test
    public void testJedisSingle() throws Exception{
        //创建一个jedis对象
        Jedis jedis = new Jedis("192.168.59.128", 6379);
        jedis.set("test", "hello jedis");
        String test = jedis.get("test");
        System.out.println(test);
        jedis.close();
    }

    @Test
    public void testJediPool() throws Exception{
        JedisPool jedisPool = new JedisPool("192.168.59.128", 6379);
        Jedis jedis = jedisPool.getResource();
        String test = jedis.get("test");
        System.out.println(test);

        //每次jedis用完必须关闭
        jedis.close();

        //系统关闭时关闭连接池
        jedisPool.close();
    }

    @Test
    public void testJedisCluster() throws Exception{
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.59.128", 7001));
        nodes.add(new HostAndPort("192.168.59.128", 7002));
        nodes.add(new HostAndPort("192.168.59.128", 7003));
        nodes.add(new HostAndPort("192.168.59.128", 7004));
        nodes.add(new HostAndPort("192.168.59.128", 7005));
        nodes.add(new HostAndPort("192.168.59.128", 7006));

        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("name", "zhangshan");
        jedisCluster.set("value", "1000");

        String name = jedisCluster.get("name");
        String value = jedisCluster.get("value");
        System.out.println(name);
        System.out.println(value);

        //系统关闭时关闭 jedisCluster
        jedisCluster.close();
    }

    @Test
    public void testJedisClientSpring() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);

        jedisClient.set("client1", "1000");
        String client1 = jedisClient.get("client1");
        System.out.println(client1);
    }
}
