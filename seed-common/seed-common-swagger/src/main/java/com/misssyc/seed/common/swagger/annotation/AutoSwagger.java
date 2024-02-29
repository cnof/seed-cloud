package com.misssyc.seed.common.swagger.annotation;

import com.misssyc.seed.common.swagger.config.SwaggerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * swagger自动配置注解
 * @author 李生平
 * @since 2024/2/21
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SwaggerConfig.class)
public @interface AutoSwagger {
}
