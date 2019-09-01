package com.oauth2.authoried.service.serviceImpl;

import com.oauth2.authoried.service.TokenService;
import com.oauth2.common.util.JwtOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    JwtOperator jwtOperator;

    @Override
    public String getToken(Map<String, Object> map) {
        if(ObjectUtils.isEmpty(jwtOperator)){
            return null;
        }
        String s = jwtOperator.generateToken(map);
        if(StringUtils.isEmpty(s)){
            return null;
        }
        return s;
    }


}
