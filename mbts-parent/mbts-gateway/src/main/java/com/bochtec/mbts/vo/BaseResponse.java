package com.bochtec.mbts.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseResponse<T> {
    @ApiModelProperty("状态码")
    private int code;
    @ApiModelProperty("信息")
    private String message;
    @ApiModelProperty("数据")
    private T data;

    private Long total;

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getMessage();
        this.data = null;
    }

    public BaseResponse(StatusCodeEnum statusCodeEnum, T data) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getMessage();
        this.data = data;
    }
}
