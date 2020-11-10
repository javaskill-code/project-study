package com.example.springdemo;

public class Demo {

    public static boolean foo(char a) {
        System.out.print(a);
        return true;

    }

    /**
     * for循环的一些疑问
     *
     * @param args
     */
//abdbcdcb
    public static void main(String[] args) {
        int i = 0;
        for (foo('a'); foo('b') && (i < 2); foo('c')) {
            i++;
            foo('d');
        }
    }
}
