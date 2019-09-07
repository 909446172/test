package com.oauth2.resource.service.serviceImpl;

import com.oauth2.common.api.AuthorizationApi;
import com.oauth2.common.entity.Info;
import com.oauth2.common.entity.Result;
import com.oauth2.common.entity.User;
import com.oauth2.resource.service.TokenService;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    public static HttpServletRequest httpServletRequest;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthorizationApi authorizationApi;

    /**
     * 判断是否存在token;
     * @return
     */
    @Override
    public Boolean existToken() {
        String token = null;
        // TODO: 2019/9/1  判断是否存在token 如果存在就放行;


        if (! StringUtils.isEmpty(token)) {
            return true;
        }
            if (ObjectUtils.isEmpty(httpServletRequest) || ObjectUtils.isEmpty(authorizationApi) ) {

                return false;
            }
            //获取token
//            String id = httpServletRequest.getParameter("id");
//          System.out.println(id);
//            String username = httpServletRequest.getParameter("username");

        Integer id = 1;
        String username = "a";

        // TODO: 2019/9/2 这里判断登录如果登录就使用登录的信息在授权服务器获取token
        

            User user = new User(Integer.valueOf(id), username);


            Result result = authorizationApi.getToken(user);
          if (ObjectUtils.isEmpty(result)) {
              return false;
           }
           System.out.println(id);
            if(result.getSm().getSuccess()){
                System.out.println(result.getInfo());
                Object info1 = result.getInfo();
                Info info = new Info();
                try {
                    BeanUtils.copyProperties(info,info1);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                System.out.println(info);

                log.info("--------------token--------------");
                log.info(info.getToken());
                return true;
            }

            return false;
    }


}
