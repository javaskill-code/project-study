package com.example.springdemo.vo;

import lombok.Data;

@Data
public class TBoxReqVo {

    /**
     * date length
     */
    private int length;

    /**
     * data contents
     */
    private TContentVo data;

}
