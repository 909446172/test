package com.oauth2.authoried.controller;


import com.oauth2.authoried.service.TokenService;
import com.oauth2.common.constant.SM;
import com.oauth2.common.entity.Info;
import com.oauth2.common.entity.Result;
import com.oauth2.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthorizationController {

    @Autowired
    TokenService tokenService ;

    @PostMapping("oauth/token")
    public Result getToken(User user){
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        // TODO: 2019/9/1  在设计token生成时使用的时id和用户名 , 后续需增加新的字段
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        String token = tokenService.getToken(map);
        Info info = new Info();
        info.setToken(token);
        // TODO: 2019/9/1   后续新添加字段时添加;
        Result result = new Result();
        result.setSm(SM.SUCCESS);
        result.setInfo(info);
        return result;
    }



}
