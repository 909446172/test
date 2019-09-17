package com.damo.openapi.demoopenapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OpenApiException {

    @ExceptionHandler(ArithmeticException.class)
   @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public String exception (Exception e) {
        System.out.println(e.getClass());
        System.out.println("-------------------------------");
        return "这是发生500aaaaaaaaaaaa错误返回的字符串";
    }
//
//
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,reason = "程序发生错误")
//    public String exception1 (RuntimeException e) {
//        System.out.println(e.getClass());
//        System.out.println("-------------------------------");
//        return "这是发生RuntimeException错误返回的字符串";
//    }



}
