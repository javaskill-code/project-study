package com.example.springdemo.utils;

import com.bc.tbox.codec.ByteDecoder;
import com.bc.vo.TContentVo;
import com.bc.vo.TItemVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TBoxUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ByteDecoder.class);

    /**
     * recieved data convert to TBoxReqVo
     *
     * @return
     */
    public static TContentVo dataToTBoxReqVo(byte[] data) {
        LOG.info("============dataToTBoxReqVo==============\t\t" + data.length);

        TContentVo tContentVo = new TContentVo();
        List<TItemVo> itemVoList = new ArrayList<>();

        // get interface type
        byte[] iTypeBytes = ByteUtils.subBytes(data, 0, 4);
        int iType = ByteUtils.binToInt(iTypeBytes);
        byte[] bytes = ByteUtil.toLH(iType);
        iType = ByteUtil.bytesToInt(bytes);
        iType = iType & 0xff;
        LOG.info("iType:" + iType);
        tContentVo.setIType(iType);
        LOG.info("调用接口类型\t\tiType:" + iType);
        tContentVo.setIType(iType);

        // get items content
        byte[] itemsBytes = ByteUtils.subBytes(data, 4, data.length - 4);
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
            int itemLength = ByteUtils.binToInt(itemLengthBytes);
            itemLengthBytes = ByteUtil.toLH(itemLength);
            itemLength = ByteUtil.bytesToInt(itemLengthBytes);
            itemLength = itemLength & 0xff;
            tItemVo.setItemLength(itemLength);
            itemPos += 4;

            // get item param type: 1:int 2:byte
            byte[] itemTypeBytes = ByteUtils.subBytes(itemsBytes, itemPos, 4);
//            int itemType = ByteUtils.binToInt(itemTypeBytes);
            int itemType = ByteUtils.binToInt(itemTypeBytes);
            itemTypeBytes = ByteUtil.toLH(itemType);

            itemType = ByteUtil.bytesToInt(itemTypeBytes);
            itemType = itemType & 0xff;
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
        LOG.info("=============最后解析的数据:\t\t" + tContentVo.toString());
        return tContentVo;
    }



}
