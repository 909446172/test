package com.oauth2.resource.service.serviceImpl;

import com.oauth2.common.api.AuthorizationApi;
import com.oauth2.common.entity.Info;
import com.oauth2.common.entity.Result;
import com.oauth2.common.entity.User;
import com.oauth2.resource.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    private HttpServletRequest httpServletRequest;


    @Autowired
    AuthorizationApi authorizationApi;


    public TokenServiceImpl(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;


    }

    /**
     * 判断是否存在token;
     * @return
     */
    public Boolean existToken() {
        String token = null;
        // TODO: 2019/9/1  判断是否存在token 如果存在就放行;
        if (! StringUtils.isEmpty(token)) {
            return true;
        }
            if (ObjectUtils.isEmpty(httpServletRequest)) {
                return false;
            }
            String id = httpServletRequest.getParameter("id");
            String username = httpServletRequest.getParameter("username");
            User user = new User(Integer.valueOf(id), username);
            Result result = authorizationApi.getToken(user);
            if(result.getSm().getSuccess()){
                Info info = (Info) result.getInfo();
                log.info("--------------token--------------");
                log.info(info.getToken());
                return true;
            }

            return false;
    }


}
