package com.misssyc.seed.admin.po;

import lombok.Data;

/**
 * @author 李生平
 * @since 2024/2/22
 **/
@Data
public class UriPO {

    /**
     * 请求路径
     */
    private String uri;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 是否需要鉴权 1-是 0-否
     */
    private Integer needCheck;
}
