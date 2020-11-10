package com.example.springdemo.vo;

import lombok.Data;

/**
 * 认证过程返回结果
 */
@Data
public class OtpVo {

    /**
     * 认证结果 : 认证结果
     */
    private Byte rc;

    /**
     * 二级证书合并的证书链数据(二级证书、根证书)
     * 测试版：采用两级证书链，
     * 正式版：后续提供具体形式
     */
    private String certChain;

    /**
     * 客户端加密证书数据
     */
    private String clientEncCert;

    /**
     * 客户端加密证书私钥密文数据（由签名公钥加密）
     * base64编码（
     * 测试版：对整个私钥结构体pkcs8格式进行加密，
     * 正式版：采用数字信封格式，具体格式后续提供）
     */
    private String clientEncKey;

    /**
     * 客户端签名证书数据
     */
    private String clentSignCert;
}
