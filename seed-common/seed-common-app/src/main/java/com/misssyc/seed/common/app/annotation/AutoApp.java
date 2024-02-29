package com.misssyc.seed.common.app.annotation;

import com.misssyc.seed.common.core.annotation.AutoFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 李生平
 * @since 2024/2/22
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@AutoFeignClients
public @interface AutoApp {
}
