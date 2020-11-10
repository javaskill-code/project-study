package com.example.springdemo.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TContentVo {

    /**
     * interface type
     */
    private int iType;

    /**
     * items
     */
    private List<TItemVo> items;


}
