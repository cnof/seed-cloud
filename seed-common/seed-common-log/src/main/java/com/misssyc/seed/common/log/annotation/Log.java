package com.misssyc.seed.common.log.annotation;

import com.misssyc.seed.common.core.enums.OperateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 33992
 * @since 2024/2/21
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 模块
     */
    String module() default "";

    /**
     * 标题
     */
    String title() default "";

    /**
     * 操作类型
     */
    OperateType operateType() default OperateType.OTHER;
}
