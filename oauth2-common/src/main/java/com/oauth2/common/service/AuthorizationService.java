package com.oauth2.common.service;

import com.oauth2.common.entity.Result;
import com.oauth2.common.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("OAUTH2-AUTHORIZED")
public interface AuthorizationService {

    @PostMapping("oauth/token")
    public Result getToken(User user);

}
