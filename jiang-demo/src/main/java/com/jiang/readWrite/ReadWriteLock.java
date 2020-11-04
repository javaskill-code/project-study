package com.jiang.readWrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    //创建一个集合
    static Map<String, String> map = new HashMap<String, String>();
    //创建一个读写锁
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    //获取读锁
    static Lock readLock = lock.readLock();
    //获取写锁
    static Lock writeLock = lock.writeLock();

    //写操作
    public Object put(String key, String value) {
        writeLock.lock();
        try {
            System.out.println("Write正在执行写操作~");
            Thread.sleep(100);
            String put = map.put(key, value);
            System.out.println("Write写操作执行完毕~");
            return put;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
        return null;

    }

    //写操作
    public Object get(String key) {
        readLock.lock();
        try {
            System.out.println("Read正在执行读操作~");
            Thread.sleep(100);
            String value = map.get(key);
            System.out.println("Read读操作执行完毕~");
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return null;

    }

    public static void main(String[] args) {
        ReadWriteLock lock = new ReadWriteLock();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    //写操作
                    lock.put(finalI + "", "value" + finalI);
                    //读操作
                    System.out.println(lock.get(finalI + ""));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
