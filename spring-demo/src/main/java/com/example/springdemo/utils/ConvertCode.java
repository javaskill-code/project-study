package com.example.springdemo.utils;

import com.bc.vo.TContentVo;
import com.bc.vo.TItemVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ConvertCode {
    private static final Logger LOG = LoggerFactory.getLogger(ConvertCode.class);

    public static TContentVo bytes2Object(byte[] b) {
        TContentVo tContentVo = new TContentVo();
        List<TItemVo> itemVoList = new ArrayList<>();
        byte[] bytes = ByteUtils.subBytes(b, 0, 4);
        int dataLength = bytes2Int(bytes);
        byte[] firstByte = ByteUtil.toLH(dataLength);
        dataLength = bytes2Int(firstByte);
//        dataLength = dataLength & 0xff;
        byte[] subBytes = ByteUtils.subBytes(b, 4, dataLength);

        byte[] iTypeBytes = ByteUtils.subBytes(subBytes, 0, 4);
        int iType = bytes2Int(iTypeBytes);
        byte[] iTypebytes = ByteUtil.toLH(iType);
        iType = bytes2Int(iTypebytes);
//        iType = iType & 0xff;
        LOG.info("iType:" + iType);
        tContentVo.setIType(iType);
        // get items content
        byte[] itemsBytes = ByteUtils.subBytes(subBytes, 4, subBytes.length - 4);
        int itemPos = 0;
        while (true) {
            TItemVo tItemVo = new TItemVo();
            // all items
            if (itemPos >= itemsBytes.length) {
                break;
            }
            // get one item
            // get one item length
            byte[] itemLengthBytes = ByteUtils.subBytes(itemsBytes, itemPos, 4);
            int itemLength = bytes2Int(itemLengthBytes);
            itemLengthBytes = ByteUtil.toLH(itemLength);
            itemLength = bytes2Int(itemLengthBytes);
//            itemLength = itemLength & 0xff;
            tItemVo.setItemLength(itemLength);
            itemPos += 4;

            // get item param type: 1:int 2:byte
            byte[] itemTypeBytes = ByteUtils.subBytes(itemsBytes, itemPos, 4);
            int itemType = bytes2Int(itemTypeBytes);
//            int itemType = ByteUtils.binToInt(itemTypeBytes);
            itemTypeBytes = ByteUtil.toLH(itemType);

            itemType = bytes2Int(itemTypeBytes);
//            itemType = itemType & 0xff;
            tItemVo.setItemType(itemType);
            itemPos += 4;

            // get item contents
            byte[] itemContentsBytes = ByteUtils.subBytes(itemsBytes, itemPos, itemLength - 4);
            tItemVo.setContents(itemContentsBytes);
            LOG.info("itemContents:\t" + new String(itemContentsBytes));

            itemVoList.add(tItemVo);
            itemPos += itemContentsBytes.length;

        }
        tContentVo.setItems(itemVoList);
        return tContentVo;
    }

    public static int bytes2Int(byte[] bytes) {
        int result = 0;
        //将每个byte依次搬运到int相应的位置
        result = bytes[0] & 0xff;
        result = result << 8 | bytes[1] & 0xff;
        result = result << 8 | bytes[2] & 0xff;
        result = result << 8 | bytes[3] & 0xff;
        return result;
    }

    public static byte[] int2Bytes(int num) {
        byte[] bytes = new byte[4];
        //通过移位运算，截取低8位的方式，将int保存到byte数组
        bytes[0] = (byte) (num >>> 24);
        bytes[1] = (byte) (num >>> 16);
        bytes[2] = (byte) (num >>> 8);
        bytes[3] = (byte) num;
        return bytes;
    }
}
