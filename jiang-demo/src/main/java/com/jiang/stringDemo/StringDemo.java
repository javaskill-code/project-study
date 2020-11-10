package com.jiang.stringDemo;

public class StringDemo {
    public static void main(String[] args) {
//        int n = 12;
//        int j = (n - 1) >> 1;
//        System.err.println(j);
//        StringBuilder stringBuilder = new StringBuilder("lizhenjiang");
//        System.err.println(stringBuilder.reverse());
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        operator(a, b);
        System.err.println(a + "\t\t" + b);
    }

    public static void operator(StringBuffer a, StringBuffer b) {
        a.append(b);
        b = a;
        b.append(b);
//        System.err.println(a + "\t11111\t" + b);
    }
}
