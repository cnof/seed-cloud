package com.misssyc.seed.common.core.exception;

/**
 * @author 李生平
 * @since 2024/2/21
 **/
public class SeedRuntimeException extends RuntimeException {

    public SeedRuntimeException(String msg) {
        super(msg);
    }

    public SeedRuntimeException(Throwable cause) {
        super(cause);
    }

    public SeedRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
