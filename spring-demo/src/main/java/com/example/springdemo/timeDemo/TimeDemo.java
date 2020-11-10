package com.example.springdemo.timeDemo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间处理类
 */
public class TimeDemo {
    public static void main(String[] args) throws InterruptedException {


        //第一种
        String time1 = "2020-11-09 19:00:00";
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(time1, dtf2);
        System.out.println(localDateTime.isBefore(LocalDateTime.now()));//你的时间在当前时间之前是true
        System.out.println(localDateTime.isAfter(LocalDateTime.now()));//在当前时间之后是false//第二种

        Date date = new Date();
        Thread.sleep(1000);
        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime localDateTime = LocalDateTime.parse(date, dtf2);
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        Duration duration = Duration.between(now, localDateTime);

        System.out.println(duration);
        long days = duration.toDays(); //相差的天数
        long hours = duration.toHours();//相差的小时数
        System.err.println(hours);
        long minutes = duration.toMinutes();//相差的分钟数
        long millis = duration.toMillis();//相差毫秒数
        long nanos = duration.toNanos();//相差的纳秒数
        System.out.println(millis);

    }
}
