package com.misssyc.seed.common.oauth.service;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author 33992
 * @since 2023/7/11
 **/
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return Collections.singletonList("*:*:*");
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }
}
