package com.misssyc.seed.generator.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Types;
import java.util.Collections;

/**
 * @author 33992
 * @since 2024/2/27
 **/
@Api(tags = "后台管理-代码生成")
@RestController
@RequestMapping
public class GeneratorController {

    @Value("${spring.datasource.druid.url}")
    private String url;

    @Value("${spring.datasource.druid.username}")
    private String username;

    @Value("${spring.datasource.druid.password}")
    private String password;

    @SaCheckLogin
    @PostMapping
    public void generate() {
        FastAutoGenerator
                .create(url, username, password)
                .globalConfig(builder -> builder
                        .author("")
                        .enableSwagger()
                        .outputDir("E:\\public\\public_project\\files\\generator")
                )
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                .packageConfig(builder -> builder
                        .parent("com.baomidou.mybatisplus.samples.generator")
                        .moduleName("system")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\public\\public_project\\files\\generator"))
                )
                .strategyConfig(builder -> builder
                        .addInclude("auth_user")
                        .addTablePrefix("auth_")
                )
                .templateEngine(new VelocityTemplateEngine())

                .execute();
        ;
    }
}
