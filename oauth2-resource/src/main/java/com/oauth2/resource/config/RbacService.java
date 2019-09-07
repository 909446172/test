package com.oauth2.resource.config;

import com.oauth2.resource.service.serviceImpl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    TokenServiceImpl tokenService ;
    /**
     * 验证是否通过;
     * @param request
     * @return
     */
    public boolean hasPermission(HttpServletRequest request) {

//        if (true) {
//            return true;
//        }

        System.out.println("------------hasPermission--------------------");
        TokenServiceImpl.httpServletRequest = request;
        if (ObjectUtils.isEmpty(tokenService)) {
            return false;
        }
        return tokenService.existToken();
    }



}