package com.example.springdemo.monitor;

public class TestSync implements Runnable {

    private int count = 100;

    public static void main(String[] args) {
        TestSync ts = new TestSync();
        Thread t1 = new Thread(ts, "线程1");
        Thread t2 = new Thread(ts, "线程2");
        Thread t3 = new Thread(ts, "线程3");

        t1.start();
        t2.start();
        t3.start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (count > 0) {
                    count--;
                    System.out.println(Thread.currentThread().getName() + " count = " + count);
                } else {
                    break;
                }
            }
        }
    }
}