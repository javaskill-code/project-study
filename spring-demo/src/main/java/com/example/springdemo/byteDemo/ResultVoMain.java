package com.example.springdemo.byteDemo;

import com.example.springdemo.utils.ByteUtil;
import com.example.springdemo.vo.ResultVo;

public class ResultVoMain {
    public static void main(String[] args) {
        byte[] intToByteArray = ByteUtil.intToByteArray(4 + 6);
        System.err.println(intToByteArray.length);
        byte[] typePrikey = ByteUtil.intToByteArray(2);
        System.err.println(typePrikey.length);
        ResultVo resultVo = new ResultVo(1, intToByteArray);
        resultVo.appendBytes(typePrikey);

        byte[] totalLenByte = ByteUtil.intToByteArray(resultVo.getTotalLen());

        byte[] apiTypeByte = ByteUtil.intToByteArray(resultVo.getApiType());
        byte[] append = ByteUtil.append(totalLenByte, apiTypeByte);
        byte[] endResult = ByteUtil.append(append, resultVo.getBytes());
        System.err.println(endResult.length);

    }
}
