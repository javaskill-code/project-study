package com.example.springdemo.byteDemo;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.springdemo.utils.ByteUtil;
import com.example.springdemo.vo.ResultVo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class ByteUtilsDemo {
    public static void main(String[] args) throws IOException {
        byte[] tboxPrikeyBytes = new byte[60];
        byte[] intToByteArray = ByteUtil.intToByteArray(4 + 6);
//        System.arraycopy(intToByteArray, 0, tboxPrikeyBytes, 40, intToByteArray.length);
//        byte[] typePrikey = ByteUtil.intToByteArray(2);
//        System.arraycopy(typePrikey, 0, tboxPrikeyBytes, intToByteArray.length+40, typePrikey.length);
//        byte[] bytes = ByteUtil.intToByteArray(2);
//        System.arraycopy(bytes, 0, tboxPrikeyBytes, intToByteArray.length+typePrikey.length+40, bytes.length);

        System.err.println(intToByteArray.length);
        byte[] typePrikey = ByteUtil.intToByteArray(2);
        System.err.println(typePrikey.length);
        ResultVo resultVo = new ResultVo(1, intToByteArray);
        resultVo.appendBytes(typePrikey);

        Object o = JSONArray.toJSON(resultVo);
        String s1 = o.toString();

        System.err.println("s1\t" + s1);
        String s = JSON.toJSONString(resultVo);
        System.err.println(s);
        byte[] bytes = JSON.toJSONString(resultVo).getBytes();
        System.err.println("bytes\t" + bytes.length);
        byte[] objectToBytes = objectToBytes(resultVo);
        System.err.println("objectToBytes\t\t" + objectToBytes.length);

//        Gson gson = new Gson();
//        String s2 = gson.toJson(resultVo);

        ByteBuffer directBuffer = ByteBuffer.allocateDirect(resultVo.getTotalLen() + 4);
        byte[] totalLenByte = ByteUtil.intToByteArray(resultVo.getTotalLen());
        directBuffer.put(totalLenByte);
        byte[] apiTypeByte = ByteUtil.intToByteArray(resultVo.getApiType());
        directBuffer.put(apiTypeByte);
        directBuffer.put(resultVo.getBytes());
        System.err.println(directBuffer);

        byte b = directBuffer.get();




    }

    /**
     * 对象转字节数组
     */
    public static byte[] objectToBytes(Object obj) throws IOException {
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream sOut = new ObjectOutputStream(out);
        ) {
            sOut.writeObject(obj);
            sOut.flush();
            byte[] bytes = out.toByteArray();
            return bytes;
        }
    }
}
