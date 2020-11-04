package com.jiang.list;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<String, String>();

        concurrentHashMap.put("aaa", "lizhenjiang");
        concurrentHashMap.put("bb", "bbbbbbb");
        concurrentHashMap.put("abbaa", "abbaaabbaaabbaa");
        concurrentHashMap.put("ccc", "cccccccccccc");
        concurrentHashMap.put("ddfdsd", "ddfdsdddfdsdddfdsd");

        concurrentHashMap.forEach((k, v) -> {
            System.err.println(k + "\t\t\t" + v);
        });
    }
}
