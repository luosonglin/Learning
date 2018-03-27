package com.love.lavender;

import com.love.lavender.domain.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by luosonglin on 26/03/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    /**
     * 这段极为简单的测试案例演示了如何通过自动配置的StringRedisTemplate对象进行Redis的读写操作，该对象从命名中就可注意到支持的是String类型。
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void Test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }


    /**
     * 如果有使用过spring-data-redis的开发者一定熟悉RedisTemplate<K, V>接口，StringRedisTemplate就相当于RedisTemplate<String, String>的实现。
     */
    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Test
    public void test() throws Exception {

        //保存对象
        User user = new User(1102, "崧麟");
        redisTemplate.opsForValue().set(user.getName(), user);

        user = new User(1101, "松林");
        redisTemplate.opsForValue().set(user.getName(), user);

        user = new User(1103, "songlin");
        redisTemplate.opsForValue().set(user.getName(), user);

        Assert.assertEquals(1102, redisTemplate.opsForValue().get("崧麟").getId());
        Assert.assertEquals(1101, redisTemplate.opsForValue().get("松林").getId());
        Assert.assertEquals(1103, redisTemplate.opsForValue().get("songlin").getId());

    }

}
