package com.misssyc.seed.common.core.constants;

import org.springframework.http.HttpStatus;

/**
 * @author 33992
 * @since 2024/2/21
 **/
public class Constants {

    /**
     * 逗号
     */
    public static final String COMMA = ",";

    /**
     * 下划线
     */
    public static final String UNDERLINE = "_";

    /**
     * 横线
     */
    public static final String HYPHEN = "-";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = HttpStatus.OK.value();

    /**
     * 失败标记
     */
    public static final Integer FAIL = HttpStatus.INTERNAL_SERVER_ERROR.value();

    /**
     * 验证码redisKey标签
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_code_key:";

    /**
     * 验证码有效期（分钟）
     */
    public static final long CAPTCHA_EXPIRE = 5;
}
