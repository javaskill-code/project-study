package com.jiang.ch04;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {

    public static void main(String[] args) {
        /**
         *  获取java线程管理器ThreadMXBean
         */
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        // 不需要获取 monitor 和 synchronizer信息 进获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        //遍历线程信息 打印线程id和线程名称
        for (ThreadInfo threadInfo : threadInfos) {
            System.err.println("[\t" + threadInfo.getThreadId() + "\t\t]" + threadInfo.getThreadName());

        }

    }
}
