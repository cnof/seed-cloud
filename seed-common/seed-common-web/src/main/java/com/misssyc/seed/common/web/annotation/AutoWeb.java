package com.misssyc.seed.common.web.annotation;

import com.misssyc.seed.common.core.annotation.AutoFeignClients;
import com.misssyc.seed.common.swagger.annotation.AutoSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 李生平
 * @since 2024/2/21
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootApplication
@AutoSwagger
@MapperScan({"com.misssyc.seed.**.dao"})
@AutoFeignClients
public @interface AutoWeb {
}
