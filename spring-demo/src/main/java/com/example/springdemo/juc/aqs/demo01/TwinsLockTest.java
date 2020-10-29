package com.example.springdemo.juc.aqs.demo01;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TwinsLockTest {

    @Test
    public void test() {
        final Lock lock = new TwinsLock(); // 创建锁的实例

        // 写一个局部类，继承Thread
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) { // 无限循环获取锁，释放锁
                    lock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        // 创建10个线程，去争夺锁，达到测试结果
        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true); // 设置为守护线程
            worker.start();
        }

        // 该方法是每隔一秒进行一次换行，目的是打印结果好看，哈哈哈
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
