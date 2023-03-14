package com.mk.utils;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author:ChenChang
 * @Description: auth token
 * @Data: Create in 14:24 2023/3/2
 * @Modified By:
 */
public class AuthToken implements AuthenticationToken {

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
