package com.jiang.jvm.demo03;

import java.util.ArrayList;

public class MemoryMinotorDemo {

    static class OOMObject {
        private byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        ArrayList<OOMObject> oomObjects = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延迟 让监视曲线的变化更加明显
            Thread.sleep(500);
            oomObjects.add(new OOMObject());
        }

    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            fillHeap(1000);
            System.gc();
        }

    }
}
