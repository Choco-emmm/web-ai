package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("程序出错啦~",e);
        return Result.error("出错啦，请联系管理员~");
    }

    /**
     * 处理重复键异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错啦~",e);
        //找关键字“Duplicate”
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        //根据空格切割
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2]+"已存在");
    }
}
