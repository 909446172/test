package com.oauth2.resource.service.serviceImpl;

import com.oauth2.resource.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    private HttpServletRequest httpServletRequest;
    private Authentication authentication;

//    @Autowired
//    AuthorizationApi authorizationApi;


    public TokenServiceImpl(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        this.authentication = authentication;

    }

    /**
     * 判断是否存在token;
     * @return
     */
    public Boolean existToken() {
//        String token = "token";
//        // TODO: 2019/9/1  判断是否存在token 如果存在就放行;
//        if (token == null || StringUtils.isEmpty(token)) {
//            if (ObjectUtils.isEmpty(httpServletRequest)) {
//                return false;
//            }
//            String id = httpServletRequest.getParameter("id");
//            String username = httpServletRequest.getParameter("username");
//            User user = new User(Integer.valueOf(id), username);
//            Result result = authorizationApi.getToken(user);
//            if(!result.getSm().getSuccess()){
//                return false;
//            }
//            Info info = (Info) result.getInfo();
//            log.info("--------------token--------------");
//            log.info(info.getToken());
//            return true;
//        }
        return true;
    }


}
