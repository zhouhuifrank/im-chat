package com.frankzhou.imchat.common.exception;

import com.frankzhou.imchat.common.base.ApiResult;
import com.frankzhou.imchat.common.base.ResultCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 全局异常处理
 * @date 2023-04-21
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChatBusinessException.class)
    public ApiResult<?> businessExceptionHandler(ChatBusinessException e) {
        // 业务异常写入warn日志
        log.warn("chat business exception: {}",e.getMessage());
        return ApiResult.getResult(new ResultCodeDTO(e.getCode(),e.getInfo(),e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResult<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("uncaught runtime exception:{}",e.getMessage());
        return ApiResult.getResult(new ResultCodeDTO(9999,"system exception",e.getMessage()));
    }
}
