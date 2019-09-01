package com.oauth2.authoried.config;

import org.springframework.security.core.GrantedAuthority;

public class MyGranted implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return null;
    }
}
