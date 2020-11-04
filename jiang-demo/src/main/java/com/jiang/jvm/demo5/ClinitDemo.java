package com.jiang.jvm.demo5;




public class ClinitDemo {

    static class Parent {
        public static int A = 1;

        static {
            A = 2;
        }
    }

    static class Sub extends Parent {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.err.println(Sub.B);
    }
}
