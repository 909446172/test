package com.oauth2.authoried.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication auth) {
        System.out.println("------------hasPermission--------------------");
        String token = null;
        // TODO: 2019/9/1  判断是否存在token 如果存在就放行;
        if(token==null|| StringUtils.isEmpty(token)){
            return false;
        }
          return true;
        }
}