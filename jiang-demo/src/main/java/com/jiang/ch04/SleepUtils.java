package com.jiang.ch04;

import java.util.concurrent.TimeUnit;

public class SleepUtils {
    public static final void second(long second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
