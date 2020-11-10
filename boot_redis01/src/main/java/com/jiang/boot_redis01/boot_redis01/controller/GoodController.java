package com.jiang.boot_redis01.boot_redis01.controller;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GoodController {
    public static final String REDIS_LOCK_KEY = "lockhhf";


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private Redisson redisson;


//    @GetMapping("/buy_goods")
//    public String buy_Goods() {
//        synchronized (this) {
//            String result = stringRedisTemplate.opsForValue().get("goods:001");
//            int goodsNumber = result == null ? 0 : Integer.parseInt(result);
//
//            if (goodsNumber > 0) {
//                int realNumber = goodsNumber - 1;
//                stringRedisTemplate.opsForValue().set("goods:001", realNumber + "");
//                System.out.println("你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort);
//                return "你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort;
//            } else {
//                System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort);
//            }
//            return "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
//        }
//
//
//    }

//    @GetMapping("/buy_goods_setNx")
//    public String buy_goods_setNx() throws Exception {
//        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
//        try {
////            Boolean lockFlag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_KEY, value);
//            Boolean lockFlag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_KEY, value, 10L, TimeUnit.SECONDS);
//            if (!lockFlag) {
//                return "抢锁失败，~~~~~~~~~~~~~";
//            } else {
//                String result = stringRedisTemplate.opsForValue().get("goods:001");
//                int goodsNumber = result == null ? 0 : Integer.parseInt(result);
//                if (goodsNumber > 0) {
//                    int realNumber = goodsNumber - 1;
//                    stringRedisTemplate.opsForValue().set("goods:001", realNumber + "");
//                    System.out.println(Thread.currentThread().getName() + "\t你已经成功秒杀商品，此时还剩余：\t" + realNumber + "\t件\t" + "\t 服务器端口:\t " + serverPort);
//                    return "你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort;
//                } else {
//                    System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort);
//                }
//                return "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
//            }
//        } finally {
////            stringRedisTemplate.delete(REDIS_LOCK_KEY);//释放锁
////            if (value.equalsIgnoreCase(stringRedisTemplate.opsForValue().get(REDIS_LOCK_KEY))) {
////                stringRedisTemplate.delete(REDIS_LOCK_KEY);//释放锁
////            }
////            while (true)
////            {
////                stringRedisTemplate.watch(REDIS_LOCK_KEY); //加事务，乐观锁
////                if (value.equalsIgnoreCase(stringRedisTemplate.opsForValue().get(REDIS_LOCK_KEY))){
////                    stringRedisTemplate.setEnableTransactionSupport(true);
////                    stringRedisTemplate.multi();//开始事务
////                    stringRedisTemplate.delete(REDIS_LOCK_KEY);
////                    List<Object> list = stringRedisTemplate.exec();
////                    if (list == null) {  //如果等于null，就是没有删掉，删除失败，再回去while循环那再重新执行删除
////                        continue;
////                    }
////                }
////                //如果删除成功，释放监控器，并且breank跳出当前循环
////                stringRedisTemplate.unwatch();
////                break;
////            }
//
//            Jedis jedis = RedisUtils.getJedis();
//
//            String script = "if redis.call('get', KEYS[1]) == ARGV[1]" + "then " + "return redis.call('del', KEYS[1])" + "else " + "  return 0 " + "end";
//            try {
//                Object result = jedis.eval(script, Collections.singletonList(REDIS_LOCK_KEY), Collections.singletonList(value));
//                if ("1".equals(result.toString())) {
//                    System.out.println(Thread.currentThread().getName() + "\t------del REDIS_LOCK_KEY success");
//                } else {
//                    System.out.println(Thread.currentThread().getName() + "\t------del REDIS_LOCK_KEY error");
//                }
//            } finally {
//                if (null != jedis) {
//                    jedis.close();
//                }
//            }
//        }
//    }

    @GetMapping("/buy_goods_setNx")
    public String buy_goods_setNx() throws Exception {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        RLock rLock = redisson.getLock(REDIS_LOCK_KEY);
        rLock.lock();
        try {
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);
            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", realNumber + "");
                System.out.println(Thread.currentThread().getName() + "\t你已经成功秒杀商品，此时还剩余：\t" + realNumber + "\t件\t" + "\t 服务器端口:\t " + serverPort);
                return "你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort;
            } else {
                System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort);
            }
            return "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
        } finally {
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }

}