package com.syz.servicebase.exceptionhandler;

import com.syz.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message(e.getMessage());
    }

    @ExceptionHandler(CustomizeException.class)
    @ResponseBody //为了返回数据
    public R error(CustomizeException e) {
        e.printStackTrace();
        log.error(e.getMsg());
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
