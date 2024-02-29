package com.misssyc.seed.common.oauth.advice;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.misssyc.seed.common.core.utils.ExceptionUtils;
import com.misssyc.seed.common.core.web.Result;
import com.misssyc.seed.common.core.web.ResultHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author 李生平
 * @since 2024/2/23
 **/
@Slf4j
@RestControllerAdvice
public class OauthExceptionHandlerAdvice {

    @ExceptionHandler(NotLoginException.class)
    public Result<Void> handleNotLoginException(NotLoginException e) {
        String message = Objects.requireNonNull(e.getMessage());
        log.error("NotLoginException {} {}", message, ExceptionUtils.getExceptionDetail(e));
        return ResultHelper.fail(401, "未登录");
    }

    @ExceptionHandler(NotPermissionException.class)
    public Result<Void> handleNotLoginException(NotPermissionException e) {
        String message = Objects.requireNonNull(e.getMessage());
        log.error("NotPermissionException {} {}", message, ExceptionUtils.getExceptionDetail(e));
        return ResultHelper.fail(403, "未授权");
    }
}
