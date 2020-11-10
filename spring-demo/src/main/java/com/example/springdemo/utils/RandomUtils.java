package com.example.springdemo.utils;

import com.nsec.exception.CPKException;
import com.nsec.software.CPKSoftwareApi;

import java.util.Random;

public class RandomUtils {
    /**
     * 生成lenght位的密钥
     *
     * @param lenght 可变长度的密钥
     * @param ma    是否转换大小写，true大写，false小写
     * @return
     */
    static Random random = new Random();

    public static String getKey(int lenght, boolean... ma) {
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //由Random生成随机数
        StringBuffer sb = new StringBuffer();
        //长度为几就循环几次
        for (int i = 0; i < lenght; ++i) {
            //产生0-61的数字
            int number = random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return ma.length != 0 ? ma[0] ? sb.toString().toUpperCase() : sb.toString().toLowerCase() : sb.toString();
    }

    public static void main(String[] args) throws CPKException {
//        System.out.println(getKey(64));
//        System.out.println(getKey(64, true));
//        System.out.println(getKey(64, false));
        String key = getKey(64);
        String key1 = getKey(64);
        byte[] bytes = key.getBytes();
        byte[] bytes1 = key1.getBytes();
        System.err.println(key);
        System.err.println(key1);
        byte[] bytes2 = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes2[i] = (byte) (bytes[i] ^ bytes1[i]);
            System.out.print(bytes2[i]);
        }
        String s = new String(bytes2);
        System.err.println("\t\t解码11\t\t\t" + s+"\t\t长度1111\t:"+s.length());
        CPKSoftwareApi api = CPKSoftwareApi.getInstance();

        String s1 = api.base64Encode(bytes2);
        System.err.println("\t\t解码\t\t\t" + s1+"\t\t长度\t:"+s1.length());

        byte[] bytes3 = api.base64Decode(s1);
        String s233 = new String(bytes3);
        System.err.println("\t\t解码11\t\t\t" + s233+"\t\t长度1111\t:"+s233.length());
    }
}
