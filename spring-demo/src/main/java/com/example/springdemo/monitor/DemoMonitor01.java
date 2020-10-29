package com.example.springdemo.monitor;

public class DemoMonitor01 {
    public static void main(String[] args) {
        DemoMonitor01 demoMonitor01 = new DemoMonitor01();
        demoMonitor01.test001();
    }

    public synchronized void test001() {
        System.err.println("==========0011===========");
    }
}
