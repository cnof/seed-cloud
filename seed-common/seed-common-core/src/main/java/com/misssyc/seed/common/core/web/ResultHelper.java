package com.misssyc.seed.common.core.web;

import com.misssyc.seed.common.core.constants.Constants;

import static com.misssyc.seed.common.core.web.Result.buildResult;

/**
 * @author 33992
 * @since 2024/2/21
 **/
public class ResultHelper {

    public static <T> Result<T> ok() {
        return buildResult(null, Constants.SUCCESS, null);
    }

    public static <T> Result<T> ok(String msg) {
        return buildResult(null, Constants.SUCCESS, msg);
    }

    public static <T> Result<T> ok(T data) {
        return buildResult(data, Constants.SUCCESS, null);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return buildResult(data, Constants.SUCCESS, msg);
    }

    public static <T> Result<T> fail() {
        return buildResult(null, Constants.FAIL, null);
    }

    public static <T> Result<T> fail(String msg) {
        return buildResult(null, Constants.FAIL, msg);
    }

    public static <T> Result<T> fail(T data) {
        return buildResult(data, Constants.FAIL, null);
    }

    public static <T> Result<T> fail(T data, String msg) {
        return buildResult(data, Constants.FAIL, msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return buildResult(null, code, msg);
    }

}
