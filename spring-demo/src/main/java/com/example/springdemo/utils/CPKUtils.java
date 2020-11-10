package com.example.springdemo.utils;

import com.bc.config.VkdConfig;
import com.cpk.jni.CPKSoft;
import com.nsec.exception.CPKException;
import com.nsec.hardware.CPKVKey;
import com.nsec.hardware.Identification;
import com.nsec.hardware.UserType;
import com.nsec.software.CPKSoftwareApi;
import com.nsec.software.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CPKUtils {
    private static final Logger LOG = LoggerFactory.getLogger(CPKUtils.class);
    //普通用户口令
    public static final String NORMAL_USER_PIN = "11111111";
    //超级用户口令
    public static final byte[] SUPER_USER_PIN = new byte[]{(byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80,
            (byte) 0x80, (byte) 0x80, (byte) 0x80};


    private static CPKSoftwareApi api = CPKSoftwareApi.getInstance();

    private static CPKVKey cpkdev;

    /**
     * 获取 CPKVKey 对象
     *
     * @param vkdPath
     * @return
     */
    public static CPKVKey getInstance(String vkdPath) {
        if (cpkdev == null) {
            //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
            synchronized (CPKVKey.class) {
                if (cpkdev == null) {
                    try {
                        cpkdev = new CPKVKey(vkdPath);
                        LOG.info("========虚拟Key成功=======");
                        cpkdev.verifyPin(UserType.NORMAL_USER, NORMAL_USER_PIN);
                        LOG.info("验证用户口令成功");
                    } catch (CPKException e) {
                        LOG.info(e.getMessage() + ":" + e.getCode());
                        return null;
                    }
                }
            }
        }
        return cpkdev;
    }

    /**
     * 获取 CPKVKey 对象
     *
     * @param vkdPath
     * @return
     */
    public static CPKVKey getInstanceWhitSuperUser(String vkdPath) {
        if (cpkdev == null) {
            //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
            synchronized (CPKVKey.class) {
                if (cpkdev == null) {
                    try {
                        cpkdev = new CPKVKey(vkdPath);
                        LOG.info("========虚拟Key成功=======");
                        cpkdev.verifyPin(UserType.NORMAL_USER, NORMAL_USER_PIN);
                        LOG.info("验证用户口令成功");
                        cpkdev.verifyPin(UserType.SUPER_USER, SUPER_USER_PIN);
                        LOG.info("======================普通用户验证用户超级口令验证成功===================");
                    } catch (CPKException e) {
                        LOG.info(e.getMessage() + ":" + e.getCode());
                        return null;
                    }
                }
            }
        }
        return cpkdev;
    }

    /**
     * 脱密
     *
     * @param cpkdev
     * @param ciphertext
     * @return
     */
    public static byte[] decrypt(CPKVKey cpkdev, byte[] ciphertext) {
        byte[] cleartext = null;
        try {
            cleartext = cpkdev.decrypt(Identification.REAL_NAME, ciphertext);
            LOG.info("解密成功，明文是：" + Tool.Bytes2String(cleartext));
        } catch (CPKException e) {
            LOG.info(e.getMessage() + ":" + e.getCode());
        }
        return cleartext;
    }

    /**
     * 获取数字信封
     *
     * @param cpkdev
     * @param addresseeId
     * @return
     */
    public static byte[] applyKey(CPKVKey cpkdev, String addresseeId) {
        byte[] applyKey = null;
        try {
            applyKey = cpkdev.applyKey(VkdConfig.pubKm, addresseeId);
            LOG.info("======数字信封获取成功==========");
        } catch (CPKException e) {
            LOG.info(e.getMessage() + ":" + e.getCode());
        }
        return applyKey;
    }

    /**
     * 根据 数字信封生成私钥
     *
     * @param cpkdev
     * @param applyKey 数字信封
     * @param keyId    密钥标识
     * @return
     */
    public static byte[] genKeyCard(CPKVKey cpkdev, byte[] applyKey, String keyId) {
        byte[] skey = null;
        byte[] genKeyCard = null;
        try {
            skey = cpkdev.openEnvelope(Identification.REAL_NAME, applyKey, true);
            LOG.info("======================打开数字信封，拿到回话sessionkey===================");
            genKeyCard = cpkdev.genKeyCard(keyId, 3, skey);
            LOG.info("================私钥生成成功=================");
        } catch (CPKException e) {
            LOG.info("================私钥生成失败=================");
            LOG.info(e.getMessage() + ":" + e.getCode());
        }
        return genKeyCard;
    }

    /**
     * 根据公钥矩阵和私钥计算 公钥
     *
     * @param pubKm
     * @param matrixIds
     * @return
     */
    public static byte[] calPubKey(String pubKm, String matrixIds) {
        byte[] pubKey = CPKSoft.CalPubKey(Tool.String2Bytes(pubKm), Tool.String2Bytes(matrixIds));
        return pubKey;
    }

    /**
     * 数据签名验证
     *
     * @param signVal 签名数据
     * @param message 被签名数据
     * @param pubKm   验证公钥矩阵
     * @return
     */
    public static boolean singVerify(byte[] signVal, String message, String pubKm) {
        try {
            api.verify(pubKm, Tool.String2Bytes(message.toString()), signVal);
            LOG.info("=========验证签名成功==========");
            return Boolean.TRUE;
        } catch (CPKException e) {
            LOG.info("=========验证签名失败==========");
            return Boolean.FALSE;
        }
    }


}
