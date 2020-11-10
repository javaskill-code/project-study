package com.example.springdemo.utils;

public class ByteUtils {

    /**
     * 从一个byte[]数组中截取一部分
     *
     * @param src
     * @param begin
     * @param count
     * @return
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bytes = new byte[count];
        for (int i = begin; i < begin + count; i++) bytes[i - begin] = src[i];
        return bytes;
    }

    /**
     * byte 转 int类型
     *
     * @param from
     * @return
     */
    public static int binToInt(byte[] from) {
        int to = 0;
        int min = 0;
        int len = from.length;
        to = 0;
        for (int i_move = len - 1, i_from = min; i_move >= 0; i_move--, i_from++) {
            to = to << 8 | (from[i_from] & 0xff);
        }
        return to;

    }

}
