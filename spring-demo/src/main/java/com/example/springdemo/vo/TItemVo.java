package com.example.springdemo.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TItemVo {

    /**
     * item length
     */
    private int itemLength;

    /**
     * contents type
     * 1: int
     * 2: byte
     */
    private int itemType;

    /**
     * contents
     */
    private byte[] contents;

}
