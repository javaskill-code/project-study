package com.example.springdemo.monitor;

import org.openjdk.jol.info.ClassLayout;

public class DemoMonitor {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }


}
