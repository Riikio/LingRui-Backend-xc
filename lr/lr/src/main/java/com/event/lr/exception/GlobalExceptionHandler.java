package com.event.lr.exception;

import com.event.lr.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error(400,StringUtils.hasLength(e.getMessage())? e.getMessage() : "操作失败",null);
    }
}//全局异常拦截器

