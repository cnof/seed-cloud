package com.misssyc.seed.common.core.advice;

import com.misssyc.seed.common.core.exception.SeedRuntimeException;
import com.misssyc.seed.common.core.web.Result;
import com.misssyc.seed.common.core.web.ResultHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 33992
 * @since 2024/2/21
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(SeedRuntimeException.class)
    public Result<Integer> myHandleException(Exception exception, HttpServletRequest request) {
        log.error("请求地址'{}', 处理异常.", request.getRequestURI(), exception);
        return ResultHelper.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Integer> handleException(Exception exception, HttpServletRequest request) {
        log.error("请求地址'{}', 发生系统异常.", request.getRequestURI(), exception);
        return ResultHelper.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "错误:未知异常");
    }
}
