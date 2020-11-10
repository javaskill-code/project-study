package com.bochtec.mbts.vo;

import lombok.Getter;

@Getter
public enum StatusCodeEnum {

    SUCCESS(200, "SUCCESS"), ERROR(500, "Business Exception"), INVALID(-1, "Illegal Argument"), NODATA(-1, "无记录");

    private int code;

    private String message;

    StatusCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
