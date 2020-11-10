package com.bochtec.mbts.config.exception;


import com.bochtec.mbts.vo.BaseResponse;
import com.bochtec.mbts.vo.StatusCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {


    /**
     * 验证参数异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String errorStr = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("/"));
        log.error(e.getMessage(), e);
        return new BaseResponse(StatusCodeEnum.ERROR.getCode(), errorStr);
    }


    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public BaseResponse handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return new BaseResponse(StatusCodeEnum.ERROR.getCode(), "不支持' " + e.getMethod() + "'请求");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public BaseResponse DuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return new BaseResponse(StatusCodeEnum.ERROR.getCode(), "数据库主键唯一制约异常");
    }

    @ExceptionHandler(DataAccessException.class)
    public BaseResponse DataAccessException(DataAccessException e) {
        log.error(e.getMessage(), e);
        return new BaseResponse(StatusCodeEnum.ERROR.getCode(), "SQL异常:\t" + e.getMessage());
    }


    /**
     * 空指针
     */
    @ExceptionHandler(NullPointerException.class)
    public BaseResponse nullPointException(NullPointerException e) {
        log.error(e.getMessage(), e);
        return new BaseResponse(StatusCodeEnum.ERROR.getCode(), "空指针");
    }


    /**
     * 验证参数异常
     */
    @ExceptionHandler(BindException.class)
    public BaseResponse handleBindException(BindException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        String errorStr = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("/"));
        log.error(e.getMessage(), e);
        return new BaseResponse(StatusCodeEnum.ERROR.getCode(), errorStr);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return new BaseResponse(StatusCodeEnum.ERROR.getCode(), e.getMessage());
    }
}
