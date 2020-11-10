package com.example.springdemo.vo;

import com.bc.utils.ByteUtil;
import lombok.ToString;

@ToString
public class ResultVo {
    /**
     * 总长度
     */
    private int totalLen;
    /**
     * 接口类型
     */
    private int apiType;
    /**
     * 返回的字节数组
     */
    private byte[] bytes;


    public ResultVo(int apiType, byte[] bytes) {
        this.apiType = apiType;
        this.bytes = bytes;
        byte[] intBytes = ByteUtil.intToByteArray(this.apiType);
        this.setTotalLen(intBytes.length + bytes.length);
    }


    public void appendBytes(byte[] bytes) {
        if (bytes == null) {
            return;
        }
        this.totalLen += bytes.length;
        // 合并数组
        byte[] append = ByteUtil.append(this.bytes, bytes);
        this.bytes = append;
    }

    /**
     * 获取最终的字节数组
     *
     * @return
     */
    public byte[] getFinalResult() {
        /**
         *  为了解决C语言那边大小端问题，特意 让 将int转为大端，低字节存储高位
         */
        byte[] totalLenByte = ByteUtil.intToByteArray(this.getTotalLen());
        totalLenByte = ByteUtil.reverse(totalLenByte);
        byte[] apiTypeByte = ByteUtil.intToByteArray(this.getApiType());
        apiTypeByte = ByteUtil.reverse(apiTypeByte);
        byte[] append = ByteUtil.append(totalLenByte, apiTypeByte);
        return ByteUtil.append(append, this.getBytes());
    }

    public int getTotalLen() {
        return totalLen;
    }

    public void setTotalLen(int totalLen) {
        this.totalLen = totalLen;
    }

    public int getApiType() {
        return apiType;
    }

    public void setApiType(int apiType) {
        this.apiType = apiType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
