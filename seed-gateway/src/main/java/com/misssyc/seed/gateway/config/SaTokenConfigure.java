package com.misssyc.seed.gateway.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

/**
 * @author 33992
 * @since 2024/2/5
 **/
@Configuration
public class SaTokenConfigure {

    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 指定 [拦截路由]
                .addInclude("/**") /* 拦截所有path */
                // 指定 [放行路由]
                .addExclude("/favicon.ico")
                // 指定[认证函数]: 每次请求执行
                .setAuth(obj -> {
                    // 放行登录请求
                    SaRouter.notMatch("/api-admin/login");
                    // 放行 swagger 相关请求
                    SaRouter.notMatch("/swagger-ui/**");
                    SaRouter.notMatch("/swagger-resources/**");
                    SaRouter.notMatch("/v2/api-docs");
                    SaRouter.notMatch("/api-admin/v2/api-docs");

                    SaRouter.match("/api-admin/users").matchMethod(HttpMethod.GET.name()).check(r -> StpUtil.checkPermission("admin:user:query"));
                });
    }
}
