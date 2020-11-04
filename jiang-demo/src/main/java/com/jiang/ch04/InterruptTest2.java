package com.jiang.ch04;

import java.util.concurrent.TimeUnit;

public class InterruptTest2 {
    public static void main(String[] args) throws Exception {
        /**
         * SleeperRunner 不停的睡眠
         */

        Thread sleeperRunner = new Thread(new SleeperRunner(), "SleeperRunner");
        sleeperRunner.setDaemon(true);
        /**
         * BusyRunner 不停的运行
         */

        Thread busyRunner = new Thread(new BusyRunner(), "SleeperRunner");
        busyRunner.setDaemon(true);
        sleeperRunner.start();
        busyRunner.start();


        // 休眠5秒
        TimeUnit.SECONDS.sleep(5);
        sleeperRunner.interrupt();
        busyRunner.interrupt();


        System.err.println("\t\tsleeperRunner interrupt is \t\t" + sleeperRunner.isInterrupted());
        System.err.println("\t\tbusyRunner interrupt is \t\t" + busyRunner.isInterrupted());
        // 防止两个线程立刻退出
        SleepUtils.second(10);
    }

    private static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }

    static class SleeperRunner implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
