package com.oauth2.resource.controller;

import com.oauth2.common.api.AuthorizationApi;
import com.oauth2.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    AuthorizationApi authorizationApi;


    @RequestMapping("test")
    public String test(){

        return "dd"+authorizationApi.getToken(new User(1,"2"));
    }


}
