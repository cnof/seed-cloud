package com.misssyc.seed.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 代码生成相关配置
 * 
 * @author ruoyi
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "generator")
public class GeneratorProperties
{
    /** 作者 */
    public String author;

    /** 生成包路径 */
    public String packageName;

    /** 自动去除表前缀，默认是false */
    public boolean autoRemovePre;

    /** 表前缀(类名不会包含表前缀) */
    public String tablePrefix;
}
