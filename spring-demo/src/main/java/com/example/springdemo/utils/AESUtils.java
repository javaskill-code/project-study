package com.example.springdemo.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

public class AESUtils {
    public static String decrypt(String password) {
        try {
            //1.生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();

            //2.转换KEY
            Key key = new SecretKeySpec(byteKey, "AES");

            //3.加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(password.getBytes());
            System.out.println("加密后：" + Hex.encodeHexString(result));

            //4.解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println("解密后：" + new String(result));
            return new String(result);
        } catch (Exception e) {
            return null;
        }
    }

}
