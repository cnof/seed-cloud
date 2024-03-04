package com.misssyc.seed.common.core.utils;

import cn.hutool.core.text.CharSequenceUtil;

/**
 * 字符串工具类
 * @author 李生平
 * @since 2024/2/21
 **/
public class StringUtils extends CharSequenceUtil {

    public static boolean isFalse(final CharSequence cs) {
        return cs == null || !equals("1", cs);
    }
}
