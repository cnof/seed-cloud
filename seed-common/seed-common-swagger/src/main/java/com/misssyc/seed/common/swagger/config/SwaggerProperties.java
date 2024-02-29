package com.misssyc.seed.common.swagger.config;

import com.misssyc.seed.common.core.constants.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李生平
 * @since 2024/2/21
 **/
@Configuration
@ConfigurationProperties(prefix = "swagger")
@Data
@RefreshScope
public class SwaggerProperties {

    /**
     * 是否开启swagger
     */
    private Boolean enabled = true;

    private String title = "种子框架";

    private String description = "接口文档";

    private String termsOfServiceUrl = "";

    private String license = "";

    private String licenseUrl = "";

    private String version = "版本号: " + Constants.VERSION;

    //是否需要头部验证 默认：true(需要)
    private Boolean needAuth = true;
}
