package com.jiang.jvm.demo03;

public class MinorGcDemo {

    private static final int _1MB = 1024 * 1024;


    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }

    /**
     * vm参数 -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGC -XX:+PrintGCDetails  -XX:SurvivorRatio=8
     */
    private static void testAllocation() {
        byte[] all01, all02, all03, all04;
        all01 = new byte[2 * _1MB];
        all02 = new byte[2 * _1MB];
        all03 = new byte[2 * _1MB];
        all04 = new byte[4 * _1MB];
    }

    /**
     * vm参数 -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGC -XX:+PrintGCDetails  -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     */
    private static void testPretenureSizeThreshold() {
        byte[] all01;
        all01 = new byte[6 * _1MB];
    }
}
