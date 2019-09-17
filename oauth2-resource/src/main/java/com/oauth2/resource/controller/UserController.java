package com.oauth2.resource.controller;

import com.netflix.discovery.converters.Auto;
import com.oauth2.common.api.AuthorizationApi;
import com.oauth2.common.entity.User;
import com.oauth2.resource.service.TokenService;
import com.oauth2.resource.service.serviceImpl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    AuthorizationApi authorizationApi;
    @Autowired
    TokenServiceImpl tokenServiceTmpl;

    @RequestMapping("test")
    public String test(){

        Boolean aBoolean = tokenServiceTmpl.existToken();
        System.out.println(aBoolean);
        return "dd"+authorizationApi.getToken(new User(1,"2"));
    }


}
