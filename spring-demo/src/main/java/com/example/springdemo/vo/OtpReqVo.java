package com.example.springdemo.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OtpReqVo {

    /**
     * 终端标识号(设备IMEI+5个0)
     */
    private String id;

    /**
     * 芯片ID
     */
    private String chipID;

    /**
     * 一次性口令(base64：认证过程接口
     */
    private String otp;

    /**
     * 签名证书公钥(XY值64位经过base64编码
     */
    private String publicKey;

    /**
     * 一次性口令(base64:认证结果接口
     */
    private String otpq;
}
