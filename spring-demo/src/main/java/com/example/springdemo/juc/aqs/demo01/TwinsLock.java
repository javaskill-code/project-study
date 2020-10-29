package com.example.springdemo.juc.aqs.demo01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinsLock implements Lock {

    private final Sync sync = new Sync(2); // 设置最多几个线程可以获取同步状态

    // 内部类 ，重写同步器中的共享相关的方法，(通俗的说AQS就是写锁的规则)
    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero");
            }
            setState(count); // 设置状态
        }

        // 尝试获取同步状态，state减
        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState(); // 获取当前AQS记录的状态
                int newCount = current - reduceCount; // 得到新的状态
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                    // 返回值大于等于0表示获取成功，否则同步状态获取失败
                }
            }
        }

        // 尝试释放同步状态，因为可能存在2个线程同时释放同步状态，
        // 为了保证安全，所以需要CAS保证原子性
        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true; // 返回true表示，同步释放成功
                }
            }
        }
    }

    @Override
    public void lock() {
        /*状态加1，sync.acquireShared(1);这个方法是AQS里的方法，
         * 该方法会调用我们重写的tryAcquireShared(arg)方法，尝试获取锁，
         * 如果返回值小于0，则表示获取不成功，那么就会调用doAcquireShared(arg)
         * 方法，将该线程加入到队列中，以ACS方法加入队列，会无限循环直至加入队列成功*/
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        /*状态减1，sync.releaseShared(1);会调用我们重写的tryReleaseShared(arg)
         * 方法，进行释放锁操作，直到释放锁成功，释放成功返回true，
         * 会调用doReleaseShared()方法唤醒后继节点*/
        sync.releaseShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        System.out.println("已中断");
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
