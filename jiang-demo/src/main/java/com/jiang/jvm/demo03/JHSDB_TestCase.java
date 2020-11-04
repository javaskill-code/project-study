package com.jiang.jvm.demo03;


public class JHSDB_TestCase {

    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.err.println("=====DONE========");
        }
    }

    private static class ObjectHolder {

    }

    /**
     *  -Xmx10m -XX:UseSerialGC -XX:-useCompressedOops
     * @param args
     */
    public static void main(String[] args) {
        Test test = new Test();
        test.foo();
    }
}
