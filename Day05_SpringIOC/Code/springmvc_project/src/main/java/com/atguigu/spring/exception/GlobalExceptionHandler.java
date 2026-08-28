package com.atguigu.spring.exception;

import com.atguigu.spring.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handlerGlobalException(Exception e) {
        e.printStackTrace();
        return Result.fail(500, "服务器繁忙，请稍后再试");
    }
}