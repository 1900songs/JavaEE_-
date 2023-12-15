package com.hospital.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.hospital.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages="com.hospital.controller")
public class GlobalExceptionHandler {

    private static final Log log = LogFactory.get();
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, Exception e){
        log.error("异常信息：",e);
        return Result.error("500",e.getMessage());
    }
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customError(HttpServletRequest request, CustomException e){
        return Result.error(e.getCode(), e.getMsg());
    }
}
