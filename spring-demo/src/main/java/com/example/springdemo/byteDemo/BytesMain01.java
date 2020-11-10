package com.example.springdemo.byteDemo;

public class BytesMain01 {
    public static void main(String[] args) {
        byte bytes = -84;
        int result = bytes & 0xff;
        System.out.println("无符bai号du数: \t" + result);
        System.out.println("2进制zhibit位dao: \t" + Integer.toBinaryString(result));
        byte bytes1 = 76;
        int result1 = bytes1 & 0xff;
        System.out.println("无符bai号du数: \t" + result1);
        System.out.println("2进制zhibit位dao: \t" + Integer.toBinaryString(bytes1));
    }
}
