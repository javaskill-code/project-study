package com.jiang.boot_redis01.boot_redis01;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTestDemo {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void add() {
        System.err.println("=====1111111111111===========");
        stringRedisTemplate.opsForValue().set("goods:001", "100");
        String result = stringRedisTemplate.opsForValue().get("goods:001");
        System.err.println("=====2222222222222===========\t" + result);
    }
}
