package com.misssyc.seed.common.core.utils;

import com.misssyc.seed.common.core.exception.SeedRuntimeException;

public class SeedAssert {

    /**
     * 断言
     *
     * @param res 表达式
     * @param msg 错误信息
     */
    public static void isTure(boolean res, String msg) {
        if (!res) {
            throw new SeedRuntimeException(msg);
        }
    }

    /**
     * 断言
     *
     * @param res 表达式
     * @param msg 错误信息
     */
    public static void isTure(boolean res, String msg, Object... obj) {
        if (!res) {
            throw new SeedRuntimeException(String.format(msg, obj));
        }
    }
}
