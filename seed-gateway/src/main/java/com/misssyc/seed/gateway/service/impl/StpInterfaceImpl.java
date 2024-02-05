package com.misssyc.seed.gateway.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 33992
 * @since 2024/2/5
 **/
@Service
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object o, String s) {
        List<String> permissions = new ArrayList<>();
        permissions.add("hello");
        return permissions;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        return roles;
    }
}
