package com.example.springdemo.vo;

import lombok.Data;

/**
 * 认证请求返回结果
 */
@Data
public class OtpcVo {

    /**
     * 认证结果 : 认证结果
     */
    private Byte rc;

    /**
     * 随机数 16字节
     */
    private Byte[] random;

    /**
     * 时间戳 8字节
     */
    private Byte[] timestamp;
}
