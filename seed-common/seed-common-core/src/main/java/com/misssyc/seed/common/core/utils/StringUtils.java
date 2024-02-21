package com.misssyc.seed.common.core.utils;

import cn.hutool.core.text.CharSequenceUtil;

/**
 * 字符串工具类
 * @author 33992
 * @since 2024/2/21
 **/
public class StringUtils {

    public static boolean isEmpty(final CharSequence cs) {
        return CharSequenceUtil.isEmpty(cs);
    }
}
