package com.oauth2.authoried.config;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

@Component
public class RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request) {
        System.out.println("------------hasPermission--------------------");
        // TODO: 2019/9/1 判断是否登录, 如果登录放行;

        return true;

    }

}