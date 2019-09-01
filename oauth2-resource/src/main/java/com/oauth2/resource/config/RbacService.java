package com.oauth2.resource.config;

import com.oauth2.resource.service.serviceImpl.TokenServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 验证是否通过;
     * @param request
     * @return
     */
    public boolean hasPermission(HttpServletRequest request) {

        System.out.println("------------hasPermission--------------------");
        TokenServiceImpl tokenService = new TokenServiceImpl(request);
        if (ObjectUtils.isEmpty(tokenService)) {
            return false;
        }
        return tokenService.existToken();
    }



}