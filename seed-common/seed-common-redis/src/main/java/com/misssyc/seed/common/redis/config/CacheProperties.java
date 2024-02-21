package com.misssyc.seed.common.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author 33992
 * @since 2024/2/21
 **/
@Configuration
@ConfigurationProperties(prefix = "cache.temp")
@RefreshScope
@Data
public class CacheProperties {

    private long time = 7;
}
