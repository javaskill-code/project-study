package com.example.springdemo.vo;

import lombok.Data;

@Data
public class RequestVo {

    /**
     * 请求类型
     */
    private int rtype;

    /**
     * T-Box 标识 明文
     */
    private String tid;

    /**
     * T-Box 标识 密文
     */
    private String tids;

    /**
     * 随机数 密文
     */
    private String randoms;

    /**
     * 签名数据 密文
     */
    private String signDatas;

    /**
     * 业务数据 密文
     */
    private String datas;
}
